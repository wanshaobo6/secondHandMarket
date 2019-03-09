package com.pxxysecondhand.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ItemExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andItemtitleIsNull() {
            addCriterion("itemTitle is null");
            return (Criteria) this;
        }

        public Criteria andItemtitleIsNotNull() {
            addCriterion("itemTitle is not null");
            return (Criteria) this;
        }

        public Criteria andItemtitleEqualTo(String value) {
            addCriterion("itemTitle =", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotEqualTo(String value) {
            addCriterion("itemTitle <>", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleGreaterThan(String value) {
            addCriterion("itemTitle >", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleGreaterThanOrEqualTo(String value) {
            addCriterion("itemTitle >=", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleLessThan(String value) {
            addCriterion("itemTitle <", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleLessThanOrEqualTo(String value) {
            addCriterion("itemTitle <=", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleLike(String value) {
            addCriterion("itemTitle like", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotLike(String value) {
            addCriterion("itemTitle not like", value, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleIn(List<String> values) {
            addCriterion("itemTitle in", values, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotIn(List<String> values) {
            addCriterion("itemTitle not in", values, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleBetween(String value1, String value2) {
            addCriterion("itemTitle between", value1, value2, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andItemtitleNotBetween(String value1, String value2) {
            addCriterion("itemTitle not between", value1, value2, "itemtitle");
            return (Criteria) this;
        }

        public Criteria andCurrpriceIsNull() {
            addCriterion("currPrice is null");
            return (Criteria) this;
        }

        public Criteria andCurrpriceIsNotNull() {
            addCriterion("currPrice is not null");
            return (Criteria) this;
        }

        public Criteria andCurrpriceEqualTo(Integer value) {
            addCriterion("currPrice =", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceNotEqualTo(Integer value) {
            addCriterion("currPrice <>", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceGreaterThan(Integer value) {
            addCriterion("currPrice >", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("currPrice >=", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceLessThan(Integer value) {
            addCriterion("currPrice <", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceLessThanOrEqualTo(Integer value) {
            addCriterion("currPrice <=", value, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceIn(List<Integer> values) {
            addCriterion("currPrice in", values, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceNotIn(List<Integer> values) {
            addCriterion("currPrice not in", values, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceBetween(Integer value1, Integer value2) {
            addCriterion("currPrice between", value1, value2, "currprice");
            return (Criteria) this;
        }

        public Criteria andCurrpriceNotBetween(Integer value1, Integer value2) {
            addCriterion("currPrice not between", value1, value2, "currprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceIsNull() {
            addCriterion("formerPrice is null");
            return (Criteria) this;
        }

        public Criteria andFormerpriceIsNotNull() {
            addCriterion("formerPrice is not null");
            return (Criteria) this;
        }

        public Criteria andFormerpriceEqualTo(Integer value) {
            addCriterion("formerPrice =", value, "formerprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceNotEqualTo(Integer value) {
            addCriterion("formerPrice <>", value, "formerprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceGreaterThan(Integer value) {
            addCriterion("formerPrice >", value, "formerprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("formerPrice >=", value, "formerprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceLessThan(Integer value) {
            addCriterion("formerPrice <", value, "formerprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceLessThanOrEqualTo(Integer value) {
            addCriterion("formerPrice <=", value, "formerprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceIn(List<Integer> values) {
            addCriterion("formerPrice in", values, "formerprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceNotIn(List<Integer> values) {
            addCriterion("formerPrice not in", values, "formerprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceBetween(Integer value1, Integer value2) {
            addCriterion("formerPrice between", value1, value2, "formerprice");
            return (Criteria) this;
        }

        public Criteria andFormerpriceNotBetween(Integer value1, Integer value2) {
            addCriterion("formerPrice not between", value1, value2, "formerprice");
            return (Criteria) this;
        }

        public Criteria andItemimagesIsNull() {
            addCriterion("itemImages is null");
            return (Criteria) this;
        }

        public Criteria andItemimagesIsNotNull() {
            addCriterion("itemImages is not null");
            return (Criteria) this;
        }

        public Criteria andItemimagesEqualTo(String value) {
            addCriterion("itemImages =", value, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesNotEqualTo(String value) {
            addCriterion("itemImages <>", value, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesGreaterThan(String value) {
            addCriterion("itemImages >", value, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesGreaterThanOrEqualTo(String value) {
            addCriterion("itemImages >=", value, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesLessThan(String value) {
            addCriterion("itemImages <", value, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesLessThanOrEqualTo(String value) {
            addCriterion("itemImages <=", value, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesLike(String value) {
            addCriterion("itemImages like", value, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesNotLike(String value) {
            addCriterion("itemImages not like", value, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesIn(List<String> values) {
            addCriterion("itemImages in", values, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesNotIn(List<String> values) {
            addCriterion("itemImages not in", values, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesBetween(String value1, String value2) {
            addCriterion("itemImages between", value1, value2, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemimagesNotBetween(String value1, String value2) {
            addCriterion("itemImages not between", value1, value2, "itemimages");
            return (Criteria) this;
        }

        public Criteria andItemdescIsNull() {
            addCriterion("itemDesc is null");
            return (Criteria) this;
        }

        public Criteria andItemdescIsNotNull() {
            addCriterion("itemDesc is not null");
            return (Criteria) this;
        }

        public Criteria andItemdescEqualTo(String value) {
            addCriterion("itemDesc =", value, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescNotEqualTo(String value) {
            addCriterion("itemDesc <>", value, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescGreaterThan(String value) {
            addCriterion("itemDesc >", value, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescGreaterThanOrEqualTo(String value) {
            addCriterion("itemDesc >=", value, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescLessThan(String value) {
            addCriterion("itemDesc <", value, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescLessThanOrEqualTo(String value) {
            addCriterion("itemDesc <=", value, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescLike(String value) {
            addCriterion("itemDesc like", value, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescNotLike(String value) {
            addCriterion("itemDesc not like", value, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescIn(List<String> values) {
            addCriterion("itemDesc in", values, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescNotIn(List<String> values) {
            addCriterion("itemDesc not in", values, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescBetween(String value1, String value2) {
            addCriterion("itemDesc between", value1, value2, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andItemdescNotBetween(String value1, String value2) {
            addCriterion("itemDesc not between", value1, value2, "itemdesc");
            return (Criteria) this;
        }

        public Criteria andIconditionIsNull() {
            addCriterion("icondition is null");
            return (Criteria) this;
        }

        public Criteria andIconditionIsNotNull() {
            addCriterion("icondition is not null");
            return (Criteria) this;
        }

        public Criteria andIconditionEqualTo(Integer value) {
            addCriterion("icondition =", value, "icondition");
            return (Criteria) this;
        }

        public Criteria andIconditionNotEqualTo(Integer value) {
            addCriterion("icondition <>", value, "icondition");
            return (Criteria) this;
        }

        public Criteria andIconditionGreaterThan(Integer value) {
            addCriterion("icondition >", value, "icondition");
            return (Criteria) this;
        }

        public Criteria andIconditionGreaterThanOrEqualTo(Integer value) {
            addCriterion("icondition >=", value, "icondition");
            return (Criteria) this;
        }

        public Criteria andIconditionLessThan(Integer value) {
            addCriterion("icondition <", value, "icondition");
            return (Criteria) this;
        }

        public Criteria andIconditionLessThanOrEqualTo(Integer value) {
            addCriterion("icondition <=", value, "icondition");
            return (Criteria) this;
        }

        public Criteria andIconditionIn(List<Integer> values) {
            addCriterion("icondition in", values, "icondition");
            return (Criteria) this;
        }

        public Criteria andIconditionNotIn(List<Integer> values) {
            addCriterion("icondition not in", values, "icondition");
            return (Criteria) this;
        }

        public Criteria andIconditionBetween(Integer value1, Integer value2) {
            addCriterion("icondition between", value1, value2, "icondition");
            return (Criteria) this;
        }

        public Criteria andIconditionNotBetween(Integer value1, Integer value2) {
            addCriterion("icondition not between", value1, value2, "icondition");
            return (Criteria) this;
        }

        public Criteria andIsintradeIsNull() {
            addCriterion("isInTrade is null");
            return (Criteria) this;
        }

        public Criteria andIsintradeIsNotNull() {
            addCriterion("isInTrade is not null");
            return (Criteria) this;
        }

        public Criteria andIsintradeEqualTo(Integer value) {
            addCriterion("isInTrade =", value, "isintrade");
            return (Criteria) this;
        }

        public Criteria andIsintradeNotEqualTo(Integer value) {
            addCriterion("isInTrade <>", value, "isintrade");
            return (Criteria) this;
        }

        public Criteria andIsintradeGreaterThan(Integer value) {
            addCriterion("isInTrade >", value, "isintrade");
            return (Criteria) this;
        }

        public Criteria andIsintradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("isInTrade >=", value, "isintrade");
            return (Criteria) this;
        }

        public Criteria andIsintradeLessThan(Integer value) {
            addCriterion("isInTrade <", value, "isintrade");
            return (Criteria) this;
        }

        public Criteria andIsintradeLessThanOrEqualTo(Integer value) {
            addCriterion("isInTrade <=", value, "isintrade");
            return (Criteria) this;
        }

        public Criteria andIsintradeIn(List<Integer> values) {
            addCriterion("isInTrade in", values, "isintrade");
            return (Criteria) this;
        }

        public Criteria andIsintradeNotIn(List<Integer> values) {
            addCriterion("isInTrade not in", values, "isintrade");
            return (Criteria) this;
        }

        public Criteria andIsintradeBetween(Integer value1, Integer value2) {
            addCriterion("isInTrade between", value1, value2, "isintrade");
            return (Criteria) this;
        }

        public Criteria andIsintradeNotBetween(Integer value1, Integer value2) {
            addCriterion("isInTrade not between", value1, value2, "isintrade");
            return (Criteria) this;
        }

        public Criteria andOrdernumIsNull() {
            addCriterion("orderNum is null");
            return (Criteria) this;
        }

        public Criteria andOrdernumIsNotNull() {
            addCriterion("orderNum is not null");
            return (Criteria) this;
        }

        public Criteria andOrdernumEqualTo(Integer value) {
            addCriterion("orderNum =", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotEqualTo(Integer value) {
            addCriterion("orderNum <>", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumGreaterThan(Integer value) {
            addCriterion("orderNum >", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumGreaterThanOrEqualTo(Integer value) {
            addCriterion("orderNum >=", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumLessThan(Integer value) {
            addCriterion("orderNum <", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumLessThanOrEqualTo(Integer value) {
            addCriterion("orderNum <=", value, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumIn(List<Integer> values) {
            addCriterion("orderNum in", values, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotIn(List<Integer> values) {
            addCriterion("orderNum not in", values, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumBetween(Integer value1, Integer value2) {
            addCriterion("orderNum between", value1, value2, "ordernum");
            return (Criteria) this;
        }

        public Criteria andOrdernumNotBetween(Integer value1, Integer value2) {
            addCriterion("orderNum not between", value1, value2, "ordernum");
            return (Criteria) this;
        }

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userId like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userId not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andCategoryidIsNull() {
            addCriterion("categoryId is null");
            return (Criteria) this;
        }

        public Criteria andCategoryidIsNotNull() {
            addCriterion("categoryId is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryidEqualTo(String value) {
            addCriterion("categoryId =", value, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidNotEqualTo(String value) {
            addCriterion("categoryId <>", value, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidGreaterThan(String value) {
            addCriterion("categoryId >", value, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidGreaterThanOrEqualTo(String value) {
            addCriterion("categoryId >=", value, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidLessThan(String value) {
            addCriterion("categoryId <", value, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidLessThanOrEqualTo(String value) {
            addCriterion("categoryId <=", value, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidLike(String value) {
            addCriterion("categoryId like", value, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidNotLike(String value) {
            addCriterion("categoryId not like", value, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidIn(List<String> values) {
            addCriterion("categoryId in", values, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidNotIn(List<String> values) {
            addCriterion("categoryId not in", values, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidBetween(String value1, String value2) {
            addCriterion("categoryId between", value1, value2, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCategoryidNotBetween(String value1, String value2) {
            addCriterion("categoryId not between", value1, value2, "categoryid");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Date value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Date value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Date value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Date value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Date value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Date value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Date> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Date> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Date value1, Date value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Date value1, Date value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNull() {
            addCriterion("updated is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedIsNotNull() {
            addCriterion("updated is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedEqualTo(Date value) {
            addCriterion("updated =", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotEqualTo(Date value) {
            addCriterion("updated <>", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThan(Date value) {
            addCriterion("updated >", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedGreaterThanOrEqualTo(Date value) {
            addCriterion("updated >=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThan(Date value) {
            addCriterion("updated <", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedLessThanOrEqualTo(Date value) {
            addCriterion("updated <=", value, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedIn(List<Date> values) {
            addCriterion("updated in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotIn(List<Date> values) {
            addCriterion("updated not in", values, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedBetween(Date value1, Date value2) {
            addCriterion("updated between", value1, value2, "updated");
            return (Criteria) this;
        }

        public Criteria andUpdatedNotBetween(Date value1, Date value2) {
            addCriterion("updated not between", value1, value2, "updated");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}