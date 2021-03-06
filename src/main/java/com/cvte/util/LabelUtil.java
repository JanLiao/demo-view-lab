package com.cvte.util;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;
import com.cvte.entity.LineData;

/** 
* @author: jan 
* @date: 2018年5月25日 下午9:11:21 
*/
public class LabelUtil {
	
	public static CircleData getAvgNewCircleData(String name) {
		
		// 返回视盘视杯当前保存数据
		if("shipan".equals(name)) {			
			if("".equals(Constant.AnalysisMix.getPanUser()) || 
					Constant.AnalysisMix.getPanUser() == null) {			
				List<String> list = Constant.AnalysisMix.getAllLabelData();
				List<CircleData> circleList = new ArrayList<CircleData>();
				for(String s : list) {
					circleList.add(strToObj(s, name));
				}
				CircleData circle = avgCircle(circleList);
				circle.setName(name);
				return circle;
			}else {
				CircleData circle = getCurAvgData(name);
				circle.setName(name);
				return circle;
			}
		}else {
			if("".equals(Constant.AnalysisMix.getBeiUser()) || 
					Constant.AnalysisMix.getBeiUser() == null) {			
				List<String> list = Constant.AnalysisMix.getAllLabelData();
				List<CircleData> circleList = new ArrayList<CircleData>();
				for(String s : list) {
					circleList.add(strToObj(s, name));
				}		
				CircleData circle = avgCircle(circleList);
				circle.setName(name);
				return circle;
			}else {
				CircleData circle = getCurAvgData(name);
				circle.setName(name);
				return circle;
			}
		}
	}
	
	// 将list linedata转换成avg linedata
	public static LineData changeAvgLineData(List<LineData> lineList) {
		LineData line = new LineData();
        int size = lineList.size();
		
		double angle = 0;
		for(LineData data : lineList) {
			angle = angle + data.getAngle();
		}
		line.setAngle(angle/size);
		
		double height = 0;
		for(LineData data : lineList) {
			height += data.getHeight();
		}
		line.setHeight(height/size);
		
		double left = 0;
		for (LineData data : lineList) {
			left += data.getLeft();
		}
		line.setLeft(left/size);
		
		double opacity = 0;
		for(LineData data : lineList) {
			opacity += data.getOpacity();
		}
		line.setOpacity(opacity/size);
		
		double scaleX = 0;
		for(LineData data : lineList) {
			scaleX += data.getScaleX();
		}
		line.setScaleX(scaleX/size);
		
		double scaleY = 0;
		for(LineData data : lineList) {
			scaleY += data.getScaleY();
		}
		line.setScaleY(scaleY/size);
		
		double strokeWidth = 0;
		for(LineData data : lineList) {
			strokeWidth += data.getStrokeWidth();
		}
		line.setStrokeWidth(strokeWidth/size + 1);
		
		double top = 0;
		for(LineData data : lineList) {
			top += data.getTop();
		}
		line.setTop(top/size);
		
		double width = 0;
		for(LineData data : lineList) {
			width += data.getWidth();
		}
		line.setWidth(width/size);
		return line;
	}
	
	// 获取当前选中AMD
	public static LineData getCurAvgLineData(String name) {
		List<String> list = Constant.AnalysisMix.getAllLabelData();
		List<LineData> lineList = new ArrayList<LineData>();
		if("".equals(Constant.AnalysisMix.getCenterUser()) || 
				Constant.AnalysisMix.getCenterUser() == null) {			
			for(String s : list) {
				lineList.add(strToLine(s, name));
			}
		}else {
			int len = Constant.LeftList.size();
			String[] s = Constant.AnalysisMix.getCenterUser().split(",");
			for(int i =0; i < len; i++) {
				if (checkUser(Constant.LeftList.get(i).getText(), s)) {
					lineList.add(strToLine(list.get(i), name));
				}
			}
		}
		LineData line = changeAvgLineData(lineList);
		return line;
	}
	
