package com.cvte.util;

import java.util.ArrayList;
import java.util.List;

/** 
* @author: jan 
* @date: 2018年5月30日 上午12:38:40 
*/
public class ReadTest {

	public static void main(String[] args) {
		String path = "C:\\Users\\CVTE\\Desktop\\test4\\mark\\mark\\2018-05-28214154\\user4\\file\\imgAllLabel";
		List<String[]> list = ReadCSV.readCSV(path);
		List<String> arr = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++) {
			String name = list.get(i)[3];
			for(int j = 0; j < list.size(); j++) {
				if(i != j) {
					if(name.equals(list.get(j)[3])) {
						arr.add(name);
					}
				}
			}
		}
		
		System.out.println(arr.size());
		System.out.println(arr.get(0));
		System.out.println(arr.get(1));
	}
	
}
