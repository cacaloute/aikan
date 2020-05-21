package com.aikan.entity;

import java.io.Serializable;

public class BookShelf implements Serializable {
	private Integer bsId;		// Èº‹±‡∫≈
	private Integer userId;		//”√ªß±‡∫≈
	private Integer bookId;		// ÈºÆ±‡∫≈
	
	public BookShelf(){
		
	}

	public Integer getBsId() {
		return bsId;
	}

	public void setBsId(Integer bsId) {
		this.bsId = bsId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	@Override
	public String toString() {
		return "BookShelf [bsId=" + bsId + ", userId=" + userId
				+ ", bookId=" + bookId + "]";
	}
}
