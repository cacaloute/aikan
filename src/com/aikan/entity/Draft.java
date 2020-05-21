package com.aikan.entity;

import java.io.Serializable;
/**
 * 稿子
 * @author Administrator
 *
 */
public class Draft implements Serializable {
	private Integer authorId;			//作者编号
	private String draftName;			//稿子名称
	private String draftImg;			//稿子封面
	private Integer draftId;			//稿子id
	
	private AuthorT authorT;			//作者
	
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
