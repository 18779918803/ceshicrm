package com.mage.crm.vo;

import com.mage.crm.base.BaseVo;

public class Module extends BaseVo {
	private Integer id;
	private String moduleName;
	private String moduleStyle;
	private String url;
	private Integer parentId;
	private Integer grade;
	private String optValue;
	private Integer orders;
	
	private String parentModuleName;
	
	public String getParentModuleName() {
		return parentModuleName;
	}
	public void setParentModuleName(String parentModuleName) {
		this.parentModuleName = parentModuleName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleStyle() {
		return moduleStyle;
	}
	public void setModuleStyle(String moduleStyle) {
		this.moduleStyle = moduleStyle;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getOptValue() {
		return optValue;
	}
	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	
	
	

}
