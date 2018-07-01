package com.cvte.entity;
/** 
* @author: jan 
* @date: 2018年5月24日 上午11:29:48 
*/
public class ConsoleImg {  //存储CSV文件

	private String imgName;
	private String imgPath;
	private String maskPath;
	private String operator;
	private String panMixUser; //所选择用户   ,分隔
	private CircleData panAvg;
	private String beiMixUser;
	private CircleData beiAvg;
	private String centerMixUser;
	private LineData centerAvg;
	private String allMixUser;
	private String allLabel;  //所有用户label  =分隔
	private String standUser;  //已某用户为标准
	private String updateTime;
	private String centerX;
	private String centerY;
	private String percent;
	private String beipercent;
	private String saveModel;
	public String getBeipercent() {
		return beipercent;
	}
	public void setBeipercent(String beipercent) {
		this.beipercent = beipercent;
	}
	public String getSaveModel() {
		return saveModel;
	}
	public void setSaveModel(String saveModel) {
		this.saveModel = saveModel;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getCenterX() {
		return centerX;
	}
	public void setCenterX(String centerX) {
		this.centerX = centerX;
	}
	public String getCenterY() {
		return centerY;
	}
	public void setCenterY(String centerY) {
		this.centerY = centerY;
	}
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
	
	public String getStandUser() {
		return standUser;
	}
	public void setStandUser(String standUser) {
		this.standUser = standUser;
	}
	public String getPanMixUser() {
		return panMixUser;
	}
	public void setPanMixUser(String panMixUser) {
		this.panMixUser = panMixUser;
	}
	public CircleData getPanAvg() {
		return panAvg;
	}
	public void setPanAvg(CircleData panAvg) {
		this.panAvg = panAvg;
	}
	public String getBeiMixUser() {
		return beiMixUser;
	}
	public void setBeiMixUser(String beiMixUser) {
		this.beiMixUser = beiMixUser;
	}
	public CircleData getBeiAvg() {
		return beiAvg;
	}
	public void setBeiAvg(CircleData beiAvg) {
		this.beiAvg = beiAvg;
	}
	public String getCenterMixUser() {
		return centerMixUser;
	}
	public void setCenterMixUser(String centerMixUser) {
		this.centerMixUser = centerMixUser;
	}
	public LineData getCenterAvg() {
		return centerAvg;
	}
	public void setCenterAvg(LineData centerAvg) {
		this.centerAvg = centerAvg;
	}
	public String getAllMixUser() {
		return allMixUser;
	}
	public void setAllMixUser(String allMixUser) {
		this.allMixUser = allMixUser;
	}
	public String getAllLabel() {
		return allLabel;
	}
	public void setAllLabel(String allLabel) {
		this.allLabel = allLabel;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public ConsoleImg(String imgName, String imgPath, String maskPath,String operator, String allLabel,
			String updateTime) {
		super();
		this.imgName = imgName;
		this.imgPath = imgPath;
		this.maskPath = maskPath;
		this.operator = operator;
		this.allLabel = allLabel;
		this.updateTime = updateTime;
	}
	public ConsoleImg() {
		super();
	}
	@Override
	public String toString() {
		return "ConsoleImg [imgName=" + imgName + ", imgPath=" + imgPath + ", maskPath=" + maskPath + ", operator="
				+ operator + ", panMixUser=" + panMixUser + ", panAvg=" + panAvg + ", beiMixUser=" + beiMixUser
				+ ", beiAvg=" + beiAvg + ", centerMixUser=" + centerMixUser + ", centerAvg=" + centerAvg
				+ ", allMixUser=" + allMixUser + ", allLabel=" + allLabel + ", standUser=" + standUser + ", updateTime="
				+ updateTime + ", centerX=" + centerX + ", centerY=" + centerY + ", percent=" + percent
				+ ", beipercent=" + beipercent + ", saveModel=" + saveModel + "]";
	}
	
}
