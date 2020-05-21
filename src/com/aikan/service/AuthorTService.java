package com.aikan.service;

import java.util.List;

import com.aikan.entity.AuthorT;

public interface AuthorTService {
	/**
	 * 添加新作者
	 * @param authorT
	 * @return -1:原id(作者)已经存在；0：添加失败；1：添加成功
	 */
	public int save(AuthorT authorT);
	/**
	 * 根据主键删除作者信息
	 * @param authorId
	 * @return -1:原id(作者)不存在；0：删除失败；1：删除成功
	 */
	public int remove(Integer authorId);
	/**
	 * 批量删除作者
	 * @param authorIds
	 * @return  0：删除失败；非0：删除成功
	 */
	public int removeManyByIds(Integer[] authorIds);
	/**
	 * 更新原始作者信息
	 * @param authorT
	 * @return -1:原id(作者)不存在；0：更新失败；1：更新成功
	 */
	public int modify(AuthorT authorT);
	/**
	 * 根据作者编号查找作者
	 * @param authorId
	 * @return AuthorT
	 */
	public AuthorT getById(Integer authorId);
	/**
	 * 根据作者名字查找作者
	 * @param authorName
	 * @return AuthorT
	 */
	public AuthorT getByName(String authorName);
	/**
	 * 得到所有的作者
	 * @return List<AuthorT>
	 */
	public List<AuthorT> getAllAuthorTs();
	/**
	 * 根据作者姓名返回作者id，没有则自增。
	 * @param authorName
	 * @return int:作者id
	 */
	public int authorNameToId(String authorName);
}
