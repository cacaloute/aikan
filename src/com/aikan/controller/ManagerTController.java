package com.aikan.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aikan.entity.AuthorT;
import com.aikan.entity.BookT;
import com.aikan.entity.CommentT;
import com.aikan.entity.ManagerT;
import com.aikan.entity.UserT;
import com.aikan.service.AuthorTService;
import com.aikan.service.BookTService;
import com.aikan.service.CommentTService;
import com.aikan.service.ManagerTService;
import com.aikan.service.UserTService;
import com.aikan.util.CommonsUtil;

/**
 * 管理员控制器
 * @author Administrator
 *
 */
@Controller
public class ManagerTController {
	
	@Autowired
	private ManagerTService managerTService;
	@Autowired
	private BookTService bookTService;
	@Autowired
	private AuthorTService authorTService;
	@Autowired
	private CommentTService commentTService;
	@Autowired
	private UserTService userTService;
	
	// 头像上传的默认路径，从项目根目录开始
	private String saveBookFacePath = "/images/bookface";
	
	/**
	 * 管理员登录成功后先跳到  manager/index.jsp, 再跳到这里做处理
	 * 跳到后台首页
	 * @return
	 */
	@RequestMapping("/manager/toindex.action")
	public String toIndex(){
		System.out.println("------------------------ManagerTController : toIndex()");
		return "background/backmenu";
	}
	
	/**
	 * 后台首页书籍管理-查找所有书籍
	 * @return
	 */
	@RequestMapping("/manager/tosearchbookt.action")
	public String toSearchBookT(Model model){
		List<BookT> lists=bookTService.getAll();
		if(lists==null||lists.size()==0){
			System.out.println("----------lists.size()=0");
		}
		for(int i=0;i<lists.size();i++){
			BookT bookT=lists.get(i);
			AuthorT authorT=authorTService.getById(bookT.getAuthorId());
		//	System.out.println("---"+bookT.getBookName()+" : "+authorT.getAuthorName());
			lists.get(i).setBookAuthor(authorT.getAuthorName());
		}
		model.addAttribute("lists", lists);
		return "background/booktlistpage";
	}
	
