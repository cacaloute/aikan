package com.aikan.entity;

import java.io.Serializable;

public class BookTCondition implements Serializable{
			
	private String bookName; 	//书名
	private String bookState;	//书籍状态（连载or完结）
	private Integer bookTypeId; //书籍种类编号
	private String authorName;	//书籍作者名称

	//分页查询的起点和终点设置
	private Integer beginIndex;
	private Integer endIndex;
	
	public BookTCondition(){
		
	}

	

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookState() {
		return bookState;
	}

	public void setBookState(String bookState) {
		this.bookState = bookState;
	}

	public Integer getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

}
