package com.cvte.util;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

import com.cvte.cons.Constant;
import com.cvte.entity.DirData;
import com.cvte.entity.TaskDir;

/** 
* @author: jan 
* @date: 2018年5月24日 下午2:43:18 
*/
public class DirUtil {

	public static DirData addDir(String dirPath, String imgPath) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//dir文件夹下数据处理
		LabelDataUtil.addData(dirPath, checkPath(dirPath), imgPath);
		
		if(checkPath(dirPath) == 0) {   //dir不存在
			TaskDir dir = new TaskDir();
			dir.setId("" + (Constant.DirList.size() + 1));
			dir.setDeleted(0);
			dir.setAddTime(format.format(new Date()));
			dir.setNums(getNums(dirPath));
			dir.setPath(dirPath);
			dir.setImgPath(imgPath);
			dir.setImgNums("" + Constant.AnalysisMix.getImgMap().size());
			dir.setOperator(Constant.User);
			Constant.DirList.add(dir);
			DirCSV.saveDir(dir);
			
			//loading image流
			ImageListInit.initImage();
			
			// 初始化sqlite DB 用于 预先读取all label数据
			SqlUtil.connInit();
			
			return new DirData(dir.getId(), dir.getPath(), 
					dir.getImgPath(), dir.getNums(), dir.getImgNums(), dir.getAddTime());
		}
		else if(checkPath(dirPath) == 1) {  //dir存在  但被删除
			System.out.println("已存在,只需更新deleted");
			DirData dir = null;
			for(int i = 0; i < Constant.DirList.size(); i++) {
				if(dirPath.equals(Constant.DirList.get(i).getPath())) {
					TaskDir task = Constant.DirList.get(i);
					Constant.DirList.get(i).setDeleted(0);
					dir = new DirData(task.getId(), task.getPath(),
							task.getImgPath(), task.getNums(), task.getImgNums()
							, task.getAddTime());
					DirCSV.updateDir(task, 0);
					break;
				}
			}
			return dir;
		}
		else if(checkPath(dirPath) == 2) {  //dir存在  且未被删除
			System.out.println("已存在,且未被删除");
			return null;
		}
		else {
			return null;
		}
	}

	private static String getImgNums(String dirPath) {
		File file = new File(dirPath);
		if(file.listFiles().length != 0) {
			String name = file.listFiles()[0].getName();
			File f1 = new File(dirPath + "/" + name + "/file/imgAllLabel");
			if(f1.exists()) {				
				List<String[]> list = ReadCSV.readCSV(dirPath + "/" + name + "/file/imgAllLabel");
				return "" + list.size();
			}
			else {
				System.out.println("不存在文件，添加有误");
				return "" + 0;
			}
		}
		else {
			System.out.println("文件夹下文件为空, 添加有误");
			return "" + 0;
		}
	}

	private static String getNums(String dirPath) {
		File file = new File(dirPath);
		return "" + file.listFiles().length;
	}

	public static int checkPath(String dirPath) {
		int flag = 0;   //0-不存在  1-存在已被删除  2-存在且未被删除
		if(Constant.DirList.size() == 0) {
			return flag;
		}
		else {
			for(TaskDir dir : Constant.DirList) {
				if(dirPath.equals(dir.getPath())) {
					if(dir.getDeleted() == 1) {
						flag = 1;
					}
					else {
						flag = 2;
					}
					break;
				}
			}
		}
		return flag;
	}

	
	
}
