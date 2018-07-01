package com.cvte.util;

import java.io.InputStream;

import javafx.concurrent.Task;
import javafx.scene.image.Image;

/** 
* @author: jan 
* @date: 2018年6月7日 下午10:15:18 
*/
public class ImageTask extends Task<Void> {

	private InputStreamCacher cacher;
	
	@Override
	protected Void call() throws Exception {
		long start = System.currentTimeMillis();
		for(int i = 0; i < 8; i++) {
			InputStream stream = cacher.getInputStream();
			System.out.println("图片流读取时间  = " + (System.currentTimeMillis() - start));
			//Image innerImg = new Image(new FileInputStream(new File(Constant.AnalysisMix.getImgPath())), 530*4, 530*4, true, false);
			Image innerImg = new Image(stream, 530*4, 530*4, true, false);
		}
		System.out.println("图片image读取时间  = " + (System.currentTimeMillis() - start));
		return null;
	}

	public InputStreamCacher getCacher() {
		return cacher;
	}

	public void setCacher(InputStreamCacher cacher) {
		this.cacher = cacher;
	}

}
