package com.cvte.util;

import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;
import com.cvte.entity.Mask;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MaskTmpUtil {

	public static void paint(GraphicsContext gc, String name, int num) {
		if("shibei".equals(name)) {
			Mask mask = Constant.AnalysisMix.getMask();
			int[][] beiMask = mask.getCurBeiMask();
			
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					//System.out.println("flag = " + beiMask[i][j]);
					if(beiMask[i][j] >= num) {
						//System.out.println("pm = " + beiMask[i][j]);
						if(beiMask[i][j - 1] < num || beiMask[i][j + 1] < num
								|| beiMask[i - 1][j] < num || beiMask[i + 1][j] < num
								|| beiMask[i - 1][j - 1] < num || beiMask[i + 1][j + 1] < num
								|| beiMask[i - 1][j + 1] < num || beiMask[i + 1][j -1] < num
								) {
							//绘制点
							paintPoint(gc, name, i, j);
							//paintPoint1(gc, name, i, 1060 - j);
						}
					}
				}
			}
		}else if("shipan".equals(name)) {
			Mask mask = Constant.AnalysisMix.getMask();
			int[][] panMask = mask.getCurPanMask();
			//从x轴扫描
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					//System.out.println("flag = " + panMask[i][j]);
					if(panMask[i][j] >= num) {
						//System.out.println("pm = " + panMask[i][j]);
						if(panMask[i][j - 1] < num || panMask[i][j + 1] < num
								|| panMask[i - 1][j] < num || panMask[i + 1][j] < num
								|| panMask[i - 1][j - 1] < num || panMask[i + 1][j + 1] < num
								|| panMask[i - 1][j + 1] < num || panMask[i + 1][j -1] < num
								) {
							//绘制点  从x轴扫
							paintPoint(gc, name, i, j);
							//重y轴扫描
							//paintPoint(gc, name, i, j);
						}
					}
				}
			}
		}
	}
	
	private static void paintPoint(GraphicsContext gc, String name, int i, int j) {
		//final int NUM_IMGS = 5;
		//final double opacity = 0.6;
        //System.out.println(opacity);
        //gc.setGlobalAlpha(opacity);
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(Color.SEAGREEN);
			double relativeX = (Constant.panAvg.getCenterX() - 53 + 0.2*i)*4 - (Constant.panAvg.getCenterX()*4 - 265);
	    	double relativeY = (Constant.panAvg.getCenterY() + 53 - 0.2*j)*4 - (Constant.panAvg.getCenterY()*4 - 265);
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(Color.SEAGREEN);
			//System.out.println("pan  == " + Constant.panAvg.getCenterX());
			//System.out.println("bei == " + Constant.beiAvg.getCenterX());
			double relativeX = (Constant.beiAvg.getCenterX() - 53 + 0.2*i)*4 - (Constant.panAvg.getCenterX()*4 - 265);
	    	double relativeY = (Constant.beiAvg.getCenterY() + 53 - 0.2*j)*4 - (Constant.panAvg.getCenterY()*4 - 265);
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		
	}
	
	public static void paintAll(GraphicsContext gc, String name, int num, CircleData pan) {
		if("shipan".equals(name)) {
			Mask mask = Constant.AnalysisMix.getMask();
			int[][] panMask = mask.getAllPanMask();
			//从x轴扫描
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					//System.out.println("flag = " + panMask[i][j]);
					if(panMask[i][j] >= num) {
						//System.out.println("pm = " + panMask[i][j]);
						if(panMask[i][j - 1] < num || panMask[i][j + 1] < num
								|| panMask[i - 1][j] < num || panMask[i + 1][j] < num
								|| panMask[i - 1][j - 1] < num || panMask[i + 1][j + 1] < num
								|| panMask[i - 1][j + 1] < num || panMask[i + 1][j -1] < num
								) {
							//绘制点  从x轴扫
							paintPointMask(gc, name, i, j, pan, Color.BLUE);
						}
					}
				}
			}
			
			
		}
		else if("shibei".equals(name)) {
			Mask mask = Constant.AnalysisMix.getMask();
			int[][] beiMask = mask.getAllBeiMask();
			
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					//System.out.println("flag = " + beiMask[i][j]);
					if(beiMask[i][j] >= num) {
						//System.out.println("pm = " + beiMask[i][j]);
						if(beiMask[i][j - 1] < num || beiMask[i][j + 1] < num
								|| beiMask[i - 1][j] < num || beiMask[i + 1][j] < num
								|| beiMask[i - 1][j - 1] < num || beiMask[i + 1][j + 1] < num
								|| beiMask[i - 1][j + 1] < num || beiMask[i + 1][j -1] < num
								) {
							//绘制点
							paintPointMask(gc, name, i, j, pan, Color.BLUE);
							//paintPoint1(gc, name, i, 1060 - j);
						}
					}
				}
			}
		}
		else {
			System.out.println("待使用");
		}
	}

	private static void paintPointMask(GraphicsContext gc, String name, int i, int j, 
			CircleData pan, Color color) {
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(color);
			double relativeX = (Constant.panAvg.getCenterX() - 53 + 0.2*i)*4 - (pan.getCenterX()*4 - 265);
	    	double relativeY = (Constant.panAvg.getCenterY() + 53 - 0.2*j)*4 - (pan.getCenterY()*4 - 265);
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(color);
			double relativeX = (Constant.beiAvg.getCenterX() - 53 + 0.2*i)*4 - (pan.getCenterX()*4 - 265);
	    	double relativeY = (Constant.beiAvg.getCenterY() + 53 - 0.2*j)*4 - (pan.getCenterY()*4 - 265);
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
	}

	public static void paint(GraphicsContext gc, String name, int num, CircleData pan) {
		if("shipan".equals(name)) {
			Mask mask = Constant.AnalysisMix.getMask();
			int[][] panMask = mask.getCurPanMask();
			//从x轴扫描
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					//System.out.println("flag = " + panMask[i][j]);
					if(panMask[i][j] >= num) {
						//System.out.println("pm = " + i + "=" + j + "=" + panMask[i][j]);
						if(panMask[i][j - 1] < num || panMask[i][j + 1] < num
								|| panMask[i - 1][j] < num || panMask[i + 1][j] < num
								|| panMask[i - 1][j - 1] < num || panMask[i + 1][j + 1] < num
								|| panMask[i - 1][j + 1] < num || panMask[i + 1][j -1] < num
								) {
							//绘制点  从x轴扫
							paintPointMask(gc, name, i, j, pan, Color.SEAGREEN);
						}
					}
				}
			}
			
			
		}
		else if("shibei".equals(name)) {
			Mask mask = Constant.AnalysisMix.getMask();
			int[][] beiMask = mask.getCurBeiMask();
			
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					//System.out.println("flag = " + beiMask[i][j]);
					if(beiMask[i][j] >= num) {
						//System.out.println("pm = " + beiMask[i][j]);
						if(beiMask[i][j - 1] < num || beiMask[i][j + 1] < num
								|| beiMask[i - 1][j] < num || beiMask[i + 1][j] < num
								|| beiMask[i - 1][j - 1] < num || beiMask[i + 1][j + 1] < num
								|| beiMask[i - 1][j + 1] < num || beiMask[i + 1][j -1] < num
								) {
							//绘制点
							paintPointMask(gc, name, i, j, pan, Color.SEAGREEN);
							//paintPoint1(gc, name, i, 1060 - j);
						}
					}
				}
			}
		}
		else {
			System.out.println("待使用");
		}
	}
}
