package com.cvte.entity;
/** 
* @author: jan 
* @date: 2018年5月25日 下午3:46:25 
*/
public class Component {

	private int tab;   //1-视盘 , 2-视杯, 3-黄斑中心, 4-all(CheckBox可选),
	private boolean radio;
	private String combox;
	//private boolean allcheckbox;  //所有选择      4-all(CheckBox可选)
	//private boolean usercheckbox; //所选用户checkbox
	public int getTab() {
		return tab;
	}
	public void setTab(int tab) {
		this.tab = tab;
	}
	public boolean isRadio() {
		return radio;
	}
	public void setRadio(boolean radio) {
		this.radio = radio;
	}
	public String getCombox() {
		return combox;
	}
	public void setCombox(String combox) {
		this.combox = combox;
	}
	public Component(int tab, boolean radio, String combox) {
		super();
		this.tab = tab;
		this.radio = radio;
		this.combox = combox;
	}
	@Override
	public String toString() {
		return "Component [tab=" + tab + ", radio=" + radio + ", combox=" + combox + "]";
	}
	public Component() {
		super();
	}

}
