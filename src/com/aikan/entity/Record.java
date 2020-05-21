package com.aikan.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 阅读记录
 * @author msh10312
 *
 */
public class Record implements Serializable {
    private Integer recordId;           //阅读记录编号
    private Integer userId;             //用户编号
	private Integer bookId;				//书籍编号
	private String chapterName;			//章节名称
	private Integer chapterId;          //章节编号

	public Record(){
		
	}

    
    public Integer getRecordId(){
        return recordId;
    }

    
    public void setRecordId(Integer recordId){
        this.recordId = recordId;
    }

    
    public Integer getUserId(){
        return userId;
    }

    
    public void setUserId(Integer userId){
        this.userId = userId;
    }

    
    public Integer getBookId(){
        return bookId;
    }

    
    public void setBookId(Integer bookId){
        this.bookId = bookId;
    }

    
    public String getChapterName(){
        return chapterName;
    }

    
    public void setChapterName(String chapterName){
        this.chapterName = chapterName;
    }

    
    public Integer getChapterId(){
        return chapterId;
    }

    
    public void setChapterId(Integer chapterId){
        this.chapterId = chapterId;
    }


    @Override
    public String toString(){
        return "Record [recordId=" + recordId + ", userId=" + userId + ", bookId=" + bookId + ", chapterName=" + chapterName + ", chapterId=" + chapterId + "]";
    }
	
}
