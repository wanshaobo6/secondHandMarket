package com.pxxysecondhand.pojo;

import java.util.Date;

public class Item {
    private String id;

    private String itemtitle;

    private Integer currprice;

    private Integer formerprice;

    private String itemimages;

    private String itemdesc;

    private Integer icondition;

    private Integer isintrade;

    private Integer ordernum;

    private String userid;

    private String categoryid;

    private Date created;

    private Date updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getItemtitle() {
        return itemtitle;
    }

    public void setItemtitle(String itemtitle) {
        this.itemtitle = itemtitle == null ? null : itemtitle.trim();
    }

    public Integer getCurrprice() {
        return currprice;
    }

    public void setCurrprice(Integer currprice) {
        this.currprice = currprice;
    }

    public Integer getFormerprice() {
        return formerprice;
    }

    public void setFormerprice(Integer formerprice) {
        this.formerprice = formerprice;
    }

    public String getItemimages() {
        return itemimages;
    }

    public void setItemimages(String itemimages) {
        this.itemimages = itemimages == null ? null : itemimages.trim();
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc == null ? null : itemdesc.trim();
    }

    public Integer getIcondition() {
        return icondition;
    }

    public void setIcondition(Integer icondition) {
        this.icondition = icondition;
    }

    public Integer getIsintrade() {
        return isintrade;
    }

    public void setIsintrade(Integer isintrade) {
        this.isintrade = isintrade;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid == null ? null : categoryid.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}