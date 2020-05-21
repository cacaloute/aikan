package com.aikan.service;

import java.util.List;

import com.aikan.entity.CommentT;

public interface CommentTService {
	/**
	 * �������
	 * @param commentT
	 * @return  0-�������ʧ�ܣ�1-������ӳɹ�;-1 �����Ѵ���
	 */
	public int save(CommentT commentT);
	/**
	 * �������
	 * @param commentT
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�;-1 �����Ѵ���
	 */
	public int saveTiezi(CommentT commentT);
	/**
	 * �������۱��ɾ��һ������
	 * @param commentId
	 * @return 0:-����ɾ��ʧ�ܣ�1:-����ɾ���ɹ���-1�����۲�����
	 */
	public int removeOne(Integer commentId);
	/**
	 * �������۱��ɾ��һ�������
	 * @param commentIds
	 * @return 0-����ɾ��ʧ�ܣ�1-����ɾ���ɹ�
	 */
	public int removeManyByIds(Integer[] commentIds);
	/**
	 * �༭�ɵ�����
	 * @param commentT
	 * @return 0-����༭ʧ�ܣ�1-����༭�ɹ���-1�����۲�����
	 */
	public int modify(CommentT commentT);
	/**
	 * �������۱�Ų���һ������
	 * @param commentId
	 * @return CommentT
	 */
	public CommentT getOneCommentById(Integer commentId);
	/**
	 * ���ݱ������鼮��Id,��ѯ���е�����
	 * @param bookId
	 * @return List<CommentT>
	 */
	public List<CommentT> getAllCommentsByBookId(Integer bookId);
	/**
	 * ���ݸ����۵�����Id,��ѯ���е�����
	 * @param parentId
	 * @return List<CommentT>
	 */
	public List<CommentT> getAllCommentsByParentId(Integer parentId);
	/**
	 * ��������������ѯ����
	 * @param commentdiv
	 * @return List<CommentT>
	 */
	public List<CommentT> getAllCommentsByCommentDiv(String commentdiv);
	/**
	 * ��������
	 * @return List<CommentT>
	 */
	public List<CommentT> getAllComments();
	
}
