package com.cvte.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.cvte.cons.Constant;

/** 
* @author: jan 
* @date: 2018年6月9日 上午10:52:15 
*/
public class PaintAllUtil {

	public static void paintPan() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for(int i = 0; i < Constant.canvasList.size(); i++) {
        	PaintTask task = new PaintTask();
        	task.setCanvas(Constant.canvasList.get(i));
        	task.setNum(i);
        	//executor.execute(task);
        	executor.submit(task);
        }
		
		executor.shutdown();
        try {
			while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
			    System.out.println("线程池没有关闭");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        System.out.println("线程池已关闭");
	}

	public static void paintBei() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for(int i = 0; i < Constant.canvasList.size(); i++) {
        	PaintBeiTask task = new PaintBeiTask();
        	task.setCanvas(Constant.canvasList.get(i));
        	task.setNum(i);
        	//executor.execute(task);
        	executor.submit(task);
        }
		
		executor.shutdown();
        try {
			while (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
			    System.out.println("线程池没有关闭");
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
        System.out.println("线程池已关闭");
	}

}
