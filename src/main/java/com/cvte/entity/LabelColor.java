package com.cvte.entity;
/** 
* @author: jan 
* @date: 2018年5月25日 上午2:26:57 
*/
public class LabelColor {

	private int red;
	private int green;
	private int blue;
	private String colorStr;
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	public String getColorStr() {
		return colorStr;
	}
	public void setColorStr(String colorStr) {
		this.colorStr = colorStr;
	}
	public LabelColor(int red, int green, int blue, String colorStr) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.colorStr = colorStr;
	}
	public LabelColor() {
		super();
	}
	@Override
	public String toString() {
		return "LabelColor [red=" + red + ", green=" + green + ", blue=" + blue + ", colorStr=" + colorStr + "]";
	}
	
	
	
}
