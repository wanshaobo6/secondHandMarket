/**   
* @Title: MyPurchases.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月5日 下午7:56:25 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;

/**
 * @author  
 *
 */
public class MyPurchase {
	//交易表中
	private String id;
	
	private Integer tradeStatus;
	
	private Integer tradeEvaluateLevel;
	
	private Date tradeCEvaluateTime;
	
	private String tradeEvaluate;
	
	private Date tradeComplishTime;
	
	private Date tradeCancelTime;
	
	private Date tradeCreatedTime;
	//商品表中
	private String itemId;
	
	private String itemTitle;
	
	private String image;
	
	private Integer currprice;
	
	//用户表
	private String userId;
	
	private String ownerName;
	
	private String phonenumber;
	
	//添加属性
	 private String timeLeftMessage;

	@Override
	public String toString() {
		return "MyPurchase [id=" + id + ", tradeStatus=" + tradeStatus + ", tradeEvaluateLevel=" + tradeEvaluateLevel
				+ ", tradeCEvaluateTime=" + tradeCEvaluateTime + ", tradeEvaluate=" + tradeEvaluate
				+ ", tradeComplishTime=" + tradeComplishTime + ", tradeCancelTime=" + tradeCancelTime
				+ ", tradeCreatedTime=" + tradeCreatedTime + ", itemId=" + itemId + ", itemTitle=" + itemTitle
				+ ", image=" + image + ", currprice=" + currprice + ", userId=" + userId + ", ownerName=" + ownerName
				+ ", phonenumber=" + phonenumber + ", timeLeftMessage=" + timeLeftMessage + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(Integer tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Integer getTradeEvaluateLevel() {
		return tradeEvaluateLevel;
	}

	public void setTradeEvaluateLevel(Integer tradeEvaluateLevel) {
		this.tradeEvaluateLevel = tradeEvaluateLevel;
	}

	public Date getTradeCEvaluateTime() {
		return tradeCEvaluateTime;
	}

	public void setTradeCEvaluateTime(Date tradeCEvaluateTime) {
		this.tradeCEvaluateTime = tradeCEvaluateTime;
	}

	public String getTradeEvaluate() {
		return tradeEvaluate;
	}

	public void setTradeEvaluate(String tradeEvaluate) {
		this.tradeEvaluate = tradeEvaluate;
	}

	public Date getTradeComplishTime() {
		return tradeComplishTime;
	}

	public void setTradeComplishTime(Date tradeComplishTime) {
		this.tradeComplishTime = tradeComplishTime;
	}

	public Date getTradeCancelTime() {
		return tradeCancelTime;
	}

	public void setTradeCancelTime(Date tradeCancelTime) {
		this.tradeCancelTime = tradeCancelTime;
	}

	public Date getTradeCreatedTime() {
		return tradeCreatedTime;
	}

	public void setTradeCreatedTime(Date tradeCreatedTime) {
		this.tradeCreatedTime = tradeCreatedTime;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getCurrprice() {
		return currprice;
	}

	public void setCurrprice(Integer currprice) {
		this.currprice = currprice;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getTimeLeftMessage() {
		return timeLeftMessage;
	}

	public void setTimeLeftMessage(String timeLeftMessage) {
		this.timeLeftMessage = timeLeftMessage;
	}
	 
	 
	
}
