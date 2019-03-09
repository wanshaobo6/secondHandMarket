/**   
* @Title: ISearchService.java 
* @Package com.pxxysecondhand.controller 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��22�� ����7:44:08 
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
	
	//���ݹؼ��ʲ�ѯ
	public SearchResult<SearchItem> searchByKeywords(String keywords ,int orderCondition , int page , int rows);
	
	//���orderNum�����û��ۺ����ۼ���ó�  ����ʱ����
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
