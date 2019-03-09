/**   
* @Title: ItemDescResult.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月25日 下午6:35:01 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author  
 *  宝贝详情所需要的全部数据
 */
public class ItemDescResult {
	//商品部分
	private String id;
	
	private String itemtitle;
	
	 private Integer currprice;
	 
	 private Integer formerprice;
	
	 private String itemimages;

	 private String itemdesc;
	 
	 private Integer icondition;
	 
	 private Integer isInTrade;
	 
	 @JsonIgnore
	 private Date updated;
	 //--------------------------------
	 private String time;
	 //卖家部分
	 @JsonIgnore
	private String userId;
	 
    private String userName;
    
    @JsonIgnore
    private Integer credit;
    
    private Integer creditLevel;
    //买家部分
	 //--------------------------------
    private Integer isCollected;
    //收藏数
    private Integer collectionNum;
	@Override
	public String toString() {
		return "ItemDescResult [id=" + id + ", itemtitle=" + itemtitle + ", currprice=" + currprice + ", formerprice="
				+ formerprice + ", itemimages=" + itemimages + ", itemdesc=" + itemdesc + ", icondition=" + icondition
				+ ", isInTrade=" + isInTrade + ", updated=" + updated + ", time=" + time + ", userId=" + userId
				+ ", userName=" + userName + ", credit=" + credit + ", creditLevel=" + creditLevel + ", isCollected="
				+ isCollected + ", collectionNum=" + collectionNum + "]";
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
	public Integer getCurrprice() {
		return currprice;
	}
	public void setCurrprice(Integer currprice) {
		this.currprice = currprice;
	}
	public Integer getFormerprice() {
		return formerprice;
	}
	public void setFormerprice(Integer formerprice) {
		this.formerprice = formerprice;
	}
	public String getItemimages() {
		return itemimages;
	}
	public void setItemimages(String itemimages) {
		this.itemimages = itemimages;
	}
	public String getItemdesc() {
		return itemdesc;
	}
	public void setItemdesc(String itemdesc) {
		this.itemdesc = itemdesc;
	}
	public Integer getIcondition() {
		return icondition;
	}
	public void setIcondition(Integer icondition) {
		this.icondition = icondition;
	}
	public Integer getIsInTrade() {
		return isInTrade;
	}
	public void setIsInTrade(Integer isInTrade) {
		this.isInTrade = isInTrade;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public Integer getCreditLevel() {
		return creditLevel;
	}
	public void setCreditLevel(Integer creditLevel) {
		this.creditLevel = creditLevel;
	}
	public Integer getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(Integer isCollected) {
		this.isCollected = isCollected;
	}
	public Integer getCollectionNum() {
		return collectionNum;
	}
	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}
    
}
