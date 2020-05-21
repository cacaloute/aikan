package com.aikan.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aikan.entity.ManagerT;
import com.aikan.service.ManagerTService;


@Controller
public class IndexController {
	
	@Resource(name="managerTServiceImpl")
	private ManagerTService managerTService;
	
	//@Resource(name="managerTServiceImpl")
	//private ManagerTService managerTService;
	
	@RequestMapping("/toindex.action")
	public String toIndex(){
		
		return "redirect:/manager/home.jsp";
	}
	
	/**
	 * 由background.jsp发送请求，跳到管理员登录页面
	 * @return
	 */
	@RequestMapping("/toback.action")
	public String toBack(){
		//结合
		//<property name="prefix" value="/WEB-INF/jsp/"/>
		//<property name="suffix" value=".jsp"/>
		//实际寻找/WEB-INF/jsp/back.jsp,前端核心控制器分发给此结果页面
		return "managerlogin";
	}
	/**
	 * 管理员注销
	 * @param session
	 * @return
	 */
	@RequestMapping("/dologout.action")
	public String doLogout(HttpSession session){
		//注销处理
		session.removeAttribute("managerT");
		// 只要带了特殊的前缀(redirect:、forward:)，则寻找页面的过程就不再使用原来的配置格式
		// 而是直接去寻找前缀后面跟的实际页面(或其他的.action控制器)
		// 其中第一个/代表的是当前项目的根目录（SpringMVC帮我们处理过）
		return "redirect:/index.jsp";
	}
	
	/**
	 * 管理员登录
	 * @param managerName
	 * @param managerPassword
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/dologinajax.action")
	@ResponseBody
	public Map doLoginAjax(@RequestParam("managerName") String managerName,@RequestParam("managerPassword") String managerPassword,
			HttpSession session,HttpServletResponse response){
		System.out.println("------------------------managerName:"+managerName);
		System.out.println("------------------------managerPassword:"+managerPassword);
		//登陆处理
		ManagerT managerT=managerTService.login(managerName, managerPassword);
		boolean result=false;
		if(managerT!=null){
			//登陆成功
			//重定向给成功页面
			//并把用户登录信息存在于会话中，以便多个页面之间都可以使用
			session.setAttribute("managerT",managerT);
			result=true;
		}
		Map data=new HashMap<>();
		data.put("result",result);
		return data;
	}
	
	@RequestMapping("/toerror.action")
	public String toError(){
		return "error";
	}
}
