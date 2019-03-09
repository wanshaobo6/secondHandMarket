/**   
* @Title: CollectionServiceImpl.java 
* @Package com.pxxysecondhand.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月2日 下午5:15:08 
* @version V1.0   
*/
package com.pxxysecondhand.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pxxysecondhand.mapper.CollectionMapper;
import com.pxxysecondhand.pojo.Collection;
import com.pxxysecondhand.pojo.CollectionExample;
import com.pxxysecondhand.pojo.CollectionExample.Criteria;
import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.service.ICollectionService;
import com.pxxysecondhand.tempPojo.MyCollections;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;
import com.pxxysecondhand.utils.CommonUtils;

/**
 * @author  
 *
 */
@Service
public class CollectionServiceImpl implements ICollectionService{
	
	@Autowired
	private CollectionMapper collectionMapper;
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICollectionService#changeMyCollection(com.pxxysecondhand.pojo.User, java.lang.String)
	 */
	@Override
	public CommonResult changeMyCollection(User user, String itemId) {
		// TODO Auto-generated method stub
		CollectionExample example = new CollectionExample();
		Criteria criteria = example.createCriteria();
		if(checkCollected(user.getId(),itemId)) {
			//已经收藏了 取消
			criteria.andCollectoridEqualTo(user.getId());
			criteria.andItemidEqualTo(itemId);
			collectionMapper.deleteByExample(example);
			return CommonResult.ok("0");
		}else {
			//添加收藏
			Collection collection = new Collection();
			collection.setCollectorid(user.getId());
			collection.setCreated(new Date());
			collection.setId(CommonUtils.uuidGenerator());
			collection.setItemid(itemId);
			collectionMapper.insert(collection);
			return CommonResult.ok("1");
		}
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICollectionService#checkCollected(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean checkCollected(String collectorId, String itemId) {
		// TODO Auto-generated method stub
		CollectionExample example = new CollectionExample();
		Criteria criteria = example.createCriteria();
		criteria.andCollectoridEqualTo(collectorId);
		criteria.andItemidEqualTo(itemId);
		List<Collection> collection = collectionMapper.selectByExample(example);
		return collection.size()==0?false:true;
	}
	/* (non-Javadoc)
	 * @see com.pxxysecondhand.service.ICollectionService#getAllOfMyCollections(java.lang.String)
	 */
	@Override
	public SearchResult<MyCollections> getAllOfMyCollections(HttpServletRequest request,String collectorId,int page,int rows) {
		// TODO Auto-generated method stub
		SearchResult searchResult = new SearchResult();
		PageHelper.startPage(page, rows);
		List<MyCollections> collectionList = collectionMapper.getCollectionsByCollectorId(collectorId);
		//查找的数据为空
		if(collectionList==null||collectionList.size()==0) {
			searchResult.setTotalCount(0);
			searchResult.setTotalPage(0);
			searchResult.setCurrentPage(page);
			searchResult.setItemList(null);
			return searchResult;
		}
		//不为空
		PageInfo<MyCollections> pageInfo = new PageInfo(collectionList);
		int total = (int) pageInfo.getTotal();
		searchResult.setTotalCount(total);
		int totalPage = total%rows==0?total/rows:(total/rows+1);
		searchResult.setTotalPage(totalPage);
		searchResult.setCurrentPage(page);
		searchResult.setItemList(collectionList);
		searchResult.setURL(CommonUtils.getURLByServletRequest(request));
		return searchResult;
	}

}
