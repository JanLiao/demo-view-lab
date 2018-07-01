package com.cvte.util;

import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

/** 
* @author: jan 
* @date: 2018年6月7日 下午4:41:10 
*/
public class CircleTask extends Task<Void> {

	private Canvas canvas;
	//private InputStreamCacher cacher;
	
	@Override
	protected Void call() throws Exception {
		long start = System.currentTimeMillis();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		System.out.println("图片加载前时间 = " + (System.currentTimeMillis() - start));
		//InputStream stream = cacher.getInputStream();
		System.out.println("circle流读取时间  = " + (System.currentTimeMillis() - start));
		//Image innerImg = new Image(new FileInputStream(new File(Constant.AnalysisMix.getImgPath())), 530*4, 530*4, true, false);
		//Image innerImg = new Image(stream, 530*4, 530*4, true, false);
		Image innerImg = Constant.CurrImageLoad;
		System.out.println("图片加载时间 = " + (System.currentTimeMillis() - start));
		//获取当前图片label视盘数据
        CircleData pan = LabelUtil.getAvgCircleData("shipan");
        System.out.println("获取平均值视盘时间 = " + (System.currentTimeMillis() - start));
        //初始化视盘avg
        Constant.panAvg = pan;
        gc.drawImage(innerImg, pan.getCenterX()*4 - 265,
        		pan.getCenterY()*4 - 265,
        		530, 530, 0, 0, 530, 530);
        gc.save();
        gc.setLineWidth(pan.getStrokeWidth());
        gc.setStroke(Color.rgb(51, 171, 160));
    	double relativeX = pan.getLeft()*4 - (pan.getCenterX()*4 - 265);
    	double relativeY = pan.getTop()*4 - (pan.getCenterY()*4 - 265);
    	gc.setTransform(new Affine(new Rotate(pan.getAngle(), relativeX, relativeY)));
    	gc.strokeOval(relativeX, relativeY, pan.getRadius()*pan.getScaleX()*2*4, pan.getRadius()*pan.getScaleY()*2*4);
    	gc.restore();
    	long end = System.currentTimeMillis();
    	System.out.println("当前thread pan画图时间 = " + (end - start));
		return null;
	}
	
	public CircleTask() {
		setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                //System.out.println("画图结束");
            }
        });
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

//	public InputStreamCacher getCacher() {
//		return cacher;
//	}
//
//	public void setCacher(InputStreamCacher cacher) {
//		this.cacher = cacher;
//	}
	
}
