package com.aikan.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.aikan.service.BookTService;
import com.aikan.service.UserTService;

public class CommonsUtil {
	@Autowired
	private BookTService bookTService;//书籍
	@Autowired
	private UserTService userTService; //用户
	
	/**
	 * 判断一个字符是是否为空
	 * @param data
	 * @return
	 */
	public static boolean isEmpty(String data){
		if(data==null||"".equals(data.trim())){
			return true;
		}
		
		return false;
	}

	/**
	 * 日期格式的字符串转换成日期对象
	 * 
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static Date string2Date(String source, String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		Date date = null;

		try {
			date = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}

		return date;
	}

	/**
	 * 把日期对象，转换成指定格式的字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2String(Date date, String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateString = null;
		if (date != null) {
			dateString = sdf.format(date);
		}
		return dateString;
	}
	/**
	 * 根据书籍地址，返回书籍所有章节名称（书籍目录）
	 * @param bookUrl
	 * @return ArrayList<String>
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static ArrayList<String> readBookReturnArrayList(String bookUrl,String charset){
		
		File file = new File("D:/书籍/"+bookUrl+".txt");
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(new FileInputStream(file),charset);
		} catch (UnsupportedEncodingException e1) {
			System.out.println("----------------isr = new InputStreamReader");
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//每章的内容
		String textConnent =null;
		//每章的名称
		String textName = null;
		// 设置默认编码
		if (charset == null) {
			charset = "UTF-8";
		}
		//存放所有的章节名称
		ArrayList<String> catalogAll=new ArrayList<>();
		
		//输入字符流
		BufferedReader br=null;

		if (file.isFile() && file.exists()) {
			try {
				br = new BufferedReader(isr);
				
				//存放读取出的每一行内容
				String textLine = null;

				while ((textLine = br.readLine()) != null) {
					if (textLine.contains("第") && textLine.contains("章")&&textLine.length()<50) {
						//将新的章节名存入arraylist中
						catalogAll.add(textLine);	
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(br!=null){
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	/*
		Iterator<String> iterator=catalogAll.iterator();
		while(iterator.hasNext()){
			String catalogName=iterator.next();
			System.out.println(catalogName+"\n");
		}
	*/
		return catalogAll;
	}
	/**
	 * 根据书籍地址返回书籍章节名和章节内容（章节名，章节内容）
	 * @param bookUrl
	 * @return Map<String, String>
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, String> readBookReturnMap(String bookUrl,String charset){
		
		File file = new File("D:/书籍/"+bookUrl+".txt");
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(new FileInputStream(file),charset);
		} catch (UnsupportedEncodingException e1) {
			System.out.println("----------------isr = new InputStreamReader");
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//每章的内容
		String textConnent =null;
		//每章的名称
		String textName = null;
		
		if (charset == null) {
			charset = "UTF-8";
		}
		
		//用来存放<章节名，章节内容>
		HashMap<String, String> nameAndConnentMap=new HashMap<>();
		
		//输入字符流
		BufferedReader br=null;

		if (file.isFile() && file.exists()) {
			try {
				br = new BufferedReader(isr);
				
				//存放读取出的每一行内容
				String textLine = null;

				while ((textLine = br.readLine()) != null) {
					if (textLine.contains("第") && textLine.contains("章")) {
						
						//存放章节名和内容
						nameAndConnentMap.put(textName,textConnent);
						
						//清空textConnent内容
						textConnent="";
						
						//此时读取的一行字符串是章节名称，赋给textName
						textName=textLine;
					}else{
						//将读取的每一行追加进textConnent字符串中
						if(textLine.trim().equals("")){
							textConnent+=textLine+"<br/>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						}else{
							textConnent+=textLine+"<br/>";
						}
					}
					
				}
				
				//将最后读取的章节名和章节内容放入map中
				nameAndConnentMap.put(textName,textConnent);
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(br!=null){
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		/*
		Iterator<String> iterator=nameAndConnentMap.keySet().iterator();
		while(iterator.hasNext()){
			String key=iterator.next();
			String Connent=nameAndConnentMap.get(key);
			
			System.out.println(key+"\n"+Connent);
		}
		*/
		return nameAndConnentMap;
	}
	/**
	 * 统计系统男女用户的人数
	 * @return 
	 */
	public static ArrayList<Integer> userSexNums(){
		return null;
	}
}
