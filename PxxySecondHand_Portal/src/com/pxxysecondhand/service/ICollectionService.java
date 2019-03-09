/**   
* @Title: ICollectionService.java 
* @Package com.pxxysecondhand.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月2日 下午5:09:45 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import javax.servlet.http.HttpServletRequest;

import com.pxxysecondhand.pojo.User;
import com.pxxysecondhand.tempPojo.MyCollections;
import com.pxxysecondhand.tempPojo.SearchResult;
import com.pxxysecondhand.utils.CommonResult;

/**
 * @author  
 *
 */
public interface ICollectionService {
	
	//该表我的收藏如果收藏了就取消没收藏收藏
	public CommonResult changeMyCollection(User user , String itemId);
	
	//查询是否收藏了这件宝贝
	public boolean checkCollected(String collectorId,String itemId);
	
	//获得所有的收藏列表
	public SearchResult<MyCollections> getAllOfMyCollections(HttpServletRequest request,String collectorId,int page,int rows);
}
