package com.aikan.service;

import com.aikan.entity.BookType;

public interface BookTypeService {
	/**
	 * �����鼮�����ţ���������
	 * @param typeId
	 * @return
	 */
	public BookType getBookTypeByTypeId(Integer typeId);
}
