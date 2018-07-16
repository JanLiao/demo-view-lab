package com.cvte.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class RepaintMaskUtil {

	public static void maskRepaint(GraphicsContext gc2) {
        Image img2 = Constant.CurrImageLoad;
		
		CircleData circle = LabelUtil.getAvgCircleData("shipan");
        //初始化视盘avg
        gc2.drawImage(img2, (circle.getCenterX()*4 - 265),
        		(circle.getCenterY()*4 - 265),
        		530, 530, 0, 0, 530, 530);
		double percent = Constant.SlideValue;
		int size = 0;
		if(Constant.AnalysisMix.getPanUser() == null || 
				"".equals(Constant.AnalysisMix.getPanUser())) {
			size = 0;
		}else {
			size = Constant.AnalysisMix.getPanUser().split(",").length;
		}
		
		int num = (int) (size*percent/100);
		//MaskUtil.paint(gc2, "shipan", num);
		MaskTmpUtil.paint(gc2, "shipan", num);
		
		double beipercent = Constant.beiSlideValue;
		int size1 = 0;
		int num1 = 0;
		if(Constant.AnalysisMix.getPanUser() == null || 
				"".equals(Constant.AnalysisMix.getBeiUser())) {
			size1 = 0;
			num1 = 0;
		}else {
			size1 = Constant.AnalysisMix.getBeiUser().split(",").length;
			num1 = (int)(size1 * beipercent/100);
		}
		
		System.out.println("num1= " + num1);
		// 绘制视杯mask
		//MaskUtil.paint(gc2, "shibei", num1);
		MaskTmpUtil.paint(gc2, "shibei", num1);
	}
	
	public static void repaintAvgNow() {
		// 清除tab canvas
		int len = Constant.TabCanvas.size();
		for (int i = 0; i < len; i++) {
			Constant.TabCanvas.get(i).getGraphicsContext2D().clearRect(0, 0, 530, 530);
		}

		// tab canvas重绘平均
		tab5RepaintAVGNew(Constant.TabCanvas.get(5).getGraphicsContext2D());
	}

	private static void tab5RepaintAVGNew(GraphicsContext gc1) {
		double width4 = 530;
        double height4 = 530;
        File fileAll = new File(Constant.AnalysisMix.getImgPath());
        InputStream fisAll = null;
		try {
			fisAll = new FileInputStream(fileAll);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        Image img4 = new Image(fisAll, width4, height4, true, true);
        //获取当前图片label视盘数据
        CircleData pan = LabelUtil.getAvgNewCircleData("shipan");
        gc1.drawImage(Constant.CurrImageLoad, pan.getCenterX()*4 - 265,
        		pan.getCenterY()*4 - 265,
        		530, 530, 0, 0, 530, 530);
    	gc1.setLineWidth(pan.getStrokeWidth());
    	gc1.save();
    	gc1.setStroke(Color.rgb(255, 0, 255));
    	double relativeX1 = pan.getLeft()*4 - (pan.getCenterX()*4 - 265);
    	double relativeY1 = pan.getTop()*4 - (pan.getCenterY()*4 - 265);
    	gc1.setTransform(new Affine(new Rotate(pan.getAngle(), relativeX1, relativeY1)));
    	gc1.strokeOval(relativeX1, relativeY1, pan.getRadius()*pan.getScaleX()*2*4, pan.getRadius()*pan.getScaleY()*2*4);
        gc1.restore();
        
        //获取当前图片label视杯数据
        CircleData bei = LabelUtil.getAvgNewCircleData("shibei");
        gc1.save();
        gc1.setLineWidth(bei.getStrokeWidth());
    	gc1.setStroke(Color.rgb(255, 0, 255));
        double relativeX2 = bei.getLeft()*4 - (pan.getCenterX()*4 - 265);
    	double relativeY2 = bei.getTop()*4 - (pan.getCenterY()*4 - 265);
    	gc1.setTransform(new Affine(new Rotate(bei.getAngle(), relativeX2, relativeY2)));
    	gc1.strokeOval(relativeX2, relativeY2, bei.getRadius()*bei.getScaleX()*2*4, bei.getRadius()*bei.getScaleY()*2*4);
    	gc1.restore();
    	
    	// mask绘制
    	repaintMaskNow(pan);
    	
    	// all-mask绘制
    	repaintAllMaskNow(pan);
	}

	private static void repaintAllMaskNow(CircleData pan) {
		GraphicsContext gc1 = Constant.TabCanvas.get(5).getGraphicsContext2D();
		
		// 绘制视盘all-mask
		MaskTmpUtil.paintAll(gc1, "shipan", 4, pan);
		
		// 绘制视杯all-mask
		MaskTmpUtil.paintAll(gc1, "shibei", 4, pan);
	}

	public static void repaintMaskNow(CircleData pan) {
		GraphicsContext gc1 = Constant.TabCanvas.get(5).getGraphicsContext2D();
		double percent = Constant.SlideValue;
		int size = 0;
		if(Constant.AnalysisMix.getPanUser() == null || 
				"".equals(Constant.AnalysisMix.getPanUser())) {
			size = 0;
		}else {
			size = Constant.AnalysisMix.getPanUser().split(",").length;
		}
		
		int num = (int) (size*percent/100);
		System.out.println("pan num = " + num);
		// 绘制mask  视盘
		MaskTmpUtil.paint(gc1, "shipan", num, pan);
		
		double beipercent = Constant.beiSlideValue;
		int size1 = 0;
		int num1 = 0;
		if(Constant.AnalysisMix.getPanUser() == null || 
				"".equals(Constant.AnalysisMix.getBeiUser())) {
			size1 = 0;
			num1 = 0;
		}else {
			size1 = Constant.AnalysisMix.getBeiUser().split(",").length;
			num1 = (int)(size1 * beipercent/100);
		}
		
		// 绘制视杯mask
		MaskTmpUtil.paint(gc1, "shibei", num1, pan);
	}
}
