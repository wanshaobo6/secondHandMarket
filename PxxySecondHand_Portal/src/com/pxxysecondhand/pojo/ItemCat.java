package com.pxxysecondhand.pojo;

import java.util.Date;
import java.util.List;

public class ItemCat {
    private String id;

    private String categoryname;

    private String parentid;

    private Boolean isparent;

    private Date created;
    
    //ÁÙÊ±Êı¾İ
    private List<ItemCat> itemCats;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname == null ? null : categoryname.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public Boolean getIsparent() {
        return isparent;
    }

    public void setIsparent(Boolean isparent) {
        this.isparent = isparent;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

	public List<ItemCat> getItemCat() {
		return itemCats;
	}

	public void setItemCat(List<ItemCat> itemCats) {
		this.itemCats = itemCats;
	}

	@Override
	public String toString() {
		return "ItemCat [id=" + id + ", categoryname=" + categoryname + ", parentid=" + parentid + ", isparent="
				+ isparent + ", created=" + created + ", itemCats=" + itemCats + "]";
	}
	
	
    
}