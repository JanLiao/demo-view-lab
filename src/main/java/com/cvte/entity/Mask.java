package com.cvte.entity;

import java.util.*;

/** 
* @author: jan 
* @date: 2018年5月29日 上午10:42:32 
*/
public class Mask {

	private int[][] allPanMask;   //所有图片mask
	private int[][] allBeiMask; //所有视杯mask
	private Map<String, Integer[][]> allSolePanMask;  //单张图片对应用户图片视盘mask
	private Map<String, Integer[][]> allSoleBeiMask;  //单张图片图片视杯mask
	private int[][] curBeiMask;
	private int[][] curPanMask;
	private String panUser;
	private String beiUser;
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
	
	
	public int[][] getAllPanMask() {
		return allPanMask;
	}
	public void setAllPanMask(int[][] allPanMask) {
		this.allPanMask = allPanMask;
	}
	public int[][] getAllBeiMask() {
		return allBeiMask;
	}
	public void setAllBeiMask(int[][] allBeiMask) {
		this.allBeiMask = allBeiMask;
	}
	public Map<String, Integer[][]> getAllSolePanMask() {
		return allSolePanMask;
	}
	public void setAllSolePanMask(Map<String, Integer[][]> allSolePanMask) {
		this.allSolePanMask = allSolePanMask;
	}
	public Map<String, Integer[][]> getAllSoleBeiMask() {
		return allSoleBeiMask;
	}
	public void setAllSoleBeiMask(Map<String, Integer[][]> allSoleBeiMask) {
		this.allSoleBeiMask = allSoleBeiMask;
	}
	public int[][] getCurBeiMask() {
		return curBeiMask;
	}
	public void setCurBeiMask(int[][] curBeiMask) {
		this.curBeiMask = curBeiMask;
	}
	public int[][] getCurPanMask() {
		return curPanMask;
	}
	public void setCurPanMask(int[][] curPanMask) {
		this.curPanMask = curPanMask;
	}
	public Mask(int[][] allPanMask, int[][] allBeiMask, Map<String, Integer[][]> allSolePanMask, 
			Map<String, Integer[][]> allSoleBeiMask, String panUser, String beiUser) {
		super();
		this.allPanMask = allPanMask;
		this.allBeiMask = allBeiMask;
		this.allSolePanMask = allSolePanMask;
		this.allSoleBeiMask = allSoleBeiMask;
		this.panUser = panUser;
		this.beiUser = beiUser;
	}
	public Mask() {
		super();
	}
	@Override
	public String toString() {
		return "Mask [allPanMask=" + Arrays.toString(allPanMask) + ", allBeiMask=" + Arrays.toString(allBeiMask)
				+ ", allSolePanMask=" + allSolePanMask + ", allSoleBeiMask=" + allSoleBeiMask + ", panUser=" + panUser
				+ ", beiUser=" + beiUser + "]";
	}
	
	

}
