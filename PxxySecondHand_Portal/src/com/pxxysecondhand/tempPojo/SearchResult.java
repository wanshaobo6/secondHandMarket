/**   
* @Title: SearchResult.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月22日 下午8:22:06 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.List;

/**
 * @author  
 *
 */
public class SearchResult<T> {
	
	//所有查询到的记录
	private List<T> itemList;
	
	//总记录数
	private long totalCount;
	
	//当前页码
	private int currentPage;
	
	//总页数
	private int totalPage;
	
	//查询的地址
	private String URL;
	
	//排序方式
	private Integer orderCondition;

	@Override
	public String toString() {
		return "SearchResult [itemList=" + itemList + ", totalCount=" + totalCount + ", currentPage=" + currentPage
				+ ", totalPage=" + totalPage + ", URL=" + URL + ", orderCondition=" + orderCondition + "]";
	}

	public List<T> getItemList() {
		return itemList;
	}

	public void setItemList(List<T> itemList) {
		this.itemList = itemList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Integer getOrderCondition() {
		return orderCondition;
	}

	public void setOrderCondition(Integer orderCondition) {
		this.orderCondition = orderCondition;
	}

	

}
