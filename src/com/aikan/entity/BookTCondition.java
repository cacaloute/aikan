package com.aikan.entity;

import java.io.Serializable;

public class BookTCondition implements Serializable{
			
	private String bookName; 	//����
	private String bookState;	//�鼮״̬������or��ᣩ
	private Integer bookTypeId; //�鼮������
	private String authorName;	//�鼮��������

	//��ҳ��ѯ�������յ�����
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
