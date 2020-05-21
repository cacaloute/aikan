package com.aikan.mapping;

import java.util.List;

import com.aikan.entity.AuthorT;

public interface AuthorTMapper {
	/**
	 * 添加新作者
	 * @param authorT
	 * @return 0-代表添加失败；1-代表添加成功
	 */
	public int insert(AuthorT authorT);
	/**
	 * 删除作者
	 * @param authorId
	 * @return 0-代表删除失败；1-代表删除成功
	 */
	public int delete(Integer authorId);
	/**
	 * 批量删除作者
	 * @param authorIds
	 * @return int  0：删除失败；非0：删除成功
	 */
	public int deleteManyByIds(Integer[] authorIds);
	/**
	 * 
	 * @param authorT
	 * @return 0-代表更新失败；1-代表更新成功
	 */
	public int update(AuthorT authorT);
	/**
	 * 根据作者名字查找作者
	 * @param authorName
	 * @return AuthorT
	 */
	public AuthorT selectName(String authorName);
	/**
	 * 根据作者编号查找作者
	 * @param authorId
	 * @return AuthorT
	 */
	public AuthorT selectId(Integer authorId);
	/**
	 * 查找所有的作者
	 * @return List<AuthorT>
	 */
	public List<AuthorT> selectAllAuthorTs();
	/**
	 * 统计一共有多少个作者,max作者编号
	 * @return int
	 */
	public int countAuthorT();
}
