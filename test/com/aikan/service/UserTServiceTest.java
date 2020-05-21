package com.aikan.service;

import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.aikan.entity.UserT;

//ʹ��JUnit��Ԫ����
@RunWith(SpringJUnit4ClassRunner.class)
// ����spring���������ļ�����������ʼ�������еĶ���
@ContextConfiguration(locations = { "classpath*:configure/spring/spring-*.xml"})
public class UserTServiceTest {
 
	//�Զ���ע�뱻���Ե�ʵ�ʶ���
	@Resource(name="userTServiceImpl")
	private UserTService userTService;
	
	
	@Test
	public void testGet(){
		UserT userT=userTService.get(1);
		if(userT!=null){
			System.out.println(userT);
			System.out.println(userT.getUserId());
		}else{
			System.out.println("���޴��û���");						
		}	
	}

	@Test
	public void testModify(){
	UserT userT =userTService.get(2);
	
	userT.setUserName("�û���");
	userT.setUserPassword("1234");
	int result=userTService.modify(userT);
	System.out.println(result);
	}
}
