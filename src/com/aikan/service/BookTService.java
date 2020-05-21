package com.aikan.service;

import java.util.List;

import com.aikan.entity.BookT;
import com.aikan.entity.BookTCondition;
import com.aikan.util.PageUtil;

public interface BookTService {
	/**
	 * ����ͼ��
	 * @param bookT
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ���-1�鼮�Ѵ���
	 */
	public int save(BookT bookT);
	/**
	 * �����鼮���ɾ��ͼ��
	 * @param bookId
	 * @return 0-����ɾ��ʧ�ܣ�1-����ɾ���ɹ�
	 */
	public int removeById(Integer bookId);
	/**
	 * ����������ɾ��һ���鼮
	 * @param bookIds
	 * @return
	 * -1 -׼����ɾ����С˵�������ݿ���ʵ�ʴ��ڵ�С˵����һ�£�������ɾ������;<br/>
	 * 0-����ɾ��ʧ�ܣ�<br/>
	 * 1-������ɾ���ɹ���<br/>
	 * 2-����ɾ���ɹ���<br/>
	 */
	public int removeManyByIds(Integer[] bookIds);
	/**
	 * �༭���޸ģ�ͼ��
	 * @param bookT
	 * @return 0-�����޸�ʧ�ܣ�1-�����޸ĳɹ��� -1�鼮������
	 */
	public int modify(BookT bookT);
	/**
	 * �����鼮��Ų���һ��ͼ��
	 * @param bookId
	 * @return BookT
	 */
	public BookT getById(Integer bookId);
	/**
	 * �鿴�����鼮
	 * @return List<BookT>
	 */
	public List<BookT> getAll();
	/**
	 * �����鼮����鿴�����鼮
	 * @param typeId
	 * @return List<BookT>
	 */
	public List<BookT> getByBookType(Integer typeId); 
	/**
	 * ��Ů������
	 * @param clicknumsAndSex
	 * @return List<BookT>
	 */
	public List<BookT> clickAndSex(String bookTypeSex);
	/**
	 * �����鼮״̬�鿴�����鼮
	 * @param bookState
	 * @return List<BookT>
	 */
	public List<BookT> getByBookState(String bookState); 
	/**
	 * ���������������з��ϵ�ͼ��
	 * @param bookName
	 * @return List<BookT>
	 */
	public List<BookT> getByBookName(String bookName);
	/**
	 * ��������
	 * @return List<BookT>
	 */
	public List<BookT> getByBookUpDate(Integer beginDate,Integer endDate);
	/**
	 * ���������ͷ�ҳ����ѯ��¼��
	 * @param bookTCondition
	 * @return List<BookT>
	 */
	public List<BookT> getByConditionPage(BookTCondition bookTCondition,PageUtil pageUtil);
	/**
	 * �������ߵ����֣�����������������ͼ��
	 * @param authorName
	 * @returnList<BookT>
	 */
	public List<BookT> getBooksByAuthorName(String authorName);
	/**
	 * �����鼮���Ʒ����鼮id��û����������
	 * @param bookName
	 * @return int:�鼮id
	 */
	public int bookNameToId(String bookName);
	/**
	 * ͳ�Ƹ����鼮������Ŀ
	 * @return List<Integer>
	 */
	public List<Integer> countBookNumsByType();

}
