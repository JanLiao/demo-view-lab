package com.cvte.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.alibaba.fastjson.JSON;
import com.cvte.cons.Constant;
import com.cvte.entity.ConsoleImg;
import com.cvte.ui.Main;
import com.cvte.ui.Mark;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年5月26日 下午11:20:29 
*/
public class BtnUtil {

	public static void preImage(Stage stage) {
		if(Constant.AnalysisMix.getFlag() == 0) {
			showAlert(stage, "当前已是第一张");
		}
		else {
			preShowSave(stage);
		}
	}

	public static void nextImage(Stage stage) {
		if(Constant.AnalysisMix.getFlag() == (Constant.AnalysisMix.getImgNums() - 1)) {
			showAlert(stage, "当前已是最后一张,请先保存返回");
		}
		else {
			nextShowSave(stage);
		}
	}
	
	private static void showAlert(Stage stage, String msg) {
		JFXAlert alert = new JFXAlert((Stage)stage.getScene().getWindow());
        alert.setOverlayClose(false);
        alert.setSize(320, 160);
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

	public static void saveImage(Stage stage) {
		showAlertSave(stage);
	}
	
	@SuppressWarnings("rawtypes")
	public static void preShowSave(Stage stage) {
		JFXAlert alert = new JFXAlert((Stage)stage.getScene().getWindow());
        alert.setOverlayClose(false);
        alert.setSize(320, 160);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("温馨小提示"));
        Label content = new Label("确定保存当前操作数据");
        content.setStyle("-fx-text-fill: rgb(77,102,204);-fx-font-size: 18px;");
        layout.setBody(content);
        JFXButton closeButton = new JFXButton("保   存");
        closeButton.setStyle("-fx-background-color: GREEN;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;-fx-pref-width: 110;");
        closeButton.setOnAction(event -> {
        	alert.hideWithAnimation();
        	//LoadingView.progressShow();
        	readySaveImgPre(stage);
        });
        JFXButton clsButton = new JFXButton("取  消");
        clsButton.setStyle("-fx-background-color: RED;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;-fx-pref-width: 110;");
        clsButton.setOnAction(event -> {
        	alert.hideWithAnimation();
        	//不保存加载上一张图片
        	loadPreImg(stage);
        });
        layout.setActions(closeButton, clsButton);
        alert.setContent(layout);
        alert.show();
	}

