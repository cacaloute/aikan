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


//@Controller注解标记此类为控制器
//结合<context:component-scan base-package="com.company.controller"/>配置创建对应的对象
@Controller
public class BookTController {
	//书籍
	@Resource(name="bookTServiceImpl")
	private BookTService bookTService;
	//作者
	@Resource(name="authorTServiceImpl")
	private AuthorTService authorTService;
	//书架
	@Resource(name="bookShelfServiceImpl")
	private BookShelfService bookShelfService;
	//评论
	@Resource(name="commentTServiceImpl")
	private CommentTService commentTService;
	//阅读记录
	@Resource(name="recordServiceImpl")
	private RecordService recordService;
	//用户
	@Resource(name="userTServiceImpl")
	private UserTService userTService;
	/**
	 * 填充首页书籍/按条件查找书籍
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
	 * 按照书籍种类查询书籍
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
	 * 按照书籍上传时间查找书籍
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
	 * 按照点击数和性别查找书籍
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
			bookTypeSex="男";
		}else{
			bookTypeSex="女";
		}

		List<BookT> bookTs=null;

		bookTs=bookTService.clickAndSex(bookTypeSex);
		
		Map result = new HashMap<>();
		result.put("bookTs", bookTs);
		result.put("pageUtil", pageUtil);
		
		return result;
	}
	/**
	 * 去到小说详情页				
	 * @param bookId
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/bookT/tobookinfor.action")
	public String toBookInfor(@RequestParam("bookId") Integer bookId,Model model){

		BookT bookT=bookTService.getById(bookId);
		//书籍点击次数加一
		Integer bookClickNums=bookT.getBookClickNums()+1;
		bookT.setBookClickNums(bookClickNums);
		
		int result=bookTService.modify(bookT);
	//测试
		System.out.println("书籍修改结果result--："+result);
		model.addAttribute("bookT", bookT);
		return "frontpage/bookT/infor";
	}
	/**
	 * 去用户书架
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
	 * 去章节内容页
	 * @param bookId
	 * @param chaptername
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/manager/bookT/tochaptercontent.action")
	public String toChapterContent(@RequestParam("bookId") Integer bookId,@RequestParam("chapterid") Integer chapterid,Model model,HttpServletRequest request,HttpSession session){

        //取得用户编号
        UserT userT=(UserT)session.getAttribute("userT");
        //用户已登录，且点击免费试读
        if(userT!=null&&chapterid==-1){
    		//新建一个记录对象
            Record record=new Record();
        	record.setUserId(userT.getUserId());
        	//设置书籍编号
            record.setBookId(bookId);
            
	        //根据用户编号，书籍编号得到用户的阅读记录
	        Record recordExist=recordService.getRecord(record);
	        //用户第一次读此书籍
	        if(recordExist==null){
	        	chapterid=0;
	        }else{
	        	//修改查询的章节号为上次保存的记录
		        chapterid=recordExist.getChapterId();
	        }
	        
        }else if(userT==null&&chapterid==-1){
        	chapterid=0;
        }

		BookT bookTExist=bookTService.getById(bookId);						
		String bookName=bookTExist.getBookName();

		String bookUrl=bookName+"/"+bookName;
		
		//获取键值对（章节名，章节内容）
		Map<String, String> map=CommonsUtil.readBookReturnMap(bookUrl,"UTF-8");
		//获取书籍的所有有序的章节名
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
			//更新阅读记录
			Record record=new Record();
	        record.setUserId(userT.getUserId());
	        //设置书籍编号
	        record.setBookId(bookId);
			record.setChapterId(chapterid);
			record.setChapterName(chaptername);
			int result=recordService.modifyOldRecord(record);
			System.out.println("更新阅读记录是否成功result ："+result);
		}
		
		return "frontpage/bookT/content";
	}
	/**
	 * 在书籍详情页中填充书籍章节
	 * @param bookId
	 * @return
	 */
	@RequestMapping("/manager/bookT/dofillbookchapter.action")
	@ResponseBody
	public Map doFillBookChapter(@RequestParam("bookId") Integer bookId,HttpServletResponse response) throws IOException {
		System.out.println("进入dofillbookchapter：bookId "+bookId);
		Map result = new HashMap<>();
		
		ArrayList<String> allChapters=null;
		BookT bookExist=bookTService.getById(bookId);
		String bookName=bookExist.getBookName();
		//书籍地址
		String bookUrl=bookName+"/"+bookName;
//		System.out.println("-----------bookUrl:"+bookUrl);
		//根据书籍地址，返回书籍所有章节名称（书籍目录）
		//获取书籍的所有有序的章节名

		allChapters=CommonsUtil.readBookReturnArrayList(bookUrl,"UTF-8");
//		System.out.println("------allChapters.size() :"+allChapters.size());
		result.put("chapters", allChapters);
		
		return result;
	}
	/**
	 * 书籍详情页--填充书籍评论
	 * @param bookId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/manager/bookT/dofillbookcomment.action")
	@ResponseBody
	public Map doFillBookComment(@RequestParam("bookId") Integer bookId,HttpServletResponse response) throws IOException {
		System.out.println("进入dofillbookcomment：bookId "+bookId);
		Map result = new HashMap<>();
		
		ArrayList<String> allChapters=null;
		//根据被评论书籍的Id,查询所有的评论
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
	 * 书籍内容页--实现书籍内容页的点击换章操作
	 * @param bookId
	 * @param chapterid
	 * @return
	 */				 
	@RequestMapping("/manager/bookT/doSearchchapterajax.action")
	@ResponseBody
	public Map doSearchChapterAjax(@RequestParam("bookId") Integer bookId,@RequestParam("chapterid") Integer chapterid,HttpSession session){
		
		Map result = new HashMap<>();
		
		BookT bookTExist=bookTService.getById(bookId);
		//根据编号得到书籍名
		String bookName=bookTExist.getBookName();
		//配置书籍地址
		String bookUrl=bookName+"/"+bookName;
		
		//获取键值对（章节名，章节内容），取书
		Map<String, String> map=CommonsUtil.readBookReturnMap(bookUrl,"UTF-8");
		//获取书籍的所有有序的章节名
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
			//新建一个记录对象
	        Record record=new Record();
	        //取得用户编号，并设置
	        
	        record.setUserId(userT.getUserId());
	        //设置书籍编号
	        record.setBookId(bookId);
	        record.setChapterId(chapterid);
	        record.setChapterName(chaptername);
	        int modifyRecordResult=recordService.modifyOldRecord(record);
	        System.out.println("更新阅读记录是否成功modifyRecordResult ："+modifyRecordResult); 
		}
		return result;
	}
	/**
	 * 书籍详情页--书籍加入书架
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
		bookShelf.setBsId(userT.getUserId());   	//默认书架编号和用户编号一致

		int result=bookShelfService.saveBookShelf(bookShelf);
		data.put("result", result);
		return data;
		
	}

	/**
	 * 书籍详情页---发表评论
	 * @param bookId
	 * @param session
	 * @return
	 */
	@RequestMapping("/manager/bookT/addcommentajax.action")
	@ResponseBody
	public Map addCommentAjax(@ModelAttribute("commentT") CommentT commentT,HttpSession session){
		
	//测试
		System.out.println("----------------------addCommentAjax");
		
		Map data = new HashMap<>();
		
		//设置评论的发表日期
		Date date=new Date();
		String commentDate=CommonsUtil.date2String(date, "yyyy-MM-dd HH:mm:ss");
		commentT.setCommentDate(commentDate);
		
		if(commentT.getCommentTitle()==null){
			commentT.setCommentTitle("");
		}
		if(commentT.getCommentdiv()==null){
			commentT.setCommentdiv("");
		}
	//测试
			System.out.println("----------------------commentT:"+commentT);	
		int result=commentTService.save(commentT);
		data.put("result", result);
		return data;
		
	}
	/**
	 * 书友圈--发表帖子
	 * @param commentT
	 * @param session
	 * @return
	 */
	@RequestMapping("/manager/bookT/addtieziajax.action")
	@ResponseBody
	public Map addTieziAjax(@ModelAttribute("commentT") CommentT commentT,HttpSession session){
		
	//测试
		System.out.println("----------------------addTieziAjax");
		
		Map data = new HashMap<>();
		
		//设置帖子的发表日期
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
		
	//测试
			System.out.println("----------------------commentT:"+commentT);	
		int result=commentTService.saveTiezi(commentT);
		data.put("result", result);
		
		
		return data;
		
	}
	/**
	 * 书友圈--查找帖子
	 * @param commentdiv
	 * @return
	 */
	@RequestMapping("/manager/bookT/dosearchtieziajax.action")
	@ResponseBody
	public Map doSearchTieziAjax(@RequestParam("commentdiv") String commentdiv,HttpSession session){
		
	//测试
		System.out.println("----------------------doSearchTieziAjax");
		//根据评论区名查询帖子
		List<CommentT> commentTs=commentTService.getAllCommentsByCommentDiv(commentdiv);
		//设置用户信息
		for(int i=0;i<commentTs.size();i++){
			CommentT commentT=commentTs.get(i);
			
			Integer userId=commentT.getUserId();
			UserT userT=userTService.get(userId);
			commentTs.get(i).setUserT(userT);
		}
		//当前登录的用户
		UserT userT=(UserT) session.getAttribute("userT");
	//测试
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
