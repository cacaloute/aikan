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
//���������ǰ�����@RequestMapping�����Ը�����ع���ÿ������@RequestMapping
@RequestMapping("/manager/authorT")
public class AuthorTController {
 
	//�ֶ���ָ����ʵ������󵽴˳�Ա�ֶα�����
	@Resource(name="userTServiceImpl")
	private UserTService userTService;

	//����
	@Resource(name="authorTServiceImpl")
	private AuthorTService authorTService;
	
	//�鼮
	@Resource(name="bookTServiceImpl")
	private BookTService bookTService;
	
	//�鼮����
	@Resource(name="bookTypeServiceImpl")
	private BookTypeService bookTypeService;

	@RequestMapping("/toauthorinfor.action")
	public String toBookInfor(@RequestParam("authorId") Integer authorId,Model model){

		AuthorT authorT=authorTService.getById(authorId);
		//�������ߵ����֣�����������������ͼ��
		List<BookT> booklists=bookTService.getBooksByAuthorName(authorT.getAuthorName());
		for(int i=0;i<booklists.size();i++){
			BookT bookT=booklists.get(i);	
			System.out.println("bookT:"+bookT);
			Integer bookTypeId=bookT.getBookTypeId();
		//����
			System.out.println("----------bookTypeId:"+bookTypeId);
			//�����鼮�����ţ���������
			BookType bookType=bookTypeService.getBookTypeByTypeId(bookTypeId);
			
		//����
			System.out.println("----------bookTypeName:"+bookType.getBookTypeName());
			
			booklists.get(i).setBookType(bookType);
		}
		
	//����
		System.out.println("���ڸ����ߵ��鼮booklists--��"+booklists.size());
		model.addAttribute("authorT", authorT);
		model.addAttribute("booklists", booklists);
		return "frontpage/authorT/authorinfor";
	}
}

