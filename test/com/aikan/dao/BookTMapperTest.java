package com.aikan.dao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//使用JUnit单元测试
@RunWith(SpringJUnit4ClassRunner.class)
//加载spring的主配置文件并解析，初始化容器中的对象
@ContextConfiguration(locations = { "classpath*:configure/spring/spring-*.xml"})
public class BookTMapperTest {

}
