package com.aikan.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * »®œﬁ¿‡
 * @author Administrator
 *
 */
public class AuthorizationT implements Serializable {
	private Integer aId;
	private String aName;
	private String aUrl;
	private Integer aType;
	private Integer aParentId;
	private Integer hasaId;
	private List<AuthorizationT> childAuthorizations=new ArrayList<>();
	
	public AuthorizationT(){
		
	}

	public Integer getaId() {
		return aId;
	}

	public void setaId(Integer aId) {
		this.aId = aId;
	}

	public String getaName() {
		return aName;
	}

	public void setaName(String aName) {
		this.aName = aName;
	}

	public String getaUrl() {
		return aUrl;
	}

	public void setaUrl(String aUrl) {
		this.aUrl = aUrl;
	}

	public Integer getaType() {
		return aType;
	}

	public void setaType(Integer aType) {
		this.aType = aType;
	}

	public Integer getaParentId() {
		return aParentId;
	}

	public void setaParentId(Integer aParentId) {
		this.aParentId = aParentId;
	}


	public Integer getHasaId() {
		return hasaId;
	}

	public void setHasaId(Integer hasaId) {
		this.hasaId = hasaId;
	}

	public List<AuthorizationT> getChildAuthorizations() {
		return childAuthorizations;
	}

	public void setChildAuthorizations(List<AuthorizationT> childAuthorizations) {
		this.childAuthorizations = childAuthorizations;
	}

	@Override
	public String toString() {
		return "AuthorizationT [aId=" + aId + ", aName=" + aName + ", aUrl="
				+ aUrl + ", aType=" + aType + ", aParentId=" + aParentId
				+ ", hasaId=" + hasaId + ", childAuthorizations="
				+ childAuthorizations + "]";
	}
	
}
