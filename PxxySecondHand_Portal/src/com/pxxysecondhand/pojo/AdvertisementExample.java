package com.pxxysecondhand.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvertisementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvertisementExample() {
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

        public Criteria andAidIsNull() {
            addCriterion("aid is null");
            return (Criteria) this;
        }

        public Criteria andAidIsNotNull() {
            addCriterion("aid is not null");
            return (Criteria) this;
        }

        public Criteria andAidEqualTo(Integer value) {
            addCriterion("aid =", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotEqualTo(Integer value) {
            addCriterion("aid <>", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThan(Integer value) {
            addCriterion("aid >", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThanOrEqualTo(Integer value) {
            addCriterion("aid >=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThan(Integer value) {
            addCriterion("aid <", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThanOrEqualTo(Integer value) {
            addCriterion("aid <=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidIn(List<Integer> values) {
            addCriterion("aid in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotIn(List<Integer> values) {
            addCriterion("aid not in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidBetween(Integer value1, Integer value2) {
            addCriterion("aid between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotBetween(Integer value1, Integer value2) {
            addCriterion("aid not between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andAdorderIsNull() {
            addCriterion("adOrder is null");
            return (Criteria) this;
        }

        public Criteria andAdorderIsNotNull() {
            addCriterion("adOrder is not null");
            return (Criteria) this;
        }

        public Criteria andAdorderEqualTo(Integer value) {
            addCriterion("adOrder =", value, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdorderNotEqualTo(Integer value) {
            addCriterion("adOrder <>", value, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdorderGreaterThan(Integer value) {
            addCriterion("adOrder >", value, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdorderGreaterThanOrEqualTo(Integer value) {
            addCriterion("adOrder >=", value, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdorderLessThan(Integer value) {
            addCriterion("adOrder <", value, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdorderLessThanOrEqualTo(Integer value) {
            addCriterion("adOrder <=", value, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdorderIn(List<Integer> values) {
            addCriterion("adOrder in", values, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdorderNotIn(List<Integer> values) {
            addCriterion("adOrder not in", values, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdorderBetween(Integer value1, Integer value2) {
            addCriterion("adOrder between", value1, value2, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdorderNotBetween(Integer value1, Integer value2) {
            addCriterion("adOrder not between", value1, value2, "adorder");
            return (Criteria) this;
        }

        public Criteria andAdnameIsNull() {
            addCriterion("adName is null");
            return (Criteria) this;
        }

        public Criteria andAdnameIsNotNull() {
            addCriterion("adName is not null");
            return (Criteria) this;
        }

        public Criteria andAdnameEqualTo(String value) {
            addCriterion("adName =", value, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameNotEqualTo(String value) {
            addCriterion("adName <>", value, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameGreaterThan(String value) {
            addCriterion("adName >", value, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameGreaterThanOrEqualTo(String value) {
            addCriterion("adName >=", value, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameLessThan(String value) {
            addCriterion("adName <", value, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameLessThanOrEqualTo(String value) {
            addCriterion("adName <=", value, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameLike(String value) {
            addCriterion("adName like", value, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameNotLike(String value) {
            addCriterion("adName not like", value, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameIn(List<String> values) {
            addCriterion("adName in", values, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameNotIn(List<String> values) {
            addCriterion("adName not in", values, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameBetween(String value1, String value2) {
            addCriterion("adName between", value1, value2, "adname");
            return (Criteria) this;
        }

        public Criteria andAdnameNotBetween(String value1, String value2) {
            addCriterion("adName not between", value1, value2, "adname");
            return (Criteria) this;
        }

        public Criteria andAddescIsNull() {
            addCriterion("adDesc is null");
            return (Criteria) this;
        }

        public Criteria andAddescIsNotNull() {
            addCriterion("adDesc is not null");
            return (Criteria) this;
        }

        public Criteria andAddescEqualTo(String value) {
            addCriterion("adDesc =", value, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescNotEqualTo(String value) {
            addCriterion("adDesc <>", value, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescGreaterThan(String value) {
            addCriterion("adDesc >", value, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescGreaterThanOrEqualTo(String value) {
            addCriterion("adDesc >=", value, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescLessThan(String value) {
            addCriterion("adDesc <", value, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescLessThanOrEqualTo(String value) {
            addCriterion("adDesc <=", value, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescLike(String value) {
            addCriterion("adDesc like", value, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescNotLike(String value) {
            addCriterion("adDesc not like", value, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescIn(List<String> values) {
            addCriterion("adDesc in", values, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescNotIn(List<String> values) {
            addCriterion("adDesc not in", values, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescBetween(String value1, String value2) {
            addCriterion("adDesc between", value1, value2, "addesc");
            return (Criteria) this;
        }

        public Criteria andAddescNotBetween(String value1, String value2) {
            addCriterion("adDesc not between", value1, value2, "addesc");
            return (Criteria) this;
        }

        public Criteria andAdimageIsNull() {
            addCriterion("adImage is null");
            return (Criteria) this;
        }

        public Criteria andAdimageIsNotNull() {
            addCriterion("adImage is not null");
            return (Criteria) this;
        }

        public Criteria andAdimageEqualTo(String value) {
            addCriterion("adImage =", value, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageNotEqualTo(String value) {
            addCriterion("adImage <>", value, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageGreaterThan(String value) {
            addCriterion("adImage >", value, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageGreaterThanOrEqualTo(String value) {
            addCriterion("adImage >=", value, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageLessThan(String value) {
            addCriterion("adImage <", value, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageLessThanOrEqualTo(String value) {
            addCriterion("adImage <=", value, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageLike(String value) {
            addCriterion("adImage like", value, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageNotLike(String value) {
            addCriterion("adImage not like", value, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageIn(List<String> values) {
            addCriterion("adImage in", values, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageNotIn(List<String> values) {
            addCriterion("adImage not in", values, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageBetween(String value1, String value2) {
            addCriterion("adImage between", value1, value2, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdimageNotBetween(String value1, String value2) {
            addCriterion("adImage not between", value1, value2, "adimage");
            return (Criteria) this;
        }

        public Criteria andAdaddressIsNull() {
            addCriterion("adAddress is null");
            return (Criteria) this;
        }

        public Criteria andAdaddressIsNotNull() {
            addCriterion("adAddress is not null");
            return (Criteria) this;
        }

        public Criteria andAdaddressEqualTo(String value) {
            addCriterion("adAddress =", value, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressNotEqualTo(String value) {
            addCriterion("adAddress <>", value, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressGreaterThan(String value) {
            addCriterion("adAddress >", value, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressGreaterThanOrEqualTo(String value) {
            addCriterion("adAddress >=", value, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressLessThan(String value) {
            addCriterion("adAddress <", value, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressLessThanOrEqualTo(String value) {
            addCriterion("adAddress <=", value, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressLike(String value) {
            addCriterion("adAddress like", value, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressNotLike(String value) {
            addCriterion("adAddress not like", value, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressIn(List<String> values) {
            addCriterion("adAddress in", values, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressNotIn(List<String> values) {
            addCriterion("adAddress not in", values, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressBetween(String value1, String value2) {
            addCriterion("adAddress between", value1, value2, "adaddress");
            return (Criteria) this;
        }

        public Criteria andAdaddressNotBetween(String value1, String value2) {
            addCriterion("adAddress not between", value1, value2, "adaddress");
            return (Criteria) this;
        }

        public Criteria andLastoperatorIsNull() {
            addCriterion("lastOperator is null");
            return (Criteria) this;
        }

        public Criteria andLastoperatorIsNotNull() {
            addCriterion("lastOperator is not null");
            return (Criteria) this;
        }

        public Criteria andLastoperatorEqualTo(Integer value) {
            addCriterion("lastOperator =", value, "lastoperator");
            return (Criteria) this;
        }

        public Criteria andLastoperatorNotEqualTo(Integer value) {
            addCriterion("lastOperator <>", value, "lastoperator");
            return (Criteria) this;
        }

        public Criteria andLastoperatorGreaterThan(Integer value) {
            addCriterion("lastOperator >", value, "lastoperator");
            return (Criteria) this;
        }

        public Criteria andLastoperatorGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastOperator >=", value, "lastoperator");
            return (Criteria) this;
        }

        public Criteria andLastoperatorLessThan(Integer value) {
            addCriterion("lastOperator <", value, "lastoperator");
            return (Criteria) this;
        }

        public Criteria andLastoperatorLessThanOrEqualTo(Integer value) {
            addCriterion("lastOperator <=", value, "lastoperator");
            return (Criteria) this;
        }

        public Criteria andLastoperatorIn(List<Integer> values) {
            addCriterion("lastOperator in", values, "lastoperator");
            return (Criteria) this;
        }

        public Criteria andLastoperatorNotIn(List<Integer> values) {
            addCriterion("lastOperator not in", values, "lastoperator");
            return (Criteria) this;
        }

        public Criteria andLastoperatorBetween(Integer value1, Integer value2) {
            addCriterion("lastOperator between", value1, value2, "lastoperator");
            return (Criteria) this;
        }

        public Criteria andLastoperatorNotBetween(Integer value1, Integer value2) {
            addCriterion("lastOperator not between", value1, value2, "lastoperator");
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