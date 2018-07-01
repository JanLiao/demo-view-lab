package com.cvte.entity;
/** 
* @author: jan 
* @date: 2018年5月24日 上午11:26:15 
*/
public class AvgAllImg {  //存储CSV文件

	private String imgName;
	private String imgPath;
	private String maskPath;
	private String operator;
	private String addTime;
	private String mix;  //是否融合   还是弃用  是否融合判断 new File("sole下是否存在")
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getMaskPath() {
		return maskPath;
	}
	public void setMaskPath(String maskPath) {
		this.maskPath = maskPath;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getMix() {
		return mix;
	}
	public void setMix(String mix) {
		this.mix = mix;
	}
	public String getoperator() {
		return operator;
	}
	public void setoperator(String operator) {
		this.operator = operator;
	}
	public AvgAllImg(String imgName, String imgPath, String maskPath, String operator, String addTime, String mix) {
		super();
		this.imgName = imgName;
		this.imgPath = imgPath;
		this.maskPath = maskPath;
		this.operator = operator;
		this.addTime = addTime;
		this.mix = mix;
	}
	@Override
	public String toString() {
		return "AvgAllImg [imgName=" + imgName + ", imgPath=" + imgPath + ", maskPath=" + maskPath + ", addTime="
				+ addTime + ", mix=" + mix + "]";
	}
	public AvgAllImg() {
		super();
	}
	
}
