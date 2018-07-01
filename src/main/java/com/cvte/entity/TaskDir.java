package com.cvte.entity;
/** 
* @author: jan 
* @date: 2018年5月24日 下午2:27:14 
*/
public class TaskDir {

	private String id;    //当前ID
	private String path;  //标注路径
	private String imgPath;  //图片路径
	private String nums;  //专家数
	private String imgNums;
	private String addTime;
	private String operator;
	private int deleted; //是否删除
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNums() {
		return nums;
	}
	public void setNums(String nums) {
		this.nums = nums;
	}
	public String getImgNums() {
		return imgNums;
	}
	public void setImgNums(String imgNums) {
		this.imgNums = imgNums;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public TaskDir(String id, String path, String nums, String imgNums, String addTime, int deleted) {
		super();
		this.id = id;
		this.path = path;
		this.nums = nums;
		this.imgNums = imgNums;
		this.addTime = addTime;
		this.deleted = deleted;
	}
	public TaskDir(String id, String path, String nums, String imgNums, String addTime, int deleted, String operator) {
		super();
		this.id = id;
		this.path = path;
		this.nums = nums;
		this.imgNums = imgNums;
		this.addTime = addTime;
		this.operator = operator;
		this.deleted = deleted;
	}
	public TaskDir() {
		super();
	}
	@Override
	public String toString() {
		return "TaskDir [id=" + id + ", path=" + path + ", imgPath=" + imgPath + ", nums=" + nums + ", imgNums="
				+ imgNums + ", addTime=" + addTime + ", operator=" + operator + ", deleted=" + deleted + "]";
	}
	
}
