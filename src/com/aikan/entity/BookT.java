package com.aikan.entity;

import java.io.Serializable;
import java.util.Date;

public class BookT implements Serializable {
	private Integer bookId;				//书籍编号
	private String bookName;			//书籍编号
	private Integer authorId;			//书籍作者id
	private String bookAuthor;			//书籍作者名字
	private String bookImg;				//书籍封面
	private String bookState;			//书籍状态（连载，完结）
	private Integer bookTypeId;			//书籍种类id
	private Integer bookWordNums;		//书籍字数
	private Integer bookChapterNums;	//书籍章节数
	private String isFree;				//是否免费
	private Integer bookClickNums;		//点击数（浏览次数）
	private String bookInfor;			//书籍信息（引导文）
	
	private AuthorT authorT;			//书籍作者
	private BookType bookType;			//书籍种类
		    
	private Date bookUpDate;			//上传时间
	
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
