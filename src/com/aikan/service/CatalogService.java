package com.aikan.service;

import java.util.List;

import com.aikan.entity.Catalog;

public interface CatalogService {
	/**
	 * 新增图书目录
	 * @param catalog
	 * @return 0-代表添加失败；1-代表添加成功
	 */
	public int save(Catalog catalog);
	/**
	 * 根据目录编号和章节编号（联合主键）查找一条目录记录（某一章）
	 * @param catalog
	 * @return Catalog
	 */
	public Catalog getOneById(Catalog catalog);
	/**
	 * 根据目录表id，查找所有的章节
	 * @param catalogId
	 * @return List<Catalog>
	 */
	public List<Catalog> getAllChapter(Integer catalogId);
	/**
	 * 修改（编辑）某一条目录记录（一章）
	 * @param catalog
	 * @return 0：修改失败 1：修改成功
	 */
	public int modify(Catalog catalog);
	/**
	 * 删除一条目录记录（一章）
	 * @param catalog
	 * @return -1：不存在  0：删除失败   1：删除成功
	 */
	public int removeOne(Catalog catalog);
	/**
	 * 根据章节编号删除一组章节
	 * @param bookChapterIds
	 * @param catalogId
	 * @return  0-代表删除失败；<br/>
	 * 			1-代表删除成功；<br/>
	 */
	public int removeManyChapter(Integer[] bookChapterIds,Integer catalogId);

}
