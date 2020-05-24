package com.aikan.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	// 获取小说中的章节
	public static List getChapter(String address, String charset) {
		// 设置默认编码

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
				// FileInputStream(new File("d:/页面/21006.txt")),
				// "UTF-8"));

				br = new BufferedReader(new FileReader(address));

				String text = null;

				while ((text = br.readLine()) != null) {
					if (text.contains("第") && text.contains("章")) {
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

	// 获取小说中的章节
	public static List getChapterContext(String address, String charset) {
		// 设置默认编码

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
				// FileInputStream(new File("d:/页面/21006.txt")),
				// "UTF-8"));

				br = new BufferedReader(new FileReader(address));

				String text = null;
				
				String nexttext=null;

				text = br.readLine();
				
				while (text != null) {

					String context ="";
					
					if (text.contains("第") && text.contains("章")) {
//						while ((context = br.readLine()) != null) {
//							if (!(context.contains("第") && context.contains("章"))) {
//								context = context + text;
//							} else {
//								list.add(context);
//								break;
//							}
//						}
						while ((nexttext = br.readLine()) != null) {
							if (!(nexttext.contains("第") && nexttext.contains("章"))) {
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
