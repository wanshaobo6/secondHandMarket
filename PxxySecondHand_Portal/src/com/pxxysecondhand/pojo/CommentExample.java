package com.pxxysecondhand.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
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

        public Criteria andItemidIsNull() {
            addCriterion("itemId is null");
            return (Criteria) this;
        }

        public Criteria andItemidIsNotNull() {
            addCriterion("itemId is not null");
            return (Criteria) this;
        }

        public Criteria andItemidEqualTo(String value) {
            addCriterion("itemId =", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotEqualTo(String value) {
            addCriterion("itemId <>", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThan(String value) {
            addCriterion("itemId >", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidGreaterThanOrEqualTo(String value) {
            addCriterion("itemId >=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThan(String value) {
            addCriterion("itemId <", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLessThanOrEqualTo(String value) {
            addCriterion("itemId <=", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidLike(String value) {
            addCriterion("itemId like", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotLike(String value) {
            addCriterion("itemId not like", value, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidIn(List<String> values) {
            addCriterion("itemId in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotIn(List<String> values) {
            addCriterion("itemId not in", values, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidBetween(String value1, String value2) {
            addCriterion("itemId between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andItemidNotBetween(String value1, String value2) {
            addCriterion("itemId not between", value1, value2, "itemid");
            return (Criteria) this;
        }

        public Criteria andParentidIsNull() {
            addCriterion("parentId is null");
            return (Criteria) this;
        }

        public Criteria andParentidIsNotNull() {
            addCriterion("parentId is not null");
            return (Criteria) this;
        }

        public Criteria andParentidEqualTo(String value) {
            addCriterion("parentId =", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotEqualTo(String value) {
            addCriterion("parentId <>", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThan(String value) {
            addCriterion("parentId >", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidGreaterThanOrEqualTo(String value) {
            addCriterion("parentId >=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThan(String value) {
            addCriterion("parentId <", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLessThanOrEqualTo(String value) {
            addCriterion("parentId <=", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidLike(String value) {
            addCriterion("parentId like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotLike(String value) {
            addCriterion("parentId not like", value, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidIn(List<String> values) {
            addCriterion("parentId in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotIn(List<String> values) {
            addCriterion("parentId not in", values, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidBetween(String value1, String value2) {
            addCriterion("parentId between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andParentidNotBetween(String value1, String value2) {
            addCriterion("parentId not between", value1, value2, "parentid");
            return (Criteria) this;
        }

        public Criteria andIsparentIsNull() {
            addCriterion("isParent is null");
            return (Criteria) this;
        }

        public Criteria andIsparentIsNotNull() {
            addCriterion("isParent is not null");
            return (Criteria) this;
        }

        public Criteria andIsparentEqualTo(Integer value) {
            addCriterion("isParent =", value, "isparent");
            return (Criteria) this;
        }

        public Criteria andIsparentNotEqualTo(Integer value) {
            addCriterion("isParent <>", value, "isparent");
            return (Criteria) this;
        }

        public Criteria andIsparentGreaterThan(Integer value) {
            addCriterion("isParent >", value, "isparent");
            return (Criteria) this;
        }

        public Criteria andIsparentGreaterThanOrEqualTo(Integer value) {
            addCriterion("isParent >=", value, "isparent");
            return (Criteria) this;
        }

        public Criteria andIsparentLessThan(Integer value) {
            addCriterion("isParent <", value, "isparent");
            return (Criteria) this;
        }

        public Criteria andIsparentLessThanOrEqualTo(Integer value) {
            addCriterion("isParent <=", value, "isparent");
            return (Criteria) this;
        }

        public Criteria andIsparentIn(List<Integer> values) {
            addCriterion("isParent in", values, "isparent");
            return (Criteria) this;
        }

        public Criteria andIsparentNotIn(List<Integer> values) {
            addCriterion("isParent not in", values, "isparent");
            return (Criteria) this;
        }

        public Criteria andIsparentBetween(Integer value1, Integer value2) {
            addCriterion("isParent between", value1, value2, "isparent");
            return (Criteria) this;
        }

        public Criteria andIsparentNotBetween(Integer value1, Integer value2) {
            addCriterion("isParent not between", value1, value2, "isparent");
            return (Criteria) this;
        }

        public Criteria andFromuseridIsNull() {
            addCriterion("fromUserId is null");
            return (Criteria) this;
        }

        public Criteria andFromuseridIsNotNull() {
            addCriterion("fromUserId is not null");
            return (Criteria) this;
        }

        public Criteria andFromuseridEqualTo(String value) {
            addCriterion("fromUserId =", value, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridNotEqualTo(String value) {
            addCriterion("fromUserId <>", value, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridGreaterThan(String value) {
            addCriterion("fromUserId >", value, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridGreaterThanOrEqualTo(String value) {
            addCriterion("fromUserId >=", value, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridLessThan(String value) {
            addCriterion("fromUserId <", value, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridLessThanOrEqualTo(String value) {
            addCriterion("fromUserId <=", value, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridLike(String value) {
            addCriterion("fromUserId like", value, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridNotLike(String value) {
            addCriterion("fromUserId not like", value, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridIn(List<String> values) {
            addCriterion("fromUserId in", values, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridNotIn(List<String> values) {
            addCriterion("fromUserId not in", values, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridBetween(String value1, String value2) {
            addCriterion("fromUserId between", value1, value2, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromuseridNotBetween(String value1, String value2) {
            addCriterion("fromUserId not between", value1, value2, "fromuserid");
            return (Criteria) this;
        }

        public Criteria andFromusernameIsNull() {
            addCriterion("fromUserName is null");
            return (Criteria) this;
        }

        public Criteria andFromusernameIsNotNull() {
            addCriterion("fromUserName is not null");
            return (Criteria) this;
        }

        public Criteria andFromusernameEqualTo(String value) {
            addCriterion("fromUserName =", value, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameNotEqualTo(String value) {
            addCriterion("fromUserName <>", value, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameGreaterThan(String value) {
            addCriterion("fromUserName >", value, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameGreaterThanOrEqualTo(String value) {
            addCriterion("fromUserName >=", value, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameLessThan(String value) {
            addCriterion("fromUserName <", value, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameLessThanOrEqualTo(String value) {
            addCriterion("fromUserName <=", value, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameLike(String value) {
            addCriterion("fromUserName like", value, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameNotLike(String value) {
            addCriterion("fromUserName not like", value, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameIn(List<String> values) {
            addCriterion("fromUserName in", values, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameNotIn(List<String> values) {
            addCriterion("fromUserName not in", values, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameBetween(String value1, String value2) {
            addCriterion("fromUserName between", value1, value2, "fromusername");
            return (Criteria) this;
        }

        public Criteria andFromusernameNotBetween(String value1, String value2) {
            addCriterion("fromUserName not between", value1, value2, "fromusername");
            return (Criteria) this;
        }

        public Criteria andTouseridIsNull() {
            addCriterion("toUserId is null");
            return (Criteria) this;
        }

        public Criteria andTouseridIsNotNull() {
            addCriterion("toUserId is not null");
            return (Criteria) this;
        }

        public Criteria andTouseridEqualTo(String value) {
            addCriterion("toUserId =", value, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridNotEqualTo(String value) {
            addCriterion("toUserId <>", value, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridGreaterThan(String value) {
            addCriterion("toUserId >", value, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridGreaterThanOrEqualTo(String value) {
            addCriterion("toUserId >=", value, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridLessThan(String value) {
            addCriterion("toUserId <", value, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridLessThanOrEqualTo(String value) {
            addCriterion("toUserId <=", value, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridLike(String value) {
            addCriterion("toUserId like", value, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridNotLike(String value) {
            addCriterion("toUserId not like", value, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridIn(List<String> values) {
            addCriterion("toUserId in", values, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridNotIn(List<String> values) {
            addCriterion("toUserId not in", values, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridBetween(String value1, String value2) {
            addCriterion("toUserId between", value1, value2, "touserid");
            return (Criteria) this;
        }

        public Criteria andTouseridNotBetween(String value1, String value2) {
            addCriterion("toUserId not between", value1, value2, "touserid");
            return (Criteria) this;
        }

        public Criteria andTousernameIsNull() {
            addCriterion("toUserName is null");
            return (Criteria) this;
        }

        public Criteria andTousernameIsNotNull() {
            addCriterion("toUserName is not null");
            return (Criteria) this;
        }

        public Criteria andTousernameEqualTo(String value) {
            addCriterion("toUserName =", value, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameNotEqualTo(String value) {
            addCriterion("toUserName <>", value, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameGreaterThan(String value) {
            addCriterion("toUserName >", value, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameGreaterThanOrEqualTo(String value) {
            addCriterion("toUserName >=", value, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameLessThan(String value) {
            addCriterion("toUserName <", value, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameLessThanOrEqualTo(String value) {
            addCriterion("toUserName <=", value, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameLike(String value) {
            addCriterion("toUserName like", value, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameNotLike(String value) {
            addCriterion("toUserName not like", value, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameIn(List<String> values) {
            addCriterion("toUserName in", values, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameNotIn(List<String> values) {
            addCriterion("toUserName not in", values, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameBetween(String value1, String value2) {
            addCriterion("toUserName between", value1, value2, "tousername");
            return (Criteria) this;
        }

        public Criteria andTousernameNotBetween(String value1, String value2) {
            addCriterion("toUserName not between", value1, value2, "tousername");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andHavareadIsNull() {
            addCriterion("havaread is null");
            return (Criteria) this;
        }

        public Criteria andHavareadIsNotNull() {
            addCriterion("havaread is not null");
            return (Criteria) this;
        }

        public Criteria andHavareadEqualTo(Integer value) {
            addCriterion("havaread =", value, "havaread");
            return (Criteria) this;
        }

        public Criteria andHavareadNotEqualTo(Integer value) {
            addCriterion("havaread <>", value, "havaread");
            return (Criteria) this;
        }

        public Criteria andHavareadGreaterThan(Integer value) {
            addCriterion("havaread >", value, "havaread");
            return (Criteria) this;
        }

        public Criteria andHavareadGreaterThanOrEqualTo(Integer value) {
            addCriterion("havaread >=", value, "havaread");
            return (Criteria) this;
        }

        public Criteria andHavareadLessThan(Integer value) {
            addCriterion("havaread <", value, "havaread");
            return (Criteria) this;
        }

        public Criteria andHavareadLessThanOrEqualTo(Integer value) {
            addCriterion("havaread <=", value, "havaread");
            return (Criteria) this;
        }

        public Criteria andHavareadIn(List<Integer> values) {
            addCriterion("havaread in", values, "havaread");
            return (Criteria) this;
        }

        public Criteria andHavareadNotIn(List<Integer> values) {
            addCriterion("havaread not in", values, "havaread");
            return (Criteria) this;
        }

        public Criteria andHavareadBetween(Integer value1, Integer value2) {
            addCriterion("havaread between", value1, value2, "havaread");
            return (Criteria) this;
        }

        public Criteria andHavareadNotBetween(Integer value1, Integer value2) {
            addCriterion("havaread not between", value1, value2, "havaread");
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