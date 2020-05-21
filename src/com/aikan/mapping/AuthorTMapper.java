package com.aikan.mapping;

import java.util.List;

import com.aikan.entity.AuthorT;

public interface AuthorTMapper {
	/**
	 * ���������
	 * @param authorT
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int insert(AuthorT authorT);
	/**
	 * ɾ������
	 * @param authorId
	 * @return 0-����ɾ��ʧ�ܣ�1-����ɾ���ɹ�
	 */
	public int delete(Integer authorId);
	/**
	 * ����ɾ������
	 * @param authorIds
	 * @return int  0��ɾ��ʧ�ܣ���0��ɾ���ɹ�
	 */
	public int deleteManyByIds(Integer[] authorIds);
	/**
	 * 
	 * @param authorT
	 * @return 0-�������ʧ�ܣ�1-������³ɹ�
	 */
	public int update(AuthorT authorT);
	/**
	 * �����������ֲ�������
	 * @param authorName
	 * @return AuthorT
	 */
	public AuthorT selectName(String authorName);
	/**
	 * �������߱�Ų�������
	 * @param authorId
	 * @return AuthorT
	 */
	public AuthorT selectId(Integer authorId);
	/**
	 * �������е�����
	 * @return List<AuthorT>
	 */
	public List<AuthorT> selectAllAuthorTs();
	/**
	 * ͳ��һ���ж��ٸ�����,max���߱��
	 * @return int
	 */
	public int countAuthorT();
}
