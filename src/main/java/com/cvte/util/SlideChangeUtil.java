package com.cvte.util;

import com.cvte.cons.Constant;
import com.cvte.entity.Mask;

/** 
* @author: jan 
* @date: 2018年5月29日 上午11:14:33 
*/
public class SlideChangeUtil {

	public static void repaint(double percent) {
		if(Constant.radio.isSelected()) {  //判断重叠radio是否被选中
			if(Constant.CurrTab == 0) {
				Constant.SlideValue = percent;
			}
			else if(Constant.CurrTab == 1) {
				Constant.beiSlideValue = percent;
			}
			System.out.println("nothing");
		}
		else {
			if(Constant.CurrTab == 0) {
				Constant.SlideValue = percent;
			}
			else if(Constant.CurrTab == 1) {
				Constant.beiSlideValue = percent;
			}
			if(Constant.maskradio.isSelected()) {
				readyPaint(percent);
			}
			else {
				System.out.println("also nothing");
			}
		}
	}

	private static void readyPaint(double percent) {
		if(Constant.CurrTab == 0) {
			System.out.println("当前tab视盘");
			Constant.SlideValue = percent;
			System.out.println("pan slide percent = " + percent);
			int len = 0;
			String user = "";
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				if(Constant.CheckBoxList.get(i).isSelected()) {
					len++;
					user = user + Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0] + ",";
				}
			}
			Constant.AnalysisMix.setPanUser(user);
			Constant.fenmu.setText("" + len);
			int len1 = (int) (len * Constant.SlideValue/100);
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
			Constant.beiSlideValue = percent;
			System.out.println("bei slide percent = " + percent);
			int len = 0;
			String user = "";
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				if(Constant.CheckBoxList.get(i).isSelected()) {
					len++;
					user = user + Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0] + ",";
				}
			}
			System.out.println("bei user = " + user);
			Constant.AnalysisMix.setBeiUser(user);
			Constant.fenmu.setText("" + len);
			int len1 = (int) (len * Constant.beiSlideValue/100);
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
