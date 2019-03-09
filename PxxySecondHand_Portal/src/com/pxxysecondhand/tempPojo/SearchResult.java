/**   
* @Title: SearchResult.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��22�� ����8:22:06 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.List;

/**
 * @author  
 *
 */
public class SearchResult<T> {
	
	//���в�ѯ���ļ�¼
	private List<T> itemList;
	
	//�ܼ�¼��
	private long totalCount;
	
	//��ǰҳ��
	private int currentPage;
	
	//��ҳ��
	private int totalPage;
	
	//��ѯ�ĵ�ַ
	private String URL;
	
	//����ʽ
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
