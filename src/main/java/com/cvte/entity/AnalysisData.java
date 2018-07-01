package com.cvte.entity;

import java.util.*;

import com.cvte.util.InputStreamCacher;

/** 
* @author: jan 
* @date: 2018年5月24日 上午11:18:41 
*/
public class AnalysisData {

	private String imgName;
	private String imgPath;
	private String taskPath;
	private int imgNums;  //所有图片总数
	private int type;  // 0-分析融合   1-查看结果
	private int flag;  //当前标志
	private String panUser;
	private String beiUser;
	private String centerUser;
	private String allLabelUser;
	private String standUser; //标准
	private String centerX;
	private String centerY;
	private String percent;
	private String beiPercent;
	private String saveModel;
	private List<String> allLabelData = new ArrayList<String>();  //当前flag所对应图片所有专家标注数据   已被打乱顺序
	private List<String> imgList = new ArrayList<String>();  //存储所有图片
	private Map<String, Integer> imgMap = new HashMap<String, Integer>();  //存储所有图片   <路径,图片名  0-未融合  1-已融合>
	private Mask mask;
	private InputStreamCacher cacaher;
	public String getBeiPercent() {
		return beiPercent;
	}
	public void setBeiPercent(String beiPercent) {
		this.beiPercent = beiPercent;
	}
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getSaveModel() {
		return saveModel;
	}
	public void setSaveModel(String saveModel) {
		this.saveModel = saveModel;
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
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public void setCenterY(String centerY) {
		this.centerY = centerY;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getPanUser() {
		return panUser;
	}
	public void setPanUser(String panUser) {
		this.panUser = panUser;
	}
	public String getBeiUser() {
		return beiUser;
	}
	public void setBeiUser(String beiUser) {
		this.beiUser = beiUser;
	}
	public String getCenterUser() {
		return centerUser;
	}
	public void setCenterUser(String centerUser) {
		this.centerUser = centerUser;
	}
	public String getAllLabelUser() {
		return allLabelUser;
	}
	public void setAllLabelUser(String allLabelUser) {
		this.allLabelUser = allLabelUser;
	}
	public String getStandUser() {
		return standUser;
	}
	public void setStandUser(String standUser) {
		this.standUser = standUser;
	}
	public String getTaskPath() {
		return taskPath;
	}
	public void setTaskPath(String taskPath) {
		this.taskPath = taskPath;
	}
	public int getImgNums() {
		return imgNums;
	}
	public void setImgNums(int imgNums) {
		this.imgNums = imgNums;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Map<String, Integer> getImgMap() {
		return imgMap;
	}
	public void setImgMap(Map<String, Integer> imgMap) {
		this.imgMap = imgMap;
	}
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}
	public List<String> getAllLabelData() {
		return allLabelData;
	}
	public void setAllLabelData(List<String> allLabelData) {
		this.allLabelData = allLabelData;
	}
	public Mask getMask() {
		return mask;
	}
	public void setMask(Mask mask) {
		this.mask = mask;
	}
	public InputStreamCacher getCacaher() {
		return cacaher;
	}
	public void setCacaher(InputStreamCacher cacaher) {
		this.cacaher = cacaher;
	}
	public AnalysisData(String imgName, String imgPath, String taskPath, int imgNums, int type, int flag,
			Map<String, Integer> imgMap, List<String> imgList, List<String> allLabelData) {
		super();
		this.imgName = imgName;
		this.imgPath = imgPath;
		this.taskPath = taskPath;
		this.imgNums = imgNums;
		this.type = type;
		this.flag = flag;
		this.imgMap = imgMap;
		this.imgList = imgList;
		this.allLabelData = allLabelData;
	}
	public AnalysisData() {
		super();
	}
	@Override
	public String toString() {
		return "AnalysisData [imgName=" + imgName + ", imgPath=" + imgPath + ", taskPath=" + taskPath + ", imgNums="
				+ imgNums + ", type=" + type + ", flag=" + flag + ", panUser=" + panUser + ", beiUser=" + beiUser
				+ ", centerUser=" + centerUser + ", allLabelUser=" + allLabelUser + ", standUser=" + standUser
				+ ", centerX=" + centerX + ", centerY=" + centerY + ", percent=" + percent + ", beiPercent="
				+ beiPercent + ", saveModel=" + saveModel + ", allLabelData=" + allLabelData + ", imgList=" + imgList
				+ ", imgMap=" + imgMap + ", cacaher=" + cacaher + "]";
	}
	
}
