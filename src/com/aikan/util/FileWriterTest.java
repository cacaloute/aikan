package com.aikan.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class FileWriterTest {

	public static void main(String[] args) {
		String path0 = "D:/书籍/bb/";
        String path1 = "D:/书籍/bb/bb.txt";
        File f1 = new File(path0);
        File f = new File(path0);
        // 创建文件夹
        if (!f1.exists()) {
            f1.mkdirs();
        }
        f = new File(path1);

        // 创建文件
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		 try {
			 BufferedWriter out = new BufferedWriter(new FileWriter(f));  
	         out.write("第一章 这是个测试"
	        		 +"白发斯蒂芬  U盾师傅is阿富汗撒谎  回复撒谎飞洒uhfshshf9  合法岁的回复is爱的好读书发哦或发生uahfuas"
	        		 +"豆腐丝固化如果经常能够热韩国人三牛肉干 傻瓜还如果"); // \r\n即为换行 
	         out.flush(); // 把缓存区内容压入文件  
             out.close(); // 最后记得关闭文件  
             
		} catch (IOException e) {
			System.out.println("文件写错误。");
			e.printStackTrace();
		} // 创建新文件  
        
	}
}
		


