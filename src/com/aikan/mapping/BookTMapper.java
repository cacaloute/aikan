package com.aikan.mapping;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aikan.entity.AuthorT;
import com.aikan.entity.BookT;
import com.aikan.entity.BookTCondition;

public interface BookTMapper extends Serializable {
	/**
	 * ����ͼ��
	 * @param bookT
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int insert(BookT bookT);
	/**
	 * ɾ��ͼ��
	 * @param bookId
	 * @return 0-����ɾ��ʧ�ܣ�1-����ɾ���ɹ�
	 */
	public int delete(Integer bookId);
	/**
	 * ����������ɾ���鼮
	 * 
	 * @param bookIds
	 * @return 
	 * -1 -׼����ɾ����С˵�������ݿ���ʵ�ʴ��ڵ�С˵����һ�£�������ɾ������;<br/>
	 * 0-����ɾ��ʧ�ܣ�<br/>
	 * 1-����ɾ���ɹ���<br/>
	 */
	public int deleteByIds(Integer[] bookIds);
	/**
	 * �༭���޸ģ�ͼ��
	 * @param bookT
	 * @return 0-�����޸�ʧ�ܣ�1-�����޸ĳɹ�
	 */
	public int update(BookT bookT);
	/**
	 * �����鼮��Ų���һ��ͼ��
	 * @param bookId
	 * @return BookT
	 */
	public BookT selectById(Integer bookId);
	/**
	 * �����鼮���Ʋ���һ��ͼ��
	 * @param bookName
	 * @return BookT
	 */
	public BookT selectOneName(String bookName);
	/**
	 * �鿴�����鼮
	 * @return List<BookT>
	 */
	public List<BookT> selectAll();
	/**
	 * Ů������/��������
	 * @param clicksex
	 * @return
	 */
	public List<BookT> clicknumsandsex(String bookTypeSex);
	/**
	 * �����鼮����鿴�����鼮
	 * @param typeId
	 * @return List<BookT>
	 */
	public List<BookT> selectByBookType(Integer typeId);
	/**
	 * ��������
	 * @return List<BookT>
	 */							
	public List<BookT> selectByBookUpdate(@Param(value="beginDate")Integer beginDate,@Param(value="endDate")Integer endDate);
	/**
	 * �����鼮״̬�鿴�����鼮
	 * @param bookState
	 * @return List<BookT>
	 */
	public List<BookT> selectByBookState(String bookState);
	/**
	 * ���������������з��ϵ�ͼ��
	 * @param bookName
	 * @return List<BookT>
	 */
	public List<BookT> selectAllByName(String bookName);
	/**
	 * ���������ϲ�ѯ
	 * @param bookTCondition
	 * @return List<BookT>
	 */
	public List<BookT> selectByCondition(BookTCondition bookTCondition);
	
	/**
	 * ��������ͳ�Ƴ����ϵļ�¼��
	 * @param condition
	 * @return int
	 */
	public int count(BookTCondition condition);
	/**
	 * ͳ��һ���ж��ٱ��鼮,max�����
	 * @return int
	 */
	public int countBookT();
	/**
	 * �������ߵ����֣�����������������ͼ��
	 * @param authorName
	 * @return List<BookT>
	 */
	public List<BookT> selectBooksByAuthorName(String authorName);
	/**
	 * �����鼮����ͳ����Ŀ
	 * @param bookTypeId
	 * @return
	 */
	public int countBookByType(Integer bookTypeId);
	
}
