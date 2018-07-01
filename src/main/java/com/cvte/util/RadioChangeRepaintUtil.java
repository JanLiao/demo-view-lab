package com.cvte.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;
import com.cvte.entity.LineData;
import com.cvte.entity.Mask;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

/** 
* @author: jan 
* @date: 2018年5月26日 上午11:06:16 
*/
public class RadioChangeRepaintUtil {

	//该方法为界面初始化调用
	public static void avgRepaint() {
		//清除tab canvas
		int len = Constant.TabCanvas.size();
		for(int i = 0; i < len; i++) {
			Constant.TabCanvas.get(i).getGraphicsContext2D().clearRect(0, 0, 530, 530);
		}
		
		//tab canvas重绘平均
		tab1Repaint(Constant.TabCanvas.get(0).getGraphicsContext2D());
		tab2Repaint(Constant.TabCanvas.get(1).getGraphicsContext2D());
		tab3Repaint(Constant.TabCanvas.get(2).getGraphicsContext2D());
		tab4Repaint(Constant.TabCanvas.get(3).getGraphicsContext2D());
	}
	
	public static void avgSoleRepaintNew(int offset) {
		Constant.TabCanvas.get(offset).getGraphicsContext2D().clearRect(0, 0, 530, 530);
		if(offset == 0) {
			tab1RepaintNew(Constant.TabCanvas.get(offset).getGraphicsContext2D());
		}
		else if(offset == 1) {
			tab2RepaintNew(Constant.TabCanvas.get(offset).getGraphicsContext2D());
		}
		else if(offset == 2) {
			tab3RepaintNew(Constant.TabCanvas.get(offset).getGraphicsContext2D());
		}
		else if(offset == 3) {
			tab4RepaintNew(Constant.TabCanvas.get(offset).getGraphicsContext2D());
		}
	}
	
	//该方法为CheckBox改变调用
	public static void avgRepaintNew() {
		// 清除tab canvas
		int len = Constant.TabCanvas.size();
		for (int i = 0; i < len; i++) {
			Constant.TabCanvas.get(i).getGraphicsContext2D().clearRect(0, 0, 530, 530);
		}

		// tab canvas重绘平均
		tab1RepaintNew(Constant.TabCanvas.get(0).getGraphicsContext2D());
		tab2RepaintNew(Constant.TabCanvas.get(1).getGraphicsContext2D());
		tab3RepaintNew(Constant.TabCanvas.get(2).getGraphicsContext2D());
		tab4RepaintNew(Constant.TabCanvas.get(3).getGraphicsContext2D());
	}
	
	private static void tab4RepaintNew(GraphicsContext gc4) {
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
        CircleData allpan = LabelUtil.getAvgCircleDataNew("shipan");
        gc4.drawImage(img4, 0, 0);
        gc4.save();
        gc4.setLineWidth(allpan.getStrokeWidth());
        gc4.setStroke(Color.rgb(51, 171, 160));
    	double relativeX5 = allpan.getLeft();
    	double relativeY5 = allpan.getTop();
    	gc4.setTransform(new Affine(new Rotate(allpan.getAngle(), relativeX5, relativeY5)));
    	gc4.strokeOval(relativeX5, relativeY5, allpan.getRadius()*allpan.getScaleX()*2, allpan.getRadius()*allpan.getScaleY()*2);
    	gc4.restore();
        
        //获取当前图片label视杯数据
        CircleData allbei = LabelUtil.getAvgCircleDataNew("shibei");
        gc4.save();
        gc4.setLineWidth(allbei.getStrokeWidth());
        gc4.setStroke(Color.rgb(0, 0, 255));
    	double relativeX4 = allbei.getLeft();
    	double relativeY4 = allbei.getTop();
    	gc4.setTransform(new Affine(new Rotate(allbei.getAngle(), relativeX4, relativeY4)));
    	gc4.strokeOval(relativeX4, relativeY4, allbei.getRadius()*allbei.getScaleX()*2, allbei.getRadius()*allbei.getScaleY()*2);
    	gc4.restore();
    	
    	//获取当前图片label 黄斑中心数据
    	LineData allLine = LabelUtil.getAvgLineDataNew("amd");
    	gc4.setLineWidth(1);
    	//两条线
    	//gc3.save();
    	gc4.setStroke(Color.rgb(255, 0, 255));
    	double relativeX8 = allLine.getLeft();
    	double relativeY8 = allLine.getTop();
    	gc4.strokeLine(relativeX8, relativeY8 + allLine.getHeight()*allLine.getScaleY()/2, 
    			relativeX8 + allLine.getWidth()*allLine.getScaleX(), relativeY8 + allLine.getHeight()*allLine.getScaleY()/2);
        //gc3.restore();
        //gc3.save();
    	gc4.strokeLine(relativeX8 + allLine.getWidth()*allLine.getScaleX()/2, relativeY8, 
    			relativeX8 + allLine.getWidth()*allLine.getScaleX()/2, relativeY8 + allLine.getHeight()*allLine.getScaleY());
        //gc3.restore();
	}

