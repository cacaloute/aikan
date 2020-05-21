package com.aikan.service;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.aikan.entity.UserT;

//使用JUnit单元测试
@RunWith(SpringJUnit4ClassRunner.class)
// 加载spring的主配置文件并解析，初始化容器中的对象
@ContextConfiguration(locations = { "classpath*:configure/spring/spring-*.xml"})
public class UserTServiceTest {
 
	//自动绑定注入被测试的实际对象
	@Resource(name="userTServiceImpl")
	private UserTService userTService;
	
	
	@Test
	public void testGet(){
		UserT userT=userTService.get(1);
		if(userT!=null){
			System.out.println(userT);
			System.out.println(userT.getUserId());
		}else{
			System.out.println("查无此用户！");						
		}	
	}

	@Test
	public void testModify(){
	UserT userT =userTService.get(2);
	
	userT.setUserName("用户二");
	userT.setUserPassword("1234");
	int result=userTService.modify(userT);
	System.out.println(result);
	}
}
