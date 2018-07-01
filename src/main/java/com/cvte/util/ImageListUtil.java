package com.cvte.util;

import com.cvte.cons.Constant;

import javafx.scene.image.Image;

/** 
* @author: jan 
* @date: 2018年6月8日 下午5:41:34 
*/
public class ImageListUtil {

	public static void updateNextImage() {
		Image curr = Constant.CurrImageLoad;
		Image next = Constant.NextImageLoad;
		Constant.PreImageLoad = curr;
		Constant.CurrImageLoad = next;
	}

	public static void setNextImage() {
		ImageListInit.nextImageLoad();
	}

	public static void updatePreImage() {
		Image pre = Constant.PreImageLoad;
		Image curr = Constant.CurrImageLoad;
		Constant.CurrImageLoad = pre;
		Constant.NextImageLoad = curr;
	}

	public static void setPreImage() {
		ImageListInit.preImageLoad();
	}

}
