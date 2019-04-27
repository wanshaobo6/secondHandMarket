/**   
* @Title: ItemServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月19日 下午8:37:44 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pxxysecondhand.component.ICommonOperationTimer;
import com.pxxysecondhand.component.IScanRecorder;
import com.pxxysecondhand.component.JedisClient;
import com.pxxysecondhand.mapper.CollectionMapper;
import com.pxxysecondhand.mapper.ItemCatMapper;
import com.pxxysecondhand.mapper.ItemMapper;
import com.pxxysecondhand.mapper.TradeMapper;
import com.pxxysecondhand.mapper.UserMapper;
import com.pxxysecondhand.pojo.CollectionExample;
import com.pxxysecondhand.pojo.CollectionExample.Criteria;
import com.pxxysecondhand.pojo.Comment;
import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.ItemCat;
import com.pxxysecondhand.pojo.ItemExample;
import com.pxxysecondhand.pojo.Trade;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICommentService;
import com.pxxysecondhand.service.IItemService;
import com.pxxysecondhand.service.ITradeService;
import com.pxxysecondhand.service.IUserLoginService;
import com.pxxysecondhand.tempPojo.ItemDescResult;
import com.pxxysecondhand.tempPojo.MyPublic;
import com.pxxysecondhand.tempPojo.NewestPublic;
import com.pxxysecondhand.tempPojo.ScanRecord;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;
import com.pxxysecondhand.utils.CookieUtils;
import com.pxxysecondhand.utils.JsonUtils;

/**
 * @author
 *
 */
@Service
public class ItemServiceImpl implements IItemService {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemCatMapper itemCatMapper;

	@Autowired
	private TradeMapper tradeMapper;

	@Autowired
	private CollectionMapper collectionMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ITradeService tradeService;

	@Autowired
	private IUserLoginService userLoginService;

	@Autowired
	private ICommentService commentService;

	@Autowired
	private IScanRecorder scanRecorder;

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private ICommonOperationTimer operationTimer;

	// 擦亮宝贝名称
	@Value("${POLISH_ITEM_EVENT_NAME}")
	private String POLISH_ITEM_EVENT_NAME;

	// 下次能够擦亮宝贝的时间间隔
	@Value("${NEXT_POLISH_ITEM_SEC_INTERVAL}")
	private Integer NEXT_POLISH_ITEM_SEC_INTERVAL;

	// #登录成功后存储用户个人资料前缀
	@Value("${USER_ONLINE_TOKEN_PREFIX}")
	private String USER_ONLINE_TOKEN_PREFIX;

	// #Guess_Token TTL - 客户多久不登录 客人身份失效时间
	@Value("${Guess_Token_TTL}")
	private int Guess_Token_TTL;
	
