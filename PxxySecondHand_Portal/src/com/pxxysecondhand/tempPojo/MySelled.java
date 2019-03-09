/**   
* @Title: MySeller.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月17日 下午10:19:30 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;

/**
 * @author  
 *    我卖出的 
 *
 */
public class MySelled {
	
	   private String itemId ;  
	   
	   private String itemtitle; 
	   
	   private String itemimage; 
	   
	   private Integer currprice;  
	   
	   private String buyerId;
	   
	   private String buyerName; 
	   
	   private String phoneNumber;
	   
	   private String tradeId; 
	   
	   private String timeLeftMessage; 
	   
	   private Integer tradeStatus; 
		
		private Integer tradeEvaluateLevel; 
		
		private Date tradeCEvaluateTime; 
		
		private Date tradeComplishTime; 
		
		private Date tradeCancelTime; 
		
		private Date tradeCreatedTime;

		@Override
		public String toString() {
			return "MySelled [itemId=" + itemId + ", itemtitle=" + itemtitle + ", itemimage=" + itemimage
					+ ", currprice=" + currprice + ", buyerId=" + buyerId + ", buyerName=" + buyerName
					+ ", phoneNumber=" + phoneNumber + ", tradeId=" + tradeId + ", timeLeftMessage=" + timeLeftMessage
					+ ", tradeStatus=" + tradeStatus + ", tradeEvaluateLevel=" + tradeEvaluateLevel
					+ ", tradeCEvaluateTime=" + tradeCEvaluateTime + ", tradeComplishTime=" + tradeComplishTime
					+ ", tradeCancelTime=" + tradeCancelTime + ", tradeCreatedTime=" + tradeCreatedTime + "]";
		}

		public String getItemId() {
			return itemId;
		}

		public void setItemId(String itemId) {
			this.itemId = itemId;
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

		public Integer getCurrprice() {
			return currprice;
		}

		public void setCurrprice(Integer currprice) {
			this.currprice = currprice;
		}

		public String getBuyerId() {
			return buyerId;
		}

		public void setBuyerId(String buyerId) {
			this.buyerId = buyerId;
		}

		public String getBuyerName() {
			return buyerName;
		}

		public void setBuyerName(String buyerName) {
			this.buyerName = buyerName;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getTradeId() {
			return tradeId;
		}

		public void setTradeId(String tradeId) {
			this.tradeId = tradeId;
		}

		public String getTimeLeftMessage() {
			return timeLeftMessage;
		}

		public void setTimeLeftMessage(String timeLeftMessage) {
			this.timeLeftMessage = timeLeftMessage;
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
		
		
}
