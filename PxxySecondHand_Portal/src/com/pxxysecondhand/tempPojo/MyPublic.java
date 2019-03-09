/**   
* @Title: MyPublic.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月28日 下午8:07:46 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;

/**
 * @author  
 * 我的闲置条目
 */
public class MyPublic {
	
    //isInTrade==0时候的现实的数据
   private String id ;  
   
   private String itemtitle; 
   
   private String itemimage; 
   
   private Integer isintrade;  
   
   private Integer currprice;  
   
   private Date itemCreateTime;  
   
   private Date itemLastModefyTime; 
   
   private Integer CollectionNum; 
   
   private String  categoryName; 
   
   private Integer allMessageNums;
   
   private Integer MessagesNotReadNums;
	
	@Override
	public String toString() {
		return "MyPublic [id=" + id + ", itemtitle=" + itemtitle + ", itemimage=" + itemimage + ", isintrade=" + isintrade
				+ ", currprice=" + currprice + ", itemCreateTime=" + itemCreateTime + ", itemLastModefyTime="
				+ itemLastModefyTime + ", CollectionNum=" + CollectionNum + ", categoryName=" + categoryName
				+ ", allMessageNums=" + allMessageNums + ", MessagesNotReadNums=" + MessagesNotReadNums + "]";
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getItemtitle() {
		return itemtitle;
	}
	
	public void setItemtitle(String itemtitle) {
		this.itemtitle = itemtitle;
	}
	
	public String getItemimage() {
		return itemimage;
	}
	
	public void setItemimage(String itemimage) {
		this.itemimage = itemimage;
	}
	
	public Integer getIsintrade() {
		return isintrade;
	}
	
	public void setIsintrade(Integer isintrade) {
		this.isintrade = isintrade;
	}
	
	public Integer getCurrprice() {
		return currprice;
	}
	
	public void setCurrprice(Integer currprice) {
		this.currprice = currprice;
	}
	
	public Date getItemCreateTime() {
		return itemCreateTime;
	}
	
	public void setItemCreateTime(Date itemCreateTime) {
		this.itemCreateTime = itemCreateTime;
	}
	
	public Date getItemLastModefyTime() {
		return itemLastModefyTime;
	}
	
	public void setItemLastModefyTime(Date itemLastModefyTime) {
		this.itemLastModefyTime = itemLastModefyTime;
	}
	
	public Integer getCollectionNum() {
		return CollectionNum;
	}
	
	public void setCollectionNum(Integer collectionNum) {
		CollectionNum = collectionNum;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public Integer getAllMessageNums() {
		return allMessageNums;
	}
	
	public void setAllMessageNums(Integer allMessageNums) {
		this.allMessageNums = allMessageNums;
	}
	
	public Integer getMessagesNotReadNums() {
		return MessagesNotReadNums;
	}
	
	public void setMessagesNotReadNums(Integer messagesNotReadNums) {
		MessagesNotReadNums = messagesNotReadNums;
	}
	
	   
	
}
