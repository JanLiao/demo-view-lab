package com.cvte.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import com.cvte.cons.Constant;

import javafx.scene.image.Image;

/**
 * @author: jan
 * @date: 2018年6月8日 下午2:38:37
 */
public class ImageListInit {

	public static void initImage() {
		currImageLoad();
		preImageLoad();
		nextImageLoad();
	}

	public static void currImageLoad() {
		System.out.println("当前imagelist loading中......" + Constant.AnalysisMix.getImgName());
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(Constant.AnalysisMix.getImgPath()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		InputStreamCacher cacher = new InputStreamCacher(fis);
		Constant.cacher = cacher;

		long start = System.currentTimeMillis();
//		List<Image> list = new ArrayList<Image>();
		//int size = Constant.AnalysisMix.getAllLabelData().size();
		//不能直接使用fis  FileInputStream流,因为不能重复使用
		Image innerImg = new Image(cacher.getInputStream(), 530 * 4, 530 * 4, true, false);
//		list.add(innerImg);
//		for (int i = 0; i < (size + 1); i++) {
//			InputStream stream = cacher.getInputStream();
//			// Image innerImg = new Image(new FileInputStream(new
//			// File(Constant.AnalysisMix.getImgPath())), 530*4, 530*4, true, false);
//		}
		System.out.println("图片流读取时间  = " + (System.currentTimeMillis() - start));
		Constant.CurrImageLoad = innerImg;
	}

	public static void preImageLoad() {
		long start = System.currentTimeMillis();
		int flag = Constant.AnalysisMix.getFlag();
		// 缓存上一张图片Image
		if (flag == 0) {
			System.out.println("没有上一张缓存");
		} else {
			String imgName = Constant.AnalysisMix.getImgList().get(flag - 1); // 含路径
			String dir = Constant.AnalysisMix.getImgList().get(flag - 1).split(",")[0];
			String imgname = imgName.split(",")[1];
			String imgPath = ImagePathUtil.getPath(dir, imgname);
			System.out.println("上一张imagelist loading中......" + imgname);

			FileInputStream fisPre = null;
			try {
				fisPre = new FileInputStream(new File(imgPath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			InputStreamCacher cacherPre = new InputStreamCacher(fisPre);
			Image innerImg = new Image(cacherPre.getInputStream(), 530 * 4, 530 * 4, true, false);
			System.out.println("图片流读取时间  = " + (System.currentTimeMillis() - start));
			Constant.PreImageLoad = innerImg;
		}
	}

	public static void nextImageLoad() {
		long start = System.currentTimeMillis();
		int flag = Constant.AnalysisMix.getFlag();
		// 缓存下一张图片Image
		if (flag + 1 >= Constant.AnalysisMix.getImgList().size()) {
			System.out.println("没有下一张缓存");
		} else {
			String imgName = Constant.AnalysisMix.getImgList().get(flag + 1); // 含路径
			String dir = Constant.AnalysisMix.getImgList().get(flag + 1).split(",")[0];
			String imgname = imgName.split(",")[1];
			String imgPath = ImagePathUtil.getPath(dir, imgname);
			System.out.println("下一张imagelist loading中......" + imgname);

			FileInputStream fisNext = null;
			try {
				fisNext = new FileInputStream(new File(imgPath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			InputStreamCacher cacherNext = new InputStreamCacher(fisNext);
			Image innerImg = new Image(cacherNext.getInputStream(), 530 * 4, 530 * 4, true, false);
			System.out.println("图片流读取时间  = " + (System.currentTimeMillis() - start));
			Constant.NextImageLoad = innerImg;
		}
	}

	@SuppressWarnings("unused")
	private static String getSoleName(String dir, String name, String imgName) {
		List<String[]> list = ReadCSV.readCSV(dir + "/" + name + "/file/imgAllLabel");
		String tmp = "";
		for (String[] s : list) {
			if (imgName.equals(s[3])) {
				String[] str = s[2].split("/");
				tmp = str[str.length - 1];
				break;
			}
		}
		return tmp;
	}
}
