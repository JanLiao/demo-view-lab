package com.cvte.util;

import java.util.ArrayList;
import java.util.List;

import com.cvte.cons.Constant;

/** 
* @author: jan 
* @date: 2018年5月26日 下午7:48:41 
*/
public class ComboxChangeUtil {

	public static void reTextOverLap(String text) {
		Constant.AnalysisMix.setStandUser(text);
		
		List<Double> coverList = new ArrayList<Double>();
		if(Constant.CurrTab == 0) {
			coverList = CoverUtil.coverRatioPan(text);
			
			CoverUtil.refreshLabel(coverList);
		}
		else if(Constant.CurrTab == 1) {
			coverList = CoverUtil.coverRatioBei(text);
			
			CoverUtil.refreshLabel(coverList);
		}
		else if(Constant.CurrTab == 2) {
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				coverList.add(1.00);
			}
			
			CoverUtil.refreshLabel(coverList);
		}
		else if(Constant.CurrTab == 3) {
			for(int i = 0; i < Constant.CheckBoxList.size(); i++) {
				coverList.add(1.00);
			}
			
			CoverUtil.refreshLabel(coverList);
		}
	}

	public static void saveModel(String text) {
		Constant.AnalysisMix.setSaveModel(text);
	}
}
