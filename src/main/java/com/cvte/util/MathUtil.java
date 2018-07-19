package com.cvte.util;

import com.cvte.entity.CircleData;

/** 
* @author: jan 
* @date: 2018年5月29日 上午3:02:03 
*/
public class MathUtil {

	public static String getCenter(String left, String top, String angle,
			String radius, String scaleX, String scaleY, double ratio) {
		double sin = Math.sin(Math.toRadians(Double.parseDouble(angle)));
    	double cos = Math.cos(Math.toRadians(Double.parseDouble(angle)));
    	//System.out.println(sin + "=" + cos);
    	double a = Double.parseDouble(radius)*Double.parseDouble(scaleX)/ratio;
    	double b = Double.parseDouble(radius)*Double.parseDouble(scaleY)/ratio;
    	double x1 = Double.parseDouble(left)/ratio + a*cos - b*sin;
    	double y1 = Double.parseDouble(top)/ratio + a*sin + b*cos;
    	
		return "" + x1 + "," + y1;
	}
	
	// 纠正
	public static String getCenterNew(String left, String top, String angle,
			String radius, String scaleX, String scaleY, double ratio) {
		double sin = Math.sin(Math.toRadians(Double.parseDouble(angle)));
    	double cos = Math.cos(Math.toRadians(Double.parseDouble(angle)));
    	//System.out.println(sin + "=" + cos);
    	double a = Double.parseDouble(radius)*Double.parseDouble(scaleX)/ratio;
    	double b = Double.parseDouble(radius)*Double.parseDouble(scaleY)/ratio;
    	double x1 = Double.parseDouble(left)/ratio + a*cos + b*sin;
    	double y1 = Double.parseDouble(top)/ratio - a*sin + b*cos;
    	
		return "" + x1 + "," + y1;
	}

	public static Integer[][] getMask(CircleData circle) {
		//System.out.println("circle = " + circle);
		double sin1 = Math.sin(Math.toRadians(circle.getAngle()));
		double cos1 = Math.cos(Math.toRadians(circle.getAngle()));
		double a1 = circle.getRadius()*circle.getScaleX();
		double b1 = circle.getRadius()*circle.getScaleY();
		double p1 = circle.getLeft();
		double q1 = -circle.getTop();
		double m1 = (circle.getLeft() + a1);
		double n1 = (-circle.getTop() - b1);
		Integer[][] data = new Integer[530][530];
		for(int i = 0; i < 530; i++) {
			for(int j = 0; j < 530; j++) {
				//double k1 = i;
				//double k2 = j - 530;
				double k1 = circle.getCenterX() - 53 + 0.2*i;
				double k2 = -circle.getCenterY() - 53 + 0.2*j;
				//System.out.println(k1 + "=" + k2);
				double tmp1 = p1 + ((k1 - p1)*cos1 - (k2 - q1)*sin1);
				double tmp2 = q1 + ((k1 - p1)*sin1 + (k2 - q1)*cos1);
				
				double x1 = (tmp1 - m1)/a1;
				double y1 = (tmp2 - n1)/b1;
				//System.out.println("cal = " + x1*x1 + y1*y1);
				if(x1*x1 + y1*y1 <= 1.0) {
					data[i][j] = new Integer(1);
					//System.out.println("data = " + data[i][j].intValue());
				}
				else {
					data[i][j] = new Integer(0);
				}
			}
		}
		return data;
	}

	
	
}
