/**   
* @Title: TradeServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��5�� ����8:22:00 
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
	
	//��ʱִ���������߳�
	@Autowired
	private CraftsMan1 craftsMan1;
	
	@Autowired
	private CraftsMan2 cratfsMan2;
	
	//�����Զ�ȷ���ջ�ʱ��
	@Value("${AUTO_COMFIRM_TIME}")
	private Integer AUTO_COMFIRM_TIME;
	
	//�����Զ�����ʱ��
	@Value("${AUTO_COMMENT_TIME}")
	private Integer AUTO_COMMENT_TIME;
	
	//Redis���ݿ���ȷ�Ͻ�����֤�뻺��ǰ׺��
	@Value("${SMS_REDIS_MAKEADEAL_CODE_PREFIX}")
	private String SMS_REDIS_MAKEADEAL_CODE_PREFIX;
	
	//��֤�볬ʱ��ʧʱ�� ��λS
	@Value("${SMS_CODE_TIMEOUT}")
	private int SMS_CODE_TIMEOUT;
	
	//ȷ�Ͻ�����֤��Timer��ʱ��ǰ׺
	@Value("${SMS_TIMER_MAKEADEAL_PREFIX}")
	private String SMS_TIMER_MAKEADEAL_PREFIX;
	
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#sendTradeSmsMessage(java.lang.String, java.lang.String)
	 */
	@Override
	public CommonResult sendTradeSmsMessage(String userId,String phonenumber,String itemId) {
		// TODO Auto-generated method stub
		boolean canIGetSmsCode = smsCodeTimer.canIGetSmsCode(SMS_TIMER_MAKEADEAL_PREFIX+phonenumber+"_"+itemId);
		//1.�鿴�û��Ƿ����������֤���Ҫ��
		if(canIGetSmsCode) {
			//2.�������������֤�� ������֤�� ��¼����ʱ�� ������֤��
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
			return new CommonResult().build(501,"���η��ͼ��ʱ��̫��");
		}
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#makeADeal(com.pxxysecondhand.pojo.User, java.lang.String, java.lang.String)
	 */
	@Override
	public CommonResult makeADeal(User user, Item item, String smsCode)  {
		// TODO Auto-generated method stub
		if(validateMakeADealSMSCode(user,item.getId(),smsCode)) {
			//�ı����õ�״̬
			itemService.changeItemIsInTrade(item.getId(), 1);
			//��������������Ϣ ���״���
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
			//����״̬(0���ڽ���,1 ������� , 2����ȡ��)7���޲����Զ������ڽ��ױ��ɽ��ױ�
			record.setTradestatus(0);
			//��������(0���潻��)
			record.setPaymenttype(0);
			//�������ͣ�Ĭ��0 һ�ڼۣ�
			record.setTradetype(0);
			tradeMapper.insert(record);
			//�����Զ�ȷ���ջ��߳�
			craftsMan1.startTheTask(record.getId(), 1, AUTO_COMFIRM_TIME);
			return CommonResult.ok();
		}else {
			return CommonResult.build(501,"��֤�����");
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
//			/System.out.println("�ҵ����ж���"+myPurchase);
		}
		//���ҵ�����Ϊ��
		if(itemList==null||itemList.size()==0) {
			searchResult .setTotalCount(0);
			searchResult.setTotalPage(0);
			searchResult.setCurrentPage(page);
			searchResult.setItemList(null);
			return searchResult;
		}
		//�Զ������б��� ����һЩ����
		for (MyPurchase myPurchase : itemList) {
			myPurchase.setImage(CommonUtils.getLargeImage(myPurchase.getImage()));
		}
		//��Ϊ��
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
		//1.�鿴�����Ƿ����
		  if(trade==null) {
			  return CommonResult.build(500, "���ײ�����");
		  }
		  //2ȷ�����Ҽ����
		  Item itemId = itemService.getItemByItemId(trade.getTradeitemid());
		  if(!itemId.getUserid().equals(user.getId())) {
			  return CommonResult.build(500, "����Ȩ����");
		  }
		//3.��ǰ����״̬
		  if(trade.getTradestatus() != 0) {
			  return CommonResult.build(500, "��������");
		  }
		//4.�޸���Ϣ
		  trade.setTradestatus(1);
		  trade.setTradecomplishtime(new Date());
		  tradeMapper.updateByPrimaryKeySelective(trade);
	    //5.�����Զ������Զ�ִ��
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
		 //1.�鿴�����Ƿ����
		  if(trade==null) {
			  return CommonResult.build(500, "���ײ�����");
		  }
		  //2 ȷ��������
		  if(!trade.getBuyerid().equals(user.getId())) {
			  return CommonResult.build(500, "����Ȩ����");
		  }
		  //3��ǰ����״̬
		  if(trade.getTradestatus() != 1) {
			  return CommonResult.build(500, "��������");
		  }
		  //4�޸���Ϣ
		  trade.setTradestatus(2);
		  trade.setTradeevaluate(content);
		  trade.setTradeevaluatelevel(evaluateLevel);
		  trade.setTradecevaluatetime(new Date());
		  tradeMapper.updateByPrimaryKeySelective(trade);
		  //5���������û���������
		  Item item = itemMapper.selectByPrimaryKey(trade.getTradeitemid());
		  reaccessSellerCreditLevel(item.getUserid());
		 return CommonResult.ok();
	}

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ITradeService#reaccessSellerCreditLevel(java.lang.String)
	 *  ���¼������ҵ�����
	 */
	@Override
	public void reaccessSellerCreditLevel(String userId) {
		// TODO Auto-generated method stub
		List<EvaluateModel> evaluateModels = tradeMapper.getEvaluateModels(userId);
		if(evaluateModels==null||evaluateModels.size()<3) {
			//���״�������3��  ���ҵ�����Ϊ5�� ������Ϊ10
			 User user = new User();
			 user.setId(userId);
			 user.setCredit(10);
			 userMapper.updateByPrimaryKeySelective(user);
		}else {
			//ÿ���˵�����������ÿ������������ν��׾���  
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
			//����û�û�в��� �򽫱����ı�Ϊ�ջ�״̬ ͬʱ���ʱ�� 
			trade.setTradestatus(1);
			trade.setTradecomplishtime(new Date());
			tradeMapper.updateByPrimaryKeySelective(trade);
			//��Ҫ�����һ����ʱ����
			return 1;
		}
		//û�б�Ҫ�����һ����ʱ����
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
		//�ǿ��ж�
		if(trade==null) {
			return 0;
		}
		if(trade.getTradestatus() == 1) {
			//����û�û�в��� �򽫱����ı佻�����״̬ ͬʱ���ʱ������� 
			trade.setTradestatus(2);
			trade.setTradecevaluatetime(new Date());
			trade.setTradeevaluatelevel(10);
			tradeMapper.updateByPrimaryKeySelective(trade);
			//5���������û���������
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
		//״̬3Ϊ���ȡ�� ״̬Ϊ4��Ϊ����ȡ���˶���
			//��ö�������
			Trade trade = getTradeByTradeId(tradeId);
			if(trade==null) {
				return CommonResult.build(500, "�ý��ײ�����");
			}
			if(trade.getTradestatus()!=0) {
				return CommonResult.build(500, "����������");
			}
		String buyerId = trade.getBuyerid();
			//�����Ҷ���
			Item item = itemService.getItemByItemId(trade.getTradeitemid());
		String sellerId = item.getUserid();
		
		if(user.getId().equals(buyerId)) {
			//����״̬��ʱ��
			trade.setTradestatus(3);
			trade.setTradecanceltime(new Date());
		}else if(user.getId().equals(sellerId) ) {
			trade.setTradestatus(4);
			trade.setTradecanceltime(new Date());
		}else {
			return CommonResult.build(500, "����Ȩ���дβ���");
		}
		tradeMapper.updateByPrimaryKeySelective(trade);
		
		//���������ϼ�
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
		//�鿴�Ľ����Ƿ����״̬�Ƿ���ȷ 
		if(myPurchase==null||myPurchase.getTradeStatus()==0||myPurchase.getTradeStatus()==3)
			return null;
		//�鿴�����ֻ��Ϊ��һ�������  
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
		//�жϵ�ǰ�Ķ����Ƿ�����ɾ��������
		Trade ttrade = tradeMapper.selectByPrimaryKey(tradeId);
		if(ttrade.getTradestatus()==2||ttrade.getTradestatus()==3||ttrade.getTradestatus()==4) {
		 //ɾ������
			TradeExample example = new TradeExample();
			Trade trade = new Trade();
			trade.setId(tradeId);
			if(i==1) {
				//ɾ����Ҷ���
				trade.setBuyerisdeleted(1);
				tradeMapper.updateByPrimaryKeySelective(trade);
			}else if(i==2) {
				//ɾ�����Ҷ���
				trade.setOwnerisdeleted(1);
				tradeMapper.updateByPrimaryKeySelective(trade);
			}
			return CommonResult.ok();
		}else {
			return CommonResult.build(500, "��ǰ����������ɾ������");
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
		   return tip+"�Զ�ȷ���ջ�";
		}else if(status == 1) {
			tip =  CommonUtils.getTimeIntervalInImpreciseWay(new Date(complishTime.getTime()+AUTO_COMMENT_TIME));
			return tip+"Ĭ������";
		}else if(status == 2) {
			return "�������,";
		}else if(status == 3) {
			return "�����ȡ���˶���";
		}else if(status == 4 ) {
			return "������ȡ������";
		}
		return "ϵͳ����";
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
//					/System.out.println("�ҵ����ж���"+myPurchase);
				}
				//���ҵ�����Ϊ��
				if(itemList==null||itemList.size()==0) {
					searchResult .setTotalCount(0);
					searchResult.setTotalPage(0);
					searchResult.setCurrentPage(page);
					searchResult.setItemList(null);
					return searchResult;
				}
				//��Ϊ��
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
