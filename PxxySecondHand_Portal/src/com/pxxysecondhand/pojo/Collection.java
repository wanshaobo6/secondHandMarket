package com.pxxysecondhand.pojo;

import java.util.Date;

import org.springframework.core.Ordered;

public class Collection {
    private String id;

    private String collectorid;

    private String itemid;

    private Date created;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCollectorid() {
        return collectorid;
    }

    public void setCollectorid(String collectorid) {
        this.collectorid = collectorid == null ? null : collectorid.trim();
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid == null ? null : itemid.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}