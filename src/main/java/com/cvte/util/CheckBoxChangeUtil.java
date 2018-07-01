package com.cvte.util;

import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;
import com.cvte.entity.LineData;
import com.cvte.entity.Mask;

/** 
* @author: jan 
* @date: 2018年5月26日 下午9:26:51 
*/
public class CheckBoxChangeUtil {

	public static void repaint() {
		int len = Constant.CheckBoxList.size();
		
		//先remove再选中
		for(int i = 0; i < len; i++) {
			if(Constant.CheckBoxList.get(i).isSelected()) {
				Constant.LeftList.get(i).selectedProperty().removeListener(Constant.ListenerLeftList.get(i));
				Constant.LeftList.get(i).setSelected(true);
				Constant.LeftList.get(i).selectedProperty().addListener(Constant.ListenerLeftList.get(i));
			}
			else {
				Constant.LeftList.get(i).selectedProperty().removeListener(Constant.ListenerLeftList.get(i));
				Constant.LeftList.get(i).setSelected(false);
				Constant.LeftList.get(i).selectedProperty().addListener(Constant.ListenerLeftList.get(i));
			}
		}
		
		String user = "";
		for (int i = 0; i < len; i++) {
			if(Constant.CheckBoxList.get(i).isSelected()) {
				user = user + Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0] + ",";
			}
		}
		
		if(Constant.CurrTab == 0) {
			System.out.println("Curr Tab0  " + user);
			int size1 = 0;
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				if(Constant.CheckBoxList.get(i).isSelected()) {
					size1++;
				}
			}
			Constant.fenmu.setText("" + size1);
			int size2 = (int) (size1 * Constant.SlideValue/100);
			Constant.fenzi.setText("" + size2);
			Constant.AnalysisMix.setPanUser(user);
			Constant.AnalysisMix.getMask().setPanUser(user);
		}
		else if(Constant.CurrTab == 1) {
			System.out.println("Curr Tab1  " + user);
			int size1 = 0;
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				if(Constant.CheckBoxList.get(i).isSelected()) {
					size1++;
				}
			}
			Constant.fenmu.setText("" + size1);
			int size2 = (int) (size1 * Constant.SlideValue/100);
			Constant.fenzi.setText("" + size2);
			Constant.AnalysisMix.setBeiUser(user);
			Constant.AnalysisMix.getMask().setBeiUser(user);
		}
		else if(Constant.CurrTab == 2) {
			System.out.println("Curr Tab2  " + user);
			Constant.AnalysisMix.setCenterUser(user);
		}
		else if(Constant.CurrTab == 3) {
			System.out.println("Curr Tab3  " + user);
			Constant.AnalysisMix.setAllLabelUser(user);
		}
		
		if(Constant.radio.isSelected()) {  //判断重叠radio是否被选中
			LineData line = LabelUtil.getAvgLineData("amd");
	        Constant.centerAvg = line;
	        CircleData bei = LabelUtil.getAvgCircleDataNew("shibei");
	        Constant.beiAvg = bei;
	        CircleData pan = LabelUtil.getAvgCircleDataNew("shipan");
	        Constant.panAvg = pan;
			RadioChangeRepaintUtil.overLapRepaint();
		}
		else {
			if(Constant.maskradio.isSelected()) {
				Mask mask = Constant.AnalysisMix.getMask();
				int[][] curPanMask = MaskUtil.getCurMask("pan", mask);
				int[][] curBeiMask = MaskUtil.getCurMask("bei", mask);
				Constant.AnalysisMix.getMask().setCurPanMask(curPanMask);
				Constant.AnalysisMix.getMask().setCurBeiMask(curBeiMask);
				//repaint mask
				RadioChangeRepaintUtil.maskRepaint();
			}
			else {
				RadioChangeRepaintUtil.avgRepaintNew();
			}
		}
	}

	public static void checkboxChanged() {
		//System.out.println("改变了");
		int size = Constant.LeftList.size();
		//System.out.println("size = " + size);
		for(int i = 0; i < size; i++) {
			if(Constant.LeftList.get(i).isSelected()) {
				Constant.CheckBoxList.get(i).setSelected(true);
			}
			else {
				Constant.CheckBoxList.get(i).setSelected(false);
			}
		}
		//System.out.println(9999);
	}
	
	
}
