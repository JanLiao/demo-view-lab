package com.cvte.entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;

/** 
* @author: jan 
* @date: 2018年5月21日 下午10:21:23 
*/
public class DirData extends RecursiveTreeObject<DirData> {

	public final SimpleStringProperty id;  //序号
	public final SimpleStringProperty dir;  //路径
	public final SimpleStringProperty imgPath;  //图片路径
	public final SimpleStringProperty nums;  //专家标注数据数
	public final SimpleStringProperty imgNums;
	public final SimpleStringProperty addtime;
	//public final ObservableValue<JFXButton> button;
	public DirData(String id, String dir, String imgPath, String nums, String imgNums, String addtime) {
		super();
		this.id = new SimpleStringProperty(id);
		this.dir = new SimpleStringProperty(dir);
		this.imgPath = new SimpleStringProperty(imgPath);
		this.nums = new SimpleStringProperty(nums);
		this.imgNums = new SimpleStringProperty(imgNums);
		this.addtime = new SimpleStringProperty(addtime);
		//this.button = button;
	}
	public SimpleStringProperty getId() {
		return id;
	}
	public SimpleStringProperty getDir() {
		return dir;
	}
	public SimpleStringProperty getNums() {
		return nums;
	}
	public SimpleStringProperty getAddtime() {
		return addtime;
	}
	public SimpleStringProperty getImgNums() {
		return imgNums;
	}
//	public ObservableValue<JFXButton> getButton() {
//		return button;
//	}
	public SimpleStringProperty getImgPath() {
		return imgPath;
	}
}
