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
	private BookTService bookTService;//�鼮
	@Autowired
	private UserTService userTService; //�û�
	
	/**
	 * �ж�һ���ַ����Ƿ�Ϊ��
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
	 * ���ڸ�ʽ���ַ���ת�������ڶ���
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
	 * �����ڶ���ת����ָ����ʽ���ַ���
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
	 * �����鼮��ַ�������鼮�����½����ƣ��鼮Ŀ¼��
	 * @param bookUrl
	 * @return ArrayList<String>
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static ArrayList<String> readBookReturnArrayList(String bookUrl,String charset){
		
		File file = new File("D:/�鼮/"+bookUrl+".txt");
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
					if (textLine.contains("��") && textLine.contains("��")&&textLine.length()<50) {
						//���µ��½�������arraylist��
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
	 * �����鼮��ַ�����鼮�½������½����ݣ��½������½����ݣ�
	 * @param bookUrl
	 * @return Map<String, String>
	 * @throws FileNotFoundException 
	 * @throws UnsupportedEncodingException 
	 */
	public static Map<String, String> readBookReturnMap(String bookUrl,String charset){
		
		File file = new File("D:/�鼮/"+bookUrl+".txt");
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
		
		if (charset == null) {
			charset = "UTF-8";
		}
		
		//�������<�½������½�����>
		HashMap<String, String> nameAndConnentMap=new HashMap<>();
		
		//�����ַ���
		BufferedReader br=null;

		if (file.isFile() && file.exists()) {
			try {
				br = new BufferedReader(isr);
				
				//��Ŷ�ȡ����ÿһ������
				String textLine = null;

				while ((textLine = br.readLine()) != null) {
					if (textLine.contains("��") && textLine.contains("��")) {
						
						//����½���������
						nameAndConnentMap.put(textName,textConnent);
						
						//���textConnent����
						textConnent="";
						
						//��ʱ��ȡ��һ���ַ������½����ƣ�����textName
						textName=textLine;
					}else{
						//����ȡ��ÿһ��׷�ӽ�textConnent�ַ�����
						if(textLine.trim().equals("")){
							textConnent+=textLine+"<br/>"+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
						}else{
							textConnent+=textLine+"<br/>";
						}
					}
					
				}
				
				//������ȡ���½������½����ݷ���map��
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
	 * ͳ��ϵͳ��Ů�û�������
	 * @return 
	 */
	public static ArrayList<Integer> userSexNums(){
		return null;
	}
}
