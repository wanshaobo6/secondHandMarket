package com.pxxysecondhand.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TradeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TradeExample() {
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

        public Criteria andBuyeridIsNull() {
            addCriterion("buyerId is null");
            return (Criteria) this;
        }

        public Criteria andBuyeridIsNotNull() {
            addCriterion("buyerId is not null");
            return (Criteria) this;
        }

        public Criteria andBuyeridEqualTo(String value) {
            addCriterion("buyerId =", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotEqualTo(String value) {
            addCriterion("buyerId <>", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridGreaterThan(String value) {
            addCriterion("buyerId >", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridGreaterThanOrEqualTo(String value) {
            addCriterion("buyerId >=", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridLessThan(String value) {
            addCriterion("buyerId <", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridLessThanOrEqualTo(String value) {
            addCriterion("buyerId <=", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridLike(String value) {
            addCriterion("buyerId like", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotLike(String value) {
            addCriterion("buyerId not like", value, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridIn(List<String> values) {
            addCriterion("buyerId in", values, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotIn(List<String> values) {
            addCriterion("buyerId not in", values, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridBetween(String value1, String value2) {
            addCriterion("buyerId between", value1, value2, "buyerid");
            return (Criteria) this;
        }

        public Criteria andBuyeridNotBetween(String value1, String value2) {
            addCriterion("buyerId not between", value1, value2, "buyerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridIsNull() {
            addCriterion("itemOwnerId is null");
            return (Criteria) this;
        }

        public Criteria andItemowneridIsNotNull() {
            addCriterion("itemOwnerId is not null");
            return (Criteria) this;
        }

        public Criteria andItemowneridEqualTo(String value) {
            addCriterion("itemOwnerId =", value, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridNotEqualTo(String value) {
            addCriterion("itemOwnerId <>", value, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridGreaterThan(String value) {
            addCriterion("itemOwnerId >", value, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridGreaterThanOrEqualTo(String value) {
            addCriterion("itemOwnerId >=", value, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridLessThan(String value) {
            addCriterion("itemOwnerId <", value, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridLessThanOrEqualTo(String value) {
            addCriterion("itemOwnerId <=", value, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridLike(String value) {
            addCriterion("itemOwnerId like", value, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridNotLike(String value) {
            addCriterion("itemOwnerId not like", value, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridIn(List<String> values) {
            addCriterion("itemOwnerId in", values, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridNotIn(List<String> values) {
            addCriterion("itemOwnerId not in", values, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridBetween(String value1, String value2) {
            addCriterion("itemOwnerId between", value1, value2, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andItemowneridNotBetween(String value1, String value2) {
            addCriterion("itemOwnerId not between", value1, value2, "itemownerid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidIsNull() {
            addCriterion("tradeItemId is null");
            return (Criteria) this;
        }

        public Criteria andTradeitemidIsNotNull() {
            addCriterion("tradeItemId is not null");
            return (Criteria) this;
        }

        public Criteria andTradeitemidEqualTo(String value) {
            addCriterion("tradeItemId =", value, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidNotEqualTo(String value) {
            addCriterion("tradeItemId <>", value, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidGreaterThan(String value) {
            addCriterion("tradeItemId >", value, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidGreaterThanOrEqualTo(String value) {
            addCriterion("tradeItemId >=", value, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidLessThan(String value) {
            addCriterion("tradeItemId <", value, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidLessThanOrEqualTo(String value) {
            addCriterion("tradeItemId <=", value, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidLike(String value) {
            addCriterion("tradeItemId like", value, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidNotLike(String value) {
            addCriterion("tradeItemId not like", value, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidIn(List<String> values) {
            addCriterion("tradeItemId in", values, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidNotIn(List<String> values) {
            addCriterion("tradeItemId not in", values, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidBetween(String value1, String value2) {
            addCriterion("tradeItemId between", value1, value2, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemidNotBetween(String value1, String value2) {
            addCriterion("tradeItemId not between", value1, value2, "tradeitemid");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleIsNull() {
            addCriterion("tradeItemTitle is null");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleIsNotNull() {
            addCriterion("tradeItemTitle is not null");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleEqualTo(String value) {
            addCriterion("tradeItemTitle =", value, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleNotEqualTo(String value) {
            addCriterion("tradeItemTitle <>", value, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleGreaterThan(String value) {
            addCriterion("tradeItemTitle >", value, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleGreaterThanOrEqualTo(String value) {
            addCriterion("tradeItemTitle >=", value, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleLessThan(String value) {
            addCriterion("tradeItemTitle <", value, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleLessThanOrEqualTo(String value) {
            addCriterion("tradeItemTitle <=", value, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleLike(String value) {
            addCriterion("tradeItemTitle like", value, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleNotLike(String value) {
            addCriterion("tradeItemTitle not like", value, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleIn(List<String> values) {
            addCriterion("tradeItemTitle in", values, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleNotIn(List<String> values) {
            addCriterion("tradeItemTitle not in", values, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleBetween(String value1, String value2) {
            addCriterion("tradeItemTitle between", value1, value2, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemtitleNotBetween(String value1, String value2) {
            addCriterion("tradeItemTitle not between", value1, value2, "tradeitemtitle");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageIsNull() {
            addCriterion("tradeItemImage is null");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageIsNotNull() {
            addCriterion("tradeItemImage is not null");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageEqualTo(String value) {
            addCriterion("tradeItemImage =", value, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageNotEqualTo(String value) {
            addCriterion("tradeItemImage <>", value, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageGreaterThan(String value) {
            addCriterion("tradeItemImage >", value, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageGreaterThanOrEqualTo(String value) {
            addCriterion("tradeItemImage >=", value, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageLessThan(String value) {
            addCriterion("tradeItemImage <", value, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageLessThanOrEqualTo(String value) {
            addCriterion("tradeItemImage <=", value, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageLike(String value) {
            addCriterion("tradeItemImage like", value, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageNotLike(String value) {
            addCriterion("tradeItemImage not like", value, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageIn(List<String> values) {
            addCriterion("tradeItemImage in", values, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageNotIn(List<String> values) {
            addCriterion("tradeItemImage not in", values, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageBetween(String value1, String value2) {
            addCriterion("tradeItemImage between", value1, value2, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradeitemimageNotBetween(String value1, String value2) {
            addCriterion("tradeItemImage not between", value1, value2, "tradeitemimage");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceIsNull() {
            addCriterion("tradeCurrPrice is null");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceIsNotNull() {
            addCriterion("tradeCurrPrice is not null");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceEqualTo(Integer value) {
            addCriterion("tradeCurrPrice =", value, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceNotEqualTo(Integer value) {
            addCriterion("tradeCurrPrice <>", value, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceGreaterThan(Integer value) {
            addCriterion("tradeCurrPrice >", value, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("tradeCurrPrice >=", value, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceLessThan(Integer value) {
            addCriterion("tradeCurrPrice <", value, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceLessThanOrEqualTo(Integer value) {
            addCriterion("tradeCurrPrice <=", value, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceIn(List<Integer> values) {
            addCriterion("tradeCurrPrice in", values, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceNotIn(List<Integer> values) {
            addCriterion("tradeCurrPrice not in", values, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceBetween(Integer value1, Integer value2) {
            addCriterion("tradeCurrPrice between", value1, value2, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradecurrpriceNotBetween(Integer value1, Integer value2) {
            addCriterion("tradeCurrPrice not between", value1, value2, "tradecurrprice");
            return (Criteria) this;
        }

        public Criteria andTradetypeIsNull() {
            addCriterion("tradeType is null");
            return (Criteria) this;
        }

        public Criteria andTradetypeIsNotNull() {
            addCriterion("tradeType is not null");
            return (Criteria) this;
        }

        public Criteria andTradetypeEqualTo(Integer value) {
            addCriterion("tradeType =", value, "tradetype");
            return (Criteria) this;
        }

        public Criteria andTradetypeNotEqualTo(Integer value) {
            addCriterion("tradeType <>", value, "tradetype");
            return (Criteria) this;
        }

        public Criteria andTradetypeGreaterThan(Integer value) {
            addCriterion("tradeType >", value, "tradetype");
            return (Criteria) this;
        }

        public Criteria andTradetypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("tradeType >=", value, "tradetype");
            return (Criteria) this;
        }

        public Criteria andTradetypeLessThan(Integer value) {
            addCriterion("tradeType <", value, "tradetype");
            return (Criteria) this;
        }

        public Criteria andTradetypeLessThanOrEqualTo(Integer value) {
            addCriterion("tradeType <=", value, "tradetype");
            return (Criteria) this;
        }

        public Criteria andTradetypeIn(List<Integer> values) {
            addCriterion("tradeType in", values, "tradetype");
            return (Criteria) this;
        }

        public Criteria andTradetypeNotIn(List<Integer> values) {
            addCriterion("tradeType not in", values, "tradetype");
            return (Criteria) this;
        }

        public Criteria andTradetypeBetween(Integer value1, Integer value2) {
            addCriterion("tradeType between", value1, value2, "tradetype");
            return (Criteria) this;
        }

        public Criteria andTradetypeNotBetween(Integer value1, Integer value2) {
            addCriterion("tradeType not between", value1, value2, "tradetype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeIsNull() {
            addCriterion("paymentType is null");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeIsNotNull() {
            addCriterion("paymentType is not null");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeEqualTo(Integer value) {
            addCriterion("paymentType =", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeNotEqualTo(Integer value) {
            addCriterion("paymentType <>", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeGreaterThan(Integer value) {
            addCriterion("paymentType >", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("paymentType >=", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeLessThan(Integer value) {
            addCriterion("paymentType <", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeLessThanOrEqualTo(Integer value) {
            addCriterion("paymentType <=", value, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeIn(List<Integer> values) {
            addCriterion("paymentType in", values, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeNotIn(List<Integer> values) {
            addCriterion("paymentType not in", values, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeBetween(Integer value1, Integer value2) {
            addCriterion("paymentType between", value1, value2, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andPaymenttypeNotBetween(Integer value1, Integer value2) {
            addCriterion("paymentType not between", value1, value2, "paymenttype");
            return (Criteria) this;
        }

        public Criteria andTradestatusIsNull() {
            addCriterion("tradeStatus is null");
            return (Criteria) this;
        }

        public Criteria andTradestatusIsNotNull() {
            addCriterion("tradeStatus is not null");
            return (Criteria) this;
        }

        public Criteria andTradestatusEqualTo(Integer value) {
            addCriterion("tradeStatus =", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusNotEqualTo(Integer value) {
            addCriterion("tradeStatus <>", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusGreaterThan(Integer value) {
            addCriterion("tradeStatus >", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("tradeStatus >=", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusLessThan(Integer value) {
            addCriterion("tradeStatus <", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusLessThanOrEqualTo(Integer value) {
            addCriterion("tradeStatus <=", value, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusIn(List<Integer> values) {
            addCriterion("tradeStatus in", values, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusNotIn(List<Integer> values) {
            addCriterion("tradeStatus not in", values, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusBetween(Integer value1, Integer value2) {
            addCriterion("tradeStatus between", value1, value2, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradestatusNotBetween(Integer value1, Integer value2) {
            addCriterion("tradeStatus not between", value1, value2, "tradestatus");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelIsNull() {
            addCriterion("tradeEvaluateLevel is null");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelIsNotNull() {
            addCriterion("tradeEvaluateLevel is not null");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelEqualTo(Integer value) {
            addCriterion("tradeEvaluateLevel =", value, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelNotEqualTo(Integer value) {
            addCriterion("tradeEvaluateLevel <>", value, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelGreaterThan(Integer value) {
            addCriterion("tradeEvaluateLevel >", value, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("tradeEvaluateLevel >=", value, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelLessThan(Integer value) {
            addCriterion("tradeEvaluateLevel <", value, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelLessThanOrEqualTo(Integer value) {
            addCriterion("tradeEvaluateLevel <=", value, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelIn(List<Integer> values) {
            addCriterion("tradeEvaluateLevel in", values, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelNotIn(List<Integer> values) {
            addCriterion("tradeEvaluateLevel not in", values, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelBetween(Integer value1, Integer value2) {
            addCriterion("tradeEvaluateLevel between", value1, value2, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andTradeevaluatelevelNotBetween(Integer value1, Integer value2) {
            addCriterion("tradeEvaluateLevel not between", value1, value2, "tradeevaluatelevel");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedIsNull() {
            addCriterion("buyerIsDeleted is null");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedIsNotNull() {
            addCriterion("buyerIsDeleted is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedEqualTo(Integer value) {
            addCriterion("buyerIsDeleted =", value, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedNotEqualTo(Integer value) {
            addCriterion("buyerIsDeleted <>", value, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedGreaterThan(Integer value) {
            addCriterion("buyerIsDeleted >", value, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("buyerIsDeleted >=", value, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedLessThan(Integer value) {
            addCriterion("buyerIsDeleted <", value, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedLessThanOrEqualTo(Integer value) {
            addCriterion("buyerIsDeleted <=", value, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedIn(List<Integer> values) {
            addCriterion("buyerIsDeleted in", values, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedNotIn(List<Integer> values) {
            addCriterion("buyerIsDeleted not in", values, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedBetween(Integer value1, Integer value2) {
            addCriterion("buyerIsDeleted between", value1, value2, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andBuyerisdeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("buyerIsDeleted not between", value1, value2, "buyerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedIsNull() {
            addCriterion("ownerIsDeleted is null");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedIsNotNull() {
            addCriterion("ownerIsDeleted is not null");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedEqualTo(Integer value) {
            addCriterion("ownerIsDeleted =", value, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedNotEqualTo(Integer value) {
            addCriterion("ownerIsDeleted <>", value, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedGreaterThan(Integer value) {
            addCriterion("ownerIsDeleted >", value, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("ownerIsDeleted >=", value, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedLessThan(Integer value) {
            addCriterion("ownerIsDeleted <", value, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedLessThanOrEqualTo(Integer value) {
            addCriterion("ownerIsDeleted <=", value, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedIn(List<Integer> values) {
            addCriterion("ownerIsDeleted in", values, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedNotIn(List<Integer> values) {
            addCriterion("ownerIsDeleted not in", values, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedBetween(Integer value1, Integer value2) {
            addCriterion("ownerIsDeleted between", value1, value2, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andOwnerisdeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("ownerIsDeleted not between", value1, value2, "ownerisdeleted");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateIsNull() {
            addCriterion("tradeEvaluate is null");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateIsNotNull() {
            addCriterion("tradeEvaluate is not null");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateEqualTo(String value) {
            addCriterion("tradeEvaluate =", value, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateNotEqualTo(String value) {
            addCriterion("tradeEvaluate <>", value, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateGreaterThan(String value) {
            addCriterion("tradeEvaluate >", value, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateGreaterThanOrEqualTo(String value) {
            addCriterion("tradeEvaluate >=", value, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateLessThan(String value) {
            addCriterion("tradeEvaluate <", value, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateLessThanOrEqualTo(String value) {
            addCriterion("tradeEvaluate <=", value, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateLike(String value) {
            addCriterion("tradeEvaluate like", value, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateNotLike(String value) {
            addCriterion("tradeEvaluate not like", value, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateIn(List<String> values) {
            addCriterion("tradeEvaluate in", values, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateNotIn(List<String> values) {
            addCriterion("tradeEvaluate not in", values, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateBetween(String value1, String value2) {
            addCriterion("tradeEvaluate between", value1, value2, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradeevaluateNotBetween(String value1, String value2) {
            addCriterion("tradeEvaluate not between", value1, value2, "tradeevaluate");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeIsNull() {
            addCriterion("tradeCreatedTime is null");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeIsNotNull() {
            addCriterion("tradeCreatedTime is not null");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeEqualTo(Date value) {
            addCriterion("tradeCreatedTime =", value, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeNotEqualTo(Date value) {
            addCriterion("tradeCreatedTime <>", value, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeGreaterThan(Date value) {
            addCriterion("tradeCreatedTime >", value, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tradeCreatedTime >=", value, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeLessThan(Date value) {
            addCriterion("tradeCreatedTime <", value, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeLessThanOrEqualTo(Date value) {
            addCriterion("tradeCreatedTime <=", value, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeIn(List<Date> values) {
            addCriterion("tradeCreatedTime in", values, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeNotIn(List<Date> values) {
            addCriterion("tradeCreatedTime not in", values, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeBetween(Date value1, Date value2) {
            addCriterion("tradeCreatedTime between", value1, value2, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecreatedtimeNotBetween(Date value1, Date value2) {
            addCriterion("tradeCreatedTime not between", value1, value2, "tradecreatedtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeIsNull() {
            addCriterion("tradeComplishTime is null");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeIsNotNull() {
            addCriterion("tradeComplishTime is not null");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeEqualTo(Date value) {
            addCriterion("tradeComplishTime =", value, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeNotEqualTo(Date value) {
            addCriterion("tradeComplishTime <>", value, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeGreaterThan(Date value) {
            addCriterion("tradeComplishTime >", value, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tradeComplishTime >=", value, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeLessThan(Date value) {
            addCriterion("tradeComplishTime <", value, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeLessThanOrEqualTo(Date value) {
            addCriterion("tradeComplishTime <=", value, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeIn(List<Date> values) {
            addCriterion("tradeComplishTime in", values, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeNotIn(List<Date> values) {
            addCriterion("tradeComplishTime not in", values, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeBetween(Date value1, Date value2) {
            addCriterion("tradeComplishTime between", value1, value2, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecomplishtimeNotBetween(Date value1, Date value2) {
            addCriterion("tradeComplishTime not between", value1, value2, "tradecomplishtime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeIsNull() {
            addCriterion("tradeCEvaluateTime is null");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeIsNotNull() {
            addCriterion("tradeCEvaluateTime is not null");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeEqualTo(Date value) {
            addCriterion("tradeCEvaluateTime =", value, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeNotEqualTo(Date value) {
            addCriterion("tradeCEvaluateTime <>", value, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeGreaterThan(Date value) {
            addCriterion("tradeCEvaluateTime >", value, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tradeCEvaluateTime >=", value, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeLessThan(Date value) {
            addCriterion("tradeCEvaluateTime <", value, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeLessThanOrEqualTo(Date value) {
            addCriterion("tradeCEvaluateTime <=", value, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeIn(List<Date> values) {
            addCriterion("tradeCEvaluateTime in", values, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeNotIn(List<Date> values) {
            addCriterion("tradeCEvaluateTime not in", values, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeBetween(Date value1, Date value2) {
            addCriterion("tradeCEvaluateTime between", value1, value2, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecevaluatetimeNotBetween(Date value1, Date value2) {
            addCriterion("tradeCEvaluateTime not between", value1, value2, "tradecevaluatetime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeIsNull() {
            addCriterion("tradeCancelTime is null");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeIsNotNull() {
            addCriterion("tradeCancelTime is not null");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeEqualTo(Date value) {
            addCriterion("tradeCancelTime =", value, "tradecanceltime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeNotEqualTo(Date value) {
            addCriterion("tradeCancelTime <>", value, "tradecanceltime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeGreaterThan(Date value) {
            addCriterion("tradeCancelTime >", value, "tradecanceltime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tradeCancelTime >=", value, "tradecanceltime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeLessThan(Date value) {
            addCriterion("tradeCancelTime <", value, "tradecanceltime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeLessThanOrEqualTo(Date value) {
            addCriterion("tradeCancelTime <=", value, "tradecanceltime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeIn(List<Date> values) {
            addCriterion("tradeCancelTime in", values, "tradecanceltime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeNotIn(List<Date> values) {
            addCriterion("tradeCancelTime not in", values, "tradecanceltime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeBetween(Date value1, Date value2) {
            addCriterion("tradeCancelTime between", value1, value2, "tradecanceltime");
            return (Criteria) this;
        }

        public Criteria andTradecanceltimeNotBetween(Date value1, Date value2) {
            addCriterion("tradeCancelTime not between", value1, value2, "tradecanceltime");
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