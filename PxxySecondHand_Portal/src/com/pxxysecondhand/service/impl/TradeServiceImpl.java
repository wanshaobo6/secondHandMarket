/**   
* @Title: TradeServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月5日 上午8:22:00 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pxxysecondhand.component.JedisClient;
import com.pxxysecondhand.component.SMSCodeTimer;
import com.pxxysecondhand.mapper.ItemMapper;
import com.pxxysecondhand.mapper.TradeMapper;
import com.pxxysecondhand.mapper.UserMapper;
import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.Trade;
import com.pxxysecondhand.pojo.TradeExample;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.IItemService;
import com.pxxysecondhand.service.ITradeService;
import com.pxxysecondhand.tempPojo.EvaluateModel;
import com.pxxysecondhand.tempPojo.MyCollections;
import com.pxxysecondhand.tempPojo.MyPublic;
import com.pxxysecondhand.tempPojo.MyPurchase;
import com.pxxysecondhand.tempPojo.MySelled;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.threads.CraftsMan1;
import com.pxxysecondhand.threads.CraftsMan2;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;
import com.pxxysecondhand.utils.SMSCodeGenerator;
import com.pxxysecondhand.utils.SMSCodeUtil;

/**
 * @author  
 *
 */
@Service
public class TradeServiceImpl implements ITradeService {
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private TradeMapper tradeMapper;
	
	@Autowired
	private IItemService itemService;
	
	@Autowired
	private SMSCodeTimer smsCodeTimer;
	
	@Autowired
	private JedisClient jedisClient;
	
	@Autowired
	private SMSCodeUtil smsCodeUtil;
	
	//定时执行任务单例线程
	@Autowired
	private CraftsMan1 craftsMan1;
	
	@Autowired
	private CraftsMan2 cratfsMan2;
	
	//到期自动确认收货时间
	@Value("${AUTO_COMFIRM_TIME}")
	private Integer AUTO_COMFIRM_TIME;
	
	//到期自动评价时间
	@Value("${AUTO_COMMENT_TIME}")
	private Integer AUTO_COMMENT_TIME;
	
	//Redis数据库中确认交易验证码缓存前缀名
	@Value("${SMS_REDIS_MAKEADEAL_CODE_PREFIX}")
	private String SMS_REDIS_MAKEADEAL_CODE_PREFIX;
	
	//验证码超时消失时间 单位S
	@Value("${SMS_CODE_TIMEOUT}")
	private int SMS_CODE_TIMEOUT;
	
