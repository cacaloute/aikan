package com.aikan.mapping;

import java.util.List;

import com.aikan.entity.BookType;

public interface BookTypeMapper {
	/**
	 * ��ѯ��������
	 * @return List<BookType>
	 */
	public List<BookType> selectAll();
	/**
	 * �����鼮�����Ų�������
	 * @param typeId
	 * @return BookType
	 */
	public BookType selectByTypeId(Integer typeId);
}
