package com.cvte.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;
import com.cvte.entity.Mask;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年5月27日 上午12:54:24 
*/
public class MaskViewUtil {

	public static void generateData() {
		Mask mask = new Mask();
		mask.setPanUser(Constant.AnalysisView.getPanUser());
		mask.setBeiUser(Constant.AnalysisView.getBeiUser());
		int[][] allPanMask = new int[530][530];
		int[][] allBeiMask = new int[530][530];
		Map<String, Integer[][]> panMap = new HashMap<String, Integer[][]>();  //单张图片图片视盘mask
		Map<String, Integer[][]> beiMap = new HashMap<String, Integer[][]>();  //单张图片图片视杯mask
		System.out.println("zong = " + Constant.AnalysisView.getAllLabelData().size());
		for(String s : Constant.AnalysisView.getAllLabelData()) {
			String user = s.split("=")[0];
			String label = s.split("=")[1];
			System.out.println("user = " + user);
			
			Integer[][] solePan = getMask(label, "shipan");
//			for(int i =0 ;i < 530; i++) {
//				for(int j = 0; j < 530; j++) {
//					System.out.println(solePan[i][j]);
//				}
//			}
			Integer[][] soleBei = getMask(label, "shibei");
			for(int i = 0; i < 530; i++) {
				for(int j = 0; j < 530; j++) {
					//System.out.println("sole pan = " + solePan[i][j].intValue());
					if(solePan[i][j].intValue() == 1) {
						//System.out.println(allPanMask[i][j]);
						allPanMask[i][j] += 1;
					}
					if(soleBei[i][j].intValue() == 1) {
						//System.out.println(allPanMask[i][j]);
						allBeiMask[i][j] += 1;
					}
				}
			}
			panMap.put(user, solePan);
			beiMap.put(user, soleBei);
		}
		mask.setAllPanMask(allPanMask);
		mask.setAllBeiMask(allBeiMask);
		mask.setAllSolePanMask(panMap);
		mask.setAllSoleBeiMask(beiMap);
		//Constant.AnalysisView.setMask(mask);
		int[][] curPanMask = getCurMask("pan", mask);
		int[][] curBeiMask = getCurMask("bei", mask);
		mask.setCurPanMask(curPanMask);
		mask.setCurBeiMask(curBeiMask);
		Constant.AnalysisView.setMask(mask);
		System.out.println("mask66666 = " + mask);
	}
	
	public static int[][] getCurMask(String name, Mask mask) {
		int[][] curMask = new int[530][530];
		if("pan".equals(name)) {
			//System.out.println("=======" + mask.getPanUser());
			String[] s = mask.getPanUser().split(",");
			for(String st : s) {
				System.out.println("name = " + st);
				Integer[][] tmp = mask.getAllSolePanMask().get(st);
				if(tmp == null) {
					System.out.println("nu  ssdsdf");
				}
				for(int i = 0; i < 530; i++) {
					for(int j = 0; j < 530; j++) {
					  curMask[i][j] = curMask[i][j] + tmp[i][j].intValue();
					}
				}
			}
		}
		else if("bei".equals(name)) {
			String[] s = mask.getBeiUser().split(",");
			for(String st : s) {
				Integer[][] tmp = mask.getAllSoleBeiMask().get(st);
				for(int i = 0; i < 530; i++) {
					for(int j = 0; j < 530; j++) {
					curMask[i][j] = curMask[i][j] + tmp[i][j].intValue();
					}
				}
			}
		}
		return curMask;
	}

