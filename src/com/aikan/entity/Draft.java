package com.aikan.entity;

import java.io.Serializable;
/**
 * ����
 * @author Administrator
 *
 */
public class Draft implements Serializable {
	private Integer authorId;			//���߱��
	private String draftName;			//��������
	private String draftImg;			//���ӷ���
	private Integer draftId;			//����id
	
	private AuthorT authorT;			//����
	
	public Draft(){
		
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getDraftName() {
		return draftName;
	}

	public void setDraftName(String draftName) {
		this.draftName = draftName;
	}

	public String getDraftImg() {
		return draftImg;
	}

	public void setDraftImg(String draftImg) {
		this.draftImg = draftImg;
	}

	public Integer getDraftId() {
		return draftId;
	}

	public void setDraftId(Integer draftId) {
		this.draftId = draftId;
	}

	public AuthorT getAuthorT() {
		return authorT;
	}

	public void setAuthorT(AuthorT authorT) {
		this.authorT = authorT;
	}

	
}
