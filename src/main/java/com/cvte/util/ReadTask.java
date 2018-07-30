package com.cvte.util;

import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;

import javafx.concurrent.Task;

public class ReadTask implements Callable<String> {
	private String dir;
	private String name;
	private String soleName;
	private String imgName;
	private long start;

	public String call() throws Exception {
		System.out.println(this.getName() + " 启动时间 = " + (System.currentTimeMillis() - this.getStart()));
		String label = "";

	    // sole label路径
	    String labelPath = this.getDir() + "/" + this.getName() + "/file/" + this.getSoleName() + "/"
	    		+ this.getImgName().split("[.]")[0];
	    File fl = new File(labelPath);
	    if(fl.exists()) {
	    	List<String[]> list = ReadCSV.readCSV(labelPath);
			label = list.get(list.size() - 1)[7];
	    }else {
	    	System.out.println(this.getName() + " = 不进行sole label查询");
	    	 // 全部label路径(若sole label未检索到 ,则进行alllabel检索数据)
			String allLabelPath = this.getDir() + "/" + this.getName() + "/file/imgAllLabel";
			// 读取alllabel 数据 
			List<String[]> allList = ReadCSV.readCSV(allLabelPath);
		    for(String[] s : allList) {
		    	if(this.getImgName().equals(s[3])) {
		    		label = s[7];
		    	}
		    }
	    }
		System.out.println(start + "=" + (System.currentTimeMillis() - this.getStart()));
		return label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSoleName() {
		return soleName;
	}

	public void setSoleName(String soleName) {
		this.soleName = soleName;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

}
