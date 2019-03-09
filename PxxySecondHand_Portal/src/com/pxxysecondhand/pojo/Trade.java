package com.pxxysecondhand.pojo;

import java.util.Date;

public class Trade {
    private String id;

    private String buyerid;

    private String itemownerid;

    private String tradeitemid;

    private String tradeitemtitle;

    private String tradeitemimage;

    private Integer tradecurrprice;

    private Integer tradetype;

    private Integer paymenttype;

    private Integer tradestatus;

    private Integer tradeevaluatelevel;

    private Integer buyerisdeleted;

    private Integer ownerisdeleted;

    private String tradeevaluate;

    private Date tradecreatedtime;

    private Date tradecomplishtime;

    private Date tradecevaluatetime;

    private Date tradecanceltime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(String buyerid) {
        this.buyerid = buyerid == null ? null : buyerid.trim();
    }

    public String getItemownerid() {
        return itemownerid;
    }

    public void setItemownerid(String itemownerid) {
        this.itemownerid = itemownerid == null ? null : itemownerid.trim();
    }

    public String getTradeitemid() {
        return tradeitemid;
    }

    public void setTradeitemid(String tradeitemid) {
        this.tradeitemid = tradeitemid == null ? null : tradeitemid.trim();
    }

    public String getTradeitemtitle() {
        return tradeitemtitle;
    }

    public void setTradeitemtitle(String tradeitemtitle) {
        this.tradeitemtitle = tradeitemtitle == null ? null : tradeitemtitle.trim();
    }

    public String getTradeitemimage() {
        return tradeitemimage;
    }

    public void setTradeitemimage(String tradeitemimage) {
        this.tradeitemimage = tradeitemimage == null ? null : tradeitemimage.trim();
    }

    public Integer getTradecurrprice() {
        return tradecurrprice;
    }

    public void setTradecurrprice(Integer tradecurrprice) {
        this.tradecurrprice = tradecurrprice;
    }

    public Integer getTradetype() {
        return tradetype;
    }

    public void setTradetype(Integer tradetype) {
        this.tradetype = tradetype;
    }

    public Integer getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(Integer paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Integer getTradestatus() {
        return tradestatus;
    }

    public void setTradestatus(Integer tradestatus) {
        this.tradestatus = tradestatus;
    }

    public Integer getTradeevaluatelevel() {
        return tradeevaluatelevel;
    }

    public void setTradeevaluatelevel(Integer tradeevaluatelevel) {
        this.tradeevaluatelevel = tradeevaluatelevel;
    }

    public Integer getBuyerisdeleted() {
        return buyerisdeleted;
    }

    public void setBuyerisdeleted(Integer buyerisdeleted) {
        this.buyerisdeleted = buyerisdeleted;
    }

    public Integer getOwnerisdeleted() {
        return ownerisdeleted;
    }

    public void setOwnerisdeleted(Integer ownerisdeleted) {
        this.ownerisdeleted = ownerisdeleted;
    }

    public String getTradeevaluate() {
        return tradeevaluate;
    }

    public void setTradeevaluate(String tradeevaluate) {
        this.tradeevaluate = tradeevaluate == null ? null : tradeevaluate.trim();
    }

    public Date getTradecreatedtime() {
        return tradecreatedtime;
    }

    public void setTradecreatedtime(Date tradecreatedtime) {
        this.tradecreatedtime = tradecreatedtime;
    }

    public Date getTradecomplishtime() {
        return tradecomplishtime;
    }

    public void setTradecomplishtime(Date tradecomplishtime) {
        this.tradecomplishtime = tradecomplishtime;
    }

    public Date getTradecevaluatetime() {
        return tradecevaluatetime;
    }

    public void setTradecevaluatetime(Date tradecevaluatetime) {
        this.tradecevaluatetime = tradecevaluatetime;
    }

    public Date getTradecanceltime() {
        return tradecanceltime;
    }

    public void setTradecanceltime(Date tradecanceltime) {
        this.tradecanceltime = tradecanceltime;
    }
}