	//确认交易验证码Timer计时器前缀
	@Value("${SMS_TIMER_MAKEADEAL_PREFIX}")
	private String SMS_TIMER_MAKEADEAL_PREFIX;
	
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#sendTradeSmsMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public CommonResult sendTradeSmsMessage(String userId,String phonenumber,String itemId) {
		// TODO Auto-generated method stub
		boolean canIGetSmsCode = smsCodeTimer.canIGetSmsCode(SMS_TIMER_MAKEADEAL_PREFIX+phonenumber+"_"+itemId);
		//1.查看用户是否满足接受验证码的要求
		if(canIGetSmsCode) {
			//2.如果满足生成验证码 发送验证码 记录发送时间 保存验证码
			String code = SMSCodeGenerator.generateSmsCode();
			try {
				String sendCondition = smsCodeUtil.sendSmsConfirmMessage(phonenumber, code);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return CommonResult.build(500, e.getMessage());
			}
			smsCodeTimer.registSendSmsCode(SMS_TIMER_MAKEADEAL_PREFIX+phonenumber+"_"+itemId);
			jedisClient.set(SMS_REDIS_MAKEADEAL_CODE_PREFIX+phonenumber+"_"+itemId, code);
			jedisClient.expire(SMS_REDIS_MAKEADEAL_CODE_PREFIX+phonenumber+"_"+itemId, SMS_CODE_TIMEOUT);
			return CommonResult.ok();
		}else {
			return new CommonResult().build(501,"两次发送间隔时间太短");
		}
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#makeADeal(com.pxxysecondhand.pojo.User, java.lang.String, java.lang.String)
	 */
	@Override
	public CommonResult makeADeal(User user, Item item, String smsCode)  {
		// TODO Auto-generated method stub
		if(validateMakeADealSMSCode(user,item.getId(),smsCode)) {
			//改变闲置的状态
			itemService.changeItemIsInTrade(item.getId(), 1);
			//补充完整交易信息 交易储存
			Trade record = new Trade();
			record.setId(CommonUtils.uuidGenerator());
			record.setBuyerid(user.getId());
			record.setBuyerisdeleted(0);
			record.setOwnerisdeleted(0);
			record.setItemownerid(item.getUserid());
			record.setTradeitemid(item.getId());
			record.setTradecreatedtime(new Date());
			record.setTradeitemtitle(item.getItemtitle());
			record.setTradeitemimage(CommonUtils.getLargeImage(item.getItemimages()));
			record.setTradecurrprice(item.getCurrprice());
			//交易状态(0正在交易,1 交易完成 , 2交易取消)7天无操作自动将正在交易表变成交易表
			record.setTradestatus(0);
			//付款类型(0当面交易)
			record.setPaymenttype(0);
			//交易类型（默认0 一口价）
			record.setTradetype(0);
			tradeMapper.insert(record);
			//到期自动确认收货线程
			craftsMan1.startTheTask(record.getId(), 1, AUTO_COMFIRM_TIME);
			return CommonResult.ok();
		}else {
			return CommonResult.build(501,"验证码错误");
		}
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#validateMakeADealSMSCode(com.pxxysecondhand.pojo.User, java.lang.String, java.lang.String)
	 */
	@Override
	public boolean validateMakeADealSMSCode(User user, String itemId, String smsCode){
		// TODO Auto-generated method stub
		String realCode = jedisClient.get(SMS_REDIS_MAKEADEAL_CODE_PREFIX+user.getPhonenumber()+"_"+itemId);
		if(realCode==null||(!realCode.equals(smsCode))) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#getAllOfMyTrade(java.lang.String, int, int)
	 */
	@Override
	public SearchResult<MyPublic> getAllOfMyTrade(HttpServletRequest request,String userId, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<MyPurchase> itemList = tradeMapper.getAllOfMyPurchase(userId);
		SearchResult searchResult = new SearchResult<MyPublic>();
		for (MyPurchase myPurchase : itemList) {
			myPurchase.setTimeLeftMessage(caculateTimeLeftMessage(myPurchase.getTradeCreatedTime(),myPurchase.getTradeComplishTime(),myPurchase.getTradeStatus()));
//			/System.out.println("我的所有订单"+myPurchase);
		}
		//查找的数据为空
		if(itemList==null||itemList.size()==0) {
			searchResult .setTotalCount(0);
			searchResult.setTotalPage(0);
			searchResult.setCurrentPage(page);
			searchResult.setItemList(null);
			return searchResult;
		}
		//对订单进行遍历 进行一些操作
		for (MyPurchase myPurchase : itemList) {
			myPurchase.setImage(CommonUtils.getLargeImage(myPurchase.getImage()));
		}
		//不为空
		PageInfo<MyPurchase> pageInfo = new PageInfo(itemList);
		int total = (int) pageInfo.getTotal();
		searchResult.setTotalCount(total);
		int totalPage = total%rows==0?total/rows:(total/rows+1);
		searchResult.setTotalPage(totalPage);
		searchResult.setCurrentPage(page);
		searchResult.setItemList(itemList);
		searchResult.setURL(CommonUtils.getURLByServletRequest(request));
		return searchResult;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#confirmMyTrade(javax.servlet.http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public CommonResult confirmMyTrade(HttpServletRequest request, User user ,String tradeId) {
		// TODO Auto-generated method stub
		  Trade trade = tradeMapper.selectByPrimaryKey(tradeId);
		//1.查看交易是否存在
		  if(trade==null) {
			  return CommonResult.build(500, "交易不存在");
		  }
		  //2确定卖家家身份
		  Item itemId = itemService.getItemByItemId(trade.getTradeitemid());
		  if(!itemId.getUserid().equals(user.getId())) {
			  return CommonResult.build(500, "您无权操作");
		  }
		//3.当前交易状态
		  if(trade.getTradestatus() != 0) {
			  return CommonResult.build(500, "操作不当");
		  }
		//4.修改信息
		  trade.setTradestatus(1);
		  trade.setTradecomplishtime(new Date());
		  tradeMapper.updateByPrimaryKeySelective(trade);
	    //5.设置自动评价自动执行
		  cratfsMan2.startTheTask(trade.getId(),2, AUTO_COMMENT_TIME);
		return CommonResult.ok();
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#evaluateItem(javax.servlet.http.HttpServletRequest, com.pxxysecondhand.pojo.User, java.lang.Integer, java.lang.String)
	 */
	@Override
	public CommonResult evaluateItem(HttpServletRequest request, User user, Integer evaluateLevel, String content , String tradeId) {
		// TODO Auto-generated method stub
		 Trade trade = tradeMapper.selectByPrimaryKey(tradeId);
		 //1.查看交易是否存在
		  if(trade==null) {
			  return CommonResult.build(500, "交易不存在");
		  }
		  //2 确定买家身份
		  if(!trade.getBuyerid().equals(user.getId())) {
			  return CommonResult.build(500, "您无权操作");
		  }
		  //3当前交易状态
		  if(trade.getTradestatus() != 1) {
			  return CommonResult.build(500, "操作不当");
		  }
		  //4修改信息
		  trade.setTradestatus(2);
		  trade.setTradeevaluate(content);
		  trade.setTradeevaluatelevel(evaluateLevel);
		  trade.setTradecevaluatetime(new Date());
		  tradeMapper.updateByPrimaryKeySelective(trade);
		  //5重新评估用户的信誉度
		  Item item = itemMapper.selectByPrimaryKey(trade.getTradeitemid());
		  reaccessSellerCreditLevel(item.getUserid());
		 return CommonResult.ok();
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#reaccessSellerCreditLevel(java.lang.String)
	 *  重新计算卖家的信用
	 */
	@Override
	public void reaccessSellerCreditLevel(String userId) {
		// TODO Auto-generated method stub
		List<EvaluateModel> evaluateModels = tradeMapper.getEvaluateModels(userId);
		if(evaluateModels==null||evaluateModels.size()<3) {
			//交易次数不足3个  卖家的评分为5星 信誉度为10
			 User user = new User();
			 user.setId(userId);
			 user.setCredit(10);
			 userMapper.updateByPrimaryKeySelective(user);
		}else {
			//每个人的评分由他和每个人最近的三次交易决定  
			 Map map = ListToCreditMap(evaluateModels);
			 Integer credit = caculateUserCredit(map);
			 User user = new User();
			 user.setId(userId);
			 user.setCredit(credit);
			 userMapper.updateByPrimaryKeySelective(user);
		}
	}

	private Integer caculateUserCredit(Map map) {
		// TODO Auto-generated method stub
		int sum = 0;
		int i = 0;
		for (Object key : map.keySet()) {
			String keyStr = (String) key;
			List<EvaluateModel> list = (List<EvaluateModel>) map.get(keyStr);
			for (EvaluateModel evaluateModel : list) {
				sum+=evaluateModel.getTradeEvaluateLevel().intValue();
				i++;
			}
		}
		return sum/i+1;
	}

	private Map ListToCreditMap(List<EvaluateModel> evaluateModels) {
		// TODO Auto-generated method stub
		Map<String,List<Integer>> map = new HashMap<String,List<Integer>>();
		for (EvaluateModel evaluateModel : evaluateModels) {
			 List<Integer> list = map.get(evaluateModel.getBuyerId());
			 if(list==null) {
				 list = new ArrayList<Integer>();
				 list.add(evaluateModel.getTradeEvaluateLevel());
				 map.put(evaluateModel.getBuyerId(), list);
			 }else if(list.size()<3) {
				 list.add(evaluateModel.getTradeEvaluateLevel());
				  map.put(evaluateModel.getBuyerId(), list);
			 }
		}
		return map;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#autoChangeTradingToTraded()
	 */
	@Override
	public int autoChangeTradingToTraded(String tradeId) {
		// TODO Auto-generated method stub
		Trade trade = getTradeByTradeId(tradeId);
		if(trade==null) {
			return 0;
		}
		if(trade.getTradestatus() == 0) {
			//如果用户没有操作 则将宝贝改变为收货状态 同时添加时间 
			trade.setTradestatus(1);
			trade.setTradecomplishtime(new Date());
			tradeMapper.updateByPrimaryKeySelective(trade);
			//需要添加下一个定时动作
			return 1;
		}
		//没有必要添加下一个定时动作
		return 0;
	}

		
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#getTradeByTradeI(java.lang.String)
	 */
	@Override
	public Trade getTradeByTradeId(String TradeId) {
		// TODO Auto-generated method stub
		Trade trade = tradeMapper.selectByPrimaryKey(TradeId);
		return trade;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#autoChangeTradedToComplish(java.lang.String)
	 */
	@Override
	public int autoChangeTradedToComplish(String tradeId) {
		// TODO Auto-generated method stub
		Trade trade = getTradeByTradeId(tradeId);
		//非空判断
		if(trade==null) {
			return 0;
		}
		if(trade.getTradestatus() == 1) {
			//如果用户没有操作 则将宝贝改变交易完成状态 同时添加时间和评论 
			trade.setTradestatus(2);
			trade.setTradecevaluatetime(new Date());
			trade.setTradeevaluatelevel(10);
			tradeMapper.updateByPrimaryKeySelective(trade);
			//5重新评估用户的信誉度
			  Item item = itemMapper.selectByPrimaryKey(trade.getTradeitemid());
			  reaccessSellerCreditLevel(item.getUserid());
			return 1;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#cancelTheTrade(com.pxxysecondhand.pojo.User, java.lang.String)
	 */
	@Override
	public CommonResult cancelTheTrade(User user, String tradeId) {
		// TODO Auto-generated method stub
		//状态3为买家取消 状态为4则为卖家取消了订单
			//获得订单对象
			Trade trade = getTradeByTradeId(tradeId);
			if(trade==null) {
				return CommonResult.build(500, "该交易不存在");
			}
			if(trade.getTradestatus()!=0) {
				return CommonResult.build(500, "您操作不当");
			}
		String buyerId = trade.getBuyerid();
			//获得买家对象
			Item item = itemService.getItemByItemId(trade.getTradeitemid());
		String sellerId = item.getUserid();
		
		if(user.getId().equals(buyerId)) {
			//更改状态和时间
			trade.setTradestatus(3);
			trade.setTradecanceltime(new Date());
		}else if(user.getId().equals(sellerId) ) {
			trade.setTradestatus(4);
			trade.setTradecanceltime(new Date());
		}else {
			return CommonResult.build(500, "您无权进行次操作");
		}
		tradeMapper.updateByPrimaryKeySelective(trade);
		
		//宝贝重新上架
		Item newitem = new Item();
		newitem.setId(trade.getTradeitemid());
		newitem.setIsintrade(0);
		itemMapper.updateByPrimaryKeySelective(newitem);
		return CommonResult.ok();
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#showCommentByTradeId(com.pxxysecondhand.pojo.User, java.lang.String)
	 */
	@Override
	public MyPurchase showCommentByTradeId(User user, String tradeId) {
		// TODO Auto-generated method stub
		MyPurchase myPurchase = tradeMapper.getMyPurchaseByTradeId(tradeId);
		//查看改交易是否存在状态是否正确 
		if(myPurchase==null||myPurchase.getTradeStatus()==0||myPurchase.getTradeStatus()==3)
			return null;
		//查看者身份只能为买家或者卖家  
		if(checkMyIdentity(user,tradeId)==0)
			return null;
		return myPurchase;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#checkMyIdentity(com.pxxysecondhand.pojo.User)
	 */
	@Override
	public int checkMyIdentity(User user,String tradeId) {
		// TODO Auto-generated method stub
		EvaluateModel EvaluateModel = tradeMapper.getBuyerIdAndOwnerId(tradeId);
		if(EvaluateModel==null)
			return 0;
		if(user.getId().equals(EvaluateModel.getBuyerId())) {
			return 1;
		}else if(user.getId().equals(EvaluateModel.getOwnerId())) {
			return 2;
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#fadeDeleteTradeRecord(java.lang.String, int)
	 */
	@Override
	public CommonResult fadeDeleteTradeRecord(String tradeId, int i) {
		// TODO Auto-generated method stub
		//判断当前的订单是否满足删除的条件
		Trade ttrade = tradeMapper.selectByPrimaryKey(tradeId);
		if(ttrade.getTradestatus()==2||ttrade.getTradestatus()==3||ttrade.getTradestatus()==4) {
		 //删除订单
			TradeExample example = new TradeExample();
			Trade trade = new Trade();
			trade.setId(tradeId);
			if(i==1) {
				//删除买家订单
				trade.setBuyerisdeleted(1);
				tradeMapper.updateByPrimaryKeySelective(trade);
			}else if(i==2) {
				//删除卖家订单
				trade.setOwnerisdeleted(1);
				tradeMapper.updateByPrimaryKeySelective(trade);
			}
			return CommonResult.ok();
		}else {
			return CommonResult.build(500, "当前订单不满足删除条件");
		}
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#caculateTimeLeftMessage(java.util.Date, java.util.Date, java.lang.Integer)
	 */
	@Override
	public String caculateTimeLeftMessage(Date createTime, Date complishTime, Integer status) {
		// TODO Auto-generated method stub
		String tip ;
		if(status == 0) {
			tip =  CommonUtils.getTimeIntervalInImpreciseWay(new Date(createTime.getTime()+AUTO_COMFIRM_TIME));
		   return tip+"自动确定收货";
		}else if(status == 1) {
			tip =  CommonUtils.getTimeIntervalInImpreciseWay(new Date(complishTime.getTime()+AUTO_COMMENT_TIME));
			return tip+"默认评价";
		}else if(status == 2) {
			return "交易完成,";
		}else if(status == 3) {
			return "买家已取消了订单";
		}else if(status == 4 ) {
			return "卖家已取消订单";
		}
		return "系统错误";
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#getAllOfMySelled(javax.servlet.http.HttpServletRequest, com.pxxysecondhand.pojo.User, int, int)
	 */
	@Override
	public SearchResult<MySelled> getAllOfMySelled(HttpServletRequest request, User user, int page, int rows) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				PageHelper.startPage(page, rows);
				List<MySelled> itemList = tradeMapper.getAllOfMySelled(user.getId());
				SearchResult searchResult = new SearchResult<MySelled>();
				for (MySelled mySelled : itemList) {
					mySelled.setTimeLeftMessage(caculateTimeLeftMessage(mySelled.getTradeCreatedTime(),mySelled.getTradeComplishTime(),mySelled.getTradeStatus()));
//					/System.out.println("我的所有订单"+myPurchase);
				}
				//查找的数据为空
				if(itemList==null||itemList.size()==0) {
					searchResult .setTotalCount(0);
					searchResult.setTotalPage(0);
					searchResult.setCurrentPage(page);
					searchResult.setItemList(null);
					return searchResult;
				}
				//不为空
				PageInfo<MySelled> pageInfo = new PageInfo(itemList);
				int total = (int) pageInfo.getTotal();
				searchResult.setTotalCount(total);
				int totalPage = total%rows==0?total/rows:(total/rows+1);
				searchResult.setTotalPage(totalPage);
				searchResult.setCurrentPage(page);
				searchResult.setItemList(itemList);
				searchResult.setURL(CommonUtils.getURLByServletRequest(request));
				return searchResult;
	}

}
