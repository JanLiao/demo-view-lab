package com.cvte.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.csvreader.CsvWriter;
import com.cvte.cons.Constant;

/** 
* @author: jan 
* @date: 2018年5月25日 上午12:23:13 
*/
public class ImgAllCSV {

	public static void saveToCSV(List<String> imgList, String dir) {
		String rootPath = System.getProperty("user.dir").replace("\\", "/");
		System.out.println(rootPath);
		File file = new File(rootPath + "/" + Constant.User + "/mixlabel/");
		if(!file.exists()) {
			file.mkdirs();
		}
		String csvPath = rootPath + "/" + Constant.User + "/mixlabel/avgAllImg";
		File f = new File(csvPath);
		if(!f.exists()) {
			try {
				f.createNewFile();
				writeCSV(imgList, dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			appendCSV(imgList, dir);
		}
	}

	private static void writeCSV(List<String> imgList, String dir) {
		String rootPath = System.getProperty("user.dir").replace("\\", "/");
		String csvPath = rootPath + "/" + Constant.User + "/mixlabel/avgAllImg";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 定义一个CSV路径  
	    try {  
	    	CsvWriter csvWriter = new CsvWriter(csvPath,',',Charset.forName("UTF-8"));
	        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);  
	       // CsvWriter csvWriter = new CsvWriter(out, ',');  
	        // 写表头  
	        String[] csvHeaders = { "task_path", "img_name","add_time", "operator"};
	        csvWriter.writeRecord(csvHeaders);  
	        for(String s : imgList) {
	        	// 写内容  
		        String[] csvContent = {dir, s, format.format(new Date()), Constant.User};
		        
		        csvWriter.writeRecord(csvContent);
	        }
	        
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经write写入--------");  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } 
	}

	private static void appendCSV(List<String> imgList, String dir) {
		String rootPath = System.getProperty("user.dir").replace("\\", "/");
		String csvPath = rootPath + "/" + Constant.User + "/mixlabel/avgAllImg";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {  
	    	 BufferedWriter out = new BufferedWriter(new 

	    	           OutputStreamWriter(new FileOutputStream(csvPath,true),"UTF-8"),1024);
	        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);  
	        CsvWriter csvWriter = new CsvWriter(out, ',');  
	        // 写表头  
	        //String[] csvHeaders = { "编号", "姓名", "年龄" };  
	        //csvWriter.writeRecord(csvHeaders);  
	        // 写内容  
	        //String
	        for(String s : imgList) {
	        	// 写内容  
		        String[] csvContent = {dir, s, format.format(new Date()), Constant.User};
		        
		        csvWriter.writeRecord(csvContent);
	        }
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经append写入--------");  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}

	
	
}
