package com.mage.crm.vo;


import com.mage.crm.base.BaseVo;

public class Permission extends BaseVo {
	private Integer id;
	private Integer roleId;
	private Integer moduleId;
	private String aclValue;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getModuleId() {
		return moduleId;
	}
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}
	public String getAclValue() {
		return aclValue;
	}
	public void setAclValue(String aclValue) {
		this.aclValue = aclValue;
	}
	
	
	

}
