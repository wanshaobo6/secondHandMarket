/**   
* @Title: TempMessage.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月20日 下午5:05:18 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;

/**
 * @author  
 *
 */
public class TempMessage {
	 private String id;

	 private Integer toone;
	 
	 private String title;
	 
	 private String content;
	 
	 private Date createtime;
	 
	 private String userId;
	 
	 private String userName;

	@Override
	public String toString() {
		return "TempMessage [id=" + id + ", toone=" + toone + ", title=" + title + ", content=" + content
				+ ", createtime=" + createtime + ", userId=" + userId + ", userName=" + userName + "]";
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	 
	 
}
