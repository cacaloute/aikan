package com.aikan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikan.entity.BookType;
import com.aikan.mapping.BookTypeMapper;
import com.aikan.service.BookTypeService;

@Service("bookTypeServiceImpl")
public class BookTypeServiceImpl implements BookTypeService {
	
	@Autowired
	private BookTypeMapper bookTypeMapper;
	
	@Override
	public BookType getBookTypeByTypeId(Integer typeId) {
		//根据书籍种类编号查找种类
		BookType bookType=bookTypeMapper.selectByTypeId(typeId);
		return bookType;
	}

}
