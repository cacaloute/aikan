package com.aikan.mapping;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aikan.entity.BookShelf;

public interface BookShelfMapper extends Serializable {
	
	/**
	 * 向书架新添图书
	 * @param BookShelf
	 * @return 0-代表添加失败；1-代表添加成功
	 */
	public int insertBookShelf(BookShelf bookShelf);
	/**
	 * 从书架中删除多本书籍
	 * @param bookShelfs
	 * @return
	 * 0-代表删除失败；
	 * 1-代表部分删除成功；
	 * 2-代表删除成功；
	 */
	public int deleteManyBooks(@Param(value="bsId")Integer bsId,@Param(value="bookIds")Integer[] bookIds);
	/**
	 * 根据书架编号查找书架(多条记录)
	 * @param bsId
	 * @return List<BookShelf>
	 */
	public List<BookShelf> selectManyBookShelf(Integer bsId);
	
}
