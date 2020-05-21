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
		File file = new File("D:/�鼮/С��ϱ/С��ϱ.txt");
	//	File file = new File("D:/�鼮/���ǵ�/���ǵ�.txt");
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
		//ÿ�µ�����
		String textConnent =null;
		//ÿ�µ�����
		String textName = null;
		// ����Ĭ�ϱ���
		if (charset == null) {
			charset = "UTF-8";
		}
		//������е��½�����
		ArrayList<String> catalogAll=new ArrayList<>();
		
		//�����ַ���
		BufferedReader br=null;

		if (file.isFile() && file.exists()) {
			try {
				br = new BufferedReader(isr);
				
				//��Ŷ�ȡ����ÿһ������
				String textLine = null;

				while ((textLine = br.readLine()) != null) {
					if (textLine.contains("��") && textLine.contains("��")&&textLine.length()<30) {
						//���µ��½�������arraylist��
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
		


