package com.aikan.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aikan.entity.BookShelf;
import com.aikan.entity.BookT;
import com.aikan.entity.BookTCondition;
import com.aikan.entity.CommentT;
import com.aikan.entity.Record;
import com.aikan.entity.UserT;
import com.aikan.service.AuthorTService;
import com.aikan.service.BookShelfService;
import com.aikan.service.BookTService;
import com.aikan.service.CommentTService;
import com.aikan.service.RecordService;
import com.aikan.service.UserTService;
import com.aikan.util.CommonsUtil;
import com.aikan.util.PageUtil;


//@Controllerע���Ǵ���Ϊ������
//���<context:component-scan base-package="com.company.controller"/>���ô�����Ӧ�Ķ���
@Controller
public class BookTController {
	//�鼮
	@Resource(name="bookTServiceImpl")
	private BookTService bookTService;
	//����
	@Resource(name="authorTServiceImpl")
	private AuthorTService authorTService;
	//���
	@Resource(name="bookShelfServiceImpl")
	private BookShelfService bookShelfService;
	//����
	@Resource(name="commentTServiceImpl")
	private CommentTService commentTService;
	//�Ķ���¼
	@Resource(name="recordServiceImpl")
	private RecordService recordService;
	//�û�
	@Resource(name="userTServiceImpl")
	private UserTService userTService;
	/**
	 * �����ҳ�鼮/�����������鼮
	 * @param bookTCondition
	 * @param errors
	 * @param pageUtil
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/manager/bookT/dosearchajax.action")
	@ResponseBody
	public Map doSearchAjax(@ModelAttribute("bookTCondition") BookTCondition bookTCondition, Errors errors, PageUtil pageUtil,HttpServletResponse response)throws IOException {

		List<BookT> bookTs=null;
		String bookName=bookTCondition.getBookName();
		String authorName=bookName;
		if(bookName!=null&&!"".equals(bookName)){
			
			bookName="%"+bookName+"%";

			bookTCondition.setBookName(bookName);

			bookTs=bookTService.getByConditionPage(bookTCondition, pageUtil);

			List<BookT> bookTs2=bookTService.getBooksByAuthorName(authorName);
			bookTs.addAll(bookTs2);
		}else{
			bookTs=bookTService.getByConditionPage(bookTCondition, pageUtil);
		}
		

		Map result = new HashMap<>();
		result.put("bookTs", bookTs);
		result.put("pageUtil", pageUtil);
		
		return result;
	}
	/**
	 * �����鼮�����ѯ�鼮
	 * @param bookTypeId
	 * @param pageUtil
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/bookT/dosearchbooksbybooktypeidajax.action")
	@ResponseBody
	public Map doSearchBooksByBookTypeIdAjax(@RequestParam("bookTypeId") String bookTypeId,PageUtil pageUtil,HttpServletResponse response){
		System.out.println("*************************************dosearchbooksbybooktypeidajax");
		
		BookTCondition bookTCondition=new BookTCondition();
		bookTCondition.setBookTypeId(Integer.parseInt(bookTypeId));
		
		List<BookT> bookTs=null;

		bookTs=bookTService.getByConditionPage(bookTCondition, pageUtil);
		
		Map result = new HashMap<>();
		result.put("bookTs", bookTs);
		result.put("pageUtil", pageUtil);
		
		return result;
	}
	/**
	 * �����鼮�ϴ�ʱ������鼮
	 * @param bookUpDate
	 * @param pageUtil
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/bookT/dosearchbooksbybookupdateajax.action")
	@ResponseBody
	public Map doSearchBooksByBookUpDateAjax(@RequestParam("bookUpDate") String bookUpDate,PageUtil pageUtil,HttpServletResponse response){
		System.out.println("*************************************dosearchbooksbybookupdateajax");
		
		//BookTCondition bookTCondition=new BookTCondition();
		
		Date bookUp=CommonsUtil.string2Date(bookUpDate, "yyyy");
		
		Integer beginDate=Integer.parseInt(bookUpDate);
		Integer endDate=beginDate+4;
		if(beginDate<=1997){
			beginDate=1900;
			endDate=1997;
		}
		List<BookT> bookTs=null;

		bookTs=bookTService.getByBookUpDate(beginDate,endDate);
		
		Map result = new HashMap<>();
		result.put("bookTs", bookTs);
		result.put("pageUtil", pageUtil);
		
		return result;
	}
	/**
	 * ���յ�������Ա�����鼮
	 * @param bookClickNumsAndSex
	 * @param pageUtil
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/bookT/dosearchbooksbyclicknumsandsexajax.action")
	@ResponseBody
	public Map doSearchBooksByClicknumsAndSexAjax(@RequestParam("bookClickNumsAndSex") String bookClickNumsAndSex,PageUtil pageUtil,HttpServletResponse response){

		
		Integer bookTypeId=Integer.parseInt(bookClickNumsAndSex);
		String bookTypeSex="";
		if(bookTypeId==1){
			bookTypeSex="��";
		}else{
			bookTypeSex="Ů";
		}

		List<BookT> bookTs=null;

		bookTs=bookTService.clickAndSex(bookTypeSex);
		
		Map result = new HashMap<>();
		result.put("bookTs", bookTs);
		result.put("pageUtil", pageUtil);
		
		return result;
	}
	/**
	 * ȥ��С˵����ҳ				
	 * @param bookId
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/bookT/tobookinfor.action")
	public String toBookInfor(@RequestParam("bookId") Integer bookId,Model model){

		BookT bookT=bookTService.getById(bookId);
		//�鼮���������һ
		Integer bookClickNums=bookT.getBookClickNums()+1;
		bookT.setBookClickNums(bookClickNums);
		
		int result=bookTService.modify(bookT);
	//����
		System.out.println("�鼮�޸Ľ��result--��"+result);
		model.addAttribute("bookT", bookT);
		return "frontpage/bookT/infor";
	}
	/**
	 * ȥ�û����
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/manager/userT/tobookshelf.action")
	public String toBookShelf(Model model,HttpSession session){
		UserT userT=(UserT) session.getAttribute("userT");
		List<BookT> booklists=bookShelfService.getAllBooks(userT.getUserId());
		model.addAttribute("booklists", booklists);
		model.addAttribute("userT", userT);
		return "frontpage/bookT/bookshelf";
	}
	/**
	 * ȥ�½�����ҳ
	 * @param bookId
	 * @param chaptername
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/manager/bookT/tochaptercontent.action")
	public String toChapterContent(@RequestParam("bookId") Integer bookId,@RequestParam("chapterid") Integer chapterid,Model model,HttpServletRequest request,HttpSession session){

        //ȡ���û����
        UserT userT=(UserT)session.getAttribute("userT");
        //�û��ѵ�¼���ҵ������Զ�
        if(userT!=null&&chapterid==-1){
    		//�½�һ����¼����
            Record record=new Record();
        	record.setUserId(userT.getUserId());
        	//�����鼮���
            record.setBookId(bookId);
            
	        //�����û���ţ��鼮��ŵõ��û����Ķ���¼
	        Record recordExist=recordService.getRecord(record);
	        //�û���һ�ζ����鼮
	        if(recordExist==null){
	        	chapterid=0;
	        }else{
	        	//�޸Ĳ�ѯ���½ں�Ϊ�ϴα���ļ�¼
		        chapterid=recordExist.getChapterId();
	        }
	        
        }else if(userT==null&&chapterid==-1){
        	chapterid=0;
        }

		BookT bookTExist=bookTService.getById(bookId);						
		String bookName=bookTExist.getBookName();

		String bookUrl=bookName+"/"+bookName;
		
		//��ȡ��ֵ�ԣ��½������½����ݣ�
		Map<String, String> map=CommonsUtil.readBookReturnMap(bookUrl,"UTF-8");
		//��ȡ�鼮������������½���
		ArrayList<String> arrayList=CommonsUtil.readBookReturnArrayList(bookUrl,"UTF-8");


		String chaptername=arrayList.get(chapterid);
		
		String chaptercontent=map.get(chaptername);
		
		model.addAttribute("chapters",arrayList);
		model.addAttribute("chapterid",chapterid);
		model.addAttribute("chapterlength",arrayList.size());
		model.addAttribute("bookName",bookName);
		model.addAttribute("bookId",bookId);
		model.addAttribute("chaptername",chaptername);
		model.addAttribute("chaptercontent", chaptercontent);
		
		if(userT!=null){
			//�����Ķ���¼
			Record record=new Record();
	        record.setUserId(userT.getUserId());
	        //�����鼮���
	        record.setBookId(bookId);
			record.setChapterId(chapterid);
			record.setChapterName(chaptername);
			int result=recordService.modifyOldRecord(record);
			System.out.println("�����Ķ���¼�Ƿ�ɹ�result ��"+result);
		}
		
		return "frontpage/bookT/content";
	}
	/**
	 * ���鼮����ҳ������鼮�½�
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/manager/bookT/dofillbookchapter.action")
	@ResponseBody
	public Map doFillBookChapter(@RequestParam("bookId") Integer bookId,HttpServletResponse response) throws IOException {
		System.out.println("����dofillbookchapter��bookId "+bookId);
		Map result = new HashMap<>();
		
		ArrayList<String> allChapters=null;
		BookT bookExist=bookTService.getById(bookId);
		String bookName=bookExist.getBookName();
		//�鼮��ַ
		String bookUrl=bookName+"/"+bookName;
//		System.out.println("-----------bookUrl:"+bookUrl);
		//�����鼮��ַ�������鼮�����½����ƣ��鼮Ŀ¼��
		//��ȡ�鼮������������½���

		allChapters=CommonsUtil.readBookReturnArrayList(bookUrl,"UTF-8");
//		System.out.println("------allChapters.size() :"+allChapters.size());
		result.put("chapters", allChapters);
		
		return result;
	}
	/**
	 * �鼮����ҳ--����鼮����
	 * @param bookId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/manager/bookT/dofillbookcomment.action")
	@ResponseBody
	public Map doFillBookComment(@RequestParam("bookId") Integer bookId,HttpServletResponse response) throws IOException {
		System.out.println("����dofillbookcomment��bookId "+bookId);
		Map result = new HashMap<>();
		
		ArrayList<String> allChapters=null;
		//���ݱ������鼮��Id,��ѯ���е�����
		List<CommentT> comments=commentTService.getAllCommentsByBookId(bookId);
		for(int i=0;i<comments.size();i++){
			CommentT commentT=comments.get(i);
			
			Integer userId=commentT.getUserId();
			UserT userT=userTService.get(userId);
			comments.get(i).setUserT(userT);
		}
//ceshi	
		System.out.println("comments : "+comments.toString());
	
		result.put("comments", comments);
		
		return result;
	}
	/**
	 * �鼮����ҳ--ʵ���鼮����ҳ�ĵ�����²���
	 * @param bookId
	 * @param chapterid
	 * @return
	 */				 
	@RequestMapping("/manager/bookT/doSearchchapterajax.action")
	@ResponseBody
	public Map doSearchChapterAjax(@RequestParam("bookId") Integer bookId,@RequestParam("chapterid") Integer chapterid,HttpSession session){
		
		Map result = new HashMap<>();
		
		BookT bookTExist=bookTService.getById(bookId);
		//���ݱ�ŵõ��鼮��
		String bookName=bookTExist.getBookName();
		//�����鼮��ַ
		String bookUrl=bookName+"/"+bookName;
		
		//��ȡ��ֵ�ԣ��½������½����ݣ���ȡ��
		Map<String, String> map=CommonsUtil.readBookReturnMap(bookUrl,"UTF-8");
		//��ȡ�鼮������������½���
		ArrayList<String> arrayList=CommonsUtil.readBookReturnArrayList(bookUrl,"UTF-8");


		String chaptername=arrayList.get(chapterid);
		
		String chaptercontent=map.get(chaptername);

		result.put("bookName", bookName);
		result.put("newchapterId", chapterid);
		result.put("newchaptername", chaptername);
		result.put("newchaptercontent", chaptercontent);
		result.put("chapterlength",arrayList.size());
		
		UserT userT=(UserT) session.getAttribute("userT");
		if(userT!=null){
			//�½�һ����¼����
	        Record record=new Record();
	        //ȡ���û���ţ�������
	        
	        record.setUserId(userT.getUserId());
	        //�����鼮���
	        record.setBookId(bookId);
	        record.setChapterId(chapterid);
	        record.setChapterName(chaptername);
	        int modifyRecordResult=recordService.modifyOldRecord(record);
	        System.out.println("�����Ķ���¼�Ƿ�ɹ�modifyRecordResult ��"+modifyRecordResult); 
		}
		return result;
	}
	/**
	 * �鼮����ҳ--�鼮�������
	 * @param bookId
	 * @param session
	 * @return
	 */
	@RequestMapping("/manager/bookT/doaddbooktobookshelf.action")
	@ResponseBody
	public Map doAddBookToBookShelfAjax(@RequestParam("bookId") Integer bookId,HttpSession session){
		
		Map data = new HashMap<>();
		UserT userT=(UserT) session.getAttribute("userT");
		
		BookShelf bookShelf=new BookShelf();
		bookShelf.setBookId(bookId);
		bookShelf.setUserId(userT.getUserId());
		bookShelf.setBsId(userT.getUserId());   	//Ĭ����ܱ�ź��û����һ��

		int result=bookShelfService.saveBookShelf(bookShelf);
		data.put("result", result);
		return data;
		
	}

