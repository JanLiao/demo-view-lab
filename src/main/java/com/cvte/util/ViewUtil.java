package com.cvte.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.cvte.cons.Constant;
import com.cvte.entity.ConsoleImg;
import com.cvte.ui.Main;
import com.cvte.ui.Mark;
import com.cvte.ui.MarkView;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** 
* @author: jan 
* @date: 2018年5月26日 下午11:20:29 
*/
public class ViewUtil {

	public static void preImage(Stage stage) {
		if(Constant.AnalysisView.getFlag() == 0) {
			showAlert(stage, "当前已是第一张");
		}
		else {
			preShowSave(stage);
		}
	}

	public static void nextImage(Stage stage) {
		if(Constant.AnalysisView.getFlag() == (Constant.AnalysisView.getImgNums() - 1)) {
			showAlert(stage, "当前已是最后一张");
		}
		else {
			nextShowSave(stage);
		}
	}
	
	@SuppressWarnings("rawtypes")
	private static void showAlert(Stage stage, String msg) {
		JFXAlert alert = new JFXAlert((Stage)stage.getScene().getWindow());
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("温馨小提示"));
        Label content = new Label(msg);
        layout.setBody(content);
        JFXButton closeButton = new JFXButton("关  闭");
        closeButton.setStyle("-fx-background-color: GREEN;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
	}
	
	public static void preShowSave(Stage stage) {
		//不保存加载上一张图片
    	loadPreImg(stage);
	}

	public static void nextShowSave(Stage stage) {
		//不保存加载下一张图片
    	loadNextImg(stage);
	}
	
	
	private static void loadPreImg(Stage stage) {
		if(Constant.AnalysisView.getFlag() == 0) {
			System.out.println("当前flag已到最小值,即将返回主界面");
//			stage.close();
//			Main main = new Main();
//			try {
//				main.start(stage);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			showAlert(stage, "当前已是第一张");
		}
		else {
			//加载上一张图
			int flag = 0;
			for(int i = Constant.AnalysisView.getFlag() - 1; i >= 0; i--) {
				String s = Constant.AnalysisMix.getImgList().get(i);
				if(Constant.AnalysisMix.getImgMap().get(s) == 1) {
					Constant.AnalysisView.setFlag(i);
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				showAlert(stage, "您要的上一张已经到底了");
			}
			else {
				// 图片名
				String imgName = Constant.AnalysisMix.getImgList().get(Constant.AnalysisView.getFlag()); // 含路径
				Constant.AnalysisView.setImgName(imgName.split(",")[1]);
				
				String dir = Constant.AnalysisMix.getImgList().get(Constant.AnalysisView.getFlag()).split(",")[0];
				File[] files = new File(dir).listFiles();
				List<String> allLabel = new ArrayList<String>();
				String imgname = imgName.split(",")[1];
				String imgPath = ImagePathUtil.getPath(dir, imgname);
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
				}
				
				//设置图片路径(依次扫各个user)
				Constant.AnalysisView.setImgPath(imgPath);
				
				// 设置当前图片所有已被标注的label
				// 打乱labeldata
				List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
				Constant.AnalysisView.setAllLabelData(shuffleLable);
				
				//该处要改  读CSV文件
				String tmp = Constant.AnalysisMix.getImgList().get(Constant.AnalysisView.getFlag());
				if(Constant.AnalysisMix.getImgMap().get(tmp) == 0) {
					Constant.AnalysisView.setAllLabelUser(user);
					Constant.AnalysisView.setBeiUser(user);
					Constant.AnalysisView.setCenterUser(user);
					Constant.AnalysisView.setPanUser(user);
				}
				else {
					String rootPath = System.getProperty("user.dir").replace("\\", "/");
					String imname = imgName.split(",")[1].split("[.]")[0];
					String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imname;
					List<String[]> listStr = ReadCSV.readCSV(csvPath);
					String imgMixData = listStr.get(listStr.size() - 1)[2];
					ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
					Constant.ViewBeiAvg = obj.getBeiAvg();
					Constant.ViewCenterAvg = obj.getCenterAvg();
					Constant.ViewPanAvg = obj.getPanAvg();
					Constant.AnalysisView.setAllLabelUser(obj.getAllMixUser());
					Constant.AnalysisView.setBeiUser(obj.getBeiMixUser());
					Constant.AnalysisView.setCenterUser(obj.getCenterMixUser());
					Constant.AnalysisView.setPanUser(obj.getPanMixUser());
					Constant.AnalysisView.setStandUser(obj.getStandUser());
					Constant.AnalysisView.setPercent(obj.getPercent());
					Constant.AnalysisView.setBeiPercent(obj.getBeipercent());
				}

				System.out.println("all data = " + Constant.AnalysisView);
				MarkView mark = new MarkView();
				Stage stagenwe = new Stage();
				//stagenwe.initStyle(StageStyle.TRANSPARENT);
				try {
					mark.start(stagenwe);
				} catch (Exception e) {
					e.printStackTrace();
				}
				stage.close();
			}
		}
	}
	
