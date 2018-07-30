package com.cvte.util;

import java.io.File;
import java.util.*;

import com.cvte.cons.Constant;
import com.cvte.entity.TaskDir;
import com.cvte.ui.Main;

import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年5月24日 下午2:26:01 
*/
public class DataInit {

	//初始化任务及图片数据  AnalysisData
	public static void loadData() {
		//初始化任务文件夹数据
		String rootPath = System.getProperty("user.dir").replace("\\", "/");
		File labelFile = new File(rootPath + "/" + Constant.User + "/mixlabel");
		if(!labelFile.exists()) {
			labelFile.mkdirs();
			System.out.println("文件夹不存在,不用load dir文件夹数据");
		}
		else {
			File dirFile = new File(rootPath + "/" + Constant.User + "/mixlabel/dir");
			if(!dirFile.exists()) {
				System.out.println("dir文件不存在");
			}
			else {
				List<TaskDir> dirList = new ArrayList<TaskDir>();
				List<String[]> list = ReadCSV.readCSV(rootPath + "/" + Constant.User + "/mixlabel/dir");
				for(String[] s : list) {
					TaskDir dir = new TaskDir(s[0], s[1], s[2], s[3], s[4], Integer.parseInt(s[5]));
					dir.setImgPath(s[7]);
					dirList.add(dir);
				}
				Constant.DirList = dirList;
			}
		}
		
		Constant.AnalysisView.setFlag(-1);
		//初始化所有专家label数据
		initLabelData();
	}

	private static void initLabelData() {
		//读取  Constant.user  下mixlabel  下 avgAllImg文件
		String rootPath = System.getProperty("user.dir").replace('\\', '/');
		String filePath = rootPath + "/" + Constant.User + "/mixlabel/avgAllImg";
		if(new File(filePath).exists()) {
			List<String[]> imgList = ReadCSV.readCSV(filePath);
			
			String tmpdir = imgList.get(0)[1].split(",")[0];
			String tmpname = imgList.get(0)[1].split(",")[1];
			String imgPath = ImagePathUtil.getPath(tmpdir, tmpname);
			String dirtmppath = ImagePathUtil.getDirPath(tmpdir);
			System.out.println("tmp = " + imgPath + "=" + dirtmppath);
			//判断图片路径是否正确，若不正确可重新选择一个临时路径	
			if(new File(imgPath).exists() && new File(dirtmppath).exists()) {
				String csvPath = rootPath + "/" + Constant.User + "/mixlabel/dir";
				List<String[]> dirList = ReadCSV.readCSV(csvPath);
				List<String> img = new ArrayList<String>();
				Map<String, Integer> map = new HashMap<String, Integer>();
				int fflag = 0;
				int tmp = 0;
				//挑出未删除dir下所有图片
				for(int i = 0; i < imgList.size(); i++) {
					if(checkDeleted(imgList.get(i)[1], dirList)) {
						System.out.println("该图片已被删除");
					}
					else {
						String ttp = dirtmppath + "," + imgList.get(i)[1].split(",")[1];
						img.add(ttp);
						String nam = imgList.get(i)[1].split(",")[1].split("[.]")[0];
						File fil = new File(rootPath + "/" + Constant.User + "/mixlabel/sole/" + nam);
						if(fil.exists()) {
							map.put(ttp, 1);
						}
						else {
							if(tmp == 0) {
								fflag = img.size() - 1;
								tmp = 1;
							}
							map.put(ttp, 0);
						}
					}
				}

				Constant.AnalysisMix.setImgList(img);
				Constant.AnalysisView.setImgList(img);
				Constant.AnalysisMix.setFlag(fflag);

				Constant.AnalysisMix.setImgMap(map);
				Constant.AnalysisView.setImgMap(map);
				
				Constant.AnalysisMix.setImgNums(img.size());
				Constant.AnalysisMix.setTaskPath(img.get(fflag).split(",")[0]);
				Constant.AnalysisView.setImgNums(img.size());
				Constant.AnalysisView.setTaskPath(img.get(fflag).split(",")[0]);
				Constant.AnalysisView.setFlag(-1);
				BtnUpdateUtil.finalChange();
				//BtnUpdateUtil.viewInit();
				
				//loading image流
				ImageListInit.initImage();
				
				// 初始化sqlite DB 用于 预先读取all label数据
				SqlUtil.connInit();
				
				Constant.stage.close();
				//LabelDemo demo = new LabelDemo();
				Main demo = new Main();
				try {
					demo.start(Constant.stage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else {
				DialogUtil.showAlertMsg(Constant.stage, "标注路径及图片路径已被改变, 请重新选择", imgList, rootPath);
			}
		}
		else {
			System.out.println("没有需要加载数据");
			Constant.stage.close();
			//LabelDemo demo = new LabelDemo();
			Main demo = new Main();
			try {
				demo.start(Constant.stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//System.out.println("init = " + Constant.AnalysisMix);
			//System.out.println("==" + Constant.AnalysisView);
		}
	}

	private static boolean checkDeleted(String imgName, List<String[]> dirList) {
		boolean flag = true;
		for(int i = 0; i < dirList.size(); i++) {
			if(imgName.split(",")[0].equals(dirList.get(i)[1])) {
				if(Integer.parseInt(dirList.get(i)[5]) == 0) {
					flag = false;
					break;
				}
				else {
					break;
				}
			}
		}
		return flag;
	}

}
