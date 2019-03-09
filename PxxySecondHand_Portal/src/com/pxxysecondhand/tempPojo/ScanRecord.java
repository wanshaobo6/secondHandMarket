/**   
* @Title: ScanRecord.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月26日 下午8:05:03 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;

/**
 * @author  
 *  用户的浏览记录
 */
public class ScanRecord {
	
	private String itemId;
	
	public ScanRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	private Date date;
	@Override
	public String toString() {
		return "ScanRecord [itemId=" + itemId + ", date=" + date + "]";
	}
	public String getItemId() {
		return itemId;
	}
	public ScanRecord(String itemId, Date date) {
		super();
		this.itemId = itemId;
		this.date = date;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
