package com.aikan.entity;

import java.io.Serializable;
import java.util.List;

public class CommentT implements Serializable {
	private Integer commentId;			//评论编号
	private Integer userId;				//评论用户编号
	private String commentTitle;		//评论标题
	private String commentConnent;		//评论内容
	private String commentDate;			//评论日期
	private Integer bookId;				//评论对象（书籍编号）
	private Integer commentedId;		//被评论的评论编号
	private Integer parentId;			//父评论编号
	private String commentdiv;			//评论区
	
	private UserT userT;				//评论用户
	private UserT userTED;				//被评论的用户
	private BookT bookT;				//被评论的书籍
	private CommentT commentTED;		//被评论的评论
	
	private List<CommentT> childComments;	//该评论下的子评论
	
	public CommentT(){
		
	}

	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCommentTitle() {
		return commentTitle;
	}

	public void setCommentTitle(String commentTitle) {
		this.commentTitle = commentTitle;
	}

	public String getCommentConnent() {
		return commentConnent;
	}

	public void setCommentConnent(String commentConnent) {
		this.commentConnent = commentConnent;
	}

	public String getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Integer getCommentedId() {
		return commentedId;
	}

	public void setCommentedId(Integer commentedId) {
		this.commentedId = commentedId;
	}
	
	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public UserT getUserT() {
		return userT;
	}

	public void setUserT(UserT userT) {
		this.userT = userT;
	}

	public UserT getUserTED() {
		return userTED;
	}

	public void setUserTED(UserT userTED) {
		this.userTED = userTED;
	}

	public BookT getBookT() {
		return bookT;
	}

	public void setBookT(BookT bookT) {
		this.bookT = bookT;
	}

	public CommentT getCommentTED() {
		return commentTED;
	}

	public void setCommentTED(CommentT commentTED) {
		this.commentTED = commentTED;
	}

	
	public List<CommentT> getChildComments() {
		return childComments;
	}

	public void setChildComments(List<CommentT> childComments) {
		this.childComments = childComments;
	}

	public String getCommentdiv() {
		return commentdiv;
	}

	public void setCommentdiv(String commentdiv) {
		this.commentdiv = commentdiv;
	}

	@Override
	public String toString() {
		return "CommentT [commentId=" + commentId + ", userId=" + userId
				+ ", commentTitle=" + commentTitle + ", commentConnent="
				+ commentConnent + ", commentDate=" + commentDate + ", bookId="
				+ bookId + ", commentedId=" + commentedId + ", parentId="
				+ parentId + ", commentdiv=" + commentdiv + "]";
	}

}
