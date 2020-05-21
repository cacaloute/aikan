package com.aikan.service;

import com.aikan.entity.BookType;

public interface BookTypeService {
	/**
	 * 根据书籍种类编号，查找种类
	 * @param typeId
	 * @return
	 */
	public BookType getBookTypeByTypeId(Integer typeId);
}
