package com.cvte.util;

import java.io.*;
import java.util.*;

import com.cvte.cons.Constant;
import com.cvte.entity.AnalysisData;

/** 
* @author: jan 
* @date: 2018年5月24日 下午6:57:22 
*/
public class LabelDataUtil {

	public static void addData(String dir, int flag, String imgAllPath) {
		//String rootPath = System.getProperty("user.dir").replace('\\', '/');
		if(flag == 0) {   //flag同DirUtil用法  checkPath(dirPath)
			//AnalysisData data = new AnalysisData();
			
			int firstFlag = 0;
			if(Constant.AnalysisMix.getImgList().size() == 0) {
				firstFlag = 1;
			}
			
			List<String> imgList = new ArrayList<String>();
			Map<String, Integer> imgMap = new HashMap<String, Integer>();
			File[] files = new File(dir).listFiles();
			for(File file : files) {   //所选文件夹下所有专家标注数据    所有标注图片
				//String csvPath = rootPath + "/";
				String filePath = dir + "/";
				if(file.isDirectory()) {
					String name = file.getName();
					//csvPath = csvPath + name + "/file/dir";
					filePath = filePath + name + "/file/imgAllLabel";
					//List<String[]> dirList = ReadCSV.readCSV(csvPath);
					List<String[]> imgAllList = ReadCSV.readCSV(filePath);
					for(String[] s : imgAllList) {
						//判断是否存在
						if(!checkList(dir + "," + s[3],imgList)) {
							imgList.add(dir + "," + s[3]);
							imgMap.put(dir + "," + s[3], 0);
						}
						
					}
				}
				else {
					System.out.println("不是文件夹  不载入");
				}
			}
			
			//imgList去重
			
			
			System.out.println("list=" + imgList.size());
			System.out.println("map = " + imgMap.size());
			//将imgList  和imgMap保存到Constant中
			addImgToCons(imgList, imgMap);
			
			//将添加文件夹下用户所有图片保存到avgAllImg中
			ImgAllCSV.saveToCSV(imgList, dir);
			
			//第一次添加数据  初始化Constant
			if(firstFlag == 1) {
				firstInitConstant(dir, imgAllPath);
			}
		}
		else if(flag == 1) {   //同flag==0
			loadExistData(dir, imgAllPath);
		}
		else if(flag == 2) {
			System.out.println("文件夹已存在未被删除,且数据已存在,不需载入数据");
		}
		else {
			System.out.println("未知操作   有误");
		}
	}

	private static void firstInitConstant(String dir, String imgAllPath) {
		//String rootPath = System.getProperty("user.dir").replace('\\', '/');
		Constant.AnalysisMix.setFlag(0);
		//Constant.AnalysisView.setFlag(0);
		
		//图片名
		String imgName = Constant.AnalysisMix.getImgList().get(0);  //含路径
		Constant.AnalysisMix.setImgName(imgName.split(",")[1]);
		//Constant.AnalysisView.setImgName(imgName.split(",")[1]);
		
		//所添加文件夹路径
		Constant.AnalysisMix.setTaskPath(dir);
		//Constant.AnalysisView.setTaskPath(dir);
		
		//总图片数
		Constant.AnalysisMix.setImgNums(Constant.AnalysisMix.getImgList().size());
		//Constant.AnalysisView.setImgNums(Constant.AnalysisView.getImgList().size());
		
		File[] files = new File(dir).listFiles();
		List<String> allLabel = new ArrayList<String>();
		String imgname = imgName.split(",")[1];
		String imgPath = ImagePathUtil.getPath(dir, imgname, imgAllPath);
		String user = "";
		for(File file : files) {
			String name = file.getName();
			//当前user下单个csv 名
			String soleName = getSoleName(dir, name, imgName.split(",")[1]);
		    
		    //先扫ImgAllLabel
		    String label = "";
		    String allLabelPath = dir + "/" + name + "/file/imgAllLabel" ;
		    List<String[]> allList = ReadCSV.readCSV(allLabelPath);
		    for(String[] s : allList) {
		    	if(imgname.equals(s[3])) {
		    		label = s[7];
		    	}
		    }
		    
		    String labelPath = dir + "/" + name + "/file/" + soleName + "/"
		    		+ imgname.split("[.]")[0];
		    File fl = new File(labelPath);
		    if(fl.exists()) {
		    	label = getLabel(labelPath);
		    	allLabel.add(name + "=" + label);
		    	user = user + name + ",";
		    }
		    else {
		    	allLabel.add(name + "=" + label);
		    	user = user + name + ",";
		    }
		}
		
		//设置图片路径(依次扫各个user)
		Constant.AnalysisMix.setImgPath(imgPath);
		//Constant.AnalysisView.setImgPath(imgPath);
		
		//设置当前图片所有已被标注的label
		//打乱labeldata
		List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
		Constant.AnalysisMix.setAllLabelData(shuffleLable);
		//Constant.AnalysisView.setAllLabelData(shuffleLable);
		
		Constant.AnalysisMix.setAllLabelUser(user);
		Constant.AnalysisMix.setBeiUser(user);
		Constant.AnalysisMix.setCenterUser(user);
		Constant.AnalysisMix.setPanUser(user);
//		Constant.AnalysisView.setAllLabelUser(user);
//		Constant.AnalysisView.setBeiUser(user);
//		Constant.AnalysisView.setCenterUser(user);
//		Constant.AnalysisView.setPanUser(user);
		
		//生成当前图片mask相关数据
		MaskUtil.generateData();
		System.out.println("Mark = " + Constant.AnalysisMix.getMask());
		
		System.out.println("all data = " + Constant.AnalysisMix);
	}

