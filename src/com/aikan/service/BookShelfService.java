package com.aikan.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aikan.entity.BookShelf;
import com.aikan.entity.BookT;

public interface BookShelfService {
	/**
	 * ���������ͼ���¼
	 * @param bookShelf
	 * @return -1:�鼮�Ѵ��� 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int saveBookShelf(BookShelf bookShelf);
	/**
	 * �������ɾ���౾�鼮
	 * @param bsId
	 * @param bookIds
	 * @return  0-����ɾ��ʧ�ܣ�<br/>
	 * 			1-����ɾ���ɹ���<br/>
	 */
	public int removeManyBooks(@Param(value="bsId")Integer bsId,@Param(value="bookIds")Integer[] bookIds);
	/**
	 * ������ܱ�Ų������(������¼)
	 * @param bsId
	 * @return List<BookShelf>
	 */
	public List<BookShelf> getOneBookShelf(Integer bsId);
	/**
	 * �����û���ţ���ѯ��������е�ͼ��(���)
	 * @param userId
	 * @return List<BookT>
	 */
	public List<BookT> getAllBooks(Integer userId);
}
