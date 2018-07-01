package com.cvte.entity;
/** 
* @author: jan 
* @date: 2018年5月24日 上午10:54:07 
*/
public class BaseData {

	private double left;
	private double top;
	private double angle;
	private double scaleX;
	private double scaleY;
	private String stroke;
	private double strokeWidth;
	private double opacity;
	private double width;
	private double height;
	public double getLeft() {
		return left;
	}
	public void setLeft(double left) {
		this.left = left;
	}
	public double getTop() {
		return top;
	}
	public void setTop(double top) {
		this.top = top;
	}
	public double getAngle() {
		return angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
	public double getScaleX() {
		return scaleX;
	}
	public void setScaleX(double scaleX) {
		this.scaleX = scaleX;
	}
	public double getScaleY() {
		return scaleY;
	}
	public void setScaleY(double scaleY) {
		this.scaleY = scaleY;
	}
	public String getStroke() {
		return stroke;
	}
	public void setStroke(String stroke) {
		this.stroke = stroke;
	}
	public double getStrokeWidth() {
		return strokeWidth;
	}
	public void setStrokeWidth(double strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
	public double getOpacity() {
		return opacity;
	}
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public BaseData() {
		super();
	}
	public BaseData(double left, double top, double angle, double scaleX, double scaleY, String stroke,
			double strokeWidth, double opacity, double width, double height) {
		super();
		this.left = left;
		this.top = top;
		this.angle = angle;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.stroke = stroke;
		this.strokeWidth = strokeWidth;
		this.opacity = opacity;
		this.width = width;
		this.height = height;
	}
	@Override
	public String toString() {
		return "BaseData [left=" + left + ", top=" + top + ", angle=" + angle + ", scaleX=" + scaleX + ", scaleY="
				+ scaleY + ", stroke=" + stroke + ", strokeWidth=" + strokeWidth + ", opacity=" + opacity + ", width="
				+ width + ", height=" + height + "]";
	}
}
