package com.aikan.service;

import java.util.List;

import com.aikan.entity.CommentT;

public interface CommentTService {
	/**
	 * 添加评论
	 * @param commentT
	 * @return  0-代表添加失败；1-代表添加成功;-1 评论已存在
	 */
	public int save(CommentT commentT);
	/**
	 * 添加帖子
	 * @param commentT
	 * @return 0-代表添加失败；1-代表添加成功;-1 帖子已存在
	 */
	public int saveTiezi(CommentT commentT);
	/**
	 * 根据评论编号删除一条评论
	 * @param commentId
	 * @return 0:-代表删除失败；1:-代表删除成功；-1：评论不存在
	 */
	public int removeOne(Integer commentId);
	/**
	 * 根据评论编号删除一组旧评论
	 * @param commentIds
	 * @return 0-代表删除失败；1-代表删除成功
	 */
	public int removeManyByIds(Integer[] commentIds);
	/**
	 * 编辑旧的评论
	 * @param commentT
	 * @return 0-代表编辑失败；1-代表编辑成功；-1：评论不存在
	 */
	public int modify(CommentT commentT);
	/**
	 * 根据评论编号查找一条评论
	 * @param commentId
	 * @return CommentT
	 */
	public CommentT getOneCommentById(Integer commentId);
	/**
	 * 根据被评论书籍的Id,查询所有的评论
	 * @param bookId
	 * @return List<CommentT>
	 */
	public List<CommentT> getAllCommentsByBookId(Integer bookId);
	/**
	 * 根据父评论的评论Id,查询所有的评论
	 * @param parentId
	 * @return List<CommentT>
	 */
	public List<CommentT> getAllCommentsByParentId(Integer parentId);
	/**
	 * 根据评论区名查询帖子
	 * @param commentdiv
	 * @return List<CommentT>
	 */
	public List<CommentT> getAllCommentsByCommentDiv(String commentdiv);
	/**
	 * 查找所有
	 * @return List<CommentT>
	 */
	public List<CommentT> getAllComments();
	
}
