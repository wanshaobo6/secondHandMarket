/**   
* @Title: MyCollections.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月3日 下午9:41:05 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;

/**
 * @author  
 *
 */
public class MyCollections {
	private String itemId;
	
	private String itemtitle;
	
	private String image;
	
	private Integer currprice;
	
	private Integer isSoldOut;
	
	private Date collectionTime;

	@Override
	public String toString() {
		return "MyCollections [itemId=" + itemId + ", itemtitle=" + itemtitle + ", image=" + image + ", currprice="
				+ currprice + ", isSoldOut=" + isSoldOut + ", collectionTime=" + collectionTime + "]";
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

	public Integer getIsSoldOut() {
		return isSoldOut;
	}

	public void setIsSoldOut(Integer isSoldOut) {
		this.isSoldOut = isSoldOut;
	}

	public Date getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(Date collectionTime) {
		this.collectionTime = collectionTime;
	}

	

	
}
