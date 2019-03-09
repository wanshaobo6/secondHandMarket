/**   
* @Title: SearchServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月22日 下午7:46:48 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pxxysecondhand.component.IScanRecorder;
import com.pxxysecondhand.component.ISearchRecorder;
import com.pxxysecondhand.mapper.CollectionMapper;
import com.pxxysecondhand.mapper.ItemMapper;
import com.pxxysecondhand.mapper.UserMapper;
import com.pxxysecondhand.pojo.CollectionExample;
import com.pxxysecondhand.pojo.Item;
import com.pxxysecondhand.pojo.ItemExample;
import com.pxxysecondhand.pojo.ItemExample.Criteria;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.pojo.UserExample;
import com.pxxysecondhand.service.IItemService;
import com.pxxysecondhand.service.ISearchService;
import com.pxxysecondhand.tempPojo.GuessItem;
import com.pxxysecondhand.tempPojo.ScanRecord;
import com.pxxysecondhand.tempPojo.SearchItem;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonUtils;
import com.pxxysecondhand.utils.CookieUtils;

/**
 * @author  
 *
 */
@Service
public class SearchServiceImpl implements ISearchService {
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CollectionMapper collectionMapper;
	//浏览记录器
	@Autowired
	private IScanRecorder scanRecorder;
	
	@Autowired
	private ISearchRecorder searchRecorder;
	
