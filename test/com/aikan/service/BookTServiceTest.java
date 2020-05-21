package com.aikan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aikan.entity.AuthorT;
import com.aikan.entity.BookT;
import com.aikan.entity.BookTCondition;
import com.aikan.entity.BookType;
import com.aikan.util.CommonsUtil;
import com.aikan.util.PageUtil;


//使用JUnit单元测试
@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring的主配置文件并解析，初始化容器中的对象
@ContextConfiguration(locations = { "classpath*:configure/spring/spring-*.xml"})
public class BookTServiceTest {

	// 自动绑定注入被测试的实际对象
	@Resource(name = "bookTServiceImpl")
	private BookTService bookTService;
	@Resource(name="authorTServiceImpl")
	private AuthorTService authorTService;

	@Test
	public void getBooks() {
		List<BookT> books = new ArrayList<>();
		books = bookTService.getAll();
		for (int i = 0; i < books.size(); i++) {
			BookT bookTExist=books.get(i);
			System.out.println(bookTExist);
		}
	}

	@Test
	public void getOneBook() {
		BookT bookT = bookTService.getById(1);
		System.out.println(bookT);

	}

	@Test
	public void testSearchName() {

		BookTCondition bookTcondition = new BookTCondition();
		bookTcondition.setBookName("%"+"无"+"%");

		PageUtil pageUtil = new PageUtil();
		// 查询第一页
		pageUtil.setPageNo(1);

		List<BookT> books = bookTService.getByConditionPage(bookTcondition,pageUtil);

		for (int i = 0; i < books.size(); i++) {
			BookT bookT = books.get(i);

			if (bookT != null) {
				System.out
						.println("-----------------------------------------------------");
				System.out.println(bookT);
				System.out
						.println("-----------------------------------------------------");
			} else {
				System.out.println("查无此小说！");
			}
		}

	}

	@Test
	public void testSave() {
		BookT bookT = new BookT();
		bookT.setBookId(86);	//书籍id
		bookT.setBookName("围城");	//书籍名称
		bookT.setBookImg("/weicheng/weicheng.PNG");	//书籍封面
		
		AuthorT authorT = new AuthorT();
	//	authorT.setAuthorId(authorTService.authorNameToId("钱钟书"));//作者id
		authorT.setAuthorName("钱钟书");	//作者名称
		bookT.setAuthorT(authorT);
		bookT.setBookState("完结");	//完结，连载
		BookType bookType = new BookType();
		bookType.setBookTypeId(6);	//书籍种类
		bookT.setBookType(bookType);
		bookT.setBookChapterNums(7);//书籍章节数
		bookT.setBookWordNums(163000);//书籍字数
		bookT.setIsFree("是");//是否免费
		bookT.setBookClickNums(4894000);//点击次数												//上传时间
		Date bookUpDate=(Date) CommonsUtil.string2Date("2018-05-16", "yyyy-MM-dd");
		bookT.setBookUpDate(bookUpDate);
					//书籍信息，简介
		bookT.setBookInfor(" 介绍：《围城》是一幅栩栩如生的世井百态图，人生的酸甜苦辣千般滋味均在其中得到了淋漓尽致的体现。钱钟书先生将自己的语言天才并入极其渊博的知识，再添加上一些讽刺主义的幽默调料，以一书而定江山。《围城》显示给我们一个真正的聪明人是怎样看人生，又怎样用所有作家都必得使用的文字来表述自己的“观”和“感”的。");

		int result = bookTService.save(bookT);
		System.out.println(result);
	}

	@Test
	public void testModify() {
		BookT bookT = bookTService.getById(2);
		bookT.setBookName("我欲成");
		int result = bookTService.modify(bookT);
		System.out.println(result);

	}
	
	@Test
	public void testbookupdate() {
		List<BookT> booklists=bookTService.getByBookUpDate(2014, 2018);
		for(int i=0;i<booklists.size();i++){
			System.out.println(booklists.get(i));
		}
	}
	
	@Test
	public void testClickAndSex() {
		
		List<BookT> booklists=new ArrayList<>();
				bookTService.clickAndSex("女");
				System.out.println("---------------------size;"+booklists.size());
		for(int i=0;i<booklists.size();i++){
			System.out.println(booklists.get(i));
		}
	}
	/**
	@Test
	public void testModifyFile() {

		Book book = bookTService.get(4);
		if (book != null) {
			book.setBookFile("/file/books/...");
			int result = bookTService.modifyFile(book);
			System.out.println(book);
			System.out.println(result);
		}

	}
	*/
	@Test
	public void testRemove(){
		int result = bookTService.removeById(2);
		System.out.println(result);
	}
	
	@Test
	public void testRemoveManyBooks(){
		Integer[] bids={5,6};
		int result = bookTService.removeManyByIds(bids);
		System.out.println(result);
	}
}

