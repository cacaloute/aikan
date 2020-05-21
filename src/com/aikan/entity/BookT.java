package com.aikan.entity;

import java.io.Serializable;
import java.util.Date;

public class BookT implements Serializable {
	private Integer bookId;				//�鼮���
	private String bookName;			//�鼮���
	private Integer authorId;			//�鼮����id
	private String bookAuthor;			//�鼮��������
	private String bookImg;				//�鼮����
	private String bookState;			//�鼮״̬�����أ���ᣩ
	private Integer bookTypeId;			//�鼮����id
	private Integer bookWordNums;		//�鼮����
	private Integer bookChapterNums;	//�鼮�½���
	private String isFree;				//�Ƿ����
	private Integer bookClickNums;		//����������������
	private String bookInfor;			//�鼮��Ϣ�������ģ�
	
	private AuthorT authorT;			//�鼮����
	private BookType bookType;			//�鼮����
		    
	private Date bookUpDate;			//�ϴ�ʱ��
	
	public BookT(){
		
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getBookImg() {
		return bookImg;
	}

	public void setBookImg(String bookImg) {
		this.bookImg = bookImg;
	}


	public String getBookState() {
		return bookState;
	}

	public void setBookState(String bookState) {
		this.bookState = bookState;
	}

	

	public Integer getBookWordNums() {
		return bookWordNums;
	}

	public void setBookWordNums(Integer bookWordNums) {
		this.bookWordNums = bookWordNums;
	}

	public Integer getBookChapterNums() {
		return bookChapterNums;
	}

	public void setBookChapterNums(Integer bookChapterNums) {
		this.bookChapterNums = bookChapterNums;
	}

	public Integer getBookTypeId() {
		return bookTypeId;
	}

	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	public Integer getBookClickNums() {
		return bookClickNums;
	}

	public void setBookClickNums(Integer bookClickNums) {
		this.bookClickNums = bookClickNums;
	}

	public String getBookInfor() {
		return bookInfor;
	}

	public void setBookInfor(String bookInfor) {
		this.bookInfor = bookInfor;
	}

	public AuthorT getAuthorT() {
		return authorT;
	}

	public void setAuthorT(AuthorT authorT) {
		this.authorT = authorT;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public Date getBookUpDate() {
		return bookUpDate;
	}

	public void setBookUpDate(Date bookUpDate) {
		this.bookUpDate = bookUpDate;
	}

	@Override
	public String toString() {
		return "BookT [bookId=" + bookId + ", bookName=" + bookName
				+ ", bookAuthor=" + bookAuthor + ", bookImg=" + bookImg
				+ ", bookState=" + bookState
				+  ", bookWordNums="
				+ bookWordNums + ", bookChapterNums=" + bookChapterNums
				+ ", isFree=" + isFree + ", bookClickNums=" + bookClickNums				
				+ ", bookType=" + bookType + ", bookUpDate=" + bookUpDate + "]";
	}

	
}
