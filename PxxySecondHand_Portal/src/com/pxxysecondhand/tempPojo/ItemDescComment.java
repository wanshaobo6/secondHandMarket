/**   
* @Title: ItemDescComment.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(��һ�仰�������ļ���ʲô) 
* @author  

* @date 2018��11��30�� ����4:25:35 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;
import java.util.List;

/**
 * @author  
 *  ItemDesc�жԱ������۵����νṹ����
 */
public class ItemDescComment {
	
    private String id;
    
    private String itemid;
	
	private List<ItemDescComment> itemDescComments;
	
    private String fromuserid;

    private String fromusername;

    private String touserid;

    private String tousername;

    private String content;

    private Date createtime;
    
    //���ڱ�������
    private String commentTime;

	@Override
	public String toString() {
		return "ItemDescComment [id=" + id + ", itemid=" + itemid + ", itemDescComments=" + itemDescComments
				+ ", fromuserid=" + fromuserid + ", fromusername=" + fromusername + ", touserid=" + touserid
				+ ", tousername=" + tousername + ", content=" + content + ", createtime=" + createtime
				+ ", commentTime=" + commentTime + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public List<ItemDescComment> getItemDescComments() {
		return itemDescComments;
	}

	public void setItemDescComments(List<ItemDescComment> itemDescComments) {
		this.itemDescComments = itemDescComments;
	}

	public String getFromuserid() {
		return fromuserid;
	}

	public void setFromuserid(String fromuserid) {
		this.fromuserid = fromuserid;
	}

	public String getFromusername() {
		return fromusername;
	}

	public void setFromusername(String fromusername) {
		this.fromusername = fromusername;
	}

	public String getTouserid() {
		return touserid;
	}

	public void setTouserid(String touserid) {
		this.touserid = touserid;
	}

	public String getTousername() {
		return tousername;
	}

	public void setTousername(String tousername) {
		this.tousername = tousername;
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

	public String getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}

    
}
