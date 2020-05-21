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


//ʹ��JUnit��Ԫ����
@RunWith(SpringJUnit4ClassRunner.class)
// ����spring���������ļ�����������ʼ�������еĶ���
@ContextConfiguration(locations = { "classpath*:configure/spring/spring-*.xml"})
public class BookTServiceTest {

	// �Զ���ע�뱻���Ե�ʵ�ʶ���
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
		bookTcondition.setBookName("%"+"��"+"%");

		PageUtil pageUtil = new PageUtil();
		// ��ѯ��һҳ
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
				System.out.println("���޴�С˵��");
			}
		}

	}

	@Test
	public void testSave() {
		BookT bookT = new BookT();
		bookT.setBookId(86);	//�鼮id
		bookT.setBookName("Χ��");	//�鼮����
		bookT.setBookImg("/weicheng/weicheng.PNG");	//�鼮����
		
		AuthorT authorT = new AuthorT();
	//	authorT.setAuthorId(authorTService.authorNameToId("Ǯ����"));//����id
		authorT.setAuthorName("Ǯ����");	//��������
		bookT.setAuthorT(authorT);
		bookT.setBookState("���");	//��ᣬ����
		BookType bookType = new BookType();
		bookType.setBookTypeId(6);	//�鼮����
		bookT.setBookType(bookType);
		bookT.setBookChapterNums(7);//�鼮�½���
		bookT.setBookWordNums(163000);//�鼮����
		bookT.setIsFree("��");//�Ƿ����
		bookT.setBookClickNums(4894000);//�������												//�ϴ�ʱ��
		Date bookUpDate=(Date) CommonsUtil.string2Date("2018-05-16", "yyyy-MM-dd");
		bookT.setBookUpDate(bookUpDate);
					//�鼮��Ϣ�����
		bookT.setBookInfor(" ���ܣ���Χ�ǡ���һ������������������̬ͼ���������������ǧ����ζ�������еõ������쾡�µ����֡�Ǯ�����������Լ���������Ų��뼫��Ԩ����֪ʶ���������һЩ����������Ĭ���ϣ���һ�������ɽ����Χ�ǡ���ʾ������һ�������Ĵ����������������������������������Ҷ��ص�ʹ�õ������������Լ��ġ��ۡ��͡��С��ġ�");

		int result = bookTService.save(bookT);
		System.out.println(result);
	}

	@Test
	public void testModify() {
		BookT bookT = bookTService.getById(2);
		bookT.setBookName("������");
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
				bookTService.clickAndSex("Ů");
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

