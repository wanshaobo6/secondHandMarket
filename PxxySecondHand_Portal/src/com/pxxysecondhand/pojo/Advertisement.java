package com.pxxysecondhand.pojo;

import java.util.Date;

public class Advertisement {
    private Integer aid;

    private Integer adorder;

    private String adname;

    private String addesc;

    private String adimage;

    private String adaddress;

    private Integer lastoperator;

    private Date created;

    private Date updated;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getAdorder() {
        return adorder;
    }

    public void setAdorder(Integer adorder) {
        this.adorder = adorder;
    }

    public String getAdname() {
        return adname;
    }

    public void setAdname(String adname) {
        this.adname = adname == null ? null : adname.trim();
    }

    public String getAddesc() {
        return addesc;
    }

    public void setAddesc(String addesc) {
        this.addesc = addesc == null ? null : addesc.trim();
    }

    public String getAdimage() {
        return adimage;
    }

    public void setAdimage(String adimage) {
        this.adimage = adimage == null ? null : adimage.trim();
    }

    public String getAdaddress() {
        return adaddress;
    }

    public void setAdaddress(String adaddress) {
        this.adaddress = adaddress == null ? null : adaddress.trim();
    }

    public Integer getLastoperator() {
        return lastoperator;
    }

    public void setLastoperator(Integer lastoperator) {
        this.lastoperator = lastoperator;
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