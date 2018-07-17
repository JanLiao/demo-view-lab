package com.cvte.util;

import com.cvte.cons.Constant;
import com.cvte.entity.Mask;

/** 
* @author: jan 
* @date: 2018年5月29日 上午11:14:33 
*/
public class SlideChangeUtil {

	public static void repaint(double percent) {
		System.out.println("slide first = " + Constant.SlideValue + "=" + Constant.beiSlideValue);
		if(Constant.CurrTab == 0) {
			System.out.println("slide 当前tab = 0");
			Constant.SlideValue = percent;
		}
		else if(Constant.CurrTab == 1) {
			System.out.println("slide 当前tab = 1");
			Constant.beiSlideValue = percent;
		}
		
		System.out.println("slide pre = " + Constant.SlideValue + "=" + Constant.beiSlideValue);
		
		if(Constant.radio.isSelected()) {  //判断重叠radio是否被选中
			System.out.println("nothing");
		}
		else {
			if(Constant.maskradio.isSelected()) {
				readyPaint(percent);
			}
			else {
				System.out.println("also nothing");
			}
		}
		System.out.println("slide after = " + Constant.SlideValue + "=" + Constant.beiSlideValue);
	}

	private static void readyPaint(double percent) {
		if(Constant.CurrTab == 0) {
			System.out.println("当前tab视盘");
			//Constant.SlideValue = percent;
			System.out.println("pan slide percent = " + percent);
			int len = 0;
//			String user = "";
//			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
//				if(Constant.CheckBoxList.get(i).isSelected()) {
//					len++;
//					user = user + Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0] + ",";
//				}
//			}
//			Constant.AnalysisMix.setPanUser(user);
			if(Constant.AnalysisMix.getPanUser() == null || 
					"".equals(Constant.AnalysisMix.getPanUser())) {
				System.out.println("pan 长度为0");
			}else {
				len = Constant.AnalysisMix.getPanUser().split(",").length;
			}
			Constant.fenmu.setText("" + len);
			int len1 = (int) (len * percent/100);
			Constant.fenzi.setText("" + len1);
			
//			Mask mask = Constant.AnalysisMix.getMask();
//			int[][] curPanMask = MaskUtil.getCurMask("pan", mask);
//			int[][] curBeiMask = MaskUtil.getCurMask("bei", mask);
//			Constant.AnalysisMix.getMask().setCurPanMask(curPanMask);
//			Constant.AnalysisMix.getMask().setCurBeiMask(curBeiMask);
			RadioChangeRepaintUtil.maskRepaint();
		}
		else if(Constant.CurrTab == 1) {
			System.out.println("当前tab视杯");
			//Constant.beiSlideValue = percent;
			System.out.println("bei slide percent = " + percent);
			int len = 0;
//			String user = "";
//			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
//				if(Constant.CheckBoxList.get(i).isSelected()) {
//					len++;
//					user = user + Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0] + ",";
//				}
//			}
//			System.out.println("bei user = " + user);
//			Constant.AnalysisMix.setBeiUser(user);
			if(Constant.AnalysisMix.getBeiUser() == null || 
					"".equals(Constant.AnalysisMix.getBeiUser())) {
				System.out.println("bei 长度为0");
			}else {
				len = Constant.AnalysisMix.getBeiUser().split(",").length;
			}
			Constant.fenmu.setText("" + len);
			int len1 = (int) (len * percent/100);
			Constant.fenzi.setText("" + len1);
			
//			Mask mask = Constant.AnalysisMix.getMask();
//			int[][] curPanMask = MaskUtil.getCurMask("pan", mask);
//			int[][] curBeiMask = MaskUtil.getCurMask("bei", mask);
//			Constant.AnalysisMix.getMask().setCurPanMask(curPanMask);
//			Constant.AnalysisMix.getMask().setCurBeiMask(curBeiMask);
			RadioChangeRepaintUtil.maskRepaint();
		}
		else {
			System.out.println("nothing to paint");
		}
	}

}
