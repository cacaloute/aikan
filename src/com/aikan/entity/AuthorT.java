package com.aikan.entity;

import java.io.Serializable;

public class AuthorT implements Serializable {
	private Integer authorId;			//���߱��
	private String authorName;			//��������
	private String authorImg;			//������Ƭ
	private String authorInfor;			//������Ϣ
	private Integer authorWorksNums;	//��Ʒ��
	private Integer authorWordNums;		//�ۼ�����
	private Integer fansNums;	//��Ʒ��
	
	public AuthorT(){
		
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorImg() {
		return authorImg;
	}

	public void setAuthorImg(String authorImg) {
		this.authorImg = authorImg;
	}

	public String getAuthorInfor() {
		return authorInfor;
	}

	public void setAuthorInfor(String authorInfor) {
		this.authorInfor = authorInfor;
	}

	public Integer getAuthorWorksNums() {
		return authorWorksNums;
	}

	public void setAuthorWorksNums(Integer authorWorksNums) {
		this.authorWorksNums = authorWorksNums;
	}

	public Integer getAuthorWordNums() {
		return authorWordNums;
	}

	public void setAuthorWordNums(Integer authorWordNums) {
		this.authorWordNums = authorWordNums;
	}

	public Integer getFansNums() {
		return fansNums;
	}

	public void setFansNums(Integer fansNums) {
		this.fansNums = fansNums;
	}

	@Override
	public String toString() {
		return "AuthorT [authorId=" + authorId + ", authorName=" + authorName
				+ ", authorImg=" + authorImg + ", authorInfor=" + authorInfor
				+ ", authorWorksNums=" + authorWorksNums + ", authorWordNums="
				+ authorWordNums + "]";
	}
}
