package com.cvte.entity;
/** 
* @author: jan 
* @date: 2018年5月24日 上午10:57:33 
*/
public class CircleData extends BaseData {

	private double radius;
	private double centerX;
	private double centerY;
	private String name;
	private double rotateX;
	private double rotateY;
	private double rotateAngle;  // 绕中心centerX、centerY的角度
	private double pointX;  // 椭圆顶点坐标
	private double pointY;  // 椭圆顶点坐标
	
	public double getPointX() {
		return pointX;
	}

	public void setPointX(double pointX) {
		this.pointX = pointX;
	}

	public double getPointY() {
		return pointY;
	}

	public void setPointY(double pointY) {
		this.pointY = pointY;
	}

	public double getRotateAngle() {
		return rotateAngle;
	}

	public void setRotateAngle(double rotateAngle) {
		this.rotateAngle = rotateAngle;
	}

	public double getRotateX() {
		return rotateX;
	}

	public void setRotateX(double rotateX) {
		this.rotateX = rotateX;
	}

	public double getRotateY() {
		return rotateY;
	}

	public void setRotateY(double rotateY) {
		this.rotateY = rotateY;
	}

	public double getCenterX() {
		return centerX;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CircleData(String name, double radius, double centerX, double centerY) {
		super();
		this.name = name;
		this.radius = radius;
		this.centerX = centerX;
		this.centerY = centerY;
	}

	public CircleData() {
		super();
	}

	@Override
	public String toString() {
		return "CircleData [radius=" + radius + ", centerX=" + centerX + ", centerY=" + centerY + ", name=" + name
				+ ", rotateX=" + rotateX + ", rotateY=" + rotateY + ", rotateAngle=" + rotateAngle + ", pointX="
				+ pointX + ", pointY=" + pointY + ", getLeft()=" + getLeft() + ", getTop()=" + getTop()
				+ ", getAngle()=" + getAngle() + ", getScaleX()=" + getScaleX() + ", getScaleY()=" + getScaleY()
				+ ", getStroke()=" + getStroke() + ", getStrokeWidth()=" + getStrokeWidth() + ", getOpacity()="
				+ getOpacity() + ", getWidth()=" + getWidth() + ", getHeight()=" + getHeight() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