	/**
	 * 后台首页书籍管理-编辑一本书籍时填充书籍信息
	 * @param bookId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/doSearchOneBook.action")
	@ResponseBody
	public Map doSearchOneBookAjax(@RequestParam("bookId") Integer bookId,HttpServletResponse response){
		//根据书籍编号查找此书
		BookT bookT=bookTService.getById(bookId);
		//完善书籍作者信息
		Integer authorId=bookT.getAuthorId();
		AuthorT author=authorTService.getById(authorId);
		bookT.setBookAuthor(author.getAuthorName());
	//测试一下
	//	System.out.println("bookt : "+bookT);
		
		Map result = new HashMap<>();
		result.put("bookT", bookT);
		
		return result;
	}
	/**
	 * 后台管理--首页书籍管理-对书籍进行修改
	 * @param bookT
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/doupdateajax.action")
	@ResponseBody
	public Map doUpdateAjax(@ModelAttribute("bookT") BookT bookT,HttpServletResponse response){
		System.out.println("**************doUpdateAjax");
		
		int result=bookTService.modify(bookT);
		
	//测试一下
	//	System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	/**
	 * 后台首页书籍管理--删除一本书
	 * @param bookId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeleteajax.action")
	@ResponseBody
	public Map doDeleteAjax(@RequestParam("bookId") Integer bookId,HttpServletResponse response){
		System.out.println("**************doUpdateAjax");
		
		int result=bookTService.removeById(bookId);
		
	//测试一下
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	
	/**
	 * 后台添加书籍--跳到添加书籍界面
	 * @return
	 */
	@RequestMapping("/manager/toaddbookt.action")
	public String toAddBookT(){
		
		return "background/addbooktpage";
	}
	/**
	 *后台--书籍管理--添加书籍
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/manager/doaddbookt.action")
	@ResponseBody
	public Map doAddAjax(@ModelAttribute("bookT") BookT bookT,@RequestParam("authorName")String authorName, @RequestParam("imgFile") MultipartFile file,
			HttpServletRequest request) {
		System.out.println("-------------后台添加书籍--做添加书籍");
		
		ServletContext application=request.getSession().getServletContext();
		String bookface="/images/bookface";
		if(bookface!=null&&!"".equals(bookface)){
			//根据外部配置，设置书籍封面上传路径
			this.saveBookFacePath=bookface;
		}

		// 找到上传路径在服务器上的实际路径
		String realPath = application.getRealPath(saveBookFacePath);
		File saveDir = new File(realPath);
		if (!saveDir.exists()) {
			// 如果这个路径不存在，则创建它
			saveDir.mkdirs();
		}

		// 给上传的文件重命名
		String fileName = System.nanoTime()
				+ file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf("."));

		boolean isUploadOK = false;
		File targetFile = new File(saveDir, fileName);
		try {
			// 把上传的文件保存到服务器指定的目的地
			file.transferTo(targetFile);
			// 上传成功
			isUploadOK = true;
			// 重定向
			// return "redirect:/tosuccess.action";
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!isUploadOK) {
			// 上传失败
			// return "index";
			Map data = new HashMap<>();
			data.put("result", -2);
			return data;
		}

		//设置书籍封面
		bookT.setBookImg("/" + fileName);
		//设置上传时间
		Date date=new Date();
		String datestr=CommonsUtil.date2String(date, "yyyy-MM-dd");
		Date bookUpDate=(Date) CommonsUtil.string2Date(datestr, "yyyy-MM-dd");
		bookT.setBookUpDate(bookUpDate);
		//设置书籍作者名
		bookT.setBookAuthor(authorName);
	
		System.out.println();
		int result = bookTService.save(bookT);
		if (result != 1) {
			// 添加失败,删除书籍封面
			targetFile.delete();
		}

		Map data = new HashMap<>();
		data.put("result", result);

		return data;
	}
	/**
	 * 后台管理--书籍管理--书籍分析
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/tocheckbookt.action")
	public String toBookTInfor(Model model){
		//统计用户男女性别人数
		List<Integer> bookTypeNums=bookTService.countBookNumsByType();
		if(bookTypeNums==null||bookTypeNums.size()==0){
			System.out.println("----------bookTypeNums.size()=0");
		}
		
		model.addAttribute("bookTypeNums", bookTypeNums);
		return "background/bookcharts";
	}
	/**
	 * 后台管理--评论管理--去评论列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/tosearchcommentt.action")
	public String toSearchCommentT(Model model){
		
		List<CommentT> lists=commentTService.getAllComments();
		if(lists==null||lists.size()==0){
			System.out.println("----------lists.size()=0");
		}
		for(int i=0;i<lists.size();i++){
			CommentT commentT=lists.get(i);
			UserT userT=userTService.get(commentT.getUserId());
		//	System.out.println("---"+bookT.getBookName()+" : "+authorT.getAuthorName());
			lists.get(i).setUserT(userT);
		}
		model.addAttribute("lists", lists);
		return "background/commentlistpage";
	}
	/**
	 * 后台管理--评论管理--删除一条评论
	 * @param commentId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeletecomment.action")
	@ResponseBody
	public Map doDeleteComment(@RequestParam("commentId") Integer commentId,HttpServletResponse response){
		System.out.println("**************doDeleteComment");
		
		int result=commentTService.removeOne(commentId);
		
	//测试一下
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	/**
	 * 后台管理--用户管理--去用户列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/tosearchuser.action")
	public String toSearchUserT(Model model){
		
		List<UserT> lists=userTService.getAllUserTs();
		if(lists==null||lists.size()==0){
			System.out.println("----------lists.size()=0");
		}
		
		model.addAttribute("lists", lists);
		return "background/userlistpage";
	}
	/**
	 * 后台管理--用户管理--用户分析
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/toaddusert.action")
	public String toUserTInfor(Model model){
		//统计用户男女性别人数
		List<Integer> userSexNums=userTService.countUserBySex();
		if(userSexNums==null||userSexNums.size()==0){
			System.out.println("----------userSexNums.size()=0");
		}
		
		model.addAttribute("userSexNums", userSexNums);
		return "background/usercharts";
	}
	/**
	 * 后台管理--用户管理--删除用户
	 * @param userId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeleteuser.action")
	@ResponseBody
	public Map doDeleteUserT(@RequestParam("userId") Integer userId,HttpServletResponse response){
		System.out.println("**************doDeleteUserT");
		
		int result=userTService.remove(userId);
		
	//测试一下
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	
	/**
	 * 后台管理--作者管理--去作者列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/tosearchauthort.action")
	public String toSearchAuthorT(Model model){
		
		List<AuthorT> lists=authorTService.getAllAuthorTs();
		if(lists==null||lists.size()==0){
			System.out.println("----------lists.size()=0");
		}
		
		model.addAttribute("lists", lists);
		return "background/authorlistpage";
	}
	/**
	 * 后台管理--作者管理--删除作者
	 * @param authorId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeleteauthort.action")
	@ResponseBody
	public Map doDeleteAuthorT(@RequestParam("authorId") Integer authorId,HttpServletResponse response){
		System.out.println("**************doDeleteAuthorT");
		
		int result=authorTService.remove(authorId);
		
	//测试一下
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	/**
	 * 后台管理--作者管理--查找一个作者，用来填充编辑框
	 * @param authorId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/doSearchOneAuthor.action")
	@ResponseBody
	public Map doSearchOneAuthorAjax(@RequestParam("authorId") Integer authorId,HttpServletResponse response){
		//根据作者编号查找作者
		AuthorT authorT=authorTService.getById(authorId);
		
	//测试一下
		System.out.println("authorT : "+authorT);
		
		Map result = new HashMap<>();
		result.put("authorT", authorT);
		
		return result;
	}
	/**
	 * 后台管理--作者管理--修改编辑作者信息
	 * @param authorT
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/doupdateauthorajax.action")
	@ResponseBody
	public Map doUpdateAuthorTAjax(@ModelAttribute("authorT") AuthorT authorT,HttpServletResponse response){
		System.out.println("**************doUpdateAuthorTAjax");
	//	AuthorT authorT2=authorTService.getById(authorT.getAuthorId());
	//	authorT.setAuthorImg(authorT2.getAuthorImg());
		int result=authorTService.modify(authorT);
		
	//测试一下
	//	System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	/**
	 * 后台管理--管理员管理--去管理员列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/tosearchmanagert.action")
	public String toSearchManagerT(Model model){
		
		List<ManagerT> lists=managerTService.getAllManager();
		if(lists==null||lists.size()==0){
			System.out.println("----------lists.size()=0");
		}
		
		model.addAttribute("lists", lists);
		return "background/managerlistpage";
	}
	/**
	 * 后台管理--管理员管理--删除管理员
	 * @param userId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeletemanagert.action")
	@ResponseBody
	public Map doDeleteManagerT(@RequestParam("managerId") Integer managerId,HttpServletResponse response){
		System.out.println("**************doDeleteAuthorT");
		
		int result=managerTService.removeOne(managerId);
		
	//测试一下
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
}
