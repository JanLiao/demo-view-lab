package com.cvte.util;

import java.util.ArrayList;
import java.util.List;

import com.cvte.cons.Constant;
import com.cvte.entity.LabelColor;

/** 
* @author: jan 
* @date: 2018年5月25日 上午2:29:42 
*/
public class ColorUtil {

	public static void initColor() {
		LabelColor c2 = new LabelColor(0, 255, 255, "#00FFFF");
		LabelColor c1 = new LabelColor(0, 0, 255, "#0000FF");
		LabelColor c3 = new LabelColor(255, 0, 255, "#FF00FF");
		LabelColor c4 = new LabelColor(124, 252, 0, "#7CFC00");
		LabelColor c5 = new LabelColor(47, 79, 79, "#2F4F4F");
		LabelColor c6 = new LabelColor(238, 130, 238, "#EE82EE");
		LabelColor c7 = new LabelColor(107, 142, 35, "#6B8E23");
		LabelColor c8 = new LabelColor(0, 250, 154, "#00FA9A");
		LabelColor c9 = new LabelColor(0, 128, 0, "#008000");
		LabelColor c10 = new LabelColor(154, 205, 50, "#9ACD32");
		LabelColor c11 = new LabelColor(205, 92, 92, "#CD5C5C");
		LabelColor c12 = new LabelColor(199, 21, 133, "#C71585");
		LabelColor c13 = new LabelColor(57, 73, 171, "#3949AB");
		LabelColor c14 = new LabelColor(24, 128, 56, "#188038");
		LabelColor c15 = new LabelColor(51, 171, 160, "#33ABA0");
		Constant.ColorList.add(c1);
		Constant.ColorList.add(c2);
		Constant.ColorList.add(c3);
		Constant.ColorList.add(c4);
		Constant.ColorList.add(c5);
		Constant.ColorList.add(c6);
		Constant.ColorList.add(c7);
		Constant.ColorList.add(c8);
		Constant.ColorList.add(c9);
		Constant.ColorList.add(c10);
		Constant.ColorList.add(c11);
		Constant.ColorList.add(c12);
		Constant.ColorList.add(c13);
		Constant.ColorList.add(c14);
		Constant.ColorList.add(c15);
		
		List<Integer> list = ShuffleUtil.shuffle();
		List<LabelColor> colorList = new ArrayList<LabelColor>();
		for(int i =0; i < list.size(); i++) {
			colorList.add(Constant.ColorList.get(list.get(i)));
		}
		Constant.ColorList = colorList;
		System.out.println("shuffle color " + Constant.ColorList);
	}

}
