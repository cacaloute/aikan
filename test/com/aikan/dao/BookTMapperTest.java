package com.aikan.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//ʹ��JUnit��Ԫ����
@RunWith(SpringJUnit4ClassRunner.class)
//����spring���������ļ�����������ʼ�������еĶ���
@ContextConfiguration(locations = { "classpath*:configure/spring/spring-*.xml"})
public class BookTMapperTest {

}
