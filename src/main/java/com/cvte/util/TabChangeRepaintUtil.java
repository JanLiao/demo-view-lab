package com.cvte.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;
import com.cvte.entity.LineData;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

/** 
* @author: jan 
* @date: 2018年5月26日 上午9:59:46 
*/
public class TabChangeRepaintUtil {

	public static void tabRepaint(String text) {
		int len = Constant.canvasList.size();
		List<Canvas> list = Constant.canvasList;
		for(int i = 0; i < len; i++) {
			list.get(i).getGraphicsContext2D().clearRect(0, 0, 530, 530);
		}
		System.out.println("当前tab右侧 = " + text + "  全部清除");
		
		//重绘右侧
		if("视   盘".equals(text)) {
			Constant.CurrTab = 0;
			Constant.slider.setValue(Constant.SlideValue);
						
			tabChangeRefreshCover();
			
			System.out.println(" shipan " + Constant.AnalysisMix.getPanUser());
			//还原CheckBox状态
			int size = Constant.CheckBoxList.size();
			for(int i = 0; i < size; i++) {
				String us = Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0];
				if(checkBox(us, Constant.AnalysisMix.getPanUser())) {
					//System.out.println(us + " -- " + Constant.AnalysisMix.getPanUser());
					Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
					Constant.CheckBoxList.get(i).setSelected(true);
					Constant.LeftList.get(i).setSelected(true);
					Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
				}
				else {
					Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
					Constant.CheckBoxList.get(i).setSelected(false);
					Constant.LeftList.get(i).setSelected(false);
					Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
				}
			}
			
			int size1 = 0;
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				if(Constant.CheckBoxList.get(i).isSelected()) {
					size1++;
				}
			}
			Constant.fenmu.setText("" + size1);
			int size2 = (int) (size1 * Constant.SlideValue/100);
			Constant.fenzi.setText("" + size2);
			//重绘当前tab
			if(Constant.radio.isSelected()) {  //判断重叠radio是否被选中
				System.out.println("tab1 canvas 重绘  重叠视盘");
				RadioChangeRepaintUtil.overLapSoleRepaint(0);
			}
			else {
				if(Constant.maskradio.isSelected()) {
					System.out.println("tab1 canvas mask重绘");
					RadioChangeRepaintUtil.maskRepaint();
				}
				else {
					System.out.println("tab1 canvas 重绘  平均视盘");
					RadioChangeRepaintUtil.avgSoleRepaintNew(0);
				}
			}
			
