/**   
* @Title: WebDynamicMessage.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��12��19�� ����7:36:36 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;

/**
 * @author  
 * ��վ��̬��Ϣ��ʱ��
 *
 */
public class WebDynamicMessage {
	
	private String id;
	
	private Integer toone;
	
	private Integer isRead;
	
	private String title;
	
	private String createTimeImPrecise;

	@Override
	public String toString() {
		return "WebDynamicMessage [id=" + id + ", toone=" + toone + ", isRead=" + isRead + ", title=" + title
				+ ", createTimeImPrecise=" + createTimeImPrecise + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getToone() {
		return toone;
	}

	public void setToone(Integer toone) {
		this.toone = toone;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTimeImPrecise() {
		return createTimeImPrecise;
	}

	public void setCreateTimeImPrecise(String createTimeImPrecise) {
		this.createTimeImPrecise = createTimeImPrecise;
	}
	
	
}
