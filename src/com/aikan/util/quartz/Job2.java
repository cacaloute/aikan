package com.aikan.util.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Job2 {
	public void doJob2() {  
		Date date=new Date();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d=sd.format(date);
		System.out.println("Ê±¼ä"+d);
}  
}