			Constant.radio.setDisable(false);
			repaintPan();
		}
		else if("视   杯".equals(text)) {
			Constant.CurrTab = 1;
			Constant.slider.setValue(Constant.beiSlideValue);			
			
			tabChangeRefreshCover();
			System.out.println("shibei " + Constant.AnalysisMix.getBeiUser());
			//还原CheckBox状态
			int size = Constant.CheckBoxList.size();
			for(int i = 0; i < size; i++) {
				String us = Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0];
				if(checkBox(us, Constant.AnalysisMix.getBeiUser())) {
					//System.out.println(us + " -- " + Constant.AnalysisMix.getBeiUser());
					Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
					Constant.CheckBoxList.get(i).setSelected(true);
					Constant.LeftList.get(i).setSelected(true);
					Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
				}
				else {
					Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
					Constant.CheckBoxList.get(i).setSelected(false);
					Constant.LeftList.get(i).setSelected(false);
					Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
				}
			}
			
			int size1 = 0;
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				if(Constant.CheckBoxList.get(i).isSelected()) {
					size1++;
				}
			}
			Constant.fenmu.setText("" + size1);
			int size2 = (int) (size1 * Constant.beiSlideValue/100);
			Constant.fenzi.setText("" + size2);
			//重绘当前tab
			if(Constant.radio.isSelected()) {  //判断重叠radio是否被选中
				System.out.println("tab2 canvas 重绘  重叠视杯");
				RadioChangeRepaintUtil.overLapSoleRepaint(1);
			}
			else {
				if(Constant.maskradio.isSelected()) {
					System.out.println("tab2 canvas 重绘mask");
					RadioChangeRepaintUtil.maskRepaint();
				}
				else {
					System.out.println("tab2 canvas 重绘  平均视杯");
					RadioChangeRepaintUtil.avgSoleRepaintNew(1);
				}
			}
			
			Constant.radio.setDisable(false);
			repaintBei();
		}
		else if("黄 斑 中 心".equals(text)) {
			Constant.CurrTab = 2;
			tabChangeRefreshCover();
			System.out.println("center  " + Constant.AnalysisMix.getCenterUser());
			//还原CheckBox状态
			int size = Constant.CheckBoxList.size();
			for(int i = 0; i < size; i++) {
				String us = Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0];
				if(checkBox(us, Constant.AnalysisMix.getCenterUser())) {
					//System.out.println(us + " -- " + Constant.AnalysisMix.getCenterUser());
					Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
					Constant.CheckBoxList.get(i).setSelected(true);
					Constant.LeftList.get(i).setSelected(true);
					Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
				}
				else {
					Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
					Constant.CheckBoxList.get(i).setSelected(false);
					Constant.LeftList.get(i).setSelected(false);
					Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
				}
			}
			//重绘当前tab
			if(Constant.radio.isSelected()) {  //判断重叠radio是否被选中
				System.out.println("tab3 canvas 重绘  重叠中心");
				RadioChangeRepaintUtil.overLapSoleRepaint(2);
			}
			else {
				System.out.println("tab3 canvas 重绘  平均中心");
				RadioChangeRepaintUtil.avgSoleRepaintNew(2);
			}
			
			Constant.radio.setDisable(false);
			repaintCenter();
		}
		else if("ALL".equals(text)) {
			Constant.CurrTab = 3;
			tabChangeRefreshCover();
			System.out.println("ALl  = " + Constant.AnalysisMix.getAllLabelUser());
			//还原CheckBox状态
			int size = Constant.CheckBoxList.size();
			for(int i = 0; i < size; i++) {
				String us = Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0];
				if(checkBox(us, Constant.AnalysisMix.getAllLabelUser())) {
					//System.out.println(us + " -- " + Constant.AnalysisMix.getAllLabelUser());
					Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
					Constant.CheckBoxList.get(i).setSelected(true);
					Constant.LeftList.get(i).setSelected(true);
					Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
				}
				else {
					Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
					Constant.CheckBoxList.get(i).setSelected(false);
					Constant.LeftList.get(i).setSelected(false);
					Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
				}
			}
			//重绘当前tab
			if(Constant.radio.isSelected()) {  //判断重叠radio是否被选中
				//RadioChangeRepaintUtil.overLapSoleRepaint(3);
				System.out.println("ALL 重叠不绘");
			}
			else {
				System.out.println("tab4 canvas 重绘  平均中心");
				RadioChangeRepaintUtil.avgSoleRepaintNew(3);
			}
			
			//隐藏重叠radio
			Constant.radio.setDisable(true);
			repaintAll();
		}
	}
	
	//还原CheckBox状态  加载时生效
	public static void recoverCheckBox() {
		int size = Constant.CheckBoxList.size();
		for(int i = 0; i < size; i++) {
			String us = Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0];
			if(checkBox(us, Constant.AnalysisMix.getPanUser())) {
				//System.out.println(us + " -- " + Constant.AnalysisMix.getPanUser());
				Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
				Constant.CheckBoxList.get(i).setSelected(true);
				Constant.LeftList.get(i).setSelected(true);
				Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
			}
			else {
				Constant.CheckBoxList.get(i).selectedProperty().removeListener(Constant.ListenerList.get(i));
				Constant.CheckBoxList.get(i).setSelected(false);
				Constant.LeftList.get(i).setSelected(false);
				Constant.CheckBoxList.get(i).selectedProperty().addListener(Constant.ListenerList.get(i));
			}
		}
	}
	

	//tab改变覆盖率也要refresh
	private static void tabChangeRefreshCover() {
		String text = Constant.AnalysisMix.getStandUser();
		
		List<Double> coverList = new ArrayList<Double>();
		if(Constant.CurrTab == 0) {
			coverList = CoverUtil.coverRatioPan(text);
			
			CoverUtil.refreshLabel(coverList);
		}
		else if(Constant.CurrTab == 1) {
			coverList = CoverUtil.coverRatioBei(text);
			
			CoverUtil.refreshLabel(coverList);
		}
		else if(Constant.CurrTab == 2) {
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				coverList.add(1.00);
			}
			
			CoverUtil.refreshLabel(coverList);
		}
		else if(Constant.CurrTab == 3) {
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				coverList.add(1.00);
			}
			
			CoverUtil.refreshLabel(coverList);
		}
	}

	private static boolean checkBox(String us, String userStr) {
		boolean flag = false;
		if("".equals(userStr)) {
			return flag;
		}
		else {
			String[] s = userStr.split(",");
			if(s.length == 0) {
				return flag;
			}
			else {
				for(int i = 0; i < s.length; i++) {
					if(us.equals(s[i])) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}

	//重绘all
	private static void repaintAll() {
		int len = Constant.canvasList.size();
		for(int i = 0; i < len; i++) {
			GraphicsContext gc = Constant.canvasList.get(i).getGraphicsContext2D();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(new File(Constant.AnalysisMix.getImgPath()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    		Image innerImg = new Image(fis, 530, 530, true, true);
			//Image innerImg = Constant.CurrImageLoad;
    		//获取当前图片label视盘数据
            CircleData solepan = LabelUtil.getSoleLabelData(i, "shipan");
            gc.drawImage(innerImg, 0, 0);
            gc.save();
            gc.setLineWidth(solepan.getStrokeWidth());
            gc.setStroke(Color.rgb(51, 171, 160));
        	double relativeX = solepan.getLeft();
        	double relativeY = solepan.getTop();
        	gc.setTransform(new Affine(new Rotate(solepan.getAngle(), relativeX, relativeY)));
        	gc.strokeOval(relativeX, relativeY, solepan.getRadius()*solepan.getScaleX()*2, solepan.getRadius()*solepan.getScaleY()*2);
        	gc.restore();
        	
        	CircleData solebei = LabelUtil.getSoleLabelData(i, "shibei");
        	gc.save();
            gc.setLineWidth(solebei.getStrokeWidth());
            gc.setStroke(Color.rgb(0, 0, 255));
        	double relativeX1 = solebei.getLeft();
        	double relativeY1 = solebei.getTop();
        	gc.setTransform(new Affine(new Rotate(solebei.getAngle(), relativeX1, relativeY1)));
        	gc.strokeOval(relativeX1, relativeY1, solebei.getRadius()*solebei.getScaleX()*2, solebei.getRadius()*solebei.getScaleY()*2);
        	gc.restore();
        	
        	LineData line = LabelUtil.getSoleLineData(i);
        	gc.setLineWidth(line.getStrokeWidth());
	    	//两条线
	    	gc.save();
	    	gc.setStroke(Color.rgb(255, 0, 255));
	    	double relativeX2 = line.getLeft();
	    	double relativeY2 = line.getTop();
	    	gc.strokeLine(relativeX2, relativeY2 + line.getHeight()*line.getScaleY()/2, 
	    			relativeX2 + line.getWidth()*line.getScaleX(),
	    			relativeY2 + line.getHeight()*line.getScaleY()/2);
	        gc.restore();
	        gc.save();
	        gc.setStroke(Color.rgb(255, 0, 255));
	    	gc.strokeLine(relativeX2 + line.getWidth()*line.getScaleX()/2, relativeY2, 
	    			relativeX2 + line.getWidth()*line.getScaleX()/2,
	    			relativeY2 + line.getHeight()*line.getScaleY());
	        gc.restore();
		}
	}

	//重绘黄斑中心
	private static void repaintCenter() {
		int len = Constant.canvasList.size();
		for(int i = 0; i < len; i++) {
			GraphicsContext gc = Constant.canvasList.get(i).getGraphicsContext2D();
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(new File(Constant.AnalysisMix.getImgPath()));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Image innerImg = new Image(fis, 530, 530, true, true);
			LineData line = LabelUtil.getSoleLineData(i);
    		//Image innerImg = Constant.CurrImageLoad;
			gc.drawImage(innerImg, 0, 0);
	    	gc.setLineWidth(line.getStrokeWidth());
	    	//两条线
	    	gc.save();
	    	gc.setStroke(Color.rgb(255, 0, 255));
	    	double relativeX = line.getLeft();
	    	double relativeY = line.getTop();
	    	System.out.println("line = " + line);
	    	System.out.println(relativeX + "=" + relativeY);
	    	gc.strokeLine(relativeX, relativeY + line.getHeight()*line.getScaleY()/2, 
	    			relativeX + line.getWidth()*line.getScaleX(), 
	    			relativeY + line.getHeight()*line.getScaleY()/2);
	        gc.restore();
	        gc.save();
	        gc.setStroke(Color.rgb(255, 0, 255));
	    	gc.strokeLine(relativeX + line.getWidth()*line.getScaleX()/2, relativeY, 
	    			relativeX + line.getWidth()*line.getScaleX()/2, 
	    			relativeY + line.getHeight()*line.getScaleY());
	        gc.restore();
		}
	}

	//重绘视杯
	private static void repaintBei() {
		PaintAllUtil.paintBei();
//		int len = Constant.canvasList.size();
//		for(int i = 0; i < len; i++) {
//			GraphicsContext gc = Constant.canvasList.get(i).getGraphicsContext2D();
//			FileInputStream fis = null;
//			try {
//				fis = new FileInputStream(new File(Constant.AnalysisMix.getImgPath()));
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//    		Image innerImg = new Image(fis, 530*4, 530*4, true, true);
//    		//获取当前图片label视盘数据
//            CircleData solebei = LabelUtil.getSoleLabelData(i, "shibei");
//            gc.drawImage(innerImg, solebei.getCenterX()*4 - 265,
//            		solebei.getCenterY()*4 - 265,
//            		530, 530, 0, 0, 530, 530);
//            gc.save();
//            gc.setLineWidth(solebei.getStrokeWidth());
//            gc.setStroke(Color.rgb(0, 0, 255));
//        	double relativeX = solebei.getLeft()*4 - (solebei.getCenterX()*4 - 265);
//        	double relativeY = solebei.getTop()*4 - (solebei.getCenterY()*4 - 265);
//        	gc.setTransform(new Affine(new Rotate(solebei.getAngle(), relativeX, relativeY)));
//        	gc.strokeOval(relativeX, relativeY, solebei.getRadius()*solebei.getScaleX()*2*4, solebei.getRadius()*solebei.getScaleY()*2*4);
//        	gc.restore();
//		}
	}

	//重绘视盘
	private static void repaintPan() {
		PaintAllUtil.paintPan();
//		int len = Constant.canvasList.size();
//		for(int i = 0; i < len; i++) {
//			GraphicsContext gc = Constant.canvasList.get(i).getGraphicsContext2D();
//			FileInputStream fis = null;
//			try {
//				fis = new FileInputStream(new File(Constant.AnalysisMix.getImgPath()));
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//    		Image innerImg = new Image(fis, 530*4, 530*4, true, true);
//    		//获取当前图片label视盘数据
//            CircleData solepan = LabelUtil.getSoleLabelData(i, "shipan");
//            gc.drawImage(innerImg, solepan.getCenterX()*4 - 265,
//            		solepan.getCenterY()*4 - 265,
//            		530, 530, 0, 0, 530, 530);
//            gc.save();
//            gc.setLineWidth(solepan.getStrokeWidth());
//            gc.setStroke(Color.rgb(51, 171, 160));
//        	double relativeX = solepan.getLeft()*4 - (solepan.getCenterX()*4 - 265);
//        	double relativeY = solepan.getTop()*4 - (solepan.getCenterY()*4 - 265);
//        	gc.setTransform(new Affine(new Rotate(solepan.getAngle(), relativeX, relativeY)));
//        	gc.strokeOval(relativeX, relativeY, solepan.getRadius()*solepan.getScaleX()*2*4, solepan.getRadius()*solepan.getScaleY()*2*4);
//        	gc.restore();
//		}
	}

}
