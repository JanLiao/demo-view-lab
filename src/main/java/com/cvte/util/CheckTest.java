package com.cvte.util;

import java.util.ArrayList;
import java.util.List;

/** 
* @author: jan 
* @date: 2018年5月28日 下午10:51:26 
*/
public class CheckTest {

	public static void main(String[] args) {
		String path = "F:\\eclipse-workspace\\LabelView\\admin\\mixlabel\\avgAllImg";
		List<String[]> list = ReadCSV.readCSV(path);
		List<String> listS = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < list.size(); j++) {
				if(i == j) {
					System.out.println(list.get(i)[1]);
				}
				else {
					if(list.get(i)[1].equals(list.get(j)[1])) {
						listS.add(list.get(i)[1]);
					}
				}
				
			}
		}
		
		System.out.println(listS.size());
		for(String s : listS) {
			System.out.println(s);
		}
	}
	
}
