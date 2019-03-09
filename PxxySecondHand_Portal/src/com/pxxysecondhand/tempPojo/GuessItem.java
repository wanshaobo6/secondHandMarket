/**   
* @Title: GuessItem.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年12月31日 上午10:45:23 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

/**
 * @author  
 *
 */
public class GuessItem {
	private String itemId;
	private String image;
	private String title;
	@Override
	public String toString() {
		return "GuessItem [itemId=" + itemId + ", image=" + image + ", title=" + title + "]";
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
