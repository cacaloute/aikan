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
	private AuthorTMapper authorTMapper;//����
	@Autowired
	private AuthorTService authorTService;
	
	@Override
	public int save(BookT bookT) {
		int result=0;
		BookT bookExist=bookTMapper.selectOneName(bookT.getBookName());
		if (bookExist != null) {
			return -1;
		}
		String authorName=bookT.getBookAuthor(); //�����鼮�������������������Ƿ����
		Integer authorId=authorTService.authorNameToId(authorName);
		bookT.setAuthorId(authorId);
		
		Integer bookId=bookTMapper.countBookT()+1;
		bookT.setBookId(bookId);//�����鼮���
		result=bookTMapper.insert(bookT);
		return result;
	}

	@Override
	public int removeById(Integer bookId) {
		int result = 0;
		// �ȼ��book��bid�Ƿ��Ѿ����ڣ���������ˣ���ɾ������������ڣ�����ɾ��
		// ���ݴ��ݽ�����С˵�����bid��ȥ���ݿ��в������bid��С˵�Ƿ����
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

		// �޸Ļ�����Ϣʱ��ҳ���ύ��������book���漰����image��С˵�ļ�·�����޸�
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
		//�鿴�����鼮
		return bookTMapper.selectAll();
	}

	@Override
	public List<BookT> getByBookType(Integer typeId) {
		// �����鼮����鿴�����鼮
		return bookTMapper.selectByBookType(typeId);
	}

	@Override
	public List<BookT> getByBookState(String bookState) {
		// �����鼮״̬�鿴�����鼮
		return bookTMapper.selectByBookState(bookState);
	}

	@Override
	public List<BookT> getByBookName(String bookName) {
		// ���������������з��ϵ�ͼ��
		return bookTMapper.selectAllByName(bookName);
	}
//T
	@Override
	public List<BookT> getByConditionPage(BookTCondition bookTCondition,PageUtil pageUtil) {
		List<BookT> bookList = new ArrayList<>();
		// ��������ͳ�Ƴ�ʵ�ʷ��ϵļ�¼��
		
		int count = bookTMapper.count(bookTCondition);
		
		// (���˼�¼�������������Ч��ҳ��)
		pageUtil.setTotalCount(count);
		
		// Ҫ���������Чҳ��
		if (pageUtil.getPageNo() < 1) {
			pageUtil.setPageNo(1);
		} else if (pageUtil.getPageNo() > pageUtil.getTotalPages()) {
			pageUtil.setPageNo(pageUtil.getTotalPages());
		}
		
		// ��ʵ�ʵķ�ҳ��Ϣ���ø���������
		bookTCondition.setBeginIndex((pageUtil.getPageNo() - 1)
				* pageUtil.getPageSize() + 1);
		bookTCondition.setEndIndex(pageUtil.getPageNo() * pageUtil.getPageSize());
		
		// ������Ч��ҳ��ֵ�󣬲�������ѯ
		bookList = bookTMapper.selectByCondition(bookTCondition);
		
		return bookList;
	}

	@Override
	public List<BookT> getBooksByAuthorName(String authorName) {
		// �������ߵ����֣�����������������ͼ��
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
			//�����鼮����ͳ����Ŀ
			Integer bookTypeNums=bookTMapper.countBookByType(i);
			lists.add(bookTypeNums);
		}
		return lists;
	}

}
