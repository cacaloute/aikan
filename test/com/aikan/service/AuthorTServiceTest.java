package com.aikan.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aikan.entity.AuthorT;

//ʹ��JUnit��Ԫ����
@RunWith(SpringJUnit4ClassRunner.class)
//����spring���������ļ�����������ʼ�������еĶ���
@ContextConfiguration(locations = { "classpath*:configure/spring/spring-*.xml"})
public class AuthorTServiceTest {
	//�Զ���ע�뱻���Ե�ʵ�ʶ���
	@Resource(name="authorTServiceImpl")
	private AuthorTService authorTService;
	
	@Test
	public void testGetAuthorT(){
		AuthorT authorT=authorTService.getById(1);
		if(authorT!=null){
			System.out.println(authorT);
			System.out.println(authorT.getAuthorId());
		}else{
			System.out.println("���޴����ߣ�");						
		}	
	}
	
	@Test
	public void testSaveAuthorT() {
		AuthorT authorT=new AuthorT();
		
		authorT.setAuthorId(authorTService.authorNameToId("�Ƽ�����"));
		authorT.setAuthorName("�Ƽ�����");
		authorT.setAuthorImg("/authorT/�Ƽ�����.PNG");
		authorT.setAuthorWordNums(37355500);
		authorT.setAuthorWorksNums(16);
		authorT.setFansNums(2000000);
		authorT.setAuthorInfor("���ļ��Ű׽����ң��й���Э��ϯ��ίԱ����������ίԱ��������ѧ��������֮һ�������������Ҹ��������");
		
		int result =authorTService.save(authorT);
		System.out.println(result);
	}
}
