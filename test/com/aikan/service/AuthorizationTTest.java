package com.aikan.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aikan.entity.AuthorizationT;
import com.aikan.entity.ManagerT;
import com.aikan.entity.RoleAuthorization;

//使用JUnit单元测试
@RunWith(SpringJUnit4ClassRunner.class)
//加载spring的主配置文件并解析，初始化容器中的对象
@ContextConfiguration(locations = { "classpath*:configure/spring/spring-*.xml"})
public class AuthorizationTTest {
	
	//自动绑定注入被测试的实际对象
	@Resource(name="authorizationTServiceImpl")
	private AuthorizationTService authorizationTService;
	
	@Resource(name="roleTServiceImpl")
	private RoleTService roleTService;
	
	@Resource(name="managerTServiceImpl")
	private ManagerTService managerTService;
	
	@Test
	public void searchAllByRoleIdTest(){
		List<AuthorizationT> lists=authorizationTService.searchAllByRoleId(1);
		for(int i=0;i<lists.size();i++){
			AuthorizationT authorizationT=lists.get(i);
			System.out.println(lists.get(i).getaName());
			if(authorizationT.getaType().equals(0)){
				List<AuthorizationT> listts=authorizationT.getChildAuthorizations();
				 for(int j=0;j<listts.size();j++){
					 System.out.println("\t"+listts.get(j).getaName());
				 }
			}
		
			//System.out.println(authorizationT);
		}
	}
	
	@Test
	public void test(){
		Map<String, List<RoleAuthorization>> menuTree = roleTService.getAuthors(1);
		 List<RoleAuthorization> value=menuTree.get("0");
		 System.out.println("value.size(): "+value.size());
		 for(int i=0;i<value.size();i++){
			 RoleAuthorization roleAuthorization=value.get(i);
			 System.out.println(roleAuthorization.getAuName());
			// if(roleAuthorization.getAuParentId()==0){
			 List<RoleAuthorization> childs=menuTree.get(roleAuthorization.getAuId().toString());
			 for(int j=0;j<childs.size();j++){
				 RoleAuthorization roleAuthorization2=childs.get(j);
				 System.out.println("\t"+roleAuthorization2.getAuUrl());
			 }
		 }
	}
		
	
	@Test
	public void testMenu(){
		ManagerT managerT=new ManagerT();
		managerT.setManagerName("超级管理员");
		managerT.setManagerPassword("1234");
		ManagerT managerT2=managerTService.login("超级管理员", "1234");
		Map<String, List<RoleAuthorization>> menuTree=managerT2.getMenuTree();
		
		Iterator<String> iterator=menuTree.keySet().iterator();
		while(iterator.hasNext()){
			String key=iterator.next();
			 List<RoleAuthorization> value=menuTree.get(key);
			 for(int i=0;i<value.size();i++){
				 RoleAuthorization authorization=value.get(i);
			 
				 System.out.println(authorization.getAuName()+"//"+authorization.getAuIdString());
				//List<RoleAuthorization> childs=authorization.
			 }
		}
		
	}
	
}
