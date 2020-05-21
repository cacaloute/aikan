package com.aikan.entity;

import java.io.Serializable;

public class BookType implements Serializable{

	private Integer bookTypeId;
	private String bookTypeName;
	private String bookTypeSex;
	
	public BookType(){
		
	}

	public Integer getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public String getBookTypeName() {
		return bookTypeName;
	}

	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}

	public String getBookTypeSex() {
		return bookTypeSex;
	}

	public void setBookTypeSex(String bookTypeSex) {
		this.bookTypeSex = bookTypeSex;
	}

	@Override
	public String toString() {
		return "BookType [bookTypeId=" + bookTypeId + ", bookTypeName="
				+ bookTypeName + ", bookTypeSex=" + bookTypeSex + "]";
	}
	
}
