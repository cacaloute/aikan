package com.aikan.mapping;

import java.io.Serializable;
import java.util.List;

import com.aikan.entity.CommentT;

public interface CommentTMapper extends Serializable {
	/**
	 * ��ǰ���۱�ŵ����ֵ
	 * @return Integer
	 */
	public Integer maxComments();
	/**
	 * �������
	 * @param commentT
	 * @return 0-�������ʧ�ܣ�1-������ӳɹ�
	 */
	public int insert(CommentT commentT);
	/**
	 * �������۱��ɾ��һ������
	 * @param commentId
	 * @return 0-����ɾ��ʧ�ܣ�1-����ɾ���ɹ�
	 */
	public int deleteOne(Integer commentId);
	/**
	 * �������۱��ɾ��һ�������
	 * @param commentIds
	 * @return 0-����ɾ��ʧ�ܣ�1-����ɾ���ɹ�
	 */
	public int deleteByIds(Integer[] commentIds);
	/**
	 * �༭�ɵ�����
	 * @param commentT
	 * @return 0-����༭ʧ�ܣ�1-����༭�ɹ�
	 */
	public int update(CommentT commentT);
	/**
	 * �������۱�Ų�������
	 * @param commentId
	 * @return CommentT
	 */
	public CommentT selectOneCommentById(Integer commentId);
	/**
	 * �����������ݲ�������
	 * @param commentConnent
	 * @return CommentT
	 */
	public CommentT selectOneCommentByCommentConnent(CommentT commentT);
	/**
	 * �������ӱ����������
	 * @param commentT
	 * @return CommentT
	 */
	public CommentT selectOneCommentByCommentTitle(CommentT commentT);
	/**
	 * ���ݱ������鼮��Id,��ѯ���е�����
	 * @param bookId
	 * @return List<CommentT>
	 */
	public List<CommentT> selectAllCommentsBookId(Integer bookId);
	/**
	 * ���ݸ����۵�����Id,��ѯ���е�����
	 * @param commentedId
	 * @return List<CommentT>
	 */
	public List<CommentT> selectAllCommentsByParentId(Integer parentId);
	/**
	 * �������������Ʋ�������
	 * @param commentdiv
	 * @return List<CommentT>
	 */
	public List<CommentT> selectAllCommentsByCommentDiv(String commentdiv);
	/**
	 * ��������
	 * @return List<CommentT>
	 */
	public List<CommentT> selectAllComments();
}
