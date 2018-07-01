package com.cvte.util;

import java.util.ArrayList;
import java.util.List;

import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;

/** 
* @author: jan 
* @date: 2018年5月27日 下午3:12:05 
*/
public class CoverViewUtil {

	public static void refreshLabel(List<Double> coverList) {
		for(int i = 0; i < coverList.size(); i++) {
			Constant.LabelList.get(i).setText("覆盖率: " + coverList.get(i));
		}
	}

	public static List<Double> coverRatioPan(String name) {
		List<Double> list = new ArrayList<Double>();
		if("".equals(name) || name == null) {
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				list.add(1.00);
			}
			return list;
		}
		else {
			//List<Double> list = new ArrayList<Double>();
			//System.out.println("name == " + name);
			int commonLabel = 0;
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				//System.out.println("name -=-= " + Constant.AnalysisMix.getAllLabelData().get(i).split(",")[0]);
				if(name.equals(Constant.AnalysisView.getAllLabelData().get(i).split("=")[0])) {
					commonLabel = i;
				}
			}
			
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				//0代表视盘
				double cover = calcuteCover(commonLabel, i, 0);
				list.add(cover);
//				if(name.equals(Constant.AnalysisMix.getAllLabelData().get(i).split(",")[0])) {
//					list.add(1.00);
//				}
//				else {
//				}
			}
			return list;
		}
	}

	public static List<Double> coverRatioBei(String name) {
		List<Double> list = new ArrayList<Double>();
		if("".equals(name) || name == null) {
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				list.add(1.00);
			}
			return list;
		}
		else {
			//List<Double> list = new ArrayList<Double>();
			//System.out.println("name == " + name);
			int commonLabel = 0;
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				//System.out.println("name -=-= " + Constant.AnalysisMix.getAllLabelData().get(i).split(",")[0]);
				if(name.equals(Constant.AnalysisView.getAllLabelData().get(i).split("=")[0])) {
					commonLabel = i;
				}
			}
			
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				//0代表视盘
				double cover = calcuteCover(commonLabel, i, 1);
				list.add(cover);
//				if(name.equals(Constant.AnalysisMix.getAllLabelData().get(i).split(",")[0])) {
//					list.add(1.00);
//				}
//				else {
//				}
			}
			return list;
		}
	}

	private static double calcuteCover(int commonLabel, int label, int flag) {
		double cover = 0;
		if(flag == 0) {
			System.out.println("计算视盘cover");
			CircleData solepan1 = LabelViewUtil.getSoleLabelData(commonLabel, "shipan");
			CircleData solepan2 = LabelViewUtil.getSoleLabelData(label, "shipan");
			cover = calCover(solepan1, solepan2);
		}
		else if(flag == 1) {
			System.out.println("计算视杯cover");
			CircleData solebei1 = LabelViewUtil.getSoleLabelData(commonLabel, "shibei");
			CircleData solebei2 = LabelViewUtil.getSoleLabelData(label, "shibei");
			cover = calCover(solebei1, solebei2);
		}
		return cover;
	}

	private static double calCover(CircleData sole1, CircleData sole2) {
//		System.out.println("sole1 = " + sole1);
//		System.out.println("sole2 = " + sole2);
//		double radius1 = sole1.getRadius();
//		double left1 = sole1.getLeft();
//		double top1 = sole1.getTop();
//		double angle1 = sole1.getAngle();
//		double scaleX1 = sole1.getScaleX();
//		double scaleY1 = sole1.getScaleY();
//		
//		double radius2 = sole2.getRadius();
//		double left2 = sole2.getLeft();
//		double top2 = sole2.getTop();
//		double angle2 = sole2.getAngle();
//		double scaleX2 = sole2.getScaleX();
//		double scaleY2 = sole2.getScaleY();
//		
//		double a1 = radius1*scaleX1;
//		double b1 = radius1*scaleY1;
//		double a2 = radius2*scaleX2;
//		double b2 = radius2*scaleY2;
//		double x1 = left1 + radius1*scaleX1;
//		double y1 = 530 - top1 - radius1*scaleY1;
//		double x2 = left2 + radius2*scaleX2;
//		double y2 = 530 - top2 - radius2*scaleY2;
//		
//		int len3 = 0;
//		int len4 = 0;
//		double sin1 = Math.sin(Math.toRadians(angle1));
//		double cos1 = Math.cos(Math.toRadians(angle1));
//		double sin2 = Math.sin(Math.toRadians(angle2));
//		double cos2 = Math.cos(Math.toRadians(angle2));
//		//斜椭圆
//		for(double i = 0; i < 530; i = i+0.1) {
//			for(double j = 0; j < 530; j = j + 0.1) {
//				double tmp1 = (i*cos1 - j*sin1 - x1)*(i*cos1 - j*sin1 - x1)/(a1*a1) + 
//						(i*sin1 + j*cos1 - y1)*(i*sin1 + j*cos1 - y1)/(b1*b1);
//				double tmp2 = (i*cos2 - j*sin2 - x2)*(i*cos2 - j*sin2 - x2)/(a2*a2) + 
//						(i*sin2 + j*cos2 - y2)*(i*sin2 + j*cos2 - y2)/(b2*b2);
//				if(tmp1 <= 1 || tmp2 <= 1) {
//					len3++;
//				}
//				if(tmp1 <= 1 && tmp2 <= 1) {
//					len4++;
//				}
//			}
//		}
		
		double sin1 = Math.sin(Math.toRadians(sole1.getAngle()));
		double cos1 = Math.cos(Math.toRadians(sole1.getAngle()));
		double a1 = sole1.getRadius() * sole1.getScaleX();
		double b1 = sole1.getRadius() * sole1.getScaleY();
		double p1 = sole1.getLeft();
		double q1 = -sole1.getTop();
		double m1 = sole1.getLeft() + a1;
		double n1 = -sole1.getTop() - b1;

		double sin2 = Math.sin(Math.toRadians(sole2.getAngle()));
		double cos2 = Math.cos(Math.toRadians(sole2.getAngle()));
		double a2 = sole2.getRadius() * sole2.getScaleX();
		double b2 = sole2.getRadius() * sole2.getScaleY();
		double p2 = sole2.getLeft();
		double q2 = -sole2.getTop();
		double m2 = sole2.getLeft() + a2;
		double n2 = -sole2.getTop() - b2;
		int len1 = 0;
		int len2 = 0;
		// int len3 = 0;
		for (double i = p1; i < 530; i = i + 0.25) {
			for (double j = q1 - 530; j < 530; j = j + 0.25) {

				double tmp1 = p1 + ((i - p1) * cos1 - (j - q1) * sin1);
				double tmp2 = q1 + ((i - p1) * sin1 + (j - q1) * cos1);

				double x1 = (tmp1 - m1) / a1;
				double y1 = (tmp2 - n1) / b1;

				tmp1 = p2 + ((i - p2) * cos2 - (j - q2) * sin2);
				tmp2 = q2 + ((i - p2) * sin2 + (j - q2) * cos2);

				double x2 = (tmp1 - m2) / a2;
				double y2 = (tmp2 - n2) / b2;

				if (x1 * x1 + y1 * y1 <= 1 || x2 * x2 + y2 * y2 <= 1) {
					len1++;
				}
				if (x1 * x1 + y1 * y1 <= 1 && x2 * x2 + y2 * y2 <= 1) {
					len2++;
				}
			}
		}

		double cover = (double) len2 / len1;
		cover = (double) Math.round(cover * 100) / 100;
		// System.out.println(len3 + "=" + len4);
		// System.out.println((double)len4/len3);
		return cover;
	}
}
