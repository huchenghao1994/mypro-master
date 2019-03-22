package com.yaspeed.web.pojo;


public class OperationLog {
	private Long id;

	private String className;

	private String methodName;

	private String sqlDesc;

	private String paraDesc;

	private Integer influenceRowNumber;

	private String deviceName;

	private String browserName;

	private String userAgent;

	private String controlUri;

	private String visitIp;

	private String createDate;

	private String createTime;

	private String createTeller;
	private String createTellerChname;

	private String createUnit;
	private String createUnitChname;

	public String getCreateTellerChname() {
		return createTellerChname;
	}

	public void setCreateTellerChname(String createTellerChname) {
		this.createTellerChname = createTellerChname;
	}

	public String getCreateUnitChname() {
		return createUnitChname;
	}

	public void setCreateUnitChname(String createUnitChname) {
		this.createUnitChname = createUnitChname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName == null ? null : methodName.trim();
	}

	public String getSqlDesc() {
		return sqlDesc;
	}

	public void setSqlDesc(String sqlDesc) {
		this.sqlDesc = sqlDesc == null ? null : sqlDesc.trim();
	}

	public String getParaDesc() {
		return paraDesc;
	}

	public void setParaDesc(String paraDesc) {
		this.paraDesc = paraDesc == null ? null : paraDesc.trim();
	}

	public Integer getInfluenceRowNumber() {
		return influenceRowNumber;
	}

	public void setInfluenceRowNumber(Integer influenceRowNumber) {
		this.influenceRowNumber = influenceRowNumber;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName == null ? null : deviceName.trim();
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName == null ? null : browserName.trim();
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent == null ? null : userAgent.trim();
	}

	public String getControlUri() {
		return controlUri;
	}

	public void setControlUri(String controlUri) {
		this.controlUri = controlUri == null ? null : controlUri.trim();
	}

	public String getVisitIp() {
		return visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp == null ? null : visitIp.trim();
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate == null ? null : createDate.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime == null ? null : createTime.trim();
	}

	public String getCreateTeller() {
		return createTeller;
	}

	public void setCreateTeller(String createTeller) {
		this.createTeller = createTeller == null ? null : createTeller.trim();
	}

	public String getCreateUnit() {
		return createUnit;
	}

	public void setCreateUnit(String createUnit) {
		this.createUnit = createUnit == null ? null : createUnit.trim();
	}
}