	public static void viewInit() {
		if(Constant.AnalysisView.getImgList().size() != 0) {
			int flag = 0;
			for(int i = 0; i < Constant.AnalysisMix.getImgList().size(); i++) {
				String s = Constant.AnalysisMix.getImgList().get(i);
				if(Constant.AnalysisMix.getImgMap().get(s) == 1) {
					Constant.AnalysisView.setFlag(i);
					flag = 1;
					break;
				}
			}
			
			if (flag == 1) {
				// 图片名
				String imgName = Constant.AnalysisMix.getImgList().get(Constant.AnalysisView.getFlag()); // 含路径
				Constant.AnalysisView.setImgName(imgName.split(",")[1]);
				// Constant.AnalysisView.setImgName(imgName.split(",")[1]);

				String dir = Constant.AnalysisMix.getImgList().get(Constant.AnalysisView.getFlag()).split(",")[0];
				File[] files = new File(dir).listFiles();
				List<String> allLabel = new ArrayList<String>();
				String imgname = imgName.split(",")[1];
				String imgPath = ImagePathUtil.getPath(dir, imgname);
				String user = "";
				for (File file : files) {
					String name = file.getName();
					// 当前user下单个csv 名
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

				// 设置图片路径(依次扫各个user)
				Constant.AnalysisView.setImgPath(imgPath);

				// 设置当前图片所有已被标注的label
				// 打乱labeldata
				List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
				Constant.AnalysisView.setAllLabelData(shuffleLable);

				// 该处要改 读CSV文件
				String tmp = Constant.AnalysisMix.getImgList().get(Constant.AnalysisView.getFlag());
				if (Constant.AnalysisMix.getImgMap().get(tmp) == 0) {
					Constant.AnalysisView.setAllLabelUser(user);
					Constant.AnalysisView.setBeiUser(user);
					Constant.AnalysisView.setCenterUser(user);
					Constant.AnalysisView.setPanUser(user);
				} else {
					String rootPath = System.getProperty("user.dir").replace("\\", "/");
					String imname = imgName.split(",")[1].split("[.]")[0];
					String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imname;
					List<String[]> listStr = ReadCSV.readCSV(csvPath);
					String imgMixData = listStr.get(listStr.size() - 1)[2];
					ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
					Constant.ViewBeiAvg = obj.getBeiAvg();
					Constant.ViewCenterAvg = obj.getCenterAvg();
					Constant.ViewPanAvg = obj.getPanAvg();
					Constant.AnalysisView.setAllLabelUser(obj.getAllMixUser());
					Constant.AnalysisView.setBeiUser(obj.getBeiMixUser());
					Constant.AnalysisView.setCenterUser(obj.getCenterMixUser());
					Constant.AnalysisView.setPanUser(obj.getPanMixUser());
					Constant.AnalysisView.setStandUser(obj.getStandUser());
					Constant.AnalysisView.setPercent(obj.getPercent());
				}

				System.out.println("all data = " + Constant.AnalysisView);
			}
		}
		else {
			System.out.println("view没有数据");
		}
	}
	
	private static void loadNextImg(Stage stage) {
		if(Constant.AnalysisView.getFlag() + 1 >= Constant.AnalysisMix.getImgNums()
				) {
			System.out.println("当前flag已到最大值,即将返回主界面");
			//重新初始化
			viewInit();
			Constant.AnalysisView.setFlag(0);
			
			Main main = new Main();
			Stage stagenew = new Stage();
			try {
				main.start(stagenew);
			} catch (Exception e) {
				e.printStackTrace();
			}
			stage.close();
		}
		else {
			System.out.println("加载下一张");
			System.out.println("kkk=" + Constant.AnalysisView.getFlag());
			//加载下一张图
			int flag = 0;
			for(int i = Constant.AnalysisView.getFlag() + 1; i < Constant.AnalysisMix.getImgList().size(); i++) {
				String s = Constant.AnalysisMix.getImgList().get(i);
				if(Constant.AnalysisMix.getImgMap().get(s) == 1) {
					Constant.AnalysisView.setFlag(i);
					flag = 1;
					break;
				}
			}
			System.out.println("view flag = " + Constant.AnalysisView.getFlag());
			if(flag == 0) {
				//重新初始化
				Main main = new Main();
				Stage stagenew = new Stage();
				try {
					main.start(stagenew);
				} catch (Exception e) {
					e.printStackTrace();
				}
				stage.close();
				viewInit();
			}
			else {
				System.out.println(8888);
				// 图片名
				String imgName = Constant.AnalysisMix.getImgList().get(Constant.AnalysisView.getFlag()); // 含路径
				Constant.AnalysisView.setImgName(imgName.split(",")[1]);
				
				String dir = Constant.AnalysisMix.getImgList().get(Constant.AnalysisView.getFlag()).split(",")[0];
				File[] files = new File(dir).listFiles();
				List<String> allLabel = new ArrayList<String>();
				String imgname = imgName.split(",")[1];
				String imgPath = ImagePathUtil.getPath(dir, imgname);
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
				Constant.AnalysisView.setImgPath(imgPath);
				
				// 设置当前图片所有已被标注的label
				// 打乱labeldata
				List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
				Constant.AnalysisView.setAllLabelData(shuffleLable);

				System.out.println("999=" + Constant.AnalysisView.getFlag());
				//该处要改  读CSV文件
				String tmp = Constant.AnalysisMix.getImgList().get(Constant.AnalysisView.getFlag());
				if(Constant.AnalysisMix.getImgMap().get(tmp) == 0) {
					Constant.AnalysisView.setAllLabelUser(user);
					Constant.AnalysisView.setBeiUser(user);
					Constant.AnalysisView.setCenterUser(user);
					Constant.AnalysisView.setPanUser(user);
				}
				else {
					String rootPath = System.getProperty("user.dir").replace("\\", "/");
					String imname = imgName.split(",")[1].split("[.]")[0];
					String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imname;
					List<String[]> listStr = ReadCSV.readCSV(csvPath);
					String imgMixData = listStr.get(listStr.size() - 1)[2];
					ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
					Constant.ViewBeiAvg = obj.getBeiAvg();
					Constant.ViewCenterAvg = obj.getCenterAvg();
					Constant.ViewPanAvg = obj.getPanAvg();
					Constant.AnalysisView.setAllLabelUser(obj.getAllMixUser());
					Constant.AnalysisView.setBeiUser(obj.getBeiMixUser());
					Constant.AnalysisView.setCenterUser(obj.getCenterMixUser());
					Constant.AnalysisView.setPanUser(obj.getPanMixUser());
					Constant.AnalysisView.setStandUser(obj.getStandUser());
					Constant.AnalysisView.setPercent(obj.getPercent());
					Constant.AnalysisView.setBeiPercent(obj.getBeipercent());
				}

				//System.out.println("all data next = " + Constant.AnalysisView);
				MarkView mark = new MarkView();
				Stage stagenwe = new Stage();
				//stagenwe.initStyle(StageStyle.TRANSPARENT);
				try {
					mark.start(stagenwe);
				} catch (Exception e) {
					e.printStackTrace();
				}
				stage.close();
			}
		}
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
	
	private static String getLabel(String labelPath) {
		List<String[]> list = ReadCSV.readCSV(labelPath);
		return list.get(list.size() - 1)[7];
	}
}
