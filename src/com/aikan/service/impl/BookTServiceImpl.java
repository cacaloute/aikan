package com.aikan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aikan.entity.BookT;
import com.aikan.entity.BookTCondition;
import com.aikan.mapping.AuthorTMapper;
import com.aikan.mapping.BookTMapper;
import com.aikan.service.AuthorTService;
import com.aikan.service.BookTService;
import com.aikan.util.PageUtil;


@Service("bookTServiceImpl")
public class BookTServiceImpl implements BookTService {
	@Autowired
	private BookTMapper bookTMapper;
	@Autowired
	private AuthorTMapper authorTMapper;//作者
	@Autowired
	private AuthorTService authorTService;
	
	@Override
	public int save(BookT bookT) {
		int result=0;
		BookT bookExist=bookTMapper.selectOneName(bookT.getBookName());
		if (bookExist != null) {
			return -1;
		}
		String authorName=bookT.getBookAuthor(); //按照书籍作者姓名，查找作者是否存在
		Integer authorId=authorTService.authorNameToId(authorName);
		bookT.setAuthorId(authorId);
		
		Integer bookId=bookTMapper.countBookT()+1;
		bookT.setBookId(bookId);//设置书籍编号
		result=bookTMapper.insert(bookT);
		return result;
	}

	@Override
	public int removeById(Integer bookId) {
		int result = 0;
		// 先检查book的bid是否已经存在，如果存在了，则删除；如果不存在，不能删除
		// 根据传递进来的小说对象的bid，去数据库中查找这个bid的小说是否存在
		BookT bookExist = bookTMapper.selectById(bookId);
		if (bookExist == null) {
			return -1;
		}

		result = bookTMapper.delete(bookId);
		return result;
	}

	@Override
	public int removeManyByIds(Integer[] bookIds) {
		int length = bookIds.length;

		int count = 0;
		for (int i = 0; i < length; i++) {
			Integer bid = bookIds[i];
			int result = bookTMapper.delete(bid);
			if (result != 0) {
				count++;
			}
		}
		if (count == length) {
			return 1;
		}
		return 0;
	}

	@Override
	public int modify(BookT bookT) {
		int result = 0;
		BookT bookExist = bookTMapper.selectById(bookT.getBookId());
		if (bookExist == null) {
			return -1;
		}

		// 修改基本信息时，页面提交的数据中book不涉及封面image和小说文件路径的修改
		bookT.setBookImg(bookExist.getBookImg());
		result = bookTMapper.update(bookT);
		return result;
	}

	@Override
	public BookT getById(Integer bookId) {
		// 
		return bookTMapper.selectById(bookId);
	}

	@Override
	public List<BookT> getAll() {
		//查看所有书籍
		return bookTMapper.selectAll();
	}

	@Override
	public List<BookT> getByBookType(Integer typeId) {
		// 根据书籍种类查看所有书籍
		return bookTMapper.selectByBookType(typeId);
	}

	@Override
	public List<BookT> getByBookState(String bookState) {
		// 根据书籍状态查看所有书籍
		return bookTMapper.selectByBookState(bookState);
	}

	@Override
	public List<BookT> getByBookName(String bookName) {
		// 根据书名查找所有符合的图书
		return bookTMapper.selectAllByName(bookName);
	}
//T
	@Override
	public List<BookT> getByConditionPage(BookTCondition bookTCondition,PageUtil pageUtil) {
		List<BookT> bookList = new ArrayList<>();
		// 根据条件统计出实际符合的记录数
		
		int count = bookTMapper.count(bookTCondition);
		
		// (有了记录数，才能算出有效的页码)
		pageUtil.setTotalCount(count);
		
		// 要合理控制有效页码
		if (pageUtil.getPageNo() < 1) {
			pageUtil.setPageNo(1);
		} else if (pageUtil.getPageNo() > pageUtil.getTotalPages()) {
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		
		// 把实际的分页信息设置给条件对象
		bookTCondition.setBeginIndex((pageUtil.getPageNo() - 1)
				* pageUtil.getPageSize() + 1);
		bookTCondition.setEndIndex(pageUtil.getPageNo() * pageUtil.getPageSize());
		
		// 控制有效的页码值后，才能做查询
		bookList = bookTMapper.selectByCondition(bookTCondition);
		
		return bookList;
	}

	@Override
	public List<BookT> getBooksByAuthorName(String authorName) {
		// 根据作者的名字，查找属于他的所有图书
		return bookTMapper.selectBooksByAuthorName(authorName);
	}

	@Override
	public int bookNameToId(String bookName) {
		BookT bookTExist=bookTMapper.selectOneName(bookName);
		if(bookTExist!=null){
			return bookTExist.getBookId();
		}
		int count=bookTMapper.countBookT()+1;
		return count;
	}

	@Override
	public List<BookT> getByBookUpDate(Integer beginDate,Integer endDate) {
		List<BookT> books=bookTMapper.selectByBookUpdate(beginDate,endDate);
		return books;
	}

	@Override
	public List<BookT> clickAndSex(String bookTypeSex) {
		List<BookT> booklists=bookTMapper.clicknumsandsex(bookTypeSex);
		return booklists;
	}

	@Override
	public List<Integer> countBookNumsByType() {
		List<Integer> lists=new ArrayList<>();
		for(int i=1;i<=8;i++){
			//根据书籍种类统计数目
			Integer bookTypeNums=bookTMapper.countBookByType(i);
			lists.add(bookTypeNums);
		}
		return lists;
	}

}