	// 获取当前选中的视盘和视杯
	public static CircleData getCurAvgData(String name) {
		List<CircleData> circleList = new ArrayList<CircleData>();
		List<String> list = Constant.AnalysisMix.getAllLabelData();
		int len = Constant.LeftList.size();
		if("shipan".equals(name)) {
			System.out.println("pan = " + Constant.AnalysisMix.getPanUser());
			String[] s = Constant.AnalysisMix.getPanUser().split(",");
			for (int i = 0; i < len; i++) {
				System.out.println("current text = " + Constant.LeftList.get(i).getText());
				if (checkUser(Constant.LeftList.get(i).getText(), s)) {
					circleList.add(strToObj(list.get(i), name));
				}
			}
		}else if("shibei".equals(name)) {
			System.out.println("bei = " + Constant.AnalysisMix.getBeiUser());
			String[] s = Constant.AnalysisMix.getBeiUser().split(",");
			for (int i = 0; i < len; i++) {
				if (checkUser(Constant.LeftList.get(i).getText(), s)) {
					circleList.add(strToObj(list.get(i), name));
				}
			}
		}
		
		CircleData circle = avgCircle(circleList);
		circle.setName(name);
		return circle;
	}
	
	public static boolean checkUser(String user, String[] s) {
		boolean flag = false;
		for(String st : s) {
			if(user.equals(st)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	//初始化circle   视盘、视杯
	public static CircleData getAvgCircleData(String name) {
		List<String> list = Constant.AnalysisMix.getAllLabelData();
		List<CircleData> circleList = new ArrayList<CircleData>();
		//long start0 = System.currentTimeMillis();
		for(String s : list) {
			circleList.add(strToObj(s, name));
		}
		//long end1 = System.currentTimeMillis();
		//System.out.println("circleList 时间 = " + (end1 - start0));
		
		CircleData circle = avgCircle(circleList);
		//long end2 = System.currentTimeMillis();
		//System.out.println("avg时间 = " + (end2 - end1));
		circle.setName(name);
		return circle;
	}
	
	public static CircleData getAvgCircleDataNew(String name) {
		List<String> list = Constant.AnalysisMix.getAllLabelData();
		List<CircleData> circleList = new ArrayList<CircleData>();
		int len = Constant.CheckBoxList.size();
		StringBuffer tmp = new StringBuffer("");
		for (int i = 0; i < len; i++) {
			if (Constant.CheckBoxList.get(i).isSelected()) {
				tmp.append(Constant.LeftList.get(i).getText() + ",");
				System.out.println(Constant.LeftList.get(i).getText() + 
						" = " + strToObj(list.get(i), name));
				circleList.add(strToObj(list.get(i), name));
			}
		}
		System.out.println("tmp = " + tmp);
//		for(String s : list) {
//			circleList.add(strToObj(s, name));
//		}
		
		CircleData circle = avgCircle(circleList);
		System.out.println("avg circle new = " + circle);
		circle.setName(name);
		return circle;
	}

	private static CircleData avgCircle(List<CircleData> circleList) {
		CircleData circle = new CircleData();
		int size = circleList.size();
		//System.out.println("size 长度 = " + size);
		
		double centerX = 0;
		double centerY = 0;
		double height = 0;
		double left = 0;
		double opacity = 0;
		double radius = 0;
		double scaleX = 0;
		double scaleY = 0;
		double top = 0;
		double width = 0;
		double strokeWidth = 0;
		double rotateAngle = 0;
		for(CircleData data : circleList) {
			centerX += data.getCenterX();
			centerY += data.getCenterY();
			height += data.getHeight();
			opacity += data.getOpacity();
			radius += data.getRadius();
			scaleX += data.getScaleX();
			scaleY += data.getScaleY();
			strokeWidth += data.getStrokeWidth();
			width += data.getWidth();
			rotateAngle += data.getRotateAngle();
		}
		circle.setCenterX(centerX/size);
		circle.setCenterY(centerY/size);
		circle.setHeight(height/size);
		circle.setOpacity(opacity/size);
		circle.setRadius(radius/size);
		circle.setScaleX(scaleX/size);
		circle.setScaleY(scaleY/size);
		circle.setStrokeWidth(strokeWidth/size);
		circle.setWidth(width/size);
		circle.setRotateAngle(rotateAngle/size);
		
		String data = getLeftTopAngle(circle);
		circle.setLeft(Double.parseDouble(data.split(",")[0]));
		circle.setTop(Double.parseDouble(data.split(",")[1]));
		circle.setAngle(Double.parseDouble(data.split(",")[2]));
		
		if(circleList.size() != 0) {
			circle.setStroke(circleList.get(0).getStroke());
		}
		//System.out.println("avg circle = " + circle);
		return circle;
	}
	
	public static String getLeftTopAngle(CircleData circle) {
		double angle1 = circle.getRotateAngle();
		double cx = circle.getCenterX();
		double cy = circle.getCenterY();
		double a = circle.getRadius()*circle.getScaleX();
		double b = circle.getRadius()*circle.getScaleY();
		double angle2 = Math.atan((a/b))*180/Math.PI;
		double px = 0;
		double py = 0;
		double angle = 0;
		if(angle1 > 0) {  // 验证通过
			double sin = Math.sin(Math.toRadians(angle1 - angle2));
			double cos = Math.cos(Math.toRadians(angle1 - angle2));
			px = cx - Math.sqrt((a*a + b*b)) * cos;
			py = cy - Math.sqrt((a*a + b*b)) * sin;
			angle = (360 - (90 - angle1));
		}else {  // 验证通过
			double sin = Math.sin(Math.toRadians(-angle1 + angle2));
			double cos = Math.cos(Math.toRadians(-angle1 + angle2));
			px = cx - Math.sqrt((a*a + b*b)) * cos;
			py = cy + Math.sqrt((a*a + b*b)) * sin;
			angle = (90 + (180 + angle1));
		}
		return "" + px + "," + py + "," + angle;
	}

	private static double changeAngleNew(List<Double> list) {
		List<Double> list1 = new ArrayList<Double>();
		List<Double> list2 = new ArrayList<Double>();
		for(Double d : list) {
			if(d > 0 && d <= 180) {
				list1.add(d);
			}else {
				if(d == 0) {
					d = (double) 360;
				}
				list2.add(d);
			}
		}
		double angle = 0;
		if(list1.size() > list2.size()) {
			for(Double d : list1) {
				angle += d;
			}
			angle = angle / list1.size();
			angle = angle * list.size();
		}else {
			for(Double d : list2) {
				angle += d;
			}
			angle = angle / list2.size();
			angle = angle * list.size();
		}
		return angle;
	}

	private static double changeAngle(List<Double> list) {
		double min = list.get(0);
		double max = list.get(0);
		StringBuffer tmp = new StringBuffer("");
		for(Double d : list) {
			tmp.append(d + ",");
			if(d < min) {
				min = d;
			}
			if(d > max) {
				max = d;
			}
		}
		System.out.println("tmp = " + tmp);
		if(max - min >= 180) {
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i) < 180.00) {
					list.set(i, list.get(i) + 360);
				}
			}
		}
		double angle = 0;
		for(Double d : list) {
			angle += d;
		}
		System.out.println("tmp angle = " + angle);
		return angle;
	}

