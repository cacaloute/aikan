package com.aikan.mapping;

import java.io.Serializable;
import java.util.List;

import com.aikan.entity.CommentT;

public interface CommentTMapper extends Serializable {
	/**
	 * 当前评论编号的最大值
	 * @return Integer
	 */
	public Integer maxComments();
	/**
	 * 添加评论
	 * @param commentT
	 * @return 0-代表添加失败；1-代表添加成功
	 */
	public int insert(CommentT commentT);
	/**
	 * 根据评论编号删除一条评论
	 * @param commentId
	 * @return 0-代表删除失败；1-代表删除成功
	 */
	public int deleteOne(Integer commentId);
	/**
	 * 根据评论编号删除一组旧评论
	 * @param commentIds
	 * @return 0-代表删除失败；1-代表删除成功
	 */
	public int deleteByIds(Integer[] commentIds);
	/**
	 * 编辑旧的评论
	 * @param commentT
	 * @return 0-代表编辑失败；1-代表编辑成功
	 */
	public int update(CommentT commentT);
	/**
	 * 根据评论编号查找评论
	 * @param commentId
	 * @return CommentT
	 */
	public CommentT selectOneCommentById(Integer commentId);
	/**
	 * 根据评论内容查找评论
	 * @param commentConnent
	 * @return CommentT
	 */
	public CommentT selectOneCommentByCommentConnent(CommentT commentT);
	/**
	 * 根据帖子标题查找帖子
	 * @param commentT
	 * @return CommentT
	 */
	public CommentT selectOneCommentByCommentTitle(CommentT commentT);
	/**
	 * 根据被评论书籍的Id,查询所有的评论
	 * @param bookId
	 * @return List<CommentT>
	 */
	public List<CommentT> selectAllCommentsBookId(Integer bookId);
	/**
	 * 根据父评论的评论Id,查询所有的评论
	 * @param commentedId
	 * @return List<CommentT>
	 */
	public List<CommentT> selectAllCommentsByParentId(Integer parentId);
	/**
	 * 根据评论区名称查找帖子
	 * @param commentdiv
	 * @return List<CommentT>
	 */
	public List<CommentT> selectAllCommentsByCommentDiv(String commentdiv);
	/**
	 * 查找所有
	 * @return List<CommentT>
	 */
	public List<CommentT> selectAllComments();
}