	@SuppressWarnings("rawtypes")
	public static void nextShowSave(Stage stage) {
		JFXAlert alert = new JFXAlert((Stage)stage.getScene().getWindow());
        alert.setOverlayClose(false);
        alert.setSize(320, 160);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("温馨小提示"));
        Label content = new Label("确定保存当前操作数据");
        content.setStyle("-fx-text-fill: rgb(77,102,204);-fx-font-size: 18px;");
        layout.setBody(content);
        JFXButton closeButton = new JFXButton("保   存");
        closeButton.setStyle("-fx-background-color: GREEN;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;-fx-pref-width: 110;");
        closeButton.setOnAction(event -> {
        	alert.hideWithAnimation();
        	readySaveImg(stage);
        });
        JFXButton clsButton = new JFXButton("取  消");
        clsButton.setStyle("-fx-background-color: RED;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;-fx-pref-width: 110;");
        clsButton.setOnAction(event -> {
        	alert.hideWithAnimation();
        	//不保存加载下一张图片
        	loadNextImg(stage);
        });
        layout.setActions(closeButton, clsButton);
        alert.setContent(layout);
        alert.show();
	}
	
	@SuppressWarnings("rawtypes")
	private static void showAlertSave(Stage stage) {
		JFXAlert alert = new JFXAlert((Stage)stage.getScene().getWindow());
		alert.setSize(320, 160);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("温馨小提示"));
        Label content = new Label("确定保存当前操作数据");
        content.setStyle("-fx-text-fill: rgb(77,102,204);-fx-font-size: 18px;");
        layout.setBody(content);
        JFXButton closeButton = new JFXButton("保   存");
        closeButton.setStyle("-fx-background-color: GREEN;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;-fx-pref-width: 110;");
        closeButton.setOnAction(event -> {
        	alert.hideWithAnimation();
        	readySaveImg(stage);
        });
        JFXButton clsButton = new JFXButton("取  消");
        clsButton.setStyle("-fx-background-color: RED;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;-fx-pref-width: 110;");
        clsButton.setOnAction(event -> {
        	alert.hideWithAnimation();
        });
        layout.setActions(closeButton, clsButton);
        alert.setContent(layout);
        alert.show();
	}
	
	private static void loadPreImg(Stage stage) {
		if(Constant.AnalysisMix.getFlag() == 0) {
			System.out.println("当前flag已到最小值,即将返回主界面");
			stage.close();
			Main main = new Main();
			try {
				main.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			//加载下一张图
			Constant.AnalysisMix.setFlag(Constant.AnalysisMix.getFlag() - 1);
			
			// 图片名
			String imgName = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()); // 含路径
			Constant.AnalysisMix.setImgName(imgName.split(",")[1]);
			//Constant.AnalysisView.setImgName(imgName.split(",")[1]);
			
			String dir = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()).split(",")[0];
			File[] files = new File(dir).listFiles();
			List<String> allLabel = new ArrayList<String>();
			String imgname = imgName.split(",")[1];
			String imgPath = ImagePathUtil.getPath(dir, imgname);
			StringBuffer user = new StringBuffer("");
			ExecutorService executor = Executors.newFixedThreadPool(10);
			Future<String>[] futures = new Future[files.length];
			long star = System.currentTimeMillis();
			for(int i = 0, len = files.length; i < len; i++) {
				String name = files[i].getName();
				user.append(name);
				user.append(",");
				//当前user下单个csv 名
				String soleName = getSoleName(dir, name, imgName);
				ReadTask task = new ReadTask();
				task.setDir(dir);
				task.setImgName(imgname);
				task.setName(name);
				task.setSoleName(soleName);
				task.setStart(star);
				futures[i] = executor.submit(task);
			}
			
			for(int i = 0, len = files.length; i < len; i++) {
				StringBuffer tmp = new StringBuffer("");
				String name = files[i].getName();
				tmp.append(name);
				tmp.append("=");
				String st = "";
				try {
					st = futures[i].get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				tmp.append(st);
				allLabel.add(tmp.toString());
			}
			executor.shutdown();
			
			//设置图片路径(依次扫各个user)
			Constant.AnalysisMix.setImgPath(imgPath);
			//Constant.AnalysisView.setImgPath(imgPath);
			
			// 设置当前图片所有已被标注的label
			// 打乱labeldata
			List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
			Constant.AnalysisMix.setAllLabelData(shuffleLable);
			//Constant.AnalysisView.setAllLabelData(shuffleLable);

			//该处要改  读CSV文件
			String tmp = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag());
			if(Constant.AnalysisMix.getImgMap().get(tmp) == 0) {
				Constant.AnalysisMix.setAllLabelUser(user.toString());
				Constant.AnalysisMix.setBeiUser(user.toString());
				Constant.AnalysisMix.setCenterUser(user.toString());
				Constant.AnalysisMix.setPanUser(user.toString());
//				Constant.AnalysisView.setAllLabelUser(user);
//				Constant.AnalysisView.setBeiUser(user);
//				Constant.AnalysisView.setCenterUser(user);
//				Constant.AnalysisView.setPanUser(user);
			}
			else {
				String rootPath = System.getProperty("user.dir").replace("\\", "/");
				String imname = imgName.split(",")[1].split("[.]")[0];
				String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imname;
				List<String[]> listStr = ReadCSV.readCSV(csvPath);
				String imgMixData = listStr.get(listStr.size() - 1)[2];
				ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
				Constant.AnalysisMix.setAllLabelUser(obj.getAllMixUser());
				Constant.AnalysisMix.setBeiUser(obj.getBeiMixUser());
				Constant.beiAvg = obj.getBeiAvg();
				Constant.AnalysisMix.setCenterUser(obj.getCenterMixUser());
				Constant.centerAvg = obj.getCenterAvg();
				Constant.AnalysisMix.setPanUser(obj.getPanMixUser());
				Constant.panAvg = obj.getPanAvg();
				Constant.AnalysisMix.setStandUser(obj.getStandUser());
				Constant.AnalysisMix.setCenterX(obj.getCenterX());
				Constant.AnalysisMix.setCenterY(obj.getCenterY());
				Constant.AnalysisMix.setPercent(obj.getPercent());
				Constant.AnalysisMix.setBeiPercent(obj.getBeipercent());
				Constant.AnalysisMix.setSaveModel(obj.getSaveModel());
				if("".equals(obj.getPercent()) || obj.getPercent() == null) {
					Constant.SlideValue = 50;
				}else {					
					Constant.SlideValue = Double.parseDouble(obj.getPercent());
				}
				if("".equals(obj.getBeipercent()) || obj.getBeipercent() == null) {
					Constant.beiSlideValue = 50;
				}else {					
					Constant.beiSlideValue = Double.parseDouble(obj.getBeipercent());
				}
//				Constant.AnalysisView.setAllLabelUser(obj.getAllMixUser());
//				Constant.AnalysisView.setBeiUser(obj.getBeiMixUser());
//				Constant.AnalysisView.setCenterUser(obj.getCenterMixUser());
//				Constant.AnalysisView.setPanUser(obj.getPanMixUser());
			}
			
			//mask
			MaskUtil.generateData();
			
			//Image update pre
			ImageListUtil.updatePreImage();

			System.out.println("all data = " + Constant.AnalysisMix);
			Stage stagenew = new Stage();
			Mark mark = new Mark();
			try {
				mark.start(stagenew);
			} catch (Exception e) {
				e.printStackTrace();
			}
			stage.close();
			ImageListUtil.setPreImage();
		}
	}
	
	public static void finalChange() {
		// 图片名
		String imgName = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()); // 含路径
		Constant.AnalysisMix.setImgName(imgName.split(",")[1]);
		//Constant.AnalysisView.setImgName(imgName.split(",")[1]);

		String dir = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()).split(",")[0];
		File[] files = new File(dir).listFiles();
		String imgPath = "";
		int pathExist = 0;
		List<String> allLabel = new ArrayList<String>();
		String imgname = imgName.split(",")[1];
		String user = "";
		for (File file : files) {
			String name = file.getName();
			// 当前user下单个csv 名
			String soleName = getSoleName(dir, name, imgName.split(",")[1]);
			if (pathExist == 0) {
				imgPath = dir + "/" + name + "/data/" + soleName + "/" + imgname.split("_")[0] + "/"
						+ imgname.split("_")[1];
				File fp = new File(imgPath);
				if (fp.exists()) {
					pathExist = 1;
				}
			}
			String labelPath = dir + "/" + name + "/file/" + soleName + "/" + imgname.split("[.]")[0];
			File fl = new File(labelPath);
			if (fl.exists()) {
				String label = getLabel(labelPath);
				allLabel.add(name + "=" + label);
				user = user + name + ",";
			}
		}

		// 设置图片路径(依次扫各个user)
		Constant.AnalysisMix.setImgPath(imgPath);
		//Constant.AnalysisView.setImgPath(imgPath);

		// 设置当前图片所有已被标注的label
		// 打乱labeldata
		List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
		Constant.AnalysisMix.setAllLabelData(shuffleLable);
		//Constant.AnalysisView.setAllLabelData(shuffleLable);

		// 该处要改 读CSV文件
		String tmp = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag());
		if (Constant.AnalysisMix.getImgMap().get(tmp) == 0) {
			Constant.AnalysisMix.setAllLabelUser(user);
			Constant.AnalysisMix.setBeiUser(user);
			Constant.AnalysisMix.setCenterUser(user);
			Constant.AnalysisMix.setPanUser(user);
//			Constant.AnalysisView.setAllLabelUser(user);
//			Constant.AnalysisView.setBeiUser(user);
//			Constant.AnalysisView.setCenterUser(user);
//			Constant.AnalysisView.setPanUser(user);
		} else {
			String rootPath = System.getProperty("user.dir").replace("\\", "/");
			String imname = imgName.split(",")[1].split("[.]")[0];
			String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imname;
			List<String[]> listStr = ReadCSV.readCSV(csvPath);
			String imgMixData = listStr.get(listStr.size() - 1)[2];
			ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
			Constant.AnalysisMix.setAllLabelUser(obj.getAllMixUser());
			Constant.AnalysisMix.setBeiUser(obj.getBeiMixUser());
			Constant.beiAvg = obj.getBeiAvg();
			Constant.AnalysisMix.setCenterUser(obj.getCenterMixUser());
			Constant.centerAvg = obj.getCenterAvg();
			Constant.AnalysisMix.setPanUser(obj.getPanMixUser());
			Constant.panAvg = obj.getPanAvg();
			Constant.AnalysisMix.setPercent(obj.getPercent());
			Constant.AnalysisMix.setBeiPercent(obj.getPercent());
			Constant.AnalysisMix.setSaveModel(obj.getSaveModel());
			if("".equals(obj.getPercent()) || obj.getPercent() == null) {
				Constant.SlideValue = 50;
			}else {					
				Constant.SlideValue = Double.parseDouble(obj.getPercent());
			}
			if("".equals(obj.getBeipercent()) || obj.getBeipercent() == null) {
				Constant.beiSlideValue = 50;
			}else {					
				Constant.beiSlideValue = Double.parseDouble(obj.getBeipercent());
			}
//			Constant.AnalysisView.setAllLabelUser(obj.getAllMixUser());
//			Constant.AnalysisView.setBeiUser(obj.getBeiMixUser());
//			Constant.AnalysisView.setCenterUser(obj.getCenterMixUser());
//			Constant.AnalysisView.setPanUser(obj.getPanMixUser());
		}
		
		//mask
		MaskUtil.generateData();

		System.out.println("all data = " + Constant.AnalysisMix);
		//System.out.println("all data = " + Constant.AnalysisView);
	}
	
	private static void loadNextImg(Stage stage) {
		if(Constant.AnalysisMix.getFlag() + 1 >= Constant.AnalysisMix.getImgNums()) {
			System.out.println("当前flag已到最大值,即将返回主界面");
			Constant.AnalysisMix.setFlag(0);
			// 初始化到第一张图片
			ImageListUtil.updateCurImage(0);
			Constant.PreImageLoad = null;
			Constant.NextImageLoad = null;
			finalChange();
			stage.close();
			Main main = new Main();
			try {
				main.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			//加载下一张图
			Constant.AnalysisMix.setFlag(Constant.AnalysisMix.getFlag() + 1);
			
			// 图片名
			String imgName = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()); // 含路径
			Constant.AnalysisMix.setImgName(imgName.split(",")[1]);
			//Constant.AnalysisView.setImgName(imgName.split(",")[1]);
			
			String dir = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()).split(",")[0];
			File[] files = new File(dir).listFiles();
			List<String> allLabel = new ArrayList<String>();
			String imgname = imgName.split(",")[1];
			String imgPath = ImagePathUtil.getPath(dir, imgname);
			StringBuffer user = new StringBuffer("");
			ExecutorService executor = Executors.newFixedThreadPool(10);
			Future<String>[] futures = new Future[files.length];
			long star = System.currentTimeMillis();
			for(int i = 0, len = files.length; i < len; i++) {
				String name = files[i].getName();
				user.append(name);
				user.append(",");
				//当前user下单个csv 名
				String soleName = getSoleName(dir, name, imgName);
				ReadTask task = new ReadTask();
				task.setDir(dir);
				task.setImgName(imgname);
				task.setName(name);
				task.setSoleName(soleName);
				task.setStart(star);
				futures[i] = executor.submit(task);
			}
			
			for(int i = 0, len = files.length; i < len; i++) {
				StringBuffer tmp = new StringBuffer("");
				String name = files[i].getName();
				tmp.append(name);
				tmp.append("=");
				String st = "";
				try {
					st = futures[i].get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				tmp.append(st);
				allLabel.add(tmp.toString());
			}
			executor.shutdown();
			
			//设置图片路径(依次扫各个user)
			Constant.AnalysisMix.setImgPath(imgPath);
			//Constant.AnalysisView.setImgPath(imgPath);
			
			// 设置当前图片所有已被标注的label
			// 打乱labeldata
			List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
			Constant.AnalysisMix.setAllLabelData(shuffleLable);
			//Constant.AnalysisView.setAllLabelData(shuffleLable);

			//该处要改  读CSV文件
			String tmp = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag());
			if(Constant.AnalysisMix.getImgMap().get(tmp) == 0) {
				Constant.AnalysisMix.setAllLabelUser(user.toString());
				Constant.AnalysisMix.setBeiUser(user.toString());
				Constant.AnalysisMix.setCenterUser(user.toString());
				Constant.AnalysisMix.setPanUser(user.toString());
//				Constant.AnalysisView.setAllLabelUser(user);
//				Constant.AnalysisView.setBeiUser(user);
//				Constant.AnalysisView.setCenterUser(user);
//				Constant.AnalysisView.setPanUser(user);
			}
			else {
				String rootPath = System.getProperty("user.dir").replace("\\", "/");
				String imname = imgName.split(",")[1].split("[.]")[0];
				String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imname;
				List<String[]> listStr = ReadCSV.readCSV(csvPath);
				String imgMixData = listStr.get(listStr.size() - 1)[2];
				ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
				Constant.AnalysisMix.setAllLabelUser(obj.getAllMixUser());
				Constant.AnalysisMix.setBeiUser(obj.getBeiMixUser());
				Constant.beiAvg = obj.getBeiAvg();
				Constant.AnalysisMix.setCenterUser(obj.getCenterMixUser());
				Constant.centerAvg = obj.getCenterAvg();
				Constant.AnalysisMix.setPanUser(obj.getPanMixUser());
				Constant.panAvg = obj.getPanAvg();
				Constant.AnalysisMix.setStandUser(obj.getStandUser());
				Constant.AnalysisMix.setPercent(obj.getPercent());
				Constant.AnalysisMix.setBeiPercent(obj.getBeipercent());
				Constant.AnalysisMix.setSaveModel(obj.getSaveModel());
				if("".equals(obj.getPercent()) || obj.getPercent() == null) {
					Constant.SlideValue = 50;
				}else {					
					Constant.SlideValue = Double.parseDouble(obj.getPercent());
				}
				if("".equals(obj.getBeipercent()) || obj.getBeipercent() == null) {
					Constant.beiSlideValue = 50;
				}else {					
					Constant.beiSlideValue = Double.parseDouble(obj.getBeipercent());
				}
//				Constant.AnalysisView.setAllLabelUser(obj.getAllMixUser());
//				Constant.AnalysisView.setBeiUser(obj.getBeiMixUser());
//				Constant.AnalysisView.setCenterUser(obj.getCenterMixUser());
//				Constant.AnalysisView.setPanUser(obj.getPanMixUser());
			}
			
			//mask
			MaskUtil.generateData();
			
			//Image update pre
			ImageListUtil.updateNextImage();

			System.out.println("all data = " + Constant.AnalysisMix);
			Stage stagenew = new Stage();
			Mark mark = new Mark();
			try {
				mark.start(stagenew);
			} catch (Exception e) {
				e.printStackTrace();
			}
			stage.close();
			ImageListUtil.setNextImage();
		}
	}
	
	private static void readySaveImgPre(Stage stage) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ConsoleImg img = new ConsoleImg();
		String allLabels = "";
		String allUser = "";
		for(int i = 0; i < Constant.AnalysisMix.getAllLabelData().size(); i++) {
			allLabels = allLabels + Constant.AnalysisMix.getAllLabelData().get(i) + "=";
			allUser = allUser + Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0] + ",";
		}
		img.setAllLabel(allLabels);
		img.setAllMixUser(allUser);
		if(Constant.beiAvg != null) {			
			img.setBeiAvg(Constant.beiAvg);
		}
		else {
			img.setBeiAvg(LabelUtil.getAvgCircleData("shibei"));
		}
		img.setBeiMixUser(Constant.AnalysisMix.getBeiUser());
		img.setCenterAvg(Constant.centerAvg);
		img.setCenterMixUser(Constant.AnalysisMix.getCenterUser());
		img.setImgName(Constant.AnalysisMix.getImgName());
		img.setImgPath(Constant.AnalysisMix.getImgPath());
		img.setMaskPath(Constant.AnalysisMix.getTaskPath());
		img.setOperator(Constant.User);
		img.setPanAvg(Constant.panAvg);
		img.setPanMixUser(Constant.AnalysisMix.getPanUser());
		img.setCenterX(Constant.AnalysisMix.getCenterX());
		img.setCenterY(Constant.AnalysisMix.getCenterY());
		img.setUpdateTime(format.format(new Date()));
		img.setPercent("" + Constant.SlideValue);
		img.setBeipercent("" + Constant.beiSlideValue);
		img.setSaveModel(Constant.AnalysisMix.getSaveModel());
		ImgConsoleCSV.saveToCSV(img);
		Constant.AnalysisMix.getImgMap().put(
				Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()), 1);
		
		if(Constant.AnalysisMix.getFlag() == 0) {
			System.out.println("当前flag已到最小值,即将返回主界面");
			stage.close();
			Main main = new Main();
			try {
				main.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			//加载上一张图
			Constant.AnalysisMix.setFlag(Constant.AnalysisMix.getFlag() - 1);
			
			// 图片名
			String imgName = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()); // 含路径
			Constant.AnalysisMix.setImgName(imgName.split(",")[1]);
			//Constant.AnalysisView.setImgName(imgName.split(",")[1]);
			
			String dir = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()).split(",")[0];
			File[] files = new File(dir).listFiles();
			List<String> allLabel = new ArrayList<String>();
			String imgname = imgName.split(",")[1];
			String imgPath = ImagePathUtil.getPath(dir, imgname);
			StringBuffer user = new StringBuffer("");
			ExecutorService executor = Executors.newFixedThreadPool(10);
			Future<String>[] futures = new Future[files.length];
			long star = System.currentTimeMillis();
			for(int i = 0, len = files.length; i < len; i++) {
				String name = files[i].getName();
				user.append(name);
				user.append(",");
				//当前user下单个csv 名
				String soleName = getSoleName(dir, name, imgName);
				ReadTask task = new ReadTask();
				task.setDir(dir);
				task.setImgName(imgname);
				task.setName(name);
				task.setSoleName(soleName);
				task.setStart(star);
				futures[i] = executor.submit(task);
			}
			
			for(int i = 0, len = files.length; i < len; i++) {
				StringBuffer tmp = new StringBuffer("");
				String name = files[i].getName();
				tmp.append(name);
				tmp.append("=");
				String st = "";
				try {
					st = futures[i].get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				tmp.append(st);
				allLabel.add(tmp.toString());
			}
			executor.shutdown();
			
			//设置图片路径(依次扫各个user)
			Constant.AnalysisMix.setImgPath(imgPath);
			//Constant.AnalysisView.setImgPath(imgPath);
			
			// 设置当前图片所有已被标注的label
			// 打乱labeldata
			List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
			Constant.AnalysisMix.setAllLabelData(shuffleLable);
			//Constant.AnalysisView.setAllLabelData(shuffleLable);

			//该处要改  读CSV文件
			String tmp = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag());
			if(Constant.AnalysisMix.getImgMap().get(tmp) == 0) {
				Constant.AnalysisMix.setAllLabelUser(user.toString());
				Constant.AnalysisMix.setBeiUser(user.toString());
				Constant.AnalysisMix.setCenterUser(user.toString());
				Constant.AnalysisMix.setPanUser(user.toString());
//				Constant.AnalysisView.setAllLabelUser(user);
//				Constant.AnalysisView.setBeiUser(user);
//				Constant.AnalysisView.setCenterUser(user);
//				Constant.AnalysisView.setPanUser(user);
			}
			else {
				String rootPath = System.getProperty("user.dir").replace("\\", "/");
				String imname = imgName.split(",")[1].split("[.]")[0];
				String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imname;
				List<String[]> listStr = ReadCSV.readCSV(csvPath);
				String imgMixData = listStr.get(listStr.size() - 1)[2];
				ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
				Constant.AnalysisMix.setAllLabelUser(obj.getAllMixUser());
				Constant.AnalysisMix.setBeiUser(obj.getBeiMixUser());
				Constant.AnalysisMix.setCenterX(obj.getCenterX());
				Constant.AnalysisMix.setCenterY(obj.getCenterY());
				Constant.beiAvg = obj.getBeiAvg();
				Constant.AnalysisMix.setCenterUser(obj.getCenterMixUser());
				Constant.centerAvg = obj.getCenterAvg();
				Constant.AnalysisMix.setPanUser(obj.getPanMixUser());
				Constant.panAvg = obj.getPanAvg();
				Constant.AnalysisMix.setPercent(obj.getPercent());
				Constant.AnalysisMix.setBeiPercent(obj.getBeipercent());
				Constant.AnalysisMix.setSaveModel(obj.getSaveModel());
				if("".equals(obj.getPercent()) || obj.getPercent() == null) {
					Constant.SlideValue = 50;
				}else {					
					Constant.SlideValue = Double.parseDouble(obj.getPercent());
				}
				if("".equals(obj.getBeipercent()) || obj.getBeipercent() == null) {
					Constant.beiSlideValue = 50;
				}else {					
					Constant.beiSlideValue = Double.parseDouble(obj.getBeipercent());
				}
//				Constant.AnalysisView.setAllLabelUser(obj.getAllMixUser());
//				Constant.AnalysisView.setBeiUser(obj.getBeiMixUser());
//				Constant.AnalysisView.setCenterUser(obj.getCenterMixUser());
//				Constant.AnalysisView.setPanUser(obj.getPanMixUser());
			}
			
			//mask
			MaskUtil.generateData();
			
			//Image update pre
			ImageListUtil.updatePreImage();

			//System.out.println("all data = " + Constant.AnalysisMix);
			Stage stagenew = new Stage();
			Mark mark = new Mark();
			try {
				mark.start(stagenew);
			} catch (Exception e) {
				e.printStackTrace();
			}
			stage.close();
			
			ImageListUtil.setPreImage();
		}
	}

	private static void readySaveImg(Stage stage) {
		long start = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ConsoleImg img = new ConsoleImg();
		String allLabels = "";
		String allUser = "";
		for(int i = 0; i < Constant.AnalysisMix.getAllLabelData().size(); i++) {
			allLabels = allLabels + Constant.AnalysisMix.getAllLabelData().get(i) + "=";
			allUser = allUser + Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0] + ",";
		}
		img.setAllLabel(allLabels);
		img.setAllMixUser(Constant.AnalysisMix.getAllLabelUser());
		if(Constant.beiAvg != null) {			
			img.setBeiAvg(Constant.beiAvg);
		}
		else {
			img.setBeiAvg(LabelUtil.getAvgCircleData("shibei"));
		}
		img.setBeiMixUser(Constant.AnalysisMix.getBeiUser());
		img.setCenterAvg(Constant.centerAvg);
		img.setCenterMixUser(Constant.AnalysisMix.getCenterUser());
		img.setImgName(Constant.AnalysisMix.getImgName());
		img.setImgPath(Constant.AnalysisMix.getImgPath());
		img.setMaskPath(Constant.AnalysisMix.getTaskPath());
		img.setOperator(Constant.User);
		img.setPanAvg(Constant.panAvg);
		img.setPanMixUser(Constant.AnalysisMix.getPanUser());
		img.setStandUser(Constant.AnalysisMix.getStandUser());
		img.setUpdateTime(format.format(new Date()));
		img.setPercent("" + Constant.SlideValue);
		img.setBeipercent("" + Constant.beiSlideValue);
		img.setSaveModel(Constant.AnalysisMix.getSaveModel());
		ImgConsoleCSV.saveToCSV(img);
		Constant.AnalysisMix.getImgMap().put(
				Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()), 1);
		
		if(Constant.AnalysisMix.getFlag() + 1 >= Constant.AnalysisMix.getImgNums()) {
			System.out.println("当前flag已到最大值,即将返回主界面");
			Constant.AnalysisMix.setFlag(0);
			// 初始化到第一张图片
			ImageListUtil.updateCurImage(0);
			Constant.PreImageLoad = null;
			Constant.NextImageLoad = null;
			finalChange();
			stage.close();
			Main main = new Main();
			try {
				main.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			//加载下一张图
			Constant.AnalysisMix.setFlag(Constant.AnalysisMix.getFlag() + 1);
			
			// 图片名
			String imgName = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()); // 含路径
			Constant.AnalysisMix.setImgName(imgName.split(",")[1]);
			//Constant.AnalysisView.setImgName(imgName.split(",")[1]);
			
			String dir = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag()).split(",")[0];
			File[] files = new File(dir).listFiles();
			List<String> allLabel = new ArrayList<String>();
			String imgname = imgName.split(",")[1];
			String imgPath = ImagePathUtil.getPath(dir, imgname);
			StringBuffer user = new StringBuffer("");
			ExecutorService executor = Executors.newFixedThreadPool(10);
			Future<String>[] futures = new Future[files.length];
			long star = System.currentTimeMillis();
			for(int i = 0, len = files.length; i < len; i++) {
				String name = files[i].getName();
				user.append(name);
				user.append(",");
				//当前user下单个csv 名
				String soleName = getSoleName(dir, name, imgName);
				ReadTask task = new ReadTask();
				task.setDir(dir);
				task.setImgName(imgname);
				task.setName(name);
				task.setSoleName(soleName);
				task.setStart(star);
				futures[i] = executor.submit(task);
			}
			System.out.println("file sole label 时间  = " + (System.currentTimeMillis() - start));
			
			for(int i = 0, len = files.length; i < len; i++) {
				StringBuffer tmp = new StringBuffer("");
				String name = files[i].getName();
				tmp.append(name);
				tmp.append("=");
				String st = "";
				try {
					st = futures[i].get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				tmp.append(st);
				allLabel.add(tmp.toString());
			}
			executor.shutdown();
			
			//设置图片路径(依次扫各个user)
			Constant.AnalysisMix.setImgPath(imgPath);
			//Constant.AnalysisView.setImgPath(imgPath);
			
			// 设置当前图片所有已被标注的label
			// 打乱labeldata
			List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
			Constant.AnalysisMix.setAllLabelData(shuffleLable);
			//Constant.AnalysisView.setAllLabelData(shuffleLable);
			
			Constant.AnalysisMix.setStandUser("");
			//Constant.AnalysisView.setStandUser("");
//			Constant.SlideValue = 50;
//			Constant.beiSlideValue = 50;

			//该处要改  读CSV文件
			String tmp = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag());
			if(Constant.AnalysisMix.getImgMap().get(tmp) == 0) {
				Constant.AnalysisMix.setAllLabelUser(user.toString());
				Constant.AnalysisMix.setBeiUser(user.toString());
				Constant.AnalysisMix.setCenterUser(user.toString());
				Constant.AnalysisMix.setPanUser(user.toString());
//				Constant.AnalysisView.setAllLabelUser(user);
//				Constant.AnalysisView.setBeiUser(user);
//				Constant.AnalysisView.setCenterUser(user);
//				Constant.AnalysisView.setPanUser(user);
			}
			else {
				String rootPath = System.getProperty("user.dir").replace("\\", "/");
				String imname = imgName.split(",")[1].split("[.]")[0];
				String csvPath = rootPath + "/" + Constant.User + "/mixlabel/sole/" + imname;
				List<String[]> listStr = ReadCSV.readCSV(csvPath);
				String imgMixData = listStr.get(listStr.size() - 1)[2];
				ConsoleImg obj = (ConsoleImg) JSON.parseObject(imgMixData, ConsoleImg.class);
				Constant.AnalysisMix.setAllLabelUser(obj.getAllMixUser());
				Constant.AnalysisMix.setBeiUser(obj.getBeiMixUser());
				Constant.beiAvg = obj.getBeiAvg();
				Constant.AnalysisMix.setCenterUser(obj.getCenterMixUser());
				Constant.centerAvg = obj.getCenterAvg();
				Constant.AnalysisMix.setPanUser(obj.getPanMixUser());
				Constant.panAvg = obj.getPanAvg();
				Constant.AnalysisMix.setPercent(obj.getPercent());
				Constant.AnalysisMix.setBeiPercent(obj.getBeipercent());
				Constant.AnalysisMix.setSaveModel(obj.getSaveModel());
				if("".equals(obj.getPercent()) || obj.getPercent() == null) {
					Constant.SlideValue = 50;
				}else {					
					Constant.SlideValue = Double.parseDouble(obj.getPercent());
				}
				if("".equals(obj.getBeipercent()) || obj.getBeipercent() == null) {
					Constant.beiSlideValue = 50;
				}else {					
					Constant.beiSlideValue = Double.parseDouble(obj.getBeipercent());
				}
				//Constant.SlideValue = Double.parseDouble(obj.getPercent());
//				Constant.AnalysisView.setAllLabelUser(obj.getAllMixUser());
//				Constant.AnalysisView.setBeiUser(obj.getBeiMixUser());
//				Constant.AnalysisView.setCenterUser(obj.getCenterMixUser());
//				Constant.AnalysisView.setPanUser(obj.getPanMixUser());
			}
			//mask
			MaskUtil.generateData();
			System.out.println("保存 并加装下一张数据用时 = " + (System.currentTimeMillis() - start));
			
			//Image load更新挪动
			ImageListUtil.updateNextImage();

			//System.out.println("all data = " + Constant.AnalysisMix);
			Stage stagenew = new Stage();
			Mark mark = new Mark();
			try {
				mark.start(stagenew);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("保存 并加装下一张数据用时 = " + (System.currentTimeMillis() - start));
			stage.close();
			ImageListUtil.setNextImage();
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
