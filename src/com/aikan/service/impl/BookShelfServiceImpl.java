package com.aikan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikan.entity.BookShelf;
import com.aikan.entity.BookT;
import com.aikan.mapping.BookShelfMapper;
import com.aikan.mapping.BookTMapper;
import com.aikan.service.BookShelfService;
@Service("bookShelfServiceImpl")
public class BookShelfServiceImpl implements BookShelfService {
	
	@Autowired
	private BookShelfMapper bookShelfMapper;
	@Autowired
	private BookTMapper bookTMapper;
	
	/**
	 * 向书架新添图书记录
	 */
	@Override
	public int saveBookShelf(BookShelf bookShelf) {
		int result=0;

		List<BookShelf> bookShelfListExist=bookShelfMapper.selectManyBookShelf(bookShelf.getBsId());
		if(bookShelfListExist==null){
			result=bookShelfMapper.insertBookShelf(bookShelf);
		}else{
			Integer bookId=bookShelf.getBookId();
			for(int i=0;i<bookShelfListExist.size();i++){
				BookShelf bookShelfExist=bookShelfListExist.get(i);
				if(bookShelfExist.getBookId()==bookId){
					return 2;
				}
			}
			result=bookShelfMapper.insertBookShelf(bookShelf);
		}
		
		return result;
	}

	/**
	 * 从书架中删除多本书籍
	 */
	@Override
	public int removeManyBooks(Integer bsId, Integer[] bookIds) {
		int result=0;
		result=bookShelfMapper.deleteManyBooks(bsId,bookIds);
		if(result==0){
			//删除失败
			return 0;
		}
		return 1;
	}

	/**
	 * 根据用户编号，查询里面的所有的图书(书架)
	 */
	@Override
	public List<BookT> getAllBooks(Integer userId) {
		List<BookShelf> bookShelfList=bookShelfMapper.selectManyBookShelf(userId);
		List<BookT> bookTList=new ArrayList<>();
		for(int i=0;i<bookShelfList.size();i++){
			BookShelf bookShelfExist=bookShelfList.get(i);
			BookT bookT=bookTMapper.selectById(bookShelfExist.getBookId());
			bookTList.add(bookT);
		}
		return bookTList;
	}

	@Override
	public List<BookShelf> getOneBookShelf(Integer bsId) {
		
		return bookShelfMapper.selectManyBookShelf(bsId);
	}

}
