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
* @date: 2018年6月7日 下午3:25:49 
*/
public class PaintTask extends Task<Void> {
	
	private Canvas canvas;
	//private InputStreamCacher cacher;
	private int num;

	@Override
	protected Void call() throws Exception {
		long start = System.currentTimeMillis();
		GraphicsContext gc = canvas.getGraphicsContext2D();
		//InputStream stream = cacher.getInputStream();
		//Image innerImg = new Image(stream, 530*4, 530*4, true, true);
		Image innerImg = Constant.CurrImageLoad;
		//获取当前图片label视盘数据
        CircleData solepan = LabelUtil.getSoleLabelData(num);
        gc.drawImage(innerImg, solepan.getCenterX()*4 - 265,
        		solepan.getCenterY()*4 - 265,
        		530, 530, 0, 0, 530, 530);
        gc.save();
        gc.setLineWidth(solepan.getStrokeWidth());
        gc.setStroke(Color.rgb(51, 171, 160));
    	double relativeX = solepan.getLeft()*4 - (solepan.getCenterX()*4 - 265);
    	double relativeY = solepan.getTop()*4 - (solepan.getCenterY()*4 - 265);
    	gc.setTransform(new Affine(new Rotate(solepan.getAngle(), relativeX, relativeY)));
    	gc.strokeOval(relativeX, relativeY, solepan.getRadius()*solepan.getScaleX()*2*4, solepan.getRadius()*solepan.getScaleY()*2*4);
    	gc.restore();
    	long end = System.currentTimeMillis();
    	System.out.println("当前thread scroll画图时间 = " + (end - start));
		return null;
	}
	
	public PaintTask() {
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

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
