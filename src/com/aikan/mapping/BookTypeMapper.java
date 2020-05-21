package com.aikan.mapping;

import java.util.List;

import com.aikan.entity.BookType;

public interface BookTypeMapper {
	/**
	 * 查询所有类型
	 * @return List<BookType>
	 */
	public List<BookType> selectAll();
	/**
	 * 根据书籍种类编号查找种类
	 * @param typeId
	 * @return BookType
	 */
	public BookType selectByTypeId(Integer typeId);
}
