package com.aikan.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * ½ÇÉ«Àà
 * @author Administrator
 *
 */
public class RoleT implements Serializable {
	private Integer roleId;
	private String roleName;
	private String roleInfor;
	private List<AuthorizationT> authorizations=new ArrayList<>();
	
	public RoleT(){
		
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleInfor() {
		return roleInfor;
	}

	public void setRoleInfor(String roleInfor) {
		this.roleInfor = roleInfor;
	}

	public List<AuthorizationT> getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(List<AuthorizationT> authorizations) {
		this.authorizations = authorizations;
	}
	
}