	public String createGuessTokenIfNotExist(HttpServletRequest request , HttpServletResponse response ) {
		// 查看是否有Guess_Token
			String Guess_Token = CookieUtils.getCookieValue(request, "Guess_Token");
			if (StringUtils.isEmpty(Guess_Token)) {
				// 没有则生成cookie给浏览器设置超时时间一个月 将记录存入记录器
				Guess_Token = CommonUtils.uuidGenerator();
				CookieUtils.setCookie(request, response, "Guess_Token", Guess_Token, Guess_Token_TTL);
			} else {
				// 有则刷新客人身份时间
				CookieUtils.setCookie(request, response, "Guess_Token", Guess_Token, Guess_Token_TTL);
			}
			//查看是否登录 有则记录此与用户的浏览记录 token为用户Id
			String Login_Token = CookieUtils.getCookieValue(request, "Login_Token");
			if(!StringUtils.isEmpty(Login_Token)) {
				try {
					User user = commentService.checkIsLogin(request);
					if(user == null) 
						return Guess_Token;
					return user.getId(); 
				} catch (Exception e) {
					// TODO Auto-generated catch block
					return Guess_Token;
				}
			}
			return Guess_Token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pxxysecondhand.service.IItemService#addItem()
	 */
	public CommonResult addItem(HttpServletRequest request, HttpServletResponse response, Item item, String token) {
		// TODO Auto-generated method stub
		// 验证表单数据格式
		if (!validateFormDate(item))
			return CommonResult.build(500, "表单数据格式错误");
		// 查询填充当前用户
		User user;
		try {
			user = userLoginService.queryByToken(request, response, token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CommonResult.build(501, "当前用户不存在");
		}
		item.setUserid(user.getId());
		// 补全数据
		item.setId(CommonUtils.uuidGenerator());
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		item.setOrdernum(0);
		item.setIsintrade(0);
		// 插入数据
		itemMapper.insert(item);
		return CommonResult.ok();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pxxysecondhand.service.IItemService#validateFormDate(com.pxxysecondhand.
	 * pojo.ItemCat)
	 */
	@Override
	public boolean validateFormDate(Item item) {
		// TODO Auto-generated method stub
		boolean available = true;

		return available;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pxxysecondhand.service.IItemService#showItem()
	 */
	@Override
	public ItemDescResult showItem(String itemId) {
		// TODO Auto-generated method stub
		// 查找宝贝的详细信息
		ItemDescResult item = itemMapper.showItem(itemId);
		if (item == null)
			return null;
		// 查找宝贝的收藏数量填充
		CollectionExample example = new CollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andCollectoridEqualTo(item.getUserId());
		int num = collectionMapper.countByExample(example);
		item.setCollectionNum(num);
		// 设置时间
		item.setTime(CommonUtils.getTimeIntervalInImpreciseWay(item.getUpdated()));
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pxxysecondhand.service.IItemService#addTheRecord(java.lang.String,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */

	@Override
	public void addTheRecord(HttpServletRequest request, HttpServletResponse response, String itemId) {
		// TODO Auto-generated method stub
		if(StringUtils.isEmpty(itemId)){
			return;
		}
		//查看是否登录 有则记录此与用户的浏览记录 token为用户Id
		String Login_Token = CookieUtils.getCookieValue(request, "Login_Token");
		if(!StringUtils.isEmpty(Login_Token)) {
			try {
				User user = userLoginService.queryByToken(request, response, Login_Token);
				scanRecorder.addRecord(user.getId(), new ScanRecord(itemId, new Date()));
				return ; 
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}
		// 查看是否有Guess_Token
		String Guess_Token = CookieUtils.getCookieValue(request, "Guess_Token");
		if (StringUtils.isEmpty(Guess_Token)) {
			// 没有则生成cookie给浏览器设置超时时间一个月 将记录存入记录器
			String newToken = CommonUtils.uuidGenerator();
			CookieUtils.setCookie(request, response, "Guess_Token", newToken, Guess_Token_TTL);
			scanRecorder.addRecord(newToken, new ScanRecord(itemId, new Date()));
		} else {
			// 有则刷新客人身份时间
			CookieUtils.setCookie(request, response, "Guess_Token", Guess_Token, Guess_Token_TTL);
			scanRecorder.addRecord(Guess_Token, new ScanRecord(itemId, new Date()));
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pxxysecondhand.service.IItemService#showMyPublic(java.lang.String,
	 * int, int)
	 */
	@Override
	public SearchResult<MyPublic> showMyPublic(User user, int page, int rows, HttpServletRequest request) {
		// TODO Auto-generated method stub
		// 查找需要的记录
		PageHelper.startPage(page, rows);
		ItemExample example = new ItemExample();
		example.setOrderByClause("'created' DESC");
		com.pxxysecondhand.pojo.ItemExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(user.getId());
		criteria.andIsintradeNotEqualTo(1);
		List<Item> itemList = itemMapper.selectByExample(example);
		// 转换称MyPublic对象
		List<MyPublic> myPublics = new ArrayList();
		for (Item item : itemList) {
			// 不显示交易中的宝贝
			if (item.getIsintrade() == 2)
				break;
			MyPublic myPublic = new MyPublic();
			myPublic.setId(item.getId());
			myPublic.setItemtitle(item.getItemtitle());
			myPublic.setItemimage(CommonUtils.getLargeImage(item.getItemimages()));
			myPublic.setIsintrade(item.getIsintrade());
			myPublic.setCurrprice(item.getCurrprice());
			myPublic.setItemCreateTime(item.getCreated());
			myPublic.setItemLastModefyTime(item.getUpdated());
			// 查询当前闲置所属分类名
			ItemCat category = itemCatMapper.selectByPrimaryKey(item.getCategoryid());
			String categoryName = category.getCategoryname();
			myPublic.setCategoryName(categoryName);
			// 查询收藏总数
			CollectionExample collectionExample = new CollectionExample();
			com.pxxysecondhand.pojo.CollectionExample.Criteria collectionCriteria = collectionExample.createCriteria();
			collectionCriteria.andItemidEqualTo(item.getId());
			int collectionNum = collectionMapper.countByExample(collectionExample);
			myPublic.setCollectionNum(collectionNum);
			// 查询所有留言数量
			myPublic.setAllMessageNums(commentService.countCommentNumByItemId(item.getId()));
			// 查询未读留言数量
			myPublic.setMessagesNotReadNums(commentService.countCommentNotReadByItemId(item.getId()));
			myPublics.add(myPublic);
		}
		// 填充SearchResult的数据
		SearchResult<MyPublic> publicResult = new SearchResult();
		publicResult.setItemList(myPublics);
		publicResult.setCurrentPage(page);
		PageInfo<Item> pageInfo = new PageInfo(itemList);
		int i = (int) pageInfo.getTotal();
		publicResult.setTotalCount(i);
		int totalPage = i % rows == 0 ? i / rows : (i / rows + 1);
		publicResult.setTotalPage(totalPage);
		publicResult.setURL(CommonUtils.getURLByServletRequest(request));
		return publicResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pxxysecondhand.service.IItemService#getItemByItemId(java.lang.String)
	 */
	@Override
	public Item getItemByItemId(String itemId) {
		// TODO Auto-generated method stub
		Item item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pxxysecondhand.service.IItemService#changeItemIsInTrade(java.lang.String,
	 * java.lang.Integer)
	 */
	@Override
	public int changeItemIsInTrade(String itemId, Integer isInTrade) {
		// TODO Auto-generated method stub
		Item item = new Item();
		item.setId(itemId);
		item.setIsintrade(isInTrade);
		return itemMapper.updateByPrimaryKeySelective(item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pxxysecondhand.service.IItemService#getNewestPublic(int)
	 */
	@Override
	public List<NewestPublic> getNewestPublic(int size) {
		// TODO Auto-generated method stub
		PageHelper.startPage(1, 6);
		ItemExample itemExample = new ItemExample();
		com.pxxysecondhand.pojo.ItemExample.Criteria criteria = itemExample.createCriteria();
		criteria.andIsintradeEqualTo(0);
		itemExample.setOrderByClause("`updated` DESC");
		List<Item> resultList = itemMapper.selectByExample(itemExample);
		if (resultList == null)
			return null;
		List<NewestPublic> newestPublics = itemsToNewestPublics(resultList);
		return newestPublics;
	}

	private List<NewestPublic> itemsToNewestPublics(List<Item> resultList) {
		// TODO Auto-generated method stub
		List<NewestPublic> newestPublics = new ArrayList();
		for (Item item : resultList) {
			NewestPublic newestPublic = new NewestPublic();
			newestPublic.setItemId(item.getId());
			newestPublic.setItemTilte(item.getItemtitle());
			newestPublic.setLastPublicTime(CommonUtils.getTimeIntervalInImpreciseWay(item.getUpdated()));
			newestPublics.add(newestPublic);
		}
		return newestPublics;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.pxxysecondhand.service.IItemService#checkSellerByItemId(javax.servlet.
	 * http.HttpServletRequest, java.lang.String)
	 */
	@Override
	public User checkSellerByItemId(HttpServletRequest request, String itemId) {
		// TODO Auto-generated method stub
		User user = commentService.checkIsLogin(request);
		// -------------无过滤器
		if (user == null)
			return null;
		// 获得商品的发布者Id
		Item item = itemMapper.selectByPrimaryKey(itemId);
		if (item == null || item.getIsintrade() == 1)
			return null;
		// 判断是否同一个人
		if (user.getId().equals(item.getUserid())) {
			return user;
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pxxysecondhand.service.IItemService#polishItem(java.lang.String)
	 */
	@Override
	public CommonResult polishItem(String itemId) {
		// TODO Auto-generated method stub
		// 判断是否能够再次操作
		boolean bool = operationTimer.canIDoAgain(itemId, POLISH_ITEM_EVENT_NAME, NEXT_POLISH_ITEM_SEC_INTERVAL);
		if (!bool) {
			return CommonResult.build(500, "两次擦亮必须大于" + NEXT_POLISH_ITEM_SEC_INTERVAL);
		}
		// 擦亮宝贝
		Item item = new Item();
		item.setId(itemId);
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		operationTimer.IHaveDoSomeThing(itemId, POLISH_ITEM_EVENT_NAME);
		return CommonResult.ok();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pxxysecondhand.service.IItemService#changeupanddown()
	 */
	@Override
	public CommonResult changeupanddown(String itemId) {
		// TODO Auto-generated method stub
		Item item = itemMapper.selectByPrimaryKey(itemId);
		Integer tradeNum = item.getIsintrade();
		if (tradeNum == 0) {
			item.setIsintrade(2);
			itemMapper.updateByPrimaryKeySelective(item);
			return CommonResult.ok(0);
		} else if (tradeNum == 2) {
			item.setIsintrade(0);
			itemMapper.updateByPrimaryKeySelective(item);
			return CommonResult.ok(1);
		}
		return CommonResult.build(500, "服务器出错");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pxxysecondhand.service.IItemService#deleteItem()
	 */
	@Override
	public boolean deleteItem(String itemId) throws Exception {
		// TODO Auto-generated method stub
		// 删除宝贝
		itemMapper.deleteByPrimaryKey(itemId);
		// 删除评论
		commentService.deleteCommentsByItemId(itemId);
		return true;
	}

	@Override
	public SearchResult<Comment> showMyMessage(User user, int page, int rows, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * if(item.getIsintrade()==1) { //交易中则填充其剩余信息 Trade trade =
	 * tradeMapper.getNewestTradeByItemId(item.getId()); if(trade == null) //数据库出错
	 * break; myPublic.setTradeId(trade.getId());
	 * myPublic.setTradeStatus(trade.getTradestatus());
	 * myPublic.setTradeEvaluateLevel(trade.getTradeevaluatelevel());
	 * myPublic.setTradeCEvaluateTime(trade.getTradecevaluatetime());
	 * myPublic.setTradeComplishTime(trade.getTradecomplishtime());
	 * myPublic.setTradeCancelTime(trade.getTradecanceltime());
	 * myPublic.setTradeCreatedTime(trade.getTradecreatedtime());
	 * myPublic.setTimeLeftMessage(tradeService.caculateTimeLeftMessage(trade.
	 * getTradecreatedtime(),trade.getTradecreatedtime(), trade.getTradestatus()));
	 * //填充卖家信息和电话 User buyer = userMapper.selectByPrimaryKey(trade.getBuyerid());
	 * myPublic.setBuyerName(buyer.getUsername());
	 * myPublic.setPhoneNumber(buyer.getPhonenumber()); }
	 */
}
