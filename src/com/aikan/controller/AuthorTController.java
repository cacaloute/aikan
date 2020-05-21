package com.aikan.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.aikan.entity.AuthorT;
import com.aikan.entity.BookT;
import com.aikan.entity.BookType;
import com.aikan.service.AuthorTService;
import com.aikan.service.BookTService;
import com.aikan.service.BookTypeService;
import com.aikan.service.UserTService;




@Controller
//可以在类的前面加上@RequestMapping，可以更方便地管理每个方法@RequestMapping
@RequestMapping("/manager/authorT")
public class AuthorTController {
 
	//手动绑定指定的实现类对象到此成员字段变量上
	@Resource(name="userTServiceImpl")
	private UserTService userTService;

	//作者
	@Resource(name="authorTServiceImpl")
	private AuthorTService authorTService;
	
	//书籍
	@Resource(name="bookTServiceImpl")
	private BookTService bookTService;
	
	//书籍种类
	@Resource(name="bookTypeServiceImpl")
	private BookTypeService bookTypeService;

	@RequestMapping("/toauthorinfor.action")
	public String toBookInfor(@RequestParam("authorId") Integer authorId,Model model){

		AuthorT authorT=authorTService.getById(authorId);
		//根据作者的名字，查找属于他的所有图书
		List<BookT> booklists=bookTService.getBooksByAuthorName(authorT.getAuthorName());
		for(int i=0;i<booklists.size();i++){
			BookT bookT=booklists.get(i);	
			System.out.println("bookT:"+bookT);
			Integer bookTypeId=bookT.getBookTypeId();
		//测试
			System.out.println("----------bookTypeId:"+bookTypeId);
			//根据书籍种类编号，查找种类
			BookType bookType=bookTypeService.getBookTypeByTypeId(bookTypeId);
			
		//测试
			System.out.println("----------bookTypeName:"+bookType.getBookTypeName());
			
			booklists.get(i).setBookType(bookType);
		}
		
	//测试
		System.out.println("属于该作者的书籍booklists--："+booklists.size());
		model.addAttribute("authorT", authorT);
		model.addAttribute("booklists", booklists);
		return "frontpage/authorT/authorinfor";
	}
}

