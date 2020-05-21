package com.aikan.controller;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aikan.entity.ManagerT;
import com.aikan.entity.UserT;
import com.aikan.service.ManagerTService;
import com.aikan.service.UserTService;
import com.aikan.util.MyMD5Util;

@Controller
//可以在类的前面加上@RequestMapping，可以更方便地管理每个方法@RequestMapping
@RequestMapping("/manager/userT")
public class UserTController {
 
	//手动绑定指定的实现类对象到此成员字段变量上
	@Resource(name="userTServiceImpl")
	private UserTService userTService;
	
	//头像上传的默认路径，从项目根目录开始
	private String savePath="/images/icon";

	@RequestMapping("/tolist.action")
	public String toList(){
		return "backgroud/user/list";
	}	
	
	@RequestMapping("/dologout.action")
	public String doLogout(HttpSession session){
		//注销处理
		session.removeAttribute("userT");
		// 只要带了特殊的前缀(redirect:、forward:)，则寻找页面的过程就不再使用原来的配置格式
		// 而是直接去寻找前缀后面跟的实际页面(或其他的.action控制器)
		// 其中第一个/代表的是当前项目的根目录（SpringMVC帮我们处理过）
		return "redirect:/index.jsp";
	}
	/**
	 * 用户注册
	 * @param userT
	 * @param file
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/doregisterajax.action")
	@ResponseBody
	public Map doRegisterAjax(@ModelAttribute("userT") UserT userT, @RequestParam("imgFile") MultipartFile file,
			HttpSession session,HttpServletRequest request){

		ServletContext application=request.getSession().getServletContext();
		String headerDir=application.getInitParameter("headerDir");
		if(headerDir!=null&&!"".equals(headerDir)){
			//根据外部配置，设置头像上传路径
			this.savePath=headerDir;
		}
		
		//找到上传路径在服务器上的实际路径
		String realPath=application.getRealPath(savePath);
		File saveDir=new File(realPath);
		if(!saveDir.exists()){
			//如果这个路径不存在，则创建它
			saveDir.mkdirs();
		}
		
		//给上传的文件重命名
		String fileName=System.nanoTime()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		
		boolean isUploadOK=false;
		File targetFile=new File(saveDir,fileName);
		try{
		//把上传的文件保存到服务器指定的目的地
			file.transferTo(targetFile);
            //上传成功
			isUploadOK=true;
			
		}catch(IllegalStateException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		if(!isUploadOK){
			//上传失败
			//return "index";
			Map data=new HashMap<>();
			data.put("result", -2);
			return data;
		}
		//保存上传头像的项目相对路径给user对象

		userT.setUserImg(savePath+"/"+fileName);
		
		//用户密码加密
		String password=userT.getUserPassword();
		String epass=MyMD5Util.encode(password);
		userT.setUserPassword(epass);
		
		int result=userTService.save(userT);
		if(result!=1){
			//添加失败，删除头像
			targetFile.delete();
		}else{
			session.setAttribute("userT", userT);
		}
		
		Map data=new HashMap<>();
		data.put("result", result);
		return data;
	}
	/**
	 * 做用户登录
	 * @param userName
	 * @param userPassword
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/dologinajax.action")
	@ResponseBody
	public Map doLoginAjax(@RequestParam("userName") String userName,@RequestParam("userPassword") String userPassword,
			HttpSession session,HttpServletResponse response){
		//登陆处理
		UserT userT=userTService.login(userName, userPassword);
		UserT userT2=(UserT) session.getAttribute("userT");
		boolean result=false;
		if(userT!=null&& userT2==null){
			//登陆成功
			//重定向给成功页面
			//并把用户登录信息存在于会话中，以便多个页面之间都可以使用
			session.setAttribute("userT",userT);
			result=true;
			System.out.println("------------------UserTController.doLoginAjax");
		}
		Map data=new HashMap<>();
		data.put("result",result);
		return data;
	}
}

