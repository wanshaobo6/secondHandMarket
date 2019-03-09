/**   
* @Title: NewestPublic.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月16日 下午6:48:20 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

/**
 * @author  
 *  //首页的最新发布临时类
 */
public class NewestPublic {
	private String itemId;
	
	private String itemTilte;
	
	private String lastPublicTime;

	@Override
	public String toString() {
		return "NewestPublic [itemId=" + itemId + ", itemTilte=" + itemTilte + ", lastPublicTime=" + lastPublicTime
				+ "]";
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemTilte() {
		return itemTilte;
	}

	public void setItemTilte(String itemTilte) {
		this.itemTilte = itemTilte;
	}

	public String getLastPublicTime() {
		return lastPublicTime;
	}

	public void setLastPublicTime(String lastPublicTime) {
		this.lastPublicTime = lastPublicTime;
	}
	
	
}
