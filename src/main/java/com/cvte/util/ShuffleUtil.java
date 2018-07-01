package com.cvte.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.cvte.cons.Constant;

/** 
* @author: jan 
* @date: 2018年5月25日 上午2:50:38 
*/
public class ShuffleUtil {

	public static List<Integer> shuffle(){
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < Constant.ColorList.size(); i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		return list;
	}

	public static List<String> shuffleLabel(List<String> allLabel) {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0; i < allLabel.size(); i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		List<String> labelList = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++) {
			labelList.add(allLabel.get(list.get(i)));
		}
		return labelList;
	}
	
	public static void main (String[] args) {
		List<String> labelList = new ArrayList<String>();
		labelList.add("user1=1");
		labelList.add("user2=2");
		labelList.add("user3=3");
		labelList.add("user4=4");
		labelList.add("user5=5");
		labelList.add("user6=6");
		labelList.add("user7=7");
		labelList.add("user8=8");
		labelList.add("user9=9");
		System.out.println(labelList);
		System.out.println(ShuffleUtil.shuffleLabel(labelList));;
	}
	
}
