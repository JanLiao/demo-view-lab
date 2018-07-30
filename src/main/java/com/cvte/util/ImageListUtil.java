package com.cvte.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

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

	public static void updateCurImage(int flag) {
		long start = System.currentTimeMillis();
		Constant.AnalysisMix.setFlag(flag);
		// 缓存当前图片Image
		String imgName = Constant.AnalysisMix.getImgList().get(flag); // 含路径
		String dir = Constant.AnalysisMix.getImgList().get(flag).split(",")[0];
		String imgname = imgName.split(",")[1];
		String imgPath = ImagePathUtil.getPath(dir, imgname);
		System.out.println("update 当前imagelist loading中......" + imgname);

		FileInputStream fisNext = null;
		try {
			fisNext = new FileInputStream(new File(imgPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStreamCacher cacherNext = new InputStreamCacher(fisNext);
		Image innerImg = new Image(cacherNext.getInputStream(), 530 * 4, 530 * 4, true, false);
		System.out.println("图片流读取时间  = " + (System.currentTimeMillis() - start));
		Constant.CurrImageLoad = innerImg;
	}

}
