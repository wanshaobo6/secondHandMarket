/**   
* @Title: ISearchService.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月22日 下午7:44:08 
* @version V1.0   
*/
package com.pxxysecondhand.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.pxxysecondhand.tempPojo.GuessItem;
import com.pxxysecondhand.tempPojo.SearchItem;
import com.pxxysecondhand.tempPojo.SearchResult;

/**
 * @author  
 *
 */
public interface ISearchService {
	
	//根据关键词查询
	public SearchResult<SearchItem> searchByKeywords(String keywords ,int orderCondition , int page , int rows);
	
	//这个orderNum根据用户综合评价计算得出  发布时给定
	public List<SearchItem> searchByKeywordsOrderByorderNum(String keywords , int page , int rows);
	
	public List<SearchItem> searchByKeywordsOrderByPolishTime(String keywords  , int page , int rows);
	
	public List<SearchItem> searchByKeywordsOrderByCollectionNum(String keywords , int page , int rows);
	
	public List<SearchItem> searchByKeywordsOrderByPriceDesc(String keywords  , int page , int rows);
	
	public List<SearchItem> searchByKeywordsOrderByPriceAesc(String keywords  , int page , int rows);
	
	public List<SearchItem> searchByKeywordsOrderByAuthorCredit(String keywords  , int page , int rows);

	
	public List<GuessItem> searchByUserHabit(HttpServletRequest request,HttpServletResponse response);

	public void createSearchRecord(String keywords, HttpServletRequest request, HttpServletResponse response);

	
	public List<String> getPopulateSearchKeys();
}
