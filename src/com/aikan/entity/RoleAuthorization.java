package com.aikan.entity;

import java.io.Serializable;
/**
 * 角色权限类
 * @author Administrator
 *
 */
public class RoleAuthorization implements Serializable {
	private Integer roleId;
	private Integer auId;
	private String auIdString;
	private String auName;
	private Integer auType;
	private Integer auParentId;
	private String auUrl;
	
	public RoleAuthorization(){
		
	}

	public String getAuUrl() {
		return auUrl;
	}

	public void setAuUrl(String auUrl) {
		this.auUrl = auUrl;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAuId() {
		return auId;
	}

	public void setAuId(Integer auId) {
		this.auId = auId;
	}

	public String getAuIdString() {
		this.auIdString=this.auId.toString();
		return auIdString;
	}

	public void setAuIdString(String auIdString) {
		this.auIdString = auIdString;
	}

	public String getAuName() {
		return auName;
	}

	public void setAuName(String auName) {
		this.auName = auName;
	}

	public Integer getAuType() {
		return auType;
	}

	public void setAuType(Integer auType) {
		this.auType = auType;
	}

	public Integer getAuParentId() {
		return auParentId;
	}

	public void setAuParentId(Integer auParentId) {
		this.auParentId = auParentId;
	}

	@Override
	public String toString() {
		return "RoleAuthorization [roleId=" + roleId + ", auId=" + auId
				+ ", auIdString=" + auIdString + ", auName=" + auName
				+ ", auType=" + auType + ", auParentId=" + auParentId
				+ ", auUrl=" + auUrl + "]";
	}

}
