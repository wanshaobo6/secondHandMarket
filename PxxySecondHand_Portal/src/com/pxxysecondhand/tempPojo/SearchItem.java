/**   
* @Title: searchItem.java 
* @Package com.pxxysecondhand.tempPojo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author  

* @date 2018年11月22日 下午7:29:54 
* @version V1.0   
*/
package com.pxxysecondhand.tempPojo;

import java.util.Date;

/**
 * @author  
 *
 *  搜索页面所需要的数据
 */
public class SearchItem {
	
    private String id;//

    private String itemtitle;//--

    private Integer currprice;//
     
    private String itemimages;//--
    
    private Integer collectionNum;//
    
    private String userName;//
    
    private Integer credit;//
    
    private Integer scanNum;//...
    
    private Date updated;//
    
    private String polishTimeImprecise;//...

	@Override
	public String toString() {
		return "SearchItem [id=" + id + ", itemtitle=" + itemtitle + ", currprice=" + currprice + ", itemimages="
				+ itemimages + ", collectionNum=" + collectionNum + ", userName=" + userName + ", credit=" + credit
				+ ", scanNum=" + scanNum + ", updated=" + updated + ", polishTimeImprecise=" + polishTimeImprecise
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemtitle() {
		return itemtitle;
	}

	public void setItemtitle(String itemtitle) {
		this.itemtitle = itemtitle;
	}

	public Integer getCurrprice() {
		return currprice;
	}

	public void setCurrprice(Integer currprice) {
		this.currprice = currprice;
	}

	public String getItemimages() {
		return itemimages;
	}

	public void setItemimages(String itemimages) {
		this.itemimages = itemimages;
	}

	public Integer getCollectionNum() {
		return collectionNum;
	}

	public void setCollectionNum(Integer collectionNum) {
		this.collectionNum = collectionNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Integer getScanNum() {
		return scanNum;
	}

	public void setScanNum(Integer scanNum) {
		this.scanNum = scanNum;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getPolishTimeImprecise() {
		return polishTimeImprecise;
	}

	public void setPolishTimeImprecise(String polishTimeImprecise) {
		this.polishTimeImprecise = polishTimeImprecise;
	}
    
    
}
