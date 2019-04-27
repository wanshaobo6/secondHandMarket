/**   
* @Title: ItemServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��19�� ����8:37:44 
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

	// ������������
	@Value("${POLISH_ITEM_EVENT_NAME}")
	private String POLISH_ITEM_EVENT_NAME;

	// �´��ܹ�����������ʱ����
	@Value("${NEXT_POLISH_ITEM_SEC_INTERVAL}")
	private Integer NEXT_POLISH_ITEM_SEC_INTERVAL;

	// #��¼�ɹ���洢�û���������ǰ׺
	@Value("${USER_ONLINE_TOKEN_PREFIX}")
	private String USER_ONLINE_TOKEN_PREFIX;

	// #Guess_Token TTL - �ͻ���ò���¼ �������ʧЧʱ��
	@Value("${Guess_Token_TTL}")
	private int Guess_Token_TTL;
	
	public String createGuessTokenIfNotExist(HttpServletRequest request , HttpServletResponse response ) {
		// �鿴�Ƿ���Guess_Token
			String Guess_Token = CookieUtils.getCookieValue(request, "Guess_Token");
			if (StringUtils.isEmpty(Guess_Token)) {
				// û��������cookie����������ó�ʱʱ��һ���� ����¼�����¼��
				Guess_Token = CommonUtils.uuidGenerator();
				CookieUtils.setCookie(request, response, "Guess_Token", Guess_Token, Guess_Token_TTL);
			} else {
				// ����ˢ�¿������ʱ��
				CookieUtils.setCookie(request, response, "Guess_Token", Guess_Token, Guess_Token_TTL);
			}
			//�鿴�Ƿ��¼ �����¼�����û��������¼ tokenΪ�û�Id
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
		// ��֤�����ݸ�ʽ
		if (!validateFormDate(item))
			return CommonResult.build(500, "�����ݸ�ʽ����");
		// ��ѯ��䵱ǰ�û�
		User user;
		try {
			user = userLoginService.queryByToken(request, response, token);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CommonResult.build(501, "��ǰ�û�������");
		}
		item.setUserid(user.getId());
		// ��ȫ����
		item.setId(CommonUtils.uuidGenerator());
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		item.setOrdernum(0);
		item.setIsintrade(0);
		// ��������
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
		// ���ұ�������ϸ��Ϣ
		ItemDescResult item = itemMapper.showItem(itemId);
		if (item == null)
			return null;
		// ���ұ������ղ��������
		CollectionExample example = new CollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andCollectoridEqualTo(item.getUserId());
		int num = collectionMapper.countByExample(example);
		item.setCollectionNum(num);
		// ����ʱ��
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
		//�鿴�Ƿ��¼ �����¼�����û��������¼ tokenΪ�û�Id
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
		// �鿴�Ƿ���Guess_Token
		String Guess_Token = CookieUtils.getCookieValue(request, "Guess_Token");
		if (StringUtils.isEmpty(Guess_Token)) {
			// û��������cookie����������ó�ʱʱ��һ���� ����¼�����¼��
			String newToken = CommonUtils.uuidGenerator();
			CookieUtils.setCookie(request, response, "Guess_Token", newToken, Guess_Token_TTL);
			scanRecorder.addRecord(newToken, new ScanRecord(itemId, new Date()));
		} else {
			// ����ˢ�¿������ʱ��
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
		// ������Ҫ�ļ�¼
		PageHelper.startPage(page, rows);
		ItemExample example = new ItemExample();
		example.setOrderByClause("'created' DESC");
		com.pxxysecondhand.pojo.ItemExample.Criteria criteria = example.createCriteria();
		criteria.andUseridEqualTo(user.getId());
		criteria.andIsintradeNotEqualTo(1);
		List<Item> itemList = itemMapper.selectByExample(example);
		// ת����MyPublic����
		List<MyPublic> myPublics = new ArrayList();
		for (Item item : itemList) {
			// ����ʾ�����еı���
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
			// ��ѯ��ǰ��������������
			ItemCat category = itemCatMapper.selectByPrimaryKey(item.getCategoryid());
			String categoryName = category.getCategoryname();
			myPublic.setCategoryName(categoryName);
			// ��ѯ�ղ�����
			CollectionExample collectionExample = new CollectionExample();
			com.pxxysecondhand.pojo.CollectionExample.Criteria collectionCriteria = collectionExample.createCriteria();
			collectionCriteria.andItemidEqualTo(item.getId());
			int collectionNum = collectionMapper.countByExample(collectionExample);
			myPublic.setCollectionNum(collectionNum);
			// ��ѯ������������
			myPublic.setAllMessageNums(commentService.countCommentNumByItemId(item.getId()));
			// ��ѯδ����������
			myPublic.setMessagesNotReadNums(commentService.countCommentNotReadByItemId(item.getId()));
			myPublics.add(myPublic);
		}
		// ���SearchResult������
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
		// -------------�޹�����
		if (user == null)
			return null;
		// �����Ʒ�ķ�����Id
		Item item = itemMapper.selectByPrimaryKey(itemId);
		if (item == null || item.getIsintrade() == 1)
			return null;
		// �ж��Ƿ�ͬһ����
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
		// �ж��Ƿ��ܹ��ٴβ���
		boolean bool = operationTimer.canIDoAgain(itemId, POLISH_ITEM_EVENT_NAME, NEXT_POLISH_ITEM_SEC_INTERVAL);
		if (!bool) {
			return CommonResult.build(500, "���β����������" + NEXT_POLISH_ITEM_SEC_INTERVAL);
		}
		// ��������
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
		return CommonResult.build(500, "����������");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pxxysecondhand.service.IItemService#deleteItem()
	 */
	@Override
	public boolean deleteItem(String itemId) throws Exception {
		// TODO Auto-generated method stub
		// ɾ������
		itemMapper.deleteByPrimaryKey(itemId);
		// ɾ������
		commentService.deleteCommentsByItemId(itemId);
		return true;
	}

	@Override
	public SearchResult<Comment> showMyMessage(User user, int page, int rows, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * if(item.getIsintrade()==1) { //�������������ʣ����Ϣ Trade trade =
	 * tradeMapper.getNewestTradeByItemId(item.getId()); if(trade == null) //���ݿ����
	 * break; myPublic.setTradeId(trade.getId());
	 * myPublic.setTradeStatus(trade.getTradestatus());
	 * myPublic.setTradeEvaluateLevel(trade.getTradeevaluatelevel());
	 * myPublic.setTradeCEvaluateTime(trade.getTradecevaluatetime());
	 * myPublic.setTradeComplishTime(trade.getTradecomplishtime());
	 * myPublic.setTradeCancelTime(trade.getTradecanceltime());
	 * myPublic.setTradeCreatedTime(trade.getTradecreatedtime());
	 * myPublic.setTimeLeftMessage(tradeService.caculateTimeLeftMessage(trade.
	 * getTradecreatedtime(),trade.getTradecreatedtime(), trade.getTradestatus()));
	 * //���������Ϣ�͵绰 User buyer = userMapper.selectByPrimaryKey(trade.getBuyerid());
	 * myPublic.setBuyerName(buyer.getUsername());
	 * myPublic.setPhoneNumber(buyer.getPhonenumber()); }
	 */
}