	private static String getLabel(String labelPath) {
		List<String[]> list = ReadCSV.readCSV(labelPath);
		return list.get(list.size() - 1)[7];
	}

	private static String getSoleName(String dir, String name, String imgName) {
		List<String[]> list = ReadCSV.readCSV(dir + "/" + name + "/file/imgAllLabel");
		String tmp = "";
		for(String[] s : list) {
			if(imgName.equals(s[3])) {
				String[] str = s[2].split("/");
				tmp = str[str.length - 1];
				break;
			}
		}
		return tmp;
	}

	private static void loadExistData(String dir, String imgAllPath) {
		String rootPath = System.getProperty("user.dir").replace('\\', '/');
		//AnalysisData data = new AnalysisData();
		
		int firstFlag = 0;
		if(Constant.AnalysisMix.getImgList().size() == 0) {
			firstFlag = 1;
		}
		
		List<String> imgList = new ArrayList<String>();
		Map<String, Integer> imgMap = new HashMap<String, Integer>();
		File[] files = new File(dir).listFiles();
		for(File file : files) {   //所选文件夹下所有专家标注数据    所有标注图片
			//String csvPath = rootPath + "/";
			String filePath = rootPath + "/";
			if(file.isDirectory()) {
				String name = file.getName();
				//csvPath = csvPath + name + "/file/dir";
				filePath = filePath + name + "/file/imgAllLabel";
				//List<String[]> dirList = ReadCSV.readCSV(csvPath);
				List<String[]> imgAllList = ReadCSV.readCSV(filePath);
				for(String[] s : imgAllList) {
					//判断是否存在
					if(!checkList(dir + "," + s[3],imgList)) {
						imgList.add(dir + "," + s[3]);
					}
					
					//判断是否存在
					if(checkMix(s[3])) {
						imgMap.put(dir + "," + s[3], 1);
					}
					else {
						imgMap.put(dir + "," + s[3], 0);
					}
				}
			}
			else {
				System.out.println("不是文件夹  不载入");
			}
		}
		
		addImgToCons(imgList, imgMap);
		
		if(firstFlag == 1) {
			firstInitConstant(dir, imgAllPath);
		}
	}

	private static void addImgToCons(List<String> imgList, Map<String, Integer> imgMap) {
		for(String s : imgList) {
			Constant.AnalysisMix.getImgList().add(s);
			Constant.AnalysisView.getImgList().add(s);
			
			Constant.AnalysisMix.getImgMap().put(s, imgMap.get(s));
			Constant.AnalysisView.getImgMap().put(s, imgMap.get(s));
		}
	}

	private static boolean checkMix(String imgName) {
		boolean flag = false;
		String rootPath = System.getProperty("user.dir").replace('\\', '/');
		String path = rootPath + "/" + Constant.User + "/mixlabel/sole";
		File f1 = new File(path);
		if(!f1.exists()) {
			f1.mkdirs();
			return flag;
		}
		else {
			String name = imgName.split("[.]")[0];
			File f2 = new File (path + "/" + name);
			if(f2.exists()) {
				flag = true;
			}
			return flag;
		}
	}

	private static boolean checkList(String img, List<String> imgList) {
		if(imgList.size() == 0) {
			return false;
		}
		
		String[] sk = img.split(",")[1].split("___");
		if(sk.length == 1) {
			String st = img.split(",")[1].split("_")[1].substring(0, 1);
			if(st.equals("L") || "R".equals(st)) {
				boolean flag = false;
				for(String s : imgList) {
					if(img.trim().equals(s)) {
						flag = true;
						break;
					}
				}
				return flag;
			}
			else {
				return true;
			}
		}
		else {
			boolean flag = false;
			for(String s : imgList) {
				if(img.trim().equals(s)) {
					flag = true;
					break;
				}
			}
			return flag;
		}
	}

	private static boolean checkDelete(String[] s, List<String[]> dirList) {
		boolean flag = false;
		for(String[] str : dirList) {
			if(s[2].equals(str[2])) {
				if(Integer.parseInt(str[7]) == 1) {
					flag = true;
				}
				break;
			}
		}
		return flag;
	}

}