	private static boolean checkUser(String user, String userStr) {
		boolean flag = false;
		String[] s = userStr.split(",");
		for(String st : s) {
			if(st.equals(user)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	private static Integer[][] getMask(String label, String name) {
		CircleData circle = getCircle(label, name);	
		System.out.println("circle = = " + circle);
		Integer[][] data = MathUtil.getMask(circle);
		return data;
	}

	private static CircleData getCircle(String label, String name) {
		JSONObject json = JSON.parseObject(label);
		JSONArray arr = json.getJSONArray("label_data");
		JSONObject obj = null;
		for(int i = 0; i < 3; i++) {
			JSONObject tmp = arr.getJSONObject(i);
			if(name.equals(tmp.getString("name"))) {
				obj = JSON.parseObject(tmp.getString("data"));
				break;
			}
		}
		CircleData circle = new CircleData();
		circle.setAngle(Double.parseDouble(obj.getString("angle")));
		circle.setHeight(Double.parseDouble(obj.getString("height")));
		circle.setLeft(Double.parseDouble(obj.getString("left")));
		circle.setOpacity(Double.parseDouble(obj.getString("opacity")));
		circle.setRadius(Double.parseDouble(obj.getString("radius")));
		circle.setScaleX(Double.parseDouble(obj.getString("scaleX")));
		circle.setScaleY(Double.parseDouble(obj.getString("scaleY")));
		circle.setStroke(obj.getString("stroke"));
		circle.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		circle.setTop(Double.parseDouble(obj.getString("top")));
		if("shipan".equals(name)) {
			CircleData pan = LabelViewUtil.getAvgCircleData("shipan");
			circle.setCenterX(pan.getCenterX());
			circle.setCenterY(pan.getCenterY());
//			circle.setCenterX(Constant.panAvg.getCenterX());
//			circle.setCenterY(Constant.panAvg.getCenterY());
		}
		else if("shibei".equals(name)) {
			CircleData bei = LabelViewUtil.getAvgCircleData("shibei");
			circle.setCenterX(bei.getCenterX());
			circle.setCenterY(bei.getCenterY());
		}
		return circle;
	}

	public static void paint(GraphicsContext gc, String name, int num) {
		if("shipan".equals(name)) {
			Mask mask = Constant.AnalysisView.getMask();
			int[][] panMask = mask.getCurPanMask();
			//System.out.println("mask = " + panMask[0][0]);
			//System.out.println(" == " + Constant.panAvg.getCenterY());
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
		else if("shibei".equals(name)) {
			Mask mask = Constant.AnalysisView.getMask();
			int[][] beiMask = mask.getCurBeiMask();
			for(int i = 1; i < 529; i++) {
				for(int j = 1; j < 529; j++) {
					//System.out.println("flag = " + panMask[i][j]);
					if(beiMask[i][j] >= num) {
						//System.out.println("pm = " + panMask[i][j]);
						if(beiMask[i][j - 1] < num || beiMask[i][j + 1] < num
								|| beiMask[i - 1][j] < num || beiMask[i + 1][j] < num
								|| beiMask[i - 1][j - 1] < num || beiMask[i + 1][j + 1] < num
								|| beiMask[i - 1][j + 1] < num || beiMask[i + 1][j -1] < num
								) {
							//绘制点
							paintPoint(gc, name, i, j);
							//paintPoint1(gc, name, i, 530 - j);
						}
					}
				}
			}
		}
		else {
			System.out.println("待使用");
		}
		
	}

	private static void paintPoint(GraphicsContext gc, String name, int i, int j) {
		//final int NUM_IMGS = 5;
		//final double opacity = 0.6;
        //System.out.println(opacity);
        //gc.setGlobalAlpha(opacity);
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(Color.BLUE);
			double relativeX = (Constant.ViewPanAvg.getCenterX() - 53 + 0.2*i)*4 - (Constant.ViewPanAvg.getCenterX()*4 - 265);
	    	double relativeY = (Constant.ViewPanAvg.getCenterY() + 53 - 0.2*j)*4 - (Constant.ViewPanAvg.getCenterY()*4 - 265);
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
			
//			gc.save();
//			gc.setFill(Color.BLUE);
//			double relativeX1 = (Constant.panAvg.getCenterX() - 53 + 0.1*i)*4 - (Constant.panAvg.getCenterX()*4 - 265);
//	    	double relativeY1 = (Constant.panAvg.getCenterY() - 53 - 0.1*j)*4 - (Constant.panAvg.getCenterY()*4 - 265);
//			//System.out.println("x = " + relativeX + "  " + relativeY);
//	    	gc.fillOval(relativeX1, relativeY1, 2, 2);
//			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(Color.BLUE);
			//System.out.println("x = " + Constant.ViewBeiAvg);
			double relativeX = (Constant.ViewBeiAvg.getCenterX() - 53 + 0.2*i)*4 - (Constant.ViewBeiAvg.getCenterX()*4 - 265);
	    	double relativeY = (Constant.ViewBeiAvg.getCenterY() + 53 - 0.2*j)*4 - (Constant.ViewBeiAvg.getCenterY()*4 - 265);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
			
//			gc.save();
//			gc.setFill(Color.BLUE);
//			double relativeX1 = (Constant.ViewPanAvg.getCenterX() - 53 + 0.1*i)*4 - (Constant.ViewPanAvg.getCenterX()*4 - 265);
//	    	double relativeY1 = (Constant.ViewPanAvg.getCenterY() + 53 - 0.1*j)*4 - (Constant.ViewPanAvg.getCenterY()*4 - 265);
//			//System.out.println("x = " + relativeX + "  " + relativeY);
//	    	gc.fillOval(relativeX1, relativeY1, 2, 2);
//			gc.restore();
		}
		
	}
	
	private static void paintPoint1(GraphicsContext gc, String name, int i, int j) {
		//final int NUM_IMGS = 5;
		//final double opacity = 0.6;
        //System.out.println(opacity);
        //gc.setGlobalAlpha(opacity);
		if("shipan".equals(name)) {
			gc.save();
			gc.setFill(Color.BLUE);
			double relativeX = (Constant.panAvg.getCenterX() - 53 + 0.2*i)*4 - (Constant.panAvg.getCenterX()*4 - 265);
	    	double relativeY = (Constant.panAvg.getCenterY() + 53 - 0.2*j)*4 - (Constant.panAvg.getCenterY()*4 - 265);
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		else if("shibei".equals(name)) {
			gc.save();
			gc.setFill(Color.BLUE);
			double relativeX = (Constant.beiAvg.getCenterX() - 53 + 0.2*i)*4 - (Constant.beiAvg.getCenterX()*4 - 265);
	    	double relativeY = (Constant.beiAvg.getCenterY() + 53 - 0.2*j)*4 - (Constant.beiAvg.getCenterY()*4 - 265);
			//System.out.println("x = " + relativeX + "  " + relativeY);
	    	gc.fillOval(relativeX, relativeY, 2, 2);
			gc.restore();
		}
		
	}

}
