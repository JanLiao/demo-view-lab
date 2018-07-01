package com.cvte.util;

import com.cvte.cons.Constant;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** 
* @author: jan 
* @date: 2018年5月30日 上午11:43:12 
*/
public class LoadingView extends Application {
	private static ProgressIndicator progressIndicator;
	private static Stage dialogStage;
    @Override
    public void start(final Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaFx Dialog");
        final Button btn = new Button();
        btn.setText("Click me to display popup dialog");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
           public void handle(ActionEvent e) {
                //Stage dialog = new Stage();
            	progressShow();
//                MyTask task = new MyTask();
//                //dialog.initStyle(StageStyle.UTILITY);
//                task.setStage(Constant.stage);
//                Thread t = new Thread(task);
//                t.start();
                //t.interrupt();
                //new Thread(task).start();
//                Scene scene2 = new Scene(new Group(new Text(25, 25, "Hello World!")));
//                dialog.setScene(scene2);
//                dialog.show();
            }
        });
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

    }
    
    public static void progressShow() {
		progressIndicator = new ProgressIndicator();
		Label label = new Label("数据加载中, 请稍后...");
        label.setTextFill(Color.BLUE);
        //label.getStyleClass().add("progress-bar-root");
        progressIndicator.setProgress(-1F);
		
		VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setBackground(Background.EMPTY);
        vBox.getChildren().addAll(progressIndicator,label);
        dialogStage = Constant.stage;
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setAlwaysOnTop(true);
        Scene scene = new Scene(vBox);
        scene.setFill(null);
        dialogStage.setScene(scene);
        
        dialogStage.show();
	}
    public static void main(String[] args) {
        launch(args);
    }
}