	private static void tab4Repaint(GraphicsContext gc4) {
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
        CircleData allpan = LabelUtil.getAvgCircleData("shipan");
        gc4.drawImage(img4, 0, 0);
        gc4.save();
        gc4.setLineWidth(allpan.getStrokeWidth());
        gc4.setStroke(Color.rgb(51, 171, 160));
    	double relativeX5 = allpan.getLeft();
    	double relativeY5 = allpan.getTop();
    	gc4.setTransform(new Affine(new Rotate(allpan.getAngle(), relativeX5, relativeY5)));
    	gc4.strokeOval(relativeX5, relativeY5, allpan.getRadius()*allpan.getScaleX()*2, allpan.getRadius()*allpan.getScaleY()*2);
    	gc4.restore();
        
        //获取当前图片label视杯数据
        CircleData allbei = LabelUtil.getAvgCircleData("shibei");
        gc4.save();
        gc4.setLineWidth(allbei.getStrokeWidth());
        gc4.setStroke(Color.rgb(0, 0, 255));
    	double relativeX4 = allbei.getLeft();
    	double relativeY4 = allbei.getTop();
    	gc4.setTransform(new Affine(new Rotate(allbei.getAngle(), relativeX4, relativeY4)));
    	gc4.strokeOval(relativeX4, relativeY4, allbei.getRadius()*allbei.getScaleX()*2, allbei.getRadius()*allbei.getScaleY()*2);
    	gc4.restore();
    	
    	//获取当前图片label 黄斑中心数据
    	LineData allLine = LabelUtil.getAvgLineData("amd");
    	gc4.setLineWidth(1);
    	//两条线
    	//gc3.save();
    	gc4.setStroke(Color.rgb(255, 0, 255));
    	double relativeX8 = allLine.getLeft();
    	double relativeY8 = allLine.getTop();
    	gc4.strokeLine(relativeX8, relativeY8 + allLine.getHeight()*allLine.getScaleY()/2, 
    			relativeX8 + allLine.getWidth()*allLine.getScaleX(), relativeY8 + allLine.getHeight()*allLine.getScaleY()/2);
        //gc3.restore();
        //gc3.save();
    	gc4.strokeLine(relativeX8 + allLine.getWidth()*allLine.getScaleX()/2, relativeY8, 
    			relativeX8 + allLine.getWidth()*allLine.getScaleX()/2, relativeY8 + allLine.getHeight()*allLine.getScaleY());
        //gc3.restore();
	}
	
