package com.aikan.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * ����Ա��
 * @author Administrator
 *
 */
public class ManagerT implements Serializable {
	private Integer managerId;			//����Ա���
	private String managerName;			//����Ա����
	private String managerPassword;		//����Ա����
	private Integer roleId;
	
	private RoleT roleT; 				//��ɫ
	
	private Map<String, List<RoleAuthorization>> menuTree=new HashMap<String,List<RoleAuthorization>>();
	private List<String> authorUrls=new ArrayList<>();
	
	public ManagerT(){
		
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

	public RoleT getRoleT() {
		return roleT;
	}

	public void setRoleT(RoleT roleT) {
		this.roleT = roleT;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Map<String, List<RoleAuthorization>> getMenuTree() {
		return menuTree;
	}

	public void setMenuTree(Map<String, List<RoleAuthorization>> menuTree) {
		this.menuTree = menuTree;
	}

	public List<String> getAuthorUrls() {
		return authorUrls;
	}

	public void setAuthorUrls(List<String> authorUrls) {
		this.authorUrls = authorUrls;
	}

	@Override
	public String toString() {
		return "ManagerT [managerId=" + managerId + ", managerName="
				+ managerName + ", managerPassword=" + managerPassword + "]";
	}
}
