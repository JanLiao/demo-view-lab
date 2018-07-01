package com.cvte.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cvte.cons.Constant;
import com.cvte.ui.Main;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.control.Label;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年6月7日 上午11:42:55 
*/
public class DialogUtil {

	@SuppressWarnings("rawtypes")
	public static void showAlert(Stage stage, String msg) {
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
	
	//未使用
	@SuppressWarnings("rawtypes")
	public static void showAlertMsg(Stage stage, String msg, List<String[]> imgList, String rootPath) {
		JFXAlert alert = new JFXAlert((Stage)stage.getScene().getWindow());
        alert.setOverlayClose(false);
        alert.setSize(320, 160);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("温馨小提示"));
        Label content = new Label(msg);
        layout.setBody(content);
        
        JFXButton imgButton = new JFXButton("选  择 路  径");
        imgButton.setStyle("-fx-background-color: GREEN;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;-fx-pref-width: 110;");
        imgButton.setOnAction(event -> {
        	//alert.hideWithAnimation();
        	String dir = selectPath();
        	if("".equals(dir)) {
        		content.setText("未选择新数据路径");
        	}
        	else {
        		Constant.tmpDataPath = dir;
        	}
        	
        	String tmpimg = openDataDir();
        	if("".equals(tmpimg)) {
        		System.out.println("未选择图片路径");
        	}
        	else {
        		content.setText("当前所选医生数据路径为 :\n" + "   " + "当前已选择图片路径为 : \n" + "   " + tmpimg);
        		Constant.tmpImagePath = tmpimg;
        	}
        });
        
        JFXButton closeButton = new JFXButton("确  定");
        closeButton.setStyle("-fx-background-color: GREEN;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;");
        closeButton.setOnAction(event -> {
        	alert.hideWithAnimation();
        	System.out.println("curr select = " + Constant.tmpDataPath + "=" + Constant.tmpImagePath);
        	
        	if(!"".equals(Constant.tmpDataPath) && !"".equals(Constant.tmpImagePath)) {
        		String csvPath = rootPath + "/" + Constant.User + "/mixlabel/dir";
        		DirCSV.updateTmpDir(Constant.tmpDataPath, Constant.tmpImagePath);
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
						img.add(Constant.tmpDataPath + "," + imgList.get(i)[1].split(",")[1]);
						String nam = imgList.get(i)[1].split(",")[1].split("[.]")[0];
						File fil = new File(rootPath + "/" + Constant.User + "/mixlabel/sole/" + nam);
						if(fil.exists()) {
							map.put(Constant.tmpDataPath + "," + imgList.get(i)[1].split(",")[1], 1);
						}
						else {
							if(tmp == 0) {
								fflag = img.size() - 1;
								tmp = 1;
							}
							map.put(Constant.tmpDataPath + "," + imgList.get(i)[1].split(",")[1], 0);
						}
					}
				}

				System.out.println("tmp img = " + img.get(0));
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
        		System.out.println("未同时选择数据及图片路径");
        	}
        	
        });
        layout.setActions(imgButton, closeButton);
        alert.setContent(layout);
        alert.show();
	}
	
	private static String openDataDir() {
		String imgPath = "";
		Stage fileStage = null;
	    DirectoryChooser folderChooser = new DirectoryChooser();
	    folderChooser.setTitle("标注图片文件夹");
	    File selectedFile = folderChooser.showDialog(fileStage);
	    if(selectedFile == null) {
	    	System.out.println("未选择文件夹");
	    }
	    else {
	    	String dir = selectedFile.getAbsolutePath().replace('\\', '/');
		    System.out.println("selected data dir = " + dir);
		    imgPath = dir;
	    }
		return imgPath;
	}

	public static String selectPath() {
		String imgpath = "";
		Stage fileStage = null;
	    DirectoryChooser folderChooser = new DirectoryChooser();
	    folderChooser.setTitle("标注图片文件夹");
	    File selectedFile = folderChooser.showDialog(fileStage);
	    if(selectedFile == null) {
	    	System.out.println("未选择文件夹");
	    }
	    else {
	    	String dir = selectedFile.getAbsolutePath().replace('\\', '/');
		    System.out.println("selected data dir = " + dir);
		    imgpath = dir;
	    }
	    return imgpath;
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
