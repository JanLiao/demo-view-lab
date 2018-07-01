package com.cvte.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.csvreader.CsvWriter;
import com.cvte.cons.Constant;
import com.cvte.entity.ConsoleImg;

/** 
* @author: jan 
* @date: 2018年5月27日 上午4:22:26 
*/
public class ImgConsoleCSV {

	public static void saveToCSV(ConsoleImg img) {
		String rootPath = System.getProperty("user.dir").replace("\\", "/");
		System.out.println(rootPath);
		File file = new File(rootPath + "/" + Constant.User + "/mixlabel/sole");
		if(!file.exists()) {
			file.mkdirs();
		}
		String imgName = img.getImgName().split("[.]")[0];
		String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imgName;
		File f = new File(csvPath);
		if(!f.exists()) {
			try {
				f.createNewFile();
				writeCSV(img);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			appendCSV(img);
		}
	}

	private static void appendCSV(ConsoleImg img) {
		String rootPath = System.getProperty("user.dir").replace("\\", "/");
		String imgName = img.getImgName().split("[.]")[0];
		String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imgName;
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
			// 写内容
			String[] csvContent = { img.getImgName(), img.getImgPath(), JSON.toJSONString(img), format.format(new Date()), Constant.User  };

			csvWriter.writeRecord(csvContent);
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经append写入--------");  
	    } catch (IOException e) {
	        e.printStackTrace();
	    }  
	}

	private static void writeCSV(ConsoleImg img) {
		String rootPath = System.getProperty("user.dir").replace("\\", "/");
		String imgName = img.getImgName().split("[.]")[0];
		String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imgName;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 定义一个CSV路径  
	    try {  
	    	CsvWriter csvWriter = new CsvWriter(csvPath,',',Charset.forName("UTF-8"));
	        // 创建CSV写对象 例如:CsvWriter(文件路径，分隔符，编码格式);  
	       // CsvWriter csvWriter = new CsvWriter(out, ',');  
	        // 写表头  
	        String[] csvHeaders = { "img_name", "img_path", "img_mix_data", "add_time", "operator"};
	        csvWriter.writeRecord(csvHeaders);  
			// 写内容
			String[] csvContent = { img.getImgName(), img.getImgPath(), JSON.toJSONString(img), format.format(new Date()), Constant.User };

			csvWriter.writeRecord(csvContent);
	        
	        csvWriter.close();  
	        System.out.println("--------CSV文件已经write写入--------");  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } 
	}

}
