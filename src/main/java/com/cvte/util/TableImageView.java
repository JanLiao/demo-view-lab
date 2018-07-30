package com.cvte.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSON;
import com.cvte.cons.Constant;
import com.cvte.cons.SqlConsts;
import com.cvte.cpu.MonitorServiceImpl;
import com.cvte.entity.ConsoleImg;
import com.cvte.ui.Mark;
import com.jfoenix.controls.JFXCheckBox;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableImageView {

	public static void viewSelectImg(String imgName) {
		long start = System.currentTimeMillis();
		Constant.AnalysisMix.setImgName(imgName);
		String oldName = Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag());
		String dir = oldName.split(",")[0];
		System.out.println("时间 = " + (System.currentTimeMillis() - start));
		String imgPath = ImagePathUtil.getPath(dir, imgName);
		System.out.println("imgPath = " + imgPath);
		System.out.println("获取图片路径时间 = " + (System.currentTimeMillis() - start));
		Constant.AnalysisMix.setImgPath(imgPath);
		
		File[] files = new File(dir).listFiles();
		List<String> allLabel = new ArrayList<String>();
		String imgname = imgName;
		//String user = "";
		StringBuffer user = new StringBuffer("");
		System.out.println("马上开始读取label时间 = " + (System.currentTimeMillis() - start));
//		ExecutorService executor = Executors.newFixedThreadPool(10);
//		Future<String>[] futures = new Future[files.length];
//		long star = System.currentTimeMillis();
//		for(int i = 0, len = files.length; i < len; i++) {
//			String name = files[i].getName();
//			user.append(name);
//			user.append(",");
//			//当前user下单个csv 名
//			String soleName = getSoleName(dir, name, imgName);
//			ReadTask task = new ReadTask();
//			task.setDir(dir);
//			task.setImgName(imgname);
//			task.setName(name);
//			task.setSoleName(soleName);
//			task.setStart(star);
//			futures[i] = executor.submit(task);
//		}
//		System.out.println("file sole label 时间  = " + (System.currentTimeMillis() - start));
//		
//		for(int i = 0, len = files.length; i < len; i++) {
//			StringBuffer tmp = new StringBuffer("");
//			String name = files[i].getName();
//			tmp.append(name);
//			tmp.append("=");
//			String st = "";
//			try {
//				st = futures[i].get();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//			tmp.append(st);
//			allLabel.add(tmp.toString());
//		}
//		executor.shutdown();
//		System.out.println("alllabel 读取时间 = " + (System.currentTimeMillis() - start));
		
		List<String> labelAllList = new ArrayList<String>();
		labelAllList = SqlUtil.queryByName(imgName);
		if(SqlConsts.CreateFlag == 1 && labelAllList.size() != 0) {
			for(int i = 0, len = files.length; i < len; i++) {
				String us = files[i].getName();
				user.append(us);
				user.append(",");
			}
			System.out.println("user 读取时间 = " + (System.currentTimeMillis() - start));
		}else {
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
			labelAllList = allLabel;
		}
		List<String> shuffleLable = ShuffleUtil.shuffleLabel(labelAllList);
		//List<String> shuffleLable = ShuffleUtil.shuffleLabel(allLabel);
		Constant.AnalysisMix.setAllLabelData(shuffleLable);
		
		// 获取imgList offset偏移量
		int offset = getFlag(imgName);
		
		String tmp = Constant.AnalysisMix.getImgList().get(offset);
		if(Constant.AnalysisMix.getImgMap().get(tmp) == 0) {
			Constant.AnalysisMix.setAllLabelUser(user.toString());
			Constant.AnalysisMix.setBeiUser(user.toString());
			Constant.AnalysisMix.setCenterUser(user.toString());
			Constant.AnalysisMix.setPanUser(user.toString());
		}
		else {
			String rootPath = System.getProperty("user.dir").replace("\\", "/");
			String imname = imgName.split("[.]")[0];
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
			Constant.AnalysisMix.setStandUser(obj.getStandUser());
			Constant.AnalysisMix.setPercent(obj.getPercent());
			Constant.AnalysisMix.setBeiPercent(obj.getBeipercent());
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
		}
		System.out.println("initial label 读取时间 = " + (System.currentTimeMillis() - start));		
		
		//Image update cur
		ImageListUtil.updateCurImage(offset);
		System.out.println("更新curr image 时间 = " + (System.currentTimeMillis() - start));
		componentInit();
		System.out.println("更新component 时间 = " + (System.currentTimeMillis() - start));

		// mask
		MaskUtil.generateData();
		System.out.println("mask 时间 = " + (System.currentTimeMillis() - start));

		//System.out.println("all data = " + Constant.AnalysisMix);
		
		// pre image
		ImageListUtil.setPreImage();
		System.out.println("set pre 时间 = " + (System.currentTimeMillis() - start));
		
		// next image
		ImageListUtil.setNextImage();
		System.out.println("set next 时间 = " + (System.currentTimeMillis() - start));
		
		try {
			System.out.println("选择后内存使用情况 = " + MonitorServiceImpl.getMonitorInfoBean());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	

	private static void componentInit() {
		Constant.avgradio.setSelected(true);
		Constant.selectionModel.select(0);
		if("".equals(Constant.AnalysisMix.getStandUser()) || Constant.AnalysisMix.getStandUser() == null) {
			Constant.jfxCombo.setPromptText("未选择");
		}else {
			Constant.jfxCombo.setPromptText(Constant.AnalysisMix.getStandUser());
		}
		
		int size = Constant.AnalysisMix.getAllLabelData().size();
		for(int i = 0 ; i < size; i++) {
			Constant.CoverList.get(i).setText("1");
		}
        for(int i = 0; i < size; i++) {
        	Constant.InnerLabel.get(i).setText(Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0]);
        	Constant.CheckBoxList.get(i).setText(Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0]);
        	Constant.LeftList.get(i).setText(Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0]);
        }
        
        String tmpstr = Constant.AnalysisMix.getImgName();
        Label lbt = Constant.NameLabel;
        if(tmpstr.length() > 18) {
        	lbt.setText("  当前: " + tmpstr.substring(0, 18) + "...");
        }else {
        	lbt.setText("  当前: " + tmpstr);
        }        
        
        Constant.NumLabel.setText("     " + (Constant.AnalysisMix.getFlag() + 1) 
        		+ " / " + Constant.AnalysisMix.getImgNums());
        Constant.NumLabel.setStyle("-fx-text-fill: GREEN;-fx-font-size: 18;");
        Constant.NumLabel.setAlignment(Pos.CENTER);
        
        if (Constant.AnalysisMix.getImgMap()
				.get(Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag())) == 0) {
        	Constant.FlagLabel.setText("未融合");
        	Constant.FlagLabel.setStyle("-fx-text-fill: GREEN;-fx-font-size: 20;");
        }else {
        	Constant.FlagLabel.setText("已融合");
        	Constant.FlagLabel.setStyle("-fx-text-fill: RED;-fx-font-size: 20;");
        }        
        
        if (Constant.AnalysisMix.getImgMap()
				.get(Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag())) == 0) {
        	Constant.BtnPre.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent e) {
    				BtnUtil.preImage(Constant.TmpStage);
    			}
            });
        	Constant.BtnNext.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent e) {
    				BtnUtil.nextImage(Constant.TmpStage);
    			}
            });
        	Constant.BtnSave.setText("保   存");
        	Constant.BtnSave.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent e) {
    				BtnUtil.saveImage(Constant.TmpStage);
    			}
            });
        }else {
        	Constant.BtnPre.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent e) {
    				BtnUpdateUtil.preImage(Constant.TmpStage);
    			}
            });
        	Constant.BtnNext.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent e) {
    				BtnUpdateUtil.nextImage(Constant.TmpStage);
    			}
            });
        	Constant.BtnSave.setText("修   改");
        	Constant.BtnSave.setOnAction(new EventHandler<ActionEvent>() {
    			@Override
    			public void handle(ActionEvent e) {
    				BtnUpdateUtil.saveImage(Constant.TmpStage);
    			}
            });
        }
        
      //恢复CheckBox状态
    	TabChangeRepaintUtil.recoverCheckBox();
    	System.out.println("为空 ==" + Constant.AnalysisMix.getStandUser());
    	//覆盖率恢复
    	if(Constant.AnalysisMix.getStandUser() != null) {
    		System.out.println("selected = " + Constant.AnalysisMix.getStandUser());
    		ComboxChangeUtil.reTextOverLap(Constant.AnalysisMix.getStandUser());
    	}
        
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CircleTask task1 = new CircleTask();
        //task1.setCacher(cacher);
        task1.setCanvas(Constant.TabCanvas.get(0));
        //executor.execute(task1);
        executor.submit(task1);
		//TabChangeRepaintUtil.tabRepaint("ALL");
        for(int i = 0; i < Constant.canvasList.size(); i++) {
        	PaintTask task = new PaintTask();
        	task.setCanvas(Constant.canvasList.get(i));
        	task.setNum(i);
        	//executor.execute(task);
        	executor.submit(task);
        }
        executor.shutdown();
        try {
			while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
			    System.out.println("线程池没有关闭");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        System.out.println("线程池已关闭");
	}


	private static int getFlag(String imgName) {
		int index = 0;
		List<String> list = Constant.AnalysisMix.getImgList();
		for(int i = 0; i < list.size(); i++) {
			if(imgName.equals(list.get(i).split(",")[1])) {
				index = i;
				break;
			}
		}
		return index;
	}

	public static String getSoleName(String dir, String name, String imgName) {
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
