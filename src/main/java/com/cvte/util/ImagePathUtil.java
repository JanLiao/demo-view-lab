package com.cvte.util;

import java.io.File;
import java.util.List;

import com.cvte.cons.Constant;

/** 
* @author: jan 
* @date: 2018年6月8日 下午11:41:15 
*/
public class ImagePathUtil {

	public static String getPath(String dir, String imgname) {
		String rootPath = System.getProperty("user.dir").replace("\\", "/");
		String imgPath = "";
		List<String[]> list = ReadCSV.readCSV(rootPath + "/" + Constant.User + "/mixlabel/dir");
		for(String[] s : list) {
			if(dir.equals(s[1]) || dir.equals(s[8])) {
				if(s.length == 8) {
					imgPath = "";
				}
				else {
					imgPath = s[9];
					System.out.println("111  =" + imgPath);
				}
				break;
			}
		}
		
		if("".equals(imgPath)) {
			System.out.println("为空");
			for(String[] s : list) {
				if(dir.equals(s[1]) || dir.equals(s[8])) {
					imgPath = s[7];
					break;
				}
			}
		}else {
			File f = new File(imgPath);
			if(!f.exists()) {
				imgPath = "";
				for(String[] s : list) {
					if(dir.equals(s[1]) || dir.equals(s[8])) {
						imgPath = s[7];
						System.out.println("222  =" + imgPath);
						break;
					}
				}
			}
		}
		
		System.out.println("透明片 = " + imgPath);
		
		String[] str = imgname.split("___");
    	if(str.length == 1) {
    		String[] s = str[0].split("_");
    		imgPath = imgPath + "/" + s[0] + "/" + s[1];
    	}
    	else {
    		if("1".equals(str[0])) {
    			String[] s = str[1].split("_");
    			imgPath = imgPath + "/" + s[0] + "/" + s[1];
    		}
    		else if("2".equals(str[0])) {
    			imgPath = imgPath + "/" + str[1];
    		}
    	}
    	System.out.println("imgPath = " + imgPath);
		return imgPath;
	}
	
	public static String getPath(String dir, String imgname, String imgAllPath) {
		String imgPath = imgAllPath;
		
		String[] str = imgname.split("___");
    	if(str.length == 1) {
    		String[] s = str[0].split("_");
    		imgPath = imgPath + "/" + s[0] + "/" + s[1];
    	}
    	else {
    		if("1".equals(str[0])) {
    			String[] s = str[1].split("_");
    			imgPath = imgPath + "/" + s[0] + "/" + s[1];
    		}
    		else if("2".equals(str[0])) {
    			imgPath = imgPath + "/" + str[1];
    		}
    	}
    	System.out.println("imgPath = " + imgPath);
		return imgPath;
	}

	public static String getDirPath(String dir) {
		String rootPath = System.getProperty("user.dir").replace("\\", "/");
		String dirPath = "";
		List<String[]> list = ReadCSV.readCSV(rootPath + "/" + Constant.User + "/mixlabel/dir");
		for(String[] s : list) {
			if(dir.equals(s[1])) {
				if(s.length == 8) {
					dirPath = "";
				}
				else {
					dirPath = s[8];
					System.out.println("333  =" + dirPath);
				}
				break;
			}
		}
		
		if("".equals(dirPath)) {
			System.out.println("为空");
			dirPath = dir;
		}else {
			File f = new File(dirPath);
			if(!f.exists()) {
				dirPath = dir;
			}
		}
		
		return dirPath;
	}

}
