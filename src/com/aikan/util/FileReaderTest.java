package com.aikan.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class FileReaderTest {

	public static void main(String[] args) {
		String charset="UTF-8";
		File file = new File("D:/书籍/小俏媳/小俏媳.txt");
	//	File file = new File("D:/书籍/佛本是道/佛本是道.txt");
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
					if (textLine.contains("第") && textLine.contains("章")&&textLine.length()<30) {
						//将新的章节名存入arraylist中
						catalogAll.add(textLine);	
						System.out.println(textLine);
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
//	
//		Iterator<String> iterator=catalogAll.iterator();
//		while(iterator.hasNext()){
//			String catalogName=iterator.next();
//			System.out.println(catalogName+"\n");
//		}
	}
	
}
		


