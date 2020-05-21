package com.aikan.entity;

import java.io.Serializable;

public class Catalog implements Serializable {
	private Integer catalogId;
	private Integer bookId;			//�鼮���
	private Integer bookChapterId;	//�½ڱ��
	private String bookChapterName;	//�½�����
	
	public Catalog(){
		
	}
	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}
	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getBookChapterId() {
		return bookChapterId;
	}

	public void setBookChapterId(Integer bookChapterId) {
		this.bookChapterId = bookChapterId;
	}

	public String getBookChapterName() {
		return bookChapterName;
	}

	public void setBookChapterName(String bookChapterName) {
		this.bookChapterName = bookChapterName;
	}
	@Override
	public String toString() {
		return "Catalog [catalogId=" + catalogId + ", bookId=" + bookId
				+ ", bookChapterId=" + bookChapterId + ", bookChapterName="
				+ bookChapterName + "]";
	}
}
