package com.aikan.mapping;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aikan.entity.AuthorT;
import com.aikan.entity.BookT;
import com.aikan.entity.BookTCondition;

public interface BookTMapper extends Serializable {
	/**
	 * 新增图书
	 * @param bookT
	 * @return 0-代表添加失败；1-代表添加成功
	 */
	public int insert(BookT bookT);
	/**
	 * 删除图书
	 * @param bookId
	 * @return 0-代表删除失败；1-代表删除成功
	 */
	public int delete(Integer bookId);
	/**
	 * 根据主键，删除书籍
	 * 
	 * @param bookIds
	 * @return 
	 * -1 -准备被删除的小说数和数据库中实际存在的小说数不一致，不能做删除操作;<br/>
	 * 0-代表删除失败；<br/>
	 * 1-代表删除成功；<br/>
	 */
	public int deleteByIds(Integer[] bookIds);
	/**
	 * 编辑（修改）图书
	 * @param bookT
	 * @return 0-代表修改失败；1-代表修改成功
	 */
	public int update(BookT bookT);
	/**
	 * 根据书籍编号查找一本图书
	 * @param bookId
	 * @return BookT
	 */
	public BookT selectById(Integer bookId);
	/**
	 * 根据书籍名称查找一本图书
	 * @param bookName
	 * @return BookT
	 */
	public BookT selectOneName(String bookName);
	/**
	 * 查看所有书籍
	 * @return List<BookT>
	 */
	public List<BookT> selectAll();
	/**
	 * 女生爱看/男生爱看
	 * @param clicksex
	 * @return
	 */
	public List<BookT> clicknumsandsex(String bookTypeSex);
	/**
	 * 根据书籍种类查看所有书籍
	 * @param typeId
	 * @return List<BookT>
	 */
	public List<BookT> selectByBookType(Integer typeId);
	/**
	 * 查找新书
	 * @return List<BookT>
	 */							
	public List<BookT> selectByBookUpdate(@Param(value="beginDate")Integer beginDate,@Param(value="endDate")Integer endDate);
	/**
	 * 根据书籍状态查看所有书籍
	 * @param bookState
	 * @return List<BookT>
	 */
	public List<BookT> selectByBookState(String bookState);
	/**
	 * 根据书名查找所有符合的图书
	 * @param bookName
	 * @return List<BookT>
	 */
	public List<BookT> selectAllByName(String bookName);
	/**
	 * 多条件复合查询
	 * @param bookTCondition
	 * @return List<BookT>
	 */
	public List<BookT> selectByCondition(BookTCondition bookTCondition);
	
	/**
	 * 根据条件统计出符合的记录数
	 * @param condition
	 * @return int
	 */
	public int count(BookTCondition condition);
	/**
	 * 统计一共有多少本书籍,max最大编号
	 * @return int
	 */
	public int countBookT();
	/**
	 * 根据作者的名字，查找属于他的所有图书
	 * @param authorName
	 * @return List<BookT>
	 */
	public List<BookT> selectBooksByAuthorName(String authorName);
	/**
	 * 根据书籍种类统计数目
	 * @param bookTypeId
	 * @return
	 */
	public int countBookByType(Integer bookTypeId);
	
}
