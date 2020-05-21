package com.aikan.service;

import java.util.List;

import com.aikan.entity.BookT;
import com.aikan.entity.BookTCondition;
import com.aikan.util.PageUtil;

public interface BookTService {
	/**
	 * 新增图书
	 * @param bookT
	 * @return 0-代表添加失败；1-代表添加成功；-1书籍已存在
	 */
	public int save(BookT bookT);
	/**
	 * 根据书籍编号删除图书
	 * @param bookId
	 * @return 0-代表删除失败；1-代表删除成功
	 */
	public int removeById(Integer bookId);
	/**
	 * 根据主键，删除一组书籍
	 * @param bookIds
	 * @return
	 * -1 -准备被删除的小说数和数据库中实际存在的小说数不一致，不能做删除操作;<br/>
	 * 0-代表删除失败；<br/>
	 * 1-代表部分删除成功；<br/>
	 * 2-代表删除成功；<br/>
	 */
	public int removeManyByIds(Integer[] bookIds);
	/**
	 * 编辑（修改）图书
	 * @param bookT
	 * @return 0-代表修改失败；1-代表修改成功； -1书籍不存在
	 */
	public int modify(BookT bookT);
	/**
	 * 根据书籍编号查找一本图书
	 * @param bookId
	 * @return BookT
	 */
	public BookT getById(Integer bookId);
	/**
	 * 查看所有书籍
	 * @return List<BookT>
	 */
	public List<BookT> getAll();
	/**
	 * 根据书籍种类查看所有书籍
	 * @param typeId
	 * @return List<BookT>
	 */
	public List<BookT> getByBookType(Integer typeId); 
	/**
	 * 男女爱看榜
	 * @param clicknumsAndSex
	 * @return List<BookT>
	 */
	public List<BookT> clickAndSex(String bookTypeSex);
	/**
	 * 根据书籍状态查看所有书籍
	 * @param bookState
	 * @return List<BookT>
	 */
	public List<BookT> getByBookState(String bookState); 
	/**
	 * 根据书名查找所有符合的图书
	 * @param bookName
	 * @return List<BookT>
	 */
	public List<BookT> getByBookName(String bookName);
	/**
	 * 查找新书
	 * @return List<BookT>
	 */
	public List<BookT> getByBookUpDate(Integer beginDate,Integer endDate);
	/**
	 * 根据条件和分页，查询记录集
	 * @param bookTCondition
	 * @return List<BookT>
	 */
	public List<BookT> getByConditionPage(BookTCondition bookTCondition,PageUtil pageUtil);
	/**
	 * 根据作者的名字，查找属于他的所有图书
	 * @param authorName
	 * @returnList<BookT>
	 */
	public List<BookT> getBooksByAuthorName(String authorName);
	/**
	 * 根据书籍名称返回书籍id，没有则自增。
	 * @param bookName
	 * @return int:书籍id
	 */
	public int bookNameToId(String bookName);
	/**
	 * 统计各个书籍种类数目
	 * @return List<Integer>
	 */
	public List<Integer> countBookNumsByType();

}
