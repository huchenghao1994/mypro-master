package com.yaspeed.web.pojo;

import java.util.ArrayList;
import java.util.List;

public class OperationLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperationLogExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNull() {
            addCriterion("CLASS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andClassNameIsNotNull() {
            addCriterion("CLASS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andClassNameEqualTo(String value) {
            addCriterion("CLASS_NAME =", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotEqualTo(String value) {
            addCriterion("CLASS_NAME <>", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThan(String value) {
            addCriterion("CLASS_NAME >", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME >=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThan(String value) {
            addCriterion("CLASS_NAME <", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLessThanOrEqualTo(String value) {
            addCriterion("CLASS_NAME <=", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameLike(String value) {
            addCriterion("CLASS_NAME like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotLike(String value) {
            addCriterion("CLASS_NAME not like", value, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameIn(List<String> values) {
            addCriterion("CLASS_NAME in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotIn(List<String> values) {
            addCriterion("CLASS_NAME not in", values, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameBetween(String value1, String value2) {
            addCriterion("CLASS_NAME between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andClassNameNotBetween(String value1, String value2) {
            addCriterion("CLASS_NAME not between", value1, value2, "className");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNull() {
            addCriterion("METHOD_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMethodNameIsNotNull() {
            addCriterion("METHOD_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMethodNameEqualTo(String value) {
            addCriterion("METHOD_NAME =", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotEqualTo(String value) {
            addCriterion("METHOD_NAME <>", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThan(String value) {
            addCriterion("METHOD_NAME >", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameGreaterThanOrEqualTo(String value) {
            addCriterion("METHOD_NAME >=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThan(String value) {
            addCriterion("METHOD_NAME <", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLessThanOrEqualTo(String value) {
            addCriterion("METHOD_NAME <=", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameLike(String value) {
            addCriterion("METHOD_NAME like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotLike(String value) {
            addCriterion("METHOD_NAME not like", value, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameIn(List<String> values) {
            addCriterion("METHOD_NAME in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotIn(List<String> values) {
            addCriterion("METHOD_NAME not in", values, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameBetween(String value1, String value2) {
            addCriterion("METHOD_NAME between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andMethodNameNotBetween(String value1, String value2) {
            addCriterion("METHOD_NAME not between", value1, value2, "methodName");
            return (Criteria) this;
        }

        public Criteria andSqlDescIsNull() {
            addCriterion("SQL_DESC is null");
            return (Criteria) this;
        }

        public Criteria andSqlDescIsNotNull() {
            addCriterion("SQL_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andSqlDescEqualTo(String value) {
            addCriterion("SQL_DESC =", value, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescNotEqualTo(String value) {
            addCriterion("SQL_DESC <>", value, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescGreaterThan(String value) {
            addCriterion("SQL_DESC >", value, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescGreaterThanOrEqualTo(String value) {
            addCriterion("SQL_DESC >=", value, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescLessThan(String value) {
            addCriterion("SQL_DESC <", value, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescLessThanOrEqualTo(String value) {
            addCriterion("SQL_DESC <=", value, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescLike(String value) {
            addCriterion("SQL_DESC like", value, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescNotLike(String value) {
            addCriterion("SQL_DESC not like", value, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescIn(List<String> values) {
            addCriterion("SQL_DESC in", values, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescNotIn(List<String> values) {
            addCriterion("SQL_DESC not in", values, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescBetween(String value1, String value2) {
            addCriterion("SQL_DESC between", value1, value2, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andSqlDescNotBetween(String value1, String value2) {
            addCriterion("SQL_DESC not between", value1, value2, "sqlDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescIsNull() {
            addCriterion("PARA_DESC is null");
            return (Criteria) this;
        }

        public Criteria andParaDescIsNotNull() {
            addCriterion("PARA_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andParaDescEqualTo(String value) {
            addCriterion("PARA_DESC =", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescNotEqualTo(String value) {
            addCriterion("PARA_DESC <>", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescGreaterThan(String value) {
            addCriterion("PARA_DESC >", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescGreaterThanOrEqualTo(String value) {
            addCriterion("PARA_DESC >=", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescLessThan(String value) {
            addCriterion("PARA_DESC <", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescLessThanOrEqualTo(String value) {
            addCriterion("PARA_DESC <=", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescLike(String value) {
            addCriterion("PARA_DESC like", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescNotLike(String value) {
            addCriterion("PARA_DESC not like", value, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescIn(List<String> values) {
            addCriterion("PARA_DESC in", values, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescNotIn(List<String> values) {
            addCriterion("PARA_DESC not in", values, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescBetween(String value1, String value2) {
            addCriterion("PARA_DESC between", value1, value2, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andParaDescNotBetween(String value1, String value2) {
            addCriterion("PARA_DESC not between", value1, value2, "paraDesc");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberIsNull() {
            addCriterion("INFLUENCE_ROW_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberIsNotNull() {
            addCriterion("INFLUENCE_ROW_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberEqualTo(Integer value) {
            addCriterion("INFLUENCE_ROW_NUMBER =", value, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberNotEqualTo(Integer value) {
            addCriterion("INFLUENCE_ROW_NUMBER <>", value, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberGreaterThan(Integer value) {
            addCriterion("INFLUENCE_ROW_NUMBER >", value, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("INFLUENCE_ROW_NUMBER >=", value, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberLessThan(Integer value) {
            addCriterion("INFLUENCE_ROW_NUMBER <", value, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberLessThanOrEqualTo(Integer value) {
            addCriterion("INFLUENCE_ROW_NUMBER <=", value, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberIn(List<Integer> values) {
            addCriterion("INFLUENCE_ROW_NUMBER in", values, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberNotIn(List<Integer> values) {
            addCriterion("INFLUENCE_ROW_NUMBER not in", values, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberBetween(Integer value1, Integer value2) {
            addCriterion("INFLUENCE_ROW_NUMBER between", value1, value2, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andInfluenceRowNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("INFLUENCE_ROW_NUMBER not between", value1, value2, "influenceRowNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNull() {
            addCriterion("DEVICE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("DEVICE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("DEVICE_NAME =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("DEVICE_NAME <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("DEVICE_NAME >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEVICE_NAME >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("DEVICE_NAME <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("DEVICE_NAME <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("DEVICE_NAME like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("DEVICE_NAME not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("DEVICE_NAME in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("DEVICE_NAME not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("DEVICE_NAME between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("DEVICE_NAME not between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameIsNull() {
            addCriterion("BROWSER_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBrowserNameIsNotNull() {
            addCriterion("BROWSER_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBrowserNameEqualTo(String value) {
            addCriterion("BROWSER_NAME =", value, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameNotEqualTo(String value) {
            addCriterion("BROWSER_NAME <>", value, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameGreaterThan(String value) {
            addCriterion("BROWSER_NAME >", value, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameGreaterThanOrEqualTo(String value) {
            addCriterion("BROWSER_NAME >=", value, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameLessThan(String value) {
            addCriterion("BROWSER_NAME <", value, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameLessThanOrEqualTo(String value) {
            addCriterion("BROWSER_NAME <=", value, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameLike(String value) {
            addCriterion("BROWSER_NAME like", value, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameNotLike(String value) {
            addCriterion("BROWSER_NAME not like", value, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameIn(List<String> values) {
            addCriterion("BROWSER_NAME in", values, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameNotIn(List<String> values) {
            addCriterion("BROWSER_NAME not in", values, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameBetween(String value1, String value2) {
            addCriterion("BROWSER_NAME between", value1, value2, "browserName");
            return (Criteria) this;
        }

        public Criteria andBrowserNameNotBetween(String value1, String value2) {
            addCriterion("BROWSER_NAME not between", value1, value2, "browserName");
            return (Criteria) this;
        }

        public Criteria andUserAgentIsNull() {
            addCriterion("USER_AGENT is null");
            return (Criteria) this;
        }

        public Criteria andUserAgentIsNotNull() {
            addCriterion("USER_AGENT is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgentEqualTo(String value) {
            addCriterion("USER_AGENT =", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotEqualTo(String value) {
            addCriterion("USER_AGENT <>", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentGreaterThan(String value) {
            addCriterion("USER_AGENT >", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentGreaterThanOrEqualTo(String value) {
            addCriterion("USER_AGENT >=", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentLessThan(String value) {
            addCriterion("USER_AGENT <", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentLessThanOrEqualTo(String value) {
            addCriterion("USER_AGENT <=", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentLike(String value) {
            addCriterion("USER_AGENT like", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotLike(String value) {
            addCriterion("USER_AGENT not like", value, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentIn(List<String> values) {
            addCriterion("USER_AGENT in", values, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotIn(List<String> values) {
            addCriterion("USER_AGENT not in", values, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentBetween(String value1, String value2) {
            addCriterion("USER_AGENT between", value1, value2, "userAgent");
            return (Criteria) this;
        }

        public Criteria andUserAgentNotBetween(String value1, String value2) {
            addCriterion("USER_AGENT not between", value1, value2, "userAgent");
            return (Criteria) this;
        }

        public Criteria andControlUriIsNull() {
            addCriterion("CONTROL_URI is null");
            return (Criteria) this;
        }

        public Criteria andControlUriIsNotNull() {
            addCriterion("CONTROL_URI is not null");
            return (Criteria) this;
        }

        public Criteria andControlUriEqualTo(String value) {
            addCriterion("CONTROL_URI =", value, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriNotEqualTo(String value) {
            addCriterion("CONTROL_URI <>", value, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriGreaterThan(String value) {
            addCriterion("CONTROL_URI >", value, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriGreaterThanOrEqualTo(String value) {
            addCriterion("CONTROL_URI >=", value, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriLessThan(String value) {
            addCriterion("CONTROL_URI <", value, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriLessThanOrEqualTo(String value) {
            addCriterion("CONTROL_URI <=", value, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriLike(String value) {
            addCriterion("CONTROL_URI like", value, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriNotLike(String value) {
            addCriterion("CONTROL_URI not like", value, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriIn(List<String> values) {
            addCriterion("CONTROL_URI in", values, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriNotIn(List<String> values) {
            addCriterion("CONTROL_URI not in", values, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriBetween(String value1, String value2) {
            addCriterion("CONTROL_URI between", value1, value2, "controlUri");
            return (Criteria) this;
        }

        public Criteria andControlUriNotBetween(String value1, String value2) {
            addCriterion("CONTROL_URI not between", value1, value2, "controlUri");
            return (Criteria) this;
        }

        public Criteria andVisitIpIsNull() {
            addCriterion("VISIT_IP is null");
            return (Criteria) this;
        }

        public Criteria andVisitIpIsNotNull() {
            addCriterion("VISIT_IP is not null");
            return (Criteria) this;
        }

        public Criteria andVisitIpEqualTo(String value) {
            addCriterion("VISIT_IP =", value, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpNotEqualTo(String value) {
            addCriterion("VISIT_IP <>", value, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpGreaterThan(String value) {
            addCriterion("VISIT_IP >", value, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpGreaterThanOrEqualTo(String value) {
            addCriterion("VISIT_IP >=", value, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpLessThan(String value) {
            addCriterion("VISIT_IP <", value, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpLessThanOrEqualTo(String value) {
            addCriterion("VISIT_IP <=", value, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpLike(String value) {
            addCriterion("VISIT_IP like", value, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpNotLike(String value) {
            addCriterion("VISIT_IP not like", value, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpIn(List<String> values) {
            addCriterion("VISIT_IP in", values, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpNotIn(List<String> values) {
            addCriterion("VISIT_IP not in", values, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpBetween(String value1, String value2) {
            addCriterion("VISIT_IP between", value1, value2, "visitIp");
            return (Criteria) this;
        }

        public Criteria andVisitIpNotBetween(String value1, String value2) {
            addCriterion("VISIT_IP not between", value1, value2, "visitIp");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(String value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(String value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(String value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(String value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLike(String value) {
            addCriterion("CREATE_DATE like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotLike(String value) {
            addCriterion("CREATE_DATE not like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<String> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<String> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(String value1, String value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(String value1, String value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("CREATE_TIME like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("CREATE_TIME not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTellerIsNull() {
            addCriterion("CREATE_TELLER is null");
            return (Criteria) this;
        }

        public Criteria andCreateTellerIsNotNull() {
            addCriterion("CREATE_TELLER is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTellerEqualTo(String value) {
            addCriterion("CREATE_TELLER =", value, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerNotEqualTo(String value) {
            addCriterion("CREATE_TELLER <>", value, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerGreaterThan(String value) {
            addCriterion("CREATE_TELLER >", value, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TELLER >=", value, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerLessThan(String value) {
            addCriterion("CREATE_TELLER <", value, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TELLER <=", value, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerLike(String value) {
            addCriterion("CREATE_TELLER like", value, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerNotLike(String value) {
            addCriterion("CREATE_TELLER not like", value, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerIn(List<String> values) {
            addCriterion("CREATE_TELLER in", values, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerNotIn(List<String> values) {
            addCriterion("CREATE_TELLER not in", values, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerBetween(String value1, String value2) {
            addCriterion("CREATE_TELLER between", value1, value2, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateTellerNotBetween(String value1, String value2) {
            addCriterion("CREATE_TELLER not between", value1, value2, "createTeller");
            return (Criteria) this;
        }

        public Criteria andCreateUnitIsNull() {
            addCriterion("CREATE_UNIT is null");
            return (Criteria) this;
        }

        public Criteria andCreateUnitIsNotNull() {
            addCriterion("CREATE_UNIT is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUnitEqualTo(String value) {
            addCriterion("CREATE_UNIT =", value, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitNotEqualTo(String value) {
            addCriterion("CREATE_UNIT <>", value, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitGreaterThan(String value) {
            addCriterion("CREATE_UNIT >", value, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_UNIT >=", value, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitLessThan(String value) {
            addCriterion("CREATE_UNIT <", value, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitLessThanOrEqualTo(String value) {
            addCriterion("CREATE_UNIT <=", value, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitLike(String value) {
            addCriterion("CREATE_UNIT like", value, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitNotLike(String value) {
            addCriterion("CREATE_UNIT not like", value, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitIn(List<String> values) {
            addCriterion("CREATE_UNIT in", values, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitNotIn(List<String> values) {
            addCriterion("CREATE_UNIT not in", values, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitBetween(String value1, String value2) {
            addCriterion("CREATE_UNIT between", value1, value2, "createUnit");
            return (Criteria) this;
        }

        public Criteria andCreateUnitNotBetween(String value1, String value2) {
            addCriterion("CREATE_UNIT not between", value1, value2, "createUnit");
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