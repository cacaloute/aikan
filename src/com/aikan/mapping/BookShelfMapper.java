package com.aikan.mapping;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aikan.entity.BookShelf;

public interface BookShelfMapper extends Serializable {
	
	/**
	 * ���������ͼ��
	 * @param BookShelf
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int insertBookShelf(BookShelf bookShelf);
	/**
	 * �������ɾ���౾�鼮
	 * @param bookShelfs
	 * @return
	 * 0-����ɾ��ʧ�ܣ�
	 * 1-������ɾ���ɹ���
	 * 2-����ɾ���ɹ���
	 */
	public int deleteManyBooks(@Param(value="bsId")Integer bsId,@Param(value="bookIds")Integer[] bookIds);
	/**
	 * ������ܱ�Ų������(������¼)
	 * @param bsId
	 * @return List<BookShelf>
	 */
	public List<BookShelf> selectManyBookShelf(Integer bsId);
	
}
