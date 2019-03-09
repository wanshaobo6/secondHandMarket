/**   
* @Title: EvaluateModel.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月6日 下午5:57:27 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

/**
 * @author  
 * 商家评估模型
 */
public class EvaluateModel {
	private String buyerId;
	
	private Integer tradeEvaluateLevel;
	
	private String ownerId;

	@Override
	public String toString() {
		return "EvaluateModel [buyerId=" + buyerId + ", tradeEvaluateLevel=" + tradeEvaluateLevel + ", ownerId="
				+ ownerId + "]";
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getTradeEvaluateLevel() {
		return tradeEvaluateLevel;
	}

	public void setTradeEvaluateLevel(Integer tradeEvaluateLevel) {
		this.tradeEvaluateLevel = tradeEvaluateLevel;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	

	
}
