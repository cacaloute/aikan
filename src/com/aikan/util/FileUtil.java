package com.aikan.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	// ��ȡС˵�е��½�
	public static List getChapter(String address, String charset) {
		// ����Ĭ�ϱ���

		List list = new ArrayList();

		BufferedReader br = null;

		File file = new File(address);

		if (charset == null) {
			charset = "UTF-8";
		}

		if (file.isFile() && file.exists()) {
			try {
				System.out.println("123");

				// br = new BufferedReader(new InputStreamReader(new
				// FileInputStream(new File("d:/ҳ��/21006.txt")),
				// "UTF-8"));

				br = new BufferedReader(new FileReader(address));

				String text = null;

				while ((text = br.readLine()) != null) {
					if (text.contains("��") && text.contains("��")) {
						list.add(text);
					}

				}
				return list;

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			// String text = FileUtil.readFile(file, "UTF-8");
			//
			// System.out.println(text);

		}
		
		return list;
	}

	// ��ȡС˵�е��½�
	public static List getChapterContext(String address, String charset) {
		// ����Ĭ�ϱ���

		List list = new ArrayList();

		BufferedReader br = null;

		File file = new File(address);

		if (charset == null) {
			charset = "UTF-8";
		}

		if (file.isFile() && file.exists()) {
			try {
				System.out.println("123");

				// br = new BufferedReader(new InputStreamReader(new
				// FileInputStream(new File("d:/ҳ��/21006.txt")),
				// "UTF-8"));

				br = new BufferedReader(new FileReader(address));

				String text = null;
				
				String nexttext=null;

				text = br.readLine();
				
				while (text != null) {

					String context ="";
					
					if (text.contains("��") && text.contains("��")) {
//						while ((context = br.readLine()) != null) {
//							if (!(context.contains("��") && context.contains("��"))) {
//								context = context + text;
//							} else {
//								list.add(context);
//								break;
//							}
//						}
						while ((nexttext = br.readLine()) != null) {
							if (!(nexttext.contains("��") && nexttext.contains("��"))) {
								context = context + nexttext+"\n";
							}else{	
								text=nexttext;				
								list.add(context);
								context ="";
							}	
						}
						list.add(context);
					}
					
					text = br.readLine();		
				}
				return list;

			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		return list;
	}
}
