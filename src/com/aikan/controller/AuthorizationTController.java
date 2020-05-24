package com.aikan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AuthorizationTController {

	//@Autowired
	//private AuthorizationTService authorizationTService;
	
	@RequestMapping("/manager/authorizationT/tolist.action")
	public String toList(){
		return "background/authorizationT/list";
	}
	
	@RequestMapping("/manager/authorizationT/tologin.action")
	public String toLogin(){
		return "frontpage/authorizationT/dologin";
	}
	
	@RequestMapping("/manager/authorizationT/operate.action")
	public String Operate(){
		return "frontpage/authorizationT/operate";
	}
	
	@RequestMapping("/manager/authorizationT/info.action")
	public String Info(){
		return "frontpage/authorizationT/info";
	}
	
	@RequestMapping("/manager/authorizationT/toregister.action")
	public String toRegister(){
		return "frontpage/authorization/register";
	}
	
	@RequestMapping("/manager/authorization/topassword.action")
	public String toPassword(){
		return "frontpage/authorizationT/password";
	}

}
