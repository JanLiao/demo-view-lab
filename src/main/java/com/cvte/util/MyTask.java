package com.cvte.util;
/** 
* @author: jan 
* @date: 2018年5月29日 下午7:22:52 
*/

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class MyTask extends Task<Void> {

	private Stage stage;

    public MyTask() {
        setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if (stage != null) {
                    stage.hide();
                }
            }
        });
    }
    @Override
    protected Void call() throws Exception {
    	System.out.println("即将关闭加载条");
        return null;
    }
    public Stage getStage() {
        return stage;
    }
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