	/**
	 * �鼮����ҳ---��������
	 * @param bookId
	 * @param session
	 * @return
	 */
	@RequestMapping("/manager/bookT/addcommentajax.action")
	@ResponseBody
	public Map addCommentAjax(@ModelAttribute("commentT") CommentT commentT,HttpSession session){
		
	//����
		System.out.println("----------------------addCommentAjax");
		
		Map data = new HashMap<>();
		
		//�������۵ķ�������
		Date date=new Date();
		String commentDate=CommonsUtil.date2String(date, "yyyy-MM-dd HH:mm:ss");
		commentT.setCommentDate(commentDate);
		
		if(commentT.getCommentTitle()==null){
			commentT.setCommentTitle("");
		}
		if(commentT.getCommentdiv()==null){
			commentT.setCommentdiv("");
		}
	//����
			System.out.println("----------------------commentT:"+commentT);	
		int result=commentTService.save(commentT);
		data.put("result", result);
		return data;
		
	}
	/**
	 * ����Ȧ--��������
	 * @param commentT
	 * @param session
	 * @return
	 */
	@RequestMapping("/manager/bookT/addtieziajax.action")
	@ResponseBody
	public Map addTieziAjax(@ModelAttribute("commentT") CommentT commentT,HttpSession session){
		
	//����
		System.out.println("----------------------addTieziAjax");
		
		Map data = new HashMap<>();
		
		//�������ӵķ�������
		Date date=new Date();
		String commentDate=CommonsUtil.date2String(date, "yyyy-MM-dd HH:mm:ss");
		commentT.setCommentDate(commentDate);
		
		if(commentT.getCommentTitle()==null){
			commentT.setCommentTitle("");
		}
		if(commentT.getCommentdiv()==null){
			commentT.setCommentdiv("");
			Integer parentId=commentT.getParentId();
			CommentT commentTParent=commentTService.getOneCommentById(parentId);
			String commentdiv=commentTParent.getCommentdiv();
			data.put("commentdiv", commentdiv);
		}
		if(commentT.getUserId()==null){
			UserT userT=(UserT) session.getAttribute("userT");
			commentT.setUserId(userT.getUserId());
		}
		
	//����
			System.out.println("----------------------commentT:"+commentT);	
		int result=commentTService.saveTiezi(commentT);
		data.put("result", result);
		
		
		return data;
		
	}
	/**
	 * ����Ȧ--��������
	 * @param commentdiv
	 * @return
	 */
	@RequestMapping("/manager/bookT/dosearchtieziajax.action")
	@ResponseBody
	public Map doSearchTieziAjax(@RequestParam("commentdiv") String commentdiv,HttpSession session){
		
	//����
		System.out.println("----------------------doSearchTieziAjax");
		//��������������ѯ����
		List<CommentT> commentTs=commentTService.getAllCommentsByCommentDiv(commentdiv);
		//�����û���Ϣ
		for(int i=0;i<commentTs.size();i++){
			CommentT commentT=commentTs.get(i);
			
			Integer userId=commentT.getUserId();
			UserT userT=userTService.get(userId);
			commentTs.get(i).setUserT(userT);
		}
		//��ǰ��¼���û�
		UserT userT=(UserT) session.getAttribute("userT");
	//����
			System.out.println("----------------------commentTs.size():"+commentTs.size());
			System.out.println("----------------------userT:"+userT);
		Map data = new HashMap<>();
		
	
		data.put("userT", userT);
		data.put("commentTs", commentTs);
		return data;
		
	}
	
	
	
	@RequestMapping("/manager/bookT/dodeletebookajax.action")
	@ResponseBody
	public Map doDeleteBookAjax(@RequestParam("ids") Integer[] bookIds,HttpSession session,HttpServletResponse response){
		
		Map data = new HashMap<>();
		UserT userT=(UserT) session.getAttribute("userT");
		Integer bsId=userT.getUserId();
		
		int result=bookShelfService.removeManyBooks(bsId, bookIds);
		//System.out.println("---------------------result: "+result);
		data.put("result", result);
		return data;
	}
}
