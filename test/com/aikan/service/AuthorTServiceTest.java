package com.aikan.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aikan.entity.AuthorT;

//使用JUnit单元测试
@RunWith(SpringJUnit4ClassRunner.class)
//加载spring的主配置文件并解析，初始化容器中的对象
@ContextConfiguration(locations = { "classpath*:configure/spring/spring-*.xml"})
public class AuthorTServiceTest {
	//自动绑定注入被测试的实际对象
	@Resource(name="authorTServiceImpl")
	private AuthorTService authorTService;
	
	@Test
	public void testGetAuthorT(){
		AuthorT authorT=authorTService.getById(1);
		if(authorT!=null){
			System.out.println(authorT);
			System.out.println(authorT.getAuthorId());
		}else{
			System.out.println("查无此作者！");						
		}	
	}
	
	@Test
	public void testSaveAuthorT() {
		AuthorT authorT=new AuthorT();
		
		authorT.setAuthorId(authorTService.authorNameToId("唐家三少"));
		authorT.setAuthorName("唐家三少");
		authorT.setAuthorImg("/authorT/唐家三少.PNG");
		authorT.setAuthorWordNums(37355500);
		authorT.setAuthorWorksNums(16);
		authorT.setFansNums(2000000);
		authorT.setAuthorInfor("阅文集团白金作家，中国作协主席团委员，北京青联委员，网络文学代表人物之一，蝉联多年作家富豪榜榜首");
		
		int result =authorTService.save(authorT);
		System.out.println(result);
	}
}
