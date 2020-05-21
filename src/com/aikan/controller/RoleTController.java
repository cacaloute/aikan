package com.aikan.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.aikan.entity.RoleT;
import com.aikan.service.RoleTService;

@Controller
@RequestMapping("/manager/role")
public class RoleTController {
	
	@Resource(name="roleTServiceImpl")
	private RoleTService roleTService;
	
	@RequestMapping("/tolist.action")
	public String toList(){
		return "background/role/list";
	}
	
	@RequestMapping("/dosearchajax.action")
	@ResponseBody
	public List<RoleT> doSearchAjax(){
		return roleTService.searchAll();
	}
	
	@RequestMapping("/toassign.action")
	public String toAssign(@RequestParam("rid") Integer id,Model model){
		Map<String,Object> data=roleTService.get(id);
		model.addAttribute("role",data.get("role"));
		model.addAttribute("allAuthors",data.get("allAuthors"));
		return "background/role/assign";
	}
	

}
