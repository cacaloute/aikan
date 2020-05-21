package com.aikan.service;

import java.util.List;

import com.aikan.entity.AuthorT;

public interface AuthorTService {
	/**
	 * ���������
	 * @param authorT
	 * @return -1:ԭid(����)�Ѿ����ڣ�0�����ʧ�ܣ�1����ӳɹ�
	 */
	public int save(AuthorT authorT);
	/**
	 * ��������ɾ��������Ϣ
	 * @param authorId
	 * @return -1:ԭid(����)�����ڣ�0��ɾ��ʧ�ܣ�1��ɾ���ɹ�
	 */
	public int remove(Integer authorId);
	/**
	 * ����ɾ������
	 * @param authorIds
	 * @return  0��ɾ��ʧ�ܣ���0��ɾ���ɹ�
	 */
	public int removeManyByIds(Integer[] authorIds);
	/**
	 * ����ԭʼ������Ϣ
	 * @param authorT
	 * @return -1:ԭid(����)�����ڣ�0������ʧ�ܣ�1�����³ɹ�
	 */
	public int modify(AuthorT authorT);
	/**
	 * �������߱�Ų�������
	 * @param authorId
	 * @return AuthorT
	 */
	public AuthorT getById(Integer authorId);
	/**
	 * �����������ֲ�������
	 * @param authorName
	 * @return AuthorT
	 */
	public AuthorT getByName(String authorName);
	/**
	 * �õ����е�����
	 * @return List<AuthorT>
	 */
	public List<AuthorT> getAllAuthorTs();
	/**
	 * ��������������������id��û����������
	 * @param authorName
	 * @return int:����id
	 */
	public int authorNameToId(String authorName);
}
