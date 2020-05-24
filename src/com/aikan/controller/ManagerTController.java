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
 * ����Ա������
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
	
	// ͷ���ϴ���Ĭ��·��������Ŀ��Ŀ¼��ʼ
	private String saveBookFacePath = "/images/bookface";
	
	/**
	 * ����Ա��¼�ɹ���������  manager/index.jsp, ����������������
	 * ������̨��ҳ
	 * @return
	 */
	@RequestMapping("/manager/toindex.action")
	public String toIndex(){
		System.out.println("------------------------ManagerTController : toIndex()");
		return "background/backmenu";
	}
	
	/**
	 * ��̨��ҳ�鼮����-���������鼮
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
	 * ��̨��ҳ�鼮����-�༭һ���鼮ʱ����鼮��Ϣ
	 * @param bookId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/doSearchOneBook.action")
	@ResponseBody
	public Map doSearchOneBookAjax(@RequestParam("bookId") Integer bookId,HttpServletResponse response){
		//�����鼮��Ų��Ҵ���
		BookT bookT=bookTService.getById(bookId);
		//�����鼮������Ϣ
		Integer authorId=bookT.getAuthorId();
		AuthorT author=authorTService.getById(authorId);
		bookT.setBookAuthor(author.getAuthorName());
	//����һ��
	//	System.out.println("bookt : "+bookT);
		
		Map result = new HashMap<>();
		result.put("bookT", bookT);
		
		return result;
	}
	/**
	 * ��̨����--��ҳ�鼮����-���鼮�����޸�
	 * @param bookT
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/doupdateajax.action")
	@ResponseBody
	public Map doUpdateAjax(@ModelAttribute("bookT") BookT bookT,HttpServletResponse response){
		System.out.println("**************doUpdateAjax");
		
		int result=bookTService.modify(bookT);
		
	//����һ��
	//	System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	/**
	 * ��̨��ҳ�鼮����--ɾ��һ����
	 * @param bookId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeleteajax.action")
	@ResponseBody
	public Map doDeleteAjax(@RequestParam("bookId") Integer bookId,HttpServletResponse response){
		System.out.println("**************doUpdateAjax");
		
		int result=bookTService.removeById(bookId);
		
	//����һ��
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	
	/**
	 * ��̨����鼮--��������鼮����
	 * @return
	 */
	@RequestMapping("/manager/toaddbookt.action")
	public String toAddBookT(){
		
		return "background/addbooktpage";
	}
	/**
	 *��̨--�鼮����--����鼮
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/manager/doaddbookt.action")
	@ResponseBody
	public Map doAddAjax(@ModelAttribute("bookT") BookT bookT,@RequestParam("authorName")String authorName, @RequestParam("imgFile") MultipartFile file,
			HttpServletRequest request) {
		System.out.println("-------------��̨����鼮--������鼮");
		
		ServletContext application=request.getSession().getServletContext();
		String bookface="/images/bookface";
		if(bookface!=null&&!"".equals(bookface)){
			//�����ⲿ���ã������鼮�����ϴ�·��
			this.saveBookFacePath=bookface;
		}

		// �ҵ��ϴ�·���ڷ������ϵ�ʵ��·��
		String realPath = application.getRealPath(saveBookFacePath);
		File saveDir = new File(realPath);
		if (!saveDir.exists()) {
			// ������·�������ڣ��򴴽���
			saveDir.mkdirs();
		}

		// ���ϴ����ļ�������
		String fileName = System.nanoTime()
				+ file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf("."));

		boolean isUploadOK = false;
		File targetFile = new File(saveDir, fileName);
		try {
			// ���ϴ����ļ����浽������ָ����Ŀ�ĵ�
			file.transferTo(targetFile);
			// �ϴ��ɹ�
			isUploadOK = true;
			// �ض���
			// return "redirect:/tosuccess.action";
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!isUploadOK) {
			// �ϴ�ʧ��
			// return "index";
			Map data = new HashMap<>();
			data.put("result", -2);
			return data;
		}

		//�����鼮����
		bookT.setBookImg("/" + fileName);
		//�����ϴ�ʱ��
		Date date=new Date();
		String datestr=CommonsUtil.date2String(date, "yyyy-MM-dd");
		Date bookUpDate=(Date) CommonsUtil.string2Date(datestr, "yyyy-MM-dd");
		bookT.setBookUpDate(bookUpDate);
		//�����鼮������
		bookT.setBookAuthor(authorName);
	
		System.out.println();
		int result = bookTService.save(bookT);
		if (result != 1) {
			// ���ʧ��,ɾ���鼮����
			targetFile.delete();
		}

		Map data = new HashMap<>();
		data.put("result", result);

		return data;
	}
	/**
	 * ��̨����--�鼮����--�鼮����
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/tocheckbookt.action")
	public String toBookTInfor(Model model){
		//ͳ���û���Ů�Ա�����
		List<Integer> bookTypeNums=bookTService.countBookNumsByType();
		if(bookTypeNums==null||bookTypeNums.size()==0){
			System.out.println("----------bookTypeNums.size()=0");
		}
		
		model.addAttribute("bookTypeNums", bookTypeNums);
		return "background/bookcharts";
	}
	/**
	 * ��̨����--���۹���--ȥ�����б�
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
	 * ��̨����--���۹���--ɾ��һ������
	 * @param commentId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeletecomment.action")
	@ResponseBody
	public Map doDeleteComment(@RequestParam("commentId") Integer commentId,HttpServletResponse response){
		System.out.println("**************doDeleteComment");
		
		int result=commentTService.removeOne(commentId);
		
	//����һ��
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	/**
	 * ��̨����--�û�����--ȥ�û��б�
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
	 * ��̨����--�û�����--�û�����
	 * @param model
	 * @return
	 */
	@RequestMapping("/manager/toaddusert.action")
	public String toUserTInfor(Model model){
		//ͳ���û���Ů�Ա�����
		List<Integer> userSexNums=userTService.countUserBySex();
		if(userSexNums==null||userSexNums.size()==0){
			System.out.println("----------userSexNums.size()=0");
		}
		
		model.addAttribute("userSexNums", userSexNums);
		return "background/usercharts";
	}
	/**
	 * ��̨����--�û�����--ɾ���û�
	 * @param userId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeleteuser.action")
	@ResponseBody
	public Map doDeleteUserT(@RequestParam("userId") Integer userId,HttpServletResponse response){
		System.out.println("**************doDeleteUserT");
		
		int result=userTService.remove(userId);
		
	//����һ��
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	
	/**
	 * ��̨����--���߹���--ȥ�����б�
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
	 * ��̨����--���߹���--ɾ������
	 * @param authorId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeleteauthort.action")
	@ResponseBody
	public Map doDeleteAuthorT(@RequestParam("authorId") Integer authorId,HttpServletResponse response){
		System.out.println("**************doDeleteAuthorT");
		
		int result=authorTService.remove(authorId);
		
	//����һ��
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	/**
	 * ��̨����--���߹���--����һ�����ߣ��������༭��
	 * @param authorId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/doSearchOneAuthor.action")
	@ResponseBody
	public Map doSearchOneAuthorAjax(@RequestParam("authorId") Integer authorId,HttpServletResponse response){
		//�������߱�Ų�������
		AuthorT authorT=authorTService.getById(authorId);
		
	//����һ��
		System.out.println("authorT : "+authorT);
		
		Map result = new HashMap<>();
		result.put("authorT", authorT);
		
		return result;
	}
	/**
	 * ��̨����--���߹���--�޸ı༭������Ϣ
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
		
	//����һ��
	//	System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
	/**
	 * ��̨����--����Ա����--ȥ����Ա�б�
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
	 * ��̨����--����Ա����--ɾ������Ա
	 * @param userId
	 * @param response
	 * @return
	 */
	@RequestMapping("/manager/dodeletemanagert.action")
	@ResponseBody
	public Map doDeleteManagerT(@RequestParam("managerId") Integer managerId,HttpServletResponse response){
		System.out.println("**************doDeleteAuthorT");
		
		int result=managerTService.removeOne(managerId);
		
	//����һ��
		System.out.println("result : "+result);
		
		Map data = new HashMap<>();
		data.put("result", result);
		
		return data;
	}
}