	//方法用途同上
	private static void tab3RepaintNew(GraphicsContext gc3) {
		double width3 = 530;
        double height3 = 530;
        FileInputStream fisCenter = null;
		try {
			fisCenter = new FileInputStream(new File(Constant.AnalysisMix.getImgPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        Image img3 = new Image(fisCenter, width3, height3, true, true);
        LineData line = LabelUtil.getAvgLineDataNew("amd");
        Constant.centerAvg = line;
    	gc3.drawImage(img3, 0, 0);
    	gc3.setLineWidth(line.getStrokeWidth());
    	//两条线
    	//gc3.save();
    	gc3.setStroke(Color.rgb(255, 0, 255));
    	double relativeX7 = line.getLeft();
    	double relativeY7 = line.getTop();
    	System.out.println("line = " + line);
    	System.out.println(relativeX7 + "=" + relativeY7);
    	gc3.strokeLine(relativeX7, relativeY7 + line.getHeight()*line.getScaleY()/2, 
    			relativeX7 + line.getWidth()*line.getScaleX(),
    			relativeY7 + line.getHeight()*line.getScaleY()/2);
        gc3.restore();
        gc3.save();
        gc3.strokeLine(relativeX7 + line.getWidth()*line.getScaleX()/2, relativeY7, 
    			relativeX7 + line.getWidth()*line.getScaleX()/2,
    			relativeY7 + line.getHeight()*line.getScaleY());
        gc3.restore();
	}

	private static void tab3Repaint(GraphicsContext gc3) {
		double width3 = 530;
        double height3 = 530;
        FileInputStream fisCenter = null;
		try {
			fisCenter = new FileInputStream(new File(Constant.AnalysisMix.getImgPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        Image img3 = new Image(fisCenter, width3, height3, true, true);
        LineData line = LabelUtil.getAvgLineData("amd");
    	gc3.drawImage(img3, 0, 0);
    	gc3.setLineWidth(line.getStrokeWidth());
    	//两条线
    	//gc3.save();
    	gc3.setStroke(Color.rgb(255, 0, 255));
    	double relativeX7 = line.getLeft()*4 - ((line.getLeft() + line.getWidth()*line.getScaleX()/2)*4 - 265);
    	double relativeY7 = line.getTop()*4 - ((line.getTop() + line.getHeight()*line.getScaleY()/2)*4 - 265);
    	System.out.println("line = " + line);
    	System.out.println(relativeX7 + "=" + relativeY7);
    	gc3.strokeLine(relativeX7, relativeY7 + line.getHeight()*line.getScaleY()*2, 
    			relativeX7 + line.getWidth()*line.getScaleX()*4, relativeY7 + line.getHeight()*line.getScaleY()*2);
        //gc3.restore();
        //gc3.save();
    	gc3.strokeLine(relativeX7 + line.getWidth()*line.getScaleX()*2, relativeY7, 
    			relativeX7 + line.getWidth()*line.getScaleX()*2, relativeY7 + line.getHeight()*line.getScaleY()*4);
        //gc3.restore();
	}
	
	//方法用途同上
	private static void tab2RepaintNew(GraphicsContext gc2) {
//		double width2 = 530*4;
//        double height2 = 530*4;
//        File fileBei = new File(Constant.AnalysisMix.getImgPath());
//        InputStream fisBei = null;
//		try {
//			fisBei = new FileInputStream(fileBei);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//        Image img2 = new Image(fisBei, width2, height2, true, true);
        Image img2 = Constant.CurrImageLoad;
      //获取当前图片label视盘数据
        CircleData bei = LabelUtil.getAvgCircleDataNew("shibei");
        Constant.beiAvg = bei;
        gc2.drawImage(img2, (bei.getCenterX()*4 - 265),
        		(bei.getCenterY()*4 - 265),
        		530, 530, 0, 0, 530, 530);
    	gc2.setLineWidth(bei.getStrokeWidth());
    	gc2.save();
    	gc2.setStroke(Color.rgb(0, 0, 255));
    	double relativeX2 = bei.getLeft()*4 - (bei.getCenterX()*4 - 265);
    	double relativeY2 = bei.getTop()*4 - (bei.getCenterY()*4 - 265);
    	gc2.setTransform(new Affine(new Rotate(bei.getAngle(), relativeX2, relativeY2)));
    	gc2.strokeOval(relativeX2, relativeY2, bei.getRadius()*bei.getScaleX()*2*4, bei.getRadius()*bei.getScaleY()*2*4);
    	gc2.restore();
	}

	private static void tab2Repaint(GraphicsContext gc2) {
//		double width2 = 530*4;
//        double height2 = 530*4;
//        File fileBei = new File(Constant.AnalysisMix.getImgPath());
//        InputStream fisBei = null;
//		try {
//			fisBei = new FileInputStream(fileBei);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//        Image img2 = new Image(fisBei, width2, height2, true, true);
        Image img2 = Constant.CurrImageLoad;
      //获取当前图片label视盘数据
        CircleData bei = LabelUtil.getAvgCircleData("shibei");
        gc2.drawImage(img2, bei.getCenterX()*4 - 265,
        		bei.getCenterY()*4 - 265,
        		530, 530, 0, 0, 530, 530);
        gc2.setLineWidth(bei.getStrokeWidth());
        gc2.save();
        gc2.setStroke(Color.rgb(0, 0, 255));
    	double relativeX2 = bei.getLeft()*4 - (bei.getCenterX()*4 - 265);
    	double relativeY2 = bei.getTop()*4 - (bei.getCenterY()*4 - 265);
    	gc2.setTransform(new Affine(new Rotate(bei.getAngle(), relativeX2, relativeY2)));
    	gc2.strokeOval(relativeX2, relativeY2, bei.getRadius()*bei.getScaleX()*2*4, bei.getRadius()*bei.getScaleY()*2*4);
    	gc2.restore();
	}
	
	//方法用途同上
	private static void tab1RepaintNew(GraphicsContext gc1) {
//		double width1 = 530*4;
//        double height1 = 530*4;
//		File filePan = new File(Constant.AnalysisMix.getImgPath());
//        InputStream fisPan = null;
//		try {
//			fisPan = new FileInputStream(filePan);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//        Image img1 = new Image(fisPan, width1, height1, true, true);
        Image img1 = Constant.CurrImageLoad;
    	//获取当前图片label视盘数据
        CircleData pan = LabelUtil.getAvgCircleDataNew("shipan");
        Constant.panAvg = pan;
        gc1.drawImage(img1, pan.getCenterX()*4 - 265,
        		pan.getCenterY()*4 - 265,
        		530, 530, 0, 0, 530, 530);
    	gc1.setLineWidth(pan.getStrokeWidth());
    	gc1.save();
    	gc1.setStroke(Color.rgb(51, 171, 160));
    	double relativeX1 = pan.getLeft()*4 - (pan.getCenterX()*4 - 265);
    	double relativeY1 = pan.getTop()*4 - (pan.getCenterY()*4 - 265);
    	gc1.setTransform(new Affine(new Rotate(pan.getAngle(), relativeX1, relativeY1)));
    	gc1.strokeOval(relativeX1, relativeY1, pan.getRadius()*pan.getScaleX()*2*4, pan.getRadius()*pan.getScaleY()*2*4);
        gc1.restore();
	}

	public static void tab1Repaint(GraphicsContext gc1) {
//		double width1 = 530*4;
//        double height1 = 530*4;
//		File filePan = new File(Constant.AnalysisMix.getImgPath());
//        InputStream fisPan = null;
//		try {
//			fisPan = new FileInputStream(filePan);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//        Image img1 = new Image(fisPan, width1, height1, true, true);
        Image img1 = Constant.CurrImageLoad;
    	//获取当前图片label视盘数据
        CircleData pan = LabelUtil.getAvgCircleData("shipan");
        gc1.drawImage(img1, (pan.getCenterX()*4 - 265),
        		(pan.getCenterY()*4 - 265),
        		530, 530, 0, 0, 530, 530);
    	gc1.setLineWidth(pan.getStrokeWidth());
    	gc1.save();
    	gc1.setStroke(Color.rgb(51, 171, 160));
    	double relativeX1 = pan.getLeft()*4 - (pan.getCenterX()*4 - 265);
    	double relativeY1 = pan.getTop()*4 - (pan.getCenterY()*4 - 265);
    	gc1.setTransform(new Affine(new Rotate(pan.getAngle(), relativeX1, relativeY1)));
    	gc1.strokeOval(relativeX1, relativeY1, pan.getRadius()*pan.getScaleX()*2*4, pan.getRadius()*pan.getScaleY()*2*4);
        gc1.restore();
	}
	
	public static void overLapSoleRepaint(int offset) {
		Constant.TabCanvas.get(offset).getGraphicsContext2D().clearRect(0, 0, 530, 530);
		if(offset == 0) {			
			tab1OverLapRepaint(Constant.TabCanvas.get(offset).getGraphicsContext2D());
		}
		else if(offset == 1) {
			tab2OverLapRepaint(Constant.TabCanvas.get(offset).getGraphicsContext2D());
		}
		else if(offset == 2) {
			tab3OverLapRepaint(Constant.TabCanvas.get(offset).getGraphicsContext2D());
		}
		else if(offset == 3) {
			tab4OverLapRepaint(Constant.TabCanvas.get(offset).getGraphicsContext2D());
		}
	}

	public static void overLapRepaint() {
		// 清除tab canvas
		int len = Constant.TabCanvas.size();
		for (int i = 0; i < len - 1; i++) {
			Constant.TabCanvas.get(i).getGraphicsContext2D().clearRect(0, 0, 530, 530);
		}
		System.out.println("OverLap tab 清除");
		
		//tab canvas重绘
		tab1OverLapRepaint(Constant.TabCanvas.get(0).getGraphicsContext2D());
		tab2OverLapRepaint(Constant.TabCanvas.get(1).getGraphicsContext2D());
		tab3OverLapRepaint(Constant.TabCanvas.get(2).getGraphicsContext2D());
		//已被disable   弃用
		//tab4OverLapRepaint(Constant.TabCanvas.get(3).getGraphicsContext2D());
	}

	private static void tab4OverLapRepaint(GraphicsContext gc4) {
		// tab4 重叠重绘
//		double width4 = 530 * 4;
//		double height4 = 530 * 4;
//		File fileBei = new File(Constant.AnalysisMix.getImgPath());
//		InputStream fisBei = null;
//		try {
//			fisBei = new FileInputStream(fileBei);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		Image img2 = new Image(fisBei, width4, height4, true, true);
		Image img2 = Constant.CurrImageLoad;

		int len = Constant.CheckBoxList.size();
		if (len > 1) {
			CircleData solebei = LabelUtil.getSoleLabelData(0, "shibei");
			//该方法已被弃用
			double commonX = (solebei.getLeft() + solebei.getRadius() * solebei.getScaleX()) * 4 - 265;
			double commonY = (solebei.getTop() + solebei.getRadius() * solebei.getScaleY()) * 4 - 265;
			gc4.drawImage(img2, solebei.getCenterX() * 4 - 265,
					solebei.getCenterY() * 4 - 265, 530, 530, 0, 0, 530, 530);
			if (Constant.CheckBoxList.get(0).isSelected()) {
				// 获取当前图片label视盘数据
				gc4.save();
				gc4.setLineWidth(solebei.getStrokeWidth());
				gc4.setStroke(Color.rgb(51, 171, 160));
				double relativeX = solebei.getLeft() * 4
						- (solebei.getCenterX() * 4 - 265);
				double relativeY = solebei.getTop() * 4
						- (solebei.getCenterY() * 4 - 265);
				gc4.setTransform(new Affine(new Rotate(solebei.getAngle(), relativeX, relativeY)));
				gc4.strokeOval(relativeX, relativeY, solebei.getRadius() * solebei.getScaleX() * 2 * 4,
						solebei.getRadius() * solebei.getScaleY() * 2 * 4);
				gc4.restore();
			}

			// 继续其他重叠绘图
			// 进行右侧CheckBox检查
			for (int i = 1; i < len; i++) {
				if (Constant.CheckBoxList.get(i).isSelected()) { // 若当前CheckBox选中则重叠显示
					CircleData solebeiOther = LabelUtil.getSoleLabelData(i, "shibei");
					gc4.save();
					gc4.setLineWidth(solebeiOther.getStrokeWidth());
					int red = Constant.ColorList.get(i).getRed();
					int green = Constant.ColorList.get(i).getGreen();
					int blue = Constant.ColorList.get(i).getBlue();
					gc4.setStroke(Color.rgb(red, green, blue));
					double relativeX = solebeiOther.getLeft() * 4
							- commonX;
					double relativeY = solebeiOther.getTop() * 4
							- commonY;
					gc4.setTransform(new Affine(new Rotate(solebeiOther.getAngle(), relativeX, relativeY)));
					gc4.strokeOval(relativeX, relativeY, solebeiOther.getRadius() * solebeiOther.getScaleX() * 2 * 4,
							solebeiOther.getRadius() * solebeiOther.getScaleY() * 2 * 4);
					gc4.restore();
				}
			}
		} else if (len == 1) {
			CircleData solebei = LabelUtil.getSoleLabelData(0, "shibei");
			gc4.drawImage(img2, solebei.getCenterX() * 4 - 265,
					solebei.getCenterY() * 4 - 265, 530, 530, 0, 0, 530, 530);
			if (Constant.CheckBoxList.get(0).isSelected()) {
				// 获取当前图片label视盘数据
				gc4.save();
				gc4.setLineWidth(solebei.getStrokeWidth());
				gc4.setStroke(Color.rgb(51, 171, 160));
				double relativeX = solebei.getLeft() * 4
						- (solebei.getCenterX() * 4 - 265);
				double relativeY = solebei.getTop() * 4
						- (solebei.getCenterY() * 4 - 265);
				gc4.setTransform(new Affine(new Rotate(solebei.getAngle(), relativeX, relativeY)));
				gc4.strokeOval(relativeX, relativeY, solebei.getRadius() * solebei.getScaleX() * 2 * 4,
						solebei.getRadius() * solebei.getScaleY() * 2 * 4);
				gc4.restore();
			}
		} else {
			System.out.println("这种情况有误,不应该有这种bug");
		}
	}

	private static void tab3OverLapRepaint(GraphicsContext gc3) {
		// tab3 重叠重绘
		double width3 = 530;
		double height3 = 530;
		File fileCenter = new File(Constant.AnalysisMix.getImgPath());
		InputStream fisCenter = null;
		try {
			fisCenter = new FileInputStream(fileCenter);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Image img3 = new Image(fisCenter, width3, height3, true, true);

		int len = Constant.CheckBoxList.size();
		if (len > 1) {
			LineData line = LabelUtil.getSoleLineData(0);
			double commonX = line.getLeft();
			double commonY = line.getTop();
			gc3.drawImage(img3, 0, 0);
			if (Constant.CheckBoxList.get(0).isSelected()) {
				// 获取当前图片label黄斑中心数据
				gc3.setLineWidth(line.getStrokeWidth());
		    	//两条线
		    	gc3.save();
		    	int redOne = Constant.ColorList.get(0).getRed();
				int greenOne = Constant.ColorList.get(0).getGreen();
				int blueOne = Constant.ColorList.get(0).getBlue();
				gc3.setStroke(Color.rgb(redOne, greenOne, blueOne));
		    	double relativeX = commonX;
		    	double relativeY = commonY;
		    	gc3.strokeLine(relativeX, relativeY + line.getHeight()*line.getScaleY()/2, 
		    			relativeX + line.getWidth()*line.getScaleX(),
		    			relativeY + line.getHeight()*line.getScaleY()/2);
		        gc3.restore();
		        gc3.save();
		        gc3.setStroke(Color.rgb(redOne, greenOne, blueOne));
		        gc3.strokeLine(relativeX + line.getWidth()*line.getScaleX()/2, relativeY, 
		    			relativeX + line.getWidth()*line.getScaleX()/2,
		    			relativeY + line.getHeight()*line.getScaleY());
		        gc3.restore();
			}

			// 继续其他重叠绘图
			// 进行右侧CheckBox检查
			for (int i = 1; i < len; i++) {
				if (Constant.CheckBoxList.get(i).isSelected()) { // 若当前CheckBox选中则重叠显示
					// 获取当前图片label黄斑中心数据
					LineData lineOther = LabelUtil.getSoleLineData(i);
					gc3.setLineWidth(lineOther.getStrokeWidth());
			    	//两条线
			    	gc3.save();
			    	int red = Constant.ColorList.get(i).getRed();
					int green = Constant.ColorList.get(i).getGreen();
					int blue = Constant.ColorList.get(i).getBlue();
					gc3.setStroke(Color.rgb(red, green, blue));
					double relativeX = lineOther.getLeft();
			    	double relativeY = lineOther.getTop();
			    	gc3.strokeLine(relativeX, relativeY + lineOther.getHeight()*lineOther.getScaleY()/2, 
			    			relativeX + lineOther.getWidth()*lineOther.getScaleX(),
			    			relativeY + lineOther.getHeight()*lineOther.getScaleY()/2);
			        gc3.restore();
			        gc3.save();
					gc3.setStroke(Color.rgb(red, green, blue));
					gc3.strokeLine(relativeX + lineOther.getWidth()*lineOther.getScaleX()/2, relativeY, 
			    			relativeX + lineOther.getWidth()*lineOther.getScaleX()/2,
			    			relativeY + lineOther.getHeight()*lineOther.getScaleY());
			        gc3.restore();
				}
			}
		} else if (len == 1) {
			LineData line = LabelUtil.getSoleLineData(0);
			gc3.drawImage(img3, 0, 0);
			if (Constant.CheckBoxList.get(0).isSelected()) {
				// 获取当前图片label黄斑中心数据
				gc3.setLineWidth(line.getStrokeWidth());
		    	//两条线
		    	gc3.save();
		    	gc3.setStroke(Color.rgb(255, 0, 255));
		    	double relativeX = line.getLeft();
		    	double relativeY = line.getTop();
		    	gc3.strokeLine(relativeX, relativeY + line.getHeight()*line.getScaleY()/2, 
		    			relativeX + line.getWidth()*line.getScaleX(),
		    			relativeY + line.getHeight()*line.getScaleY()/2);
		        gc3.restore();
		        gc3.save();
		        gc3.setStroke(Color.rgb(255, 0, 255));
		        gc3.strokeLine(relativeX + line.getWidth()*line.getScaleX()/2, relativeY, 
		    			relativeX + line.getWidth()*line.getScaleX()/2,
		    			relativeY + line.getHeight()*line.getScaleY());
		        gc3.restore();
			}
		} else {
			System.out.println("这种情况有误,不应该有这种bug");
		}
	}

	private static void tab2OverLapRepaint(GraphicsContext gc2) {
		// tab2 重叠重绘
//		double width2 = 530 * 4;
//		double height2 = 530 * 4;
//		File fileBei = new File(Constant.AnalysisMix.getImgPath());
//		InputStream fisBei = null;
//		try {
//			fisBei = new FileInputStream(fileBei);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		Image img2 = new Image(fisBei, width2, height2, true, true);
		Image img2 = Constant.CurrImageLoad;

		int len = Constant.CheckBoxList.size();
		if (len > 1) {
			CircleData solebei = LabelUtil.getSoleLabelData(0, "shibei");
			double commonX = solebei.getCenterX() * 4 - 265;
			double commonY = solebei.getCenterY() * 4 - 265;
			gc2.drawImage(img2, solebei.getCenterX() * 4 - 265,
					solebei.getCenterY() * 4 - 265, 530, 530, 0, 0, 530, 530);
			if (Constant.CheckBoxList.get(0).isSelected()) {
				// 获取当前图片label视盘数据
				gc2.save();
				gc2.setLineWidth(solebei.getStrokeWidth());
				int redOne = Constant.ColorList.get(0).getRed();
				int greenOne = Constant.ColorList.get(0).getGreen();
				int blueOne = Constant.ColorList.get(0).getBlue();
				gc2.setStroke(Color.rgb(redOne, greenOne, blueOne));
				double relativeX = solebei.getLeft() * 4
						- (solebei.getCenterX() * 4 - 265);
				double relativeY = solebei.getTop() * 4
						- (solebei.getCenterY() * 4 - 265);
				gc2.setTransform(new Affine(new Rotate(solebei.getAngle(), relativeX, relativeY)));
				gc2.strokeOval(relativeX, relativeY, solebei.getRadius() * solebei.getScaleX() * 2 * 4,
						solebei.getRadius() * solebei.getScaleY() * 2 * 4);
				gc2.restore();
			}

			// 继续其他重叠绘图
			// 进行右侧CheckBox检查
			for (int i = 1; i < len; i++) {
				if (Constant.CheckBoxList.get(i).isSelected()) { // 若当前CheckBox选中则重叠显示
					CircleData solebeiOther = LabelUtil.getSoleLabelData(i, "shibei");
					gc2.save();
					gc2.setLineWidth(solebeiOther.getStrokeWidth());
					int red = Constant.ColorList.get(i).getRed();
					int green = Constant.ColorList.get(i).getGreen();
					int blue = Constant.ColorList.get(i).getBlue();
					gc2.setStroke(Color.rgb(red, green, blue));
					double relativeX = solebeiOther.getLeft() * 4
							- commonX;
					double relativeY = solebeiOther.getTop() * 4
							- commonY;
					gc2.setTransform(new Affine(new Rotate(solebeiOther.getAngle(), relativeX, relativeY)));
					gc2.strokeOval(relativeX, relativeY, solebeiOther.getRadius() * solebeiOther.getScaleX() * 2 * 4,
							solebeiOther.getRadius() * solebeiOther.getScaleY() * 2 * 4);
					gc2.restore();
				}
			}
		} else if (len == 1) {
			CircleData solebei = LabelUtil.getSoleLabelData(0, "shibei");
			gc2.drawImage(img2, solebei.getCenterX() * 4 - 265,
					solebei.getCenterY() * 4 - 265, 530, 530, 0, 0, 530, 530);
			if (Constant.CheckBoxList.get(0).isSelected()) {
				// 获取当前图片label视盘数据
				gc2.save();
				gc2.setLineWidth(solebei.getStrokeWidth());
				gc2.setStroke(Color.rgb(51, 171, 160));
				double relativeX = solebei.getLeft() * 4
						- (solebei.getCenterX() * 4 - 265);
				double relativeY = solebei.getTop() * 4
						- (solebei.getCenterY() * 4 - 265);
				gc2.setTransform(new Affine(new Rotate(solebei.getAngle(), relativeX, relativeY)));
				gc2.strokeOval(relativeX, relativeY, solebei.getRadius() * solebei.getScaleX() * 2 * 4,
						solebei.getRadius() * solebei.getScaleY() * 2 * 4);
				gc2.restore();
			}
		} else {
			System.out.println("这种情况有误,不应该有这种bug");
		}
	}

	private static void tab1OverLapRepaint(GraphicsContext gc1) {
		// tab1 重叠重绘
//		double width1 = 530 * 4;
//		double height1 = 530 * 4;
//		File filePan = new File(Constant.AnalysisMix.getImgPath());
//		InputStream fisPan = null;
//		try {
//			fisPan = new FileInputStream(filePan);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		Image img1 = new Image(fisPan, width1, height1, true, true);
		Image img1 = Constant.CurrImageLoad;
		
		int len = Constant.CheckBoxList.size();
		if(len > 1) {
			CircleData solepan = LabelUtil.getSoleLabelData(0, "shipan");
			double commonX = solepan.getCenterX()*4 - 265;
			double commonY = solepan.getCenterY()*4 - 265;
            gc1.drawImage(img1, solepan.getCenterX()*4 - 265,
            		solepan.getCenterY()*4 - 265,
            		530, 530, 0, 0, 530, 530);
            if(Constant.CheckBoxList.get(0).isSelected()) {
            	//获取当前图片label视盘数据
            	gc1.save();
                gc1.setLineWidth(solepan.getStrokeWidth());
                int redOne = Constant.ColorList.get(0).getRed();
				int greenOne = Constant.ColorList.get(0).getGreen();
				int blueOne = Constant.ColorList.get(0).getBlue();
				gc1.setStroke(Color.rgb(redOne, greenOne, blueOne));
            	double relativeX = solepan.getLeft()*4 - (solepan.getCenterX()*4 - 265);
            	double relativeY = solepan.getTop()*4 - (solepan.getCenterY()*4 - 265);
            	gc1.setTransform(new Affine(new Rotate(solepan.getAngle(), relativeX, relativeY)));
            	gc1.strokeOval(relativeX, relativeY, solepan.getRadius()*solepan.getScaleX()*2*4, solepan.getRadius()*solepan.getScaleY()*2*4);
            	gc1.restore();
            }
            
            //继续其他重叠绘图
            //进行右侧CheckBox检查
            for(int i =1; i < len; i++) {
            	if(Constant.CheckBoxList.get(i).isSelected()) {  //若当前CheckBox选中则重叠显示
            		CircleData solepanOther = LabelUtil.getSoleLabelData(i, "shipan");
            		gc1.save();
                    gc1.setLineWidth(solepanOther.getStrokeWidth());
                    int red = Constant.ColorList.get(i).getRed();
                    int green = Constant.ColorList.get(i).getGreen();
                    int blue = Constant.ColorList.get(i).getBlue();
                    gc1.setStroke(Color.rgb(red, green, blue));
                	double relativeX = solepanOther.getLeft()*4 - commonX;
                	double relativeY = solepanOther.getTop()*4 - commonY;
                	gc1.setTransform(new Affine(new Rotate(solepanOther.getAngle(), relativeX, relativeY)));
                	gc1.strokeOval(relativeX, relativeY, solepanOther.getRadius()*solepanOther.getScaleX()*2*4, solepanOther.getRadius()*solepanOther.getScaleY()*2*4);
                	gc1.restore();
    			}
            }
		}
		else if(len == 1){
			CircleData solepan = LabelUtil.getSoleLabelData(0, "shipan");
            gc1.drawImage(img1, solepan.getCenterX()*4 - 265,
            		solepan.getCenterY()*4 - 265,
            		530, 530, 0, 0, 530, 530);
            if(Constant.CheckBoxList.get(0).isSelected()) {
            	//获取当前图片label视盘数据
            	gc1.save();
                gc1.setLineWidth(solepan.getStrokeWidth());
                gc1.setStroke(Color.rgb(51, 171, 160));
            	double relativeX = solepan.getLeft()*4 - (solepan.getCenterX()*4 - 265);
            	double relativeY = solepan.getTop()*4 - (solepan.getCenterY()*4 - 265);
            	gc1.setTransform(new Affine(new Rotate(solepan.getAngle(), relativeX, relativeY)));
            	gc1.strokeOval(relativeX, relativeY, solepan.getRadius()*solepan.getScaleX()*2*4, solepan.getRadius()*solepan.getScaleY()*2*4);
            	gc1.restore();
            }
		}
		else {
			System.out.println("这种情况有误,不应该有这种bug");
		}
	}

	public static void maskRepaint() {
		Mask mask = Constant.AnalysisMix.getMask();
		int[][] curPanMask = MaskUtil.getCurMask("pan", mask);
		int[][] curBeiMask = MaskUtil.getCurMask("bei", mask);
		Constant.AnalysisMix.getMask().setCurPanMask(curPanMask);
		Constant.AnalysisMix.getMask().setCurBeiMask(curBeiMask);
		//MaskUtil.generateData();
		//清除tab1和tab2
		Constant.TabCanvas.get(0).getGraphicsContext2D().clearRect(0, 0, 530, 530);
		Constant.TabCanvas.get(1).getGraphicsContext2D().clearRect(0, 0, 530, 530);
		
		///tab1和tab2 canvas重绘
		tab1MaskRepaint(Constant.TabCanvas.get(0).getGraphicsContext2D());
		tab2MaskRepaint(Constant.TabCanvas.get(1).getGraphicsContext2D());
	}

	private static void tab2MaskRepaint(GraphicsContext gc2) {
		// tab2 重叠重绘
//		double width2 = 530 * 4;
//		double height2 = 530 * 4;
//		File fileBei = new File(Constant.AnalysisMix.getImgPath());
//		InputStream fisBei = null;
//		try {
//			fisBei = new FileInputStream(fileBei);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		Image img2 = new Image(fisBei, width2, height2, true, true);
		Image img2 = Constant.CurrImageLoad;
		
		CircleData circle = LabelUtil.getAvgCircleData("shibei");
        //初始化视盘avg
        gc2.drawImage(img2, (circle.getCenterX()*4 - 265),
        		(circle.getCenterY()*4 - 265),
        		530, 530, 0, 0, 530, 530);
		double percent = Constant.beiSlideValue;
		
		int len = Constant.CheckBoxList.size();
		int size = 0;
		for(int i = 0; i < len; i++) {
			if(Constant.CheckBoxList.get(i).isSelected()) {
				size++;
			}
		}
		int num = (int) (size*percent/100);
		System.out.println("bei num = " + num);
		
		Mask mask = Constant.AnalysisMix.getMask();
		int[][] curPanMask = MaskUtil.getCurMask("pan", mask);
		int[][] curBeiMask = MaskUtil.getCurMask("bei", mask);
		mask.setCurPanMask(curPanMask);
		mask.setCurBeiMask(curBeiMask);
		Constant.AnalysisMix.setMask(mask);
		
		MaskUtil.paint(gc2, "shibei", num);
	}

	private static void tab1MaskRepaint(GraphicsContext gc2) {
//		double width2 = 530 * 4;
//		double height2 = 530 * 4;
//		File fileBei = new File(Constant.AnalysisMix.getImgPath());
//		InputStream fisBei = null;
//		try {
//			fisBei = new FileInputStream(fileBei);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		Image img2 = new Image(fisBei, width2, height2, true, true);
		Image img2 = Constant.CurrImageLoad;
		
		CircleData circle = LabelUtil.getAvgCircleData("shipan");
        //初始化视盘avg
        gc2.drawImage(img2, (circle.getCenterX()*4 - 265),
        		(circle.getCenterY()*4 - 265),
        		530, 530, 0, 0, 530, 530);
		double percent = Constant.SlideValue;
		
		int len = Constant.CheckBoxList.size();
		int size = 0;
		for(int i = 0; i < len; i++) {
			if(Constant.CheckBoxList.get(i).isSelected()) {
				size++;
			}
		}
		int num = (int) (size*percent/100);
		MaskUtil.paint(gc2, "shipan", num);
	}
}