	@Autowired
	private IItemService itemService;
	

	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#searchByKeywords(java.lang.String)
	 */
	@Override
	public SearchResult<SearchItem> searchByKeywords(String keywords ,int orderCondition , int page , int rows) {
		// TODO Auto-generated method stub
		//暂时按照闲置名称查找
		List<SearchItem> searchItems = null;
		switch(orderCondition){
			case 0: searchItems = searchByKeywordsOrderByorderNum( keywords ,  page ,  rows);break;
			case 1: searchItems = searchByKeywordsOrderByPolishTime( keywords ,  page ,  rows);break;
			case 2:  searchItems = searchByKeywordsOrderByCollectionNum( keywords ,  page ,  rows);break;
			case 3: searchItems = searchByKeywordsOrderByPriceDesc( keywords ,  page ,  rows); break;
			case 4: searchItems = searchByKeywordsOrderByPriceAesc( keywords ,  page ,  rows); break;
			case 5: searchItems = searchByKeywordsOrderByAuthorCredit( keywords ,  page ,  rows); break;
			default: searchItems = searchByKeywordsOrderByorderNum( keywords ,  page ,  rows); break;
			
		}
		SearchResult<SearchItem> searchResult = new SearchResult();
		//查找的数据为空
		if(searchItems==null||searchItems.size()==0) {
			searchResult.setTotalCount(0);
			searchResult.setTotalPage(0);
			searchResult.setCurrentPage(page);
			searchResult.setItemList(null);
			return searchResult;
		}
		//遍历数据
		for (SearchItem searchItem : searchItems) {
			//关键字变红
			String newtitle = searchItem.getItemtitle().replaceAll(keywords, "<span style=\"color:#FF7200\">"+keywords+"</span>");
			searchItem.setItemtitle(newtitle);
			//取出第一张图片
			searchItem.setItemimages(CommonUtils.getLargeImage(searchItem.getItemimages()));
			//设置收藏量
			 CollectionExample example = new CollectionExample();
			 com.pxxysecondhand.pojo.CollectionExample.Criteria exampleCriteria = example.createCriteria();
			 exampleCriteria.andItemidEqualTo(searchItem.getId());
			 int collectionNum = collectionMapper.countByExample(example);
			 searchItem.setCollectionNum(collectionNum);
			//设置浏览量
			searchItem.setScanNum(scanRecorder.getScanNumByItemId(searchItem.getId()));
			//设置不精确时间清楚精确时间
			searchItem.setPolishTimeImprecise(CommonUtils.getTimeIntervalInImpreciseWay(searchItem.getUpdated()));
			searchItem.setUpdated(null);
		}
		 PageInfo<SearchItem> pageInfo = new PageInfo<SearchItem>(searchItems);
		 int i  = (int) pageInfo.getTotal();
		searchResult.setTotalCount(pageInfo.getTotal());
		int totalPage = i%rows==0?i/rows:(i/rows+1);
		searchResult.setOrderCondition(orderCondition);
		searchResult.setTotalPage(totalPage);
		searchResult.setCurrentPage(page);
		searchResult.setItemList(searchItems);
		return searchResult;
	}

	
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#searchByKeywordsOrderByorderNum(java.lang.String, int, int)
	 * 综合排序
	 */
	@Override
	public List<SearchItem> searchByKeywordsOrderByorderNum(String keywords, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<SearchItem> searchItems =  itemMapper.searchByKeywordsOrderByorderNum(keywords);
		return searchItems;
	}


	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#searchByKeywordsOrderByPolishTime(java.lang.String, int, int)
	 */
	@Override
	public List<SearchItem> searchByKeywordsOrderByPolishTime(String keywords, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<SearchItem> searchItems =  itemMapper.searchByKeywordsOrderByPolishTime(keywords);
		return searchItems;
	}


	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#searchByKeywordsOrderByCollectionNum(java.lang.String, int, int)
	 */
	@Override
	public List<SearchItem> searchByKeywordsOrderByCollectionNum(String keywords, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<SearchItem> searchItems =  itemMapper.searchByKeywordsOrderByCollectionNum(keywords);
		return searchItems;
	}


	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#searchByKeywordsOrderByPriceDesc(java.lang.String, int, int)
	 */
	@Override
	public List<SearchItem> searchByKeywordsOrderByPriceDesc(String keywords, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<SearchItem> searchItems =  itemMapper.searchByKeywordsOrderByPriceDesc(keywords);
		return searchItems;
	}



	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#searchByKeywordsOrderByAuthorCredit(java.lang.String, int, int)
	 */
	@Override
	public List<SearchItem> searchByKeywordsOrderByAuthorCredit(String keywords, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<SearchItem> searchItems =  itemMapper.searchByKeywordsOrderByAuthorCredit(keywords);
		return searchItems;
	}


	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#searchByKeywordsOrderByPriceAesc(java.lang.String, int, int)
	 */
	@Override
	public List<SearchItem> searchByKeywordsOrderByPriceAesc(String keywords, int page, int rows) {
		// TODO Auto-generated method stub
		PageHelper.startPage(page, rows);
		List<SearchItem> searchItems =  itemMapper.searchByKeywordsOrderByPriceAesc(keywords);
		return searchItems;
	}


	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#searchByUserHabit(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public List<GuessItem> searchByUserHabit(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		//获取当前用户的GuessId  
		String guessCookie = CookieUtils.getCookieValue(request, "Guess_Token");
		List<GuessItem> guessItem;
		if(StringUtils.isEmpty(guessCookie)) {
			//为空则为用户创建一个GuessId  并随机相应的物品
			CookieUtils.setCookie(request, response, "Guess_Token", CommonUtils.uuidGenerator());
			 guessItem = RandomRecommend();
		}
		Map<String, List<ScanRecord>> scanedItemMap = scanRecorder.getItemRecordMapByGuessToken(guessCookie);
		if(scanedItemMap ==  null) {
			
		}
		 guessItem = RandomRecommend();
		//查询ScanRecorder
		//分析记录
		//查询
		return guessItem;
	}


	//随机推荐
	private List<GuessItem> RandomRecommend() {
		// TODO Auto-generated method stub
		List<GuessItem> guessItems = new ArrayList();
		//查询架上商品总量
		int totalItemNum = getTotalOnSell();
		if(totalItemNum<=12) {
			return getAllItems();
		}else {
			/*while(guessItems.size()<11) {
				
			}*/
		}
		return getAllItems();
	}


	
	private List<GuessItem> getAllItems() {
		// TODO Auto-generated method stub
		ItemExample example = new ItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsintradeEqualTo(0);
		List<Item> items = itemMapper.selectByExample(example);
		List<GuessItem> guessItems = new ArrayList();
		for (Item item : items) {
			GuessItem guessItem= new GuessItem();
			guessItem.setItemId(item.getId());
			guessItem.setImage(CommonUtils.getLargeImage(item.getItemimages()));
			guessItem.setTitle(CommonUtils.handleTitleInMaxLength(10, item.getItemtitle()));
			guessItems.add(guessItem);
		}
		return guessItems;
	}


	private int getTotalOnSell() {
		// TODO Auto-generated method stub
		ItemExample example = new ItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIsintradeEqualTo(0);
		int totalItemNum = itemMapper.countByExample(example);
		return totalItemNum;
	}


	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#createSearchRecord(java.lang.String, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void createSearchRecord(String keywords, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String token = itemService.createGuessTokenIfNotExist(request, response);
		searchRecorder.addRecord(keywords,token);
	}


	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ISearchService#getPopulateSearchKeys()
	 */
	@Override
	public List<String> getPopulateSearchKeys() {
		// TODO Auto-generated method stub
		return searchRecorder.getPopulteKeywords();
	}

}
