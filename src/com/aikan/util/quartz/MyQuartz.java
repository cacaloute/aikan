package com.aikan.util.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyQuartz {

//	@Scheduled(cron="0/5 * * * * ?")   //没5秒钟执行一次
	public void quartz(){
		Date date=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d=sd.format(date);
		System.out.println("我执行了，执行间隔："+d);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