	private static CircleData strToObj(String s, String name) {
		String label = s.split("=")[1];
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
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
		double w = Double.parseDouble(canvasObj.getString("w"));
		double h = Double.parseDouble(canvasObj.getString("h"));
		double ratio = 0.0;
		//System.out.println(canvasObj.getString("w") + "=" + canvasObj.getString("h"));
		if(w >= h) {
			ratio = h / 530;
		}
		else {
			ratio = w / 530;
		}
		CircleData circle = new CircleData();
		circle.setAngle(Double.parseDouble(obj.getString("angle")));
		circle.setHeight(Double.parseDouble(obj.getString("height")));
		circle.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
		circle.setName(name);
		circle.setOpacity(Double.parseDouble(obj.getString("opacity")));
		circle.setRadius(Double.parseDouble(obj.getString("radius")));
		circle.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		circle.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		circle.setStroke(obj.getString("stroke"));
		circle.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		circle.setTop(Double.parseDouble(obj.getString("top"))/ratio);
		circle.setWidth(Double.parseDouble(obj.getString("width")));
		String centerXY = MathUtil.getCenter(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setCenterX(Double.parseDouble(centerXY.split(",")[0]));
		circle.setCenterY(Double.parseDouble(centerXY.split(",")[1]));
		
		// 平均准确计算升级
		String point = MathUtil.getPoint(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setPointX(Double.parseDouble(point.split(",")[0]));
		circle.setPointY(Double.parseDouble(point.split(",")[1]));
		double rotateAngle = MathUtil.getAngle(centerXY, point);
		circle.setRotateAngle(rotateAngle);
		//System.out.println("circle = " + circle);
		return circle;
	}
	
	//该方法同上
	public static LineData getAvgLineDataNew(String name) {
		List<String> list = Constant.AnalysisMix.getAllLabelData();
		List<LineData> lineList = new ArrayList<LineData>();
		int len = Constant.CheckBoxList.size();
		for (int i = 0; i < len; i++) {
			if (Constant.CheckBoxList.get(i).isSelected()) {
				lineList.add(strToLine(list.get(i), name));
			}
		}
//		for(String s : list) {
//			lineList.add(strToLine(s, name));
//		}
		LineData line = new LineData();
        int size = lineList.size();
		
		double angle = 0;
		for(LineData data : lineList) {
			angle = angle + data.getAngle();
		}
		line.setAngle(angle/size);
		
		double height = 0;
		for(LineData data : lineList) {
			height += data.getHeight();
		}
		line.setHeight(height/size);
		
		double left = 0;
		for (LineData data : lineList) {
			left += data.getLeft();
		}
		line.setLeft(left/size);
		
		double opacity = 0;
		for(LineData data : lineList) {
			opacity += data.getOpacity();
		}
		line.setOpacity(opacity/size);
		
		double scaleX = 0;
		for(LineData data : lineList) {
			scaleX += data.getScaleX();
		}
		line.setScaleX(scaleX/size);
		
		double scaleY = 0;
		for(LineData data : lineList) {
			scaleY += data.getScaleY();
		}
		line.setScaleY(scaleY/size);
		
		double strokeWidth = 0;
		for(LineData data : lineList) {
			strokeWidth += data.getStrokeWidth();
		}
		line.setStrokeWidth(strokeWidth/size + 1);
		
		double top = 0;
		for(LineData data : lineList) {
			top += data.getTop();
		}
		line.setTop(top/size);
		
		double width = 0;
		for(LineData data : lineList) {
			width += data.getWidth();
		}
		line.setWidth(width/size);
		return line;
	}

	public static LineData getAvgLineData(String name) {
		List<String> list = Constant.AnalysisMix.getAllLabelData();
		List<LineData> lineList = new ArrayList<LineData>();
		for(String s : list) {
			lineList.add(strToLine(s, name));
		}
		LineData line = new LineData();
        int size = lineList.size();
		
		double angle = 0;
		for(LineData data : lineList) {
			angle = angle + data.getAngle();
		}
		line.setAngle(angle/size);
		
		double height = 0;
		for(LineData data : lineList) {
			height += data.getHeight();
		}
		line.setHeight(height/size);
		
		double left = 0;
		for (LineData data : lineList) {
			left += data.getLeft();
		}
		line.setLeft(left/size);
		
		double opacity = 0;
		for(LineData data : lineList) {
			opacity += data.getOpacity();
		}
		line.setOpacity(opacity/size);
		
		double scaleX = 0;
		for(LineData data : lineList) {
			scaleX += data.getScaleX();
		}
		line.setScaleX(scaleX/size);
		
		double scaleY = 0;
		for(LineData data : lineList) {
			scaleY += data.getScaleY();
		}
		line.setScaleY(scaleY/size);
		
		double strokeWidth = 0;
		for(LineData data : lineList) {
			strokeWidth += data.getStrokeWidth();
		}
		line.setStrokeWidth(strokeWidth/size + 1);
		
		double top = 0;
		for(LineData data : lineList) {
			top += data.getTop();
		}
		line.setTop(top/size);
		
		double width = 0;
		for(LineData data : lineList) {
			width += data.getWidth();
		}
		line.setWidth(width/size);
		return line;
	}

	private static LineData strToLine(String s, String name) {
		String label = s.split("=")[1];
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
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
		double w = Double.parseDouble(canvasObj.getString("w"));
		double h = Double.parseDouble(canvasObj.getString("h"));
		double ratio = 0.0;
		//System.out.println(canvasObj.getString("w") + "=" + canvasObj.getString("h"));
		if(w >= h) {
			ratio = h / 530;
		}
		else {
			ratio = w / 530;
		}
		LineData line = new LineData();
		line.setAngle(Double.parseDouble(obj.getString("angle")));
		line.setHeight(Double.parseDouble(obj.getString("height")));
		line.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
		line.setOpacity(Double.parseDouble(obj.getString("opacity")));
		line.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		line.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		line.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")) + 1);
		line.setTop(Double.parseDouble(obj.getString("top"))/ratio);
		line.setWidth(Double.parseDouble(obj.getString("width")));
		//System.out.println("sole line = " + line);
		return line;
	}
	
	public static CircleData getSoleLabelDataView(int offset) {
		List<String> list = Constant.AnalysisView.getAllLabelData();
		String label = list.get(offset).split("=")[1];
		String name = "shipan";
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
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
		double w = Double.parseDouble(canvasObj.getString("w"));
		double h = Double.parseDouble(canvasObj.getString("h"));
		double ratio = 0.0;
		//System.out.println(canvasObj.getString("w") + "=" + canvasObj.getString("h"));
		if(w >= h) {
			ratio = h / 530;
		}
		else {
			ratio = w / 530;
		}
		CircleData circle = new CircleData();
		circle.setAngle(Double.parseDouble(obj.getString("angle")));
		circle.setHeight(Double.parseDouble(obj.getString("height")));
		circle.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
		circle.setName(name);
		circle.setOpacity(Double.parseDouble(obj.getString("opacity")));
		circle.setRadius(Double.parseDouble(obj.getString("radius")));
		circle.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		circle.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		circle.setStroke(obj.getString("stroke"));
		circle.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		circle.setTop(Double.parseDouble(obj.getString("top"))/ratio);
		circle.setWidth(Double.parseDouble(obj.getString("width")));
		String centerXY = MathUtil.getCenter(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setCenterX(Double.parseDouble(centerXY.split(",")[0]));
		circle.setCenterY(Double.parseDouble(centerXY.split(",")[1]));
		
		String point = MathUtil.getPoint(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setPointX(Double.parseDouble(point.split(",")[0]));
		circle.setPointY(Double.parseDouble(point.split(",")[1]));
		double rotateAngle = MathUtil.getAngle(centerXY, point);
		circle.setRotateAngle(rotateAngle);
		return circle;
	}

	public static CircleData getSoleLabelData(int offset) {
		List<String> list = Constant.AnalysisMix.getAllLabelData();
		String label = list.get(offset).split("=")[1];
		String name = "shipan";
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
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
		double w = Double.parseDouble(canvasObj.getString("w"));
		double h = Double.parseDouble(canvasObj.getString("h"));
		double ratio = 0.0;
		//System.out.println(canvasObj.getString("w") + "=" + canvasObj.getString("h"));
		if(w >= h) {
			ratio = h / 530;
		}
		else {
			ratio = w / 530;
		}
		CircleData circle = new CircleData();
		circle.setAngle(Double.parseDouble(obj.getString("angle")));
		circle.setHeight(Double.parseDouble(obj.getString("height")));
		circle.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
		circle.setName(name);
		circle.setOpacity(Double.parseDouble(obj.getString("opacity")));
		circle.setRadius(Double.parseDouble(obj.getString("radius")));
		circle.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		circle.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		circle.setStroke(obj.getString("stroke"));
		circle.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		circle.setTop(Double.parseDouble(obj.getString("top"))/ratio);
		circle.setWidth(Double.parseDouble(obj.getString("width")));
		String centerXY = MathUtil.getCenter(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setCenterX(Double.parseDouble(centerXY.split(",")[0]));
		circle.setCenterY(Double.parseDouble(centerXY.split(",")[1]));
		
		String point = MathUtil.getPoint(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setPointX(Double.parseDouble(point.split(",")[0]));
		circle.setPointY(Double.parseDouble(point.split(",")[1]));
		double rotateAngle = MathUtil.getAngle(centerXY, point);
		circle.setRotateAngle(rotateAngle);
		return circle;
	}

	public static CircleData getSoleLabelData(int offset, String name) {
		List<String> list = Constant.AnalysisMix.getAllLabelData();
		String label = list.get(offset).split("=")[1];
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
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
		double w = Double.parseDouble(canvasObj.getString("w"));
		double h = Double.parseDouble(canvasObj.getString("h"));
		double ratio = 0.0;
		//System.out.println(canvasObj.getString("w") + "=" + canvasObj.getString("h"));
		if(w >= h) {
			ratio = h / 530;
		}
		else {
			ratio = w / 530;
		}
		CircleData circle = new CircleData();
		circle.setAngle(Double.parseDouble(obj.getString("angle")));
		circle.setHeight(Double.parseDouble(obj.getString("height")));
		circle.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
		circle.setName(name);
		circle.setOpacity(Double.parseDouble(obj.getString("opacity")));
		circle.setRadius(Double.parseDouble(obj.getString("radius")));
		circle.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		circle.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		circle.setStroke(obj.getString("stroke"));
		circle.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		circle.setTop(Double.parseDouble(obj.getString("top"))/ratio);
		circle.setWidth(Double.parseDouble(obj.getString("width")));
		String centerXY = MathUtil.getCenter(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setCenterX(Double.parseDouble(centerXY.split(",")[0]));
		circle.setCenterY(Double.parseDouble(centerXY.split(",")[1]));
		
		String point = MathUtil.getPoint(obj.getString("left"), obj.getString("top"),
				obj.getString("angle"), obj.getString("radius"), obj.getString("scaleX"),
				obj.getString("scaleY"), ratio);
		circle.setPointX(Double.parseDouble(point.split(",")[0]));
		circle.setPointY(Double.parseDouble(point.split(",")[1]));
		double rotateAngle = MathUtil.getAngle(centerXY, point);
		circle.setRotateAngle(rotateAngle);
		return circle;
	}

	public static LineData getSoleLineData(int offset) {
		List<String> list = Constant.AnalysisMix.getAllLabelData();
		String label = list.get(offset).split("=")[1];
		JSONObject json = JSON.parseObject(label);
		JSONArray arr = json.getJSONArray("label_data");
		JSONObject obj = null;
		String name = "amd";
		for(int i = 0; i < 3; i++) {
			JSONObject tmp = arr.getJSONObject(i);
			if(name.equals(tmp.getString("name"))) {
				obj = JSON.parseObject(tmp.getString("data"));
				break;
			}
		}
		String canvas = json.getString("canvas_data");
		JSONObject canvasObj = JSON.parseObject(canvas);
		double w = Double.parseDouble(canvasObj.getString("w"));
		double h = Double.parseDouble(canvasObj.getString("h"));
		double ratio = 0.0;
		//System.out.println(canvasObj.getString("w") + "=" + canvasObj.getString("h"));
		if(w >= h) {
			ratio = h / 530;
		}
		else {
			ratio = w / 530;
		}
		//JSONArray arr1 = obj.getJSONArray("objects");
		//JSONObject obj1 = arr1.getJSONObject(0);
		//JSONObject obj2 = arr1.getJSONObject(1);
		LineData line = new LineData();
		line.setAngle(Double.parseDouble(obj.getString("angle")));
		line.setHeight(Double.parseDouble(obj.getString("height")));
		line.setLeft(Double.parseDouble(obj.getString("left"))/ratio);
				//+ Double.parseDouble(obj1.getString("left")) + 
				//Double.parseDouble(obj2.getString("left")));
		line.setOpacity(Double.parseDouble(obj.getString("opacity")));
		line.setScaleX(Double.parseDouble(obj.getString("scaleX"))/ratio);
		line.setScaleY(Double.parseDouble(obj.getString("scaleY"))/ratio);
		line.setStrokeWidth(Double.parseDouble(obj.getString("strokeWidth")));
		line.setTop(Double.parseDouble(obj.getString("top"))/ratio);
				//+ Double.parseDouble(obj1.getString("top")) + 
				//Double.parseDouble(obj2.getString("top")));
		line.setWidth(Double.parseDouble(obj.getString("width")));
		System.out.println("sole line = " + line);
		return line;
	}

}
