package com.aikan.entity;

import java.io.Serializable;

public class UserT implements Serializable {
	private Integer userId;			//�û����
	private String userName;		//�û�����
	private String userPassword;	//�û�����
	private String userEmail;		//�û�Email
	private String userImg;			//�û�ͷ��
	private String userSex;
	
	public UserT(){
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	@Override
	public String toString() {
		return "UserT [userId=" + userId + ", userName=" + userName
				+ ", userPassword=" + userPassword + ", userEmail=" + userEmail
				+ ", userImg=" + userImg + "]";
	}
	
}
