package com.aikan.util.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyQuartz {

//	@Scheduled(cron="0/5 * * * * ?")   //û5����ִ��һ��
	public void quartz(){
		Date date=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d=sd.format(date);
		System.out.println("��ִ���ˣ�ִ�м����"+d);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
