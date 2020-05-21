package com.aikan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aikan.entity.BookShelf;
import com.aikan.entity.BookT;

public interface BookShelfService {
	/**
	 * 向书架新添图书记录
	 * @param bookShelf
	 * @return -1:书籍已存在 0-代表添加失败；1-代表添加成功
	 */
	public int saveBookShelf(BookShelf bookShelf);
	/**
	 * 从书架中删除多本书籍
	 * @param bsId
	 * @param bookIds
	 * @return  0-代表删除失败；<br/>
	 * 			1-代表删除成功；<br/>
	 */
	public int removeManyBooks(@Param(value="bsId")Integer bsId,@Param(value="bookIds")Integer[] bookIds);
	/**
	 * 根据书架编号查找书架(多条记录)
	 * @param bsId
	 * @return List<BookShelf>
	 */
	public List<BookShelf> getOneBookShelf(Integer bsId);
	/**
	 * 根据用户编号，查询里面的所有的图书(书架)
	 * @param userId
	 * @return List<BookT>
	 */
	public List<BookT> getAllBooks(Integer userId);
}
