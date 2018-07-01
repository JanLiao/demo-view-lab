package com.cvte.util;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXSpinner;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** 
* @author: jan 
* @date: 2018年5月29日 下午4:34:22 
*/
public class Loading extends Application{
	
	private static JFXAlert alertRoot = null;
	private ProgressIndicator progressIndicator;
	private static Stage dialogStage1;

	@Override
	public void start(Stage stage) throws Exception {
		
		
		HBox root = new HBox();
		root.setMinSize(500, 400);
		root.setAlignment(Pos.CENTER);
		
		JFXButton button1 = new JFXButton("弹层");
        button1.setStyle("-fx-background-color: rgb(24, 128, 56);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
        button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//showAlert(stage, "ssss");
				progressShow(stage);
			}
        });
        
        JFXButton button2 = new JFXButton("确定");
        button2.setStyle("-fx-background-color: rgb(24, 128, 56);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
        button2.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		
        		System.out.println(666);
        	}
        });
        root.getChildren().add(button2);
        root.getChildren().add(button1);
        
        Task<Void> task = new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				Thread.sleep(8000);
				dialogStage1.hide();;
				System.out.println(888);
				showAlert(stage, "666");
				return null;
			}
        	
        };
        new Thread(task).start();;
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		//progressShow();
		
	}
	
	protected void runAc() {
		System.out.println("0000");
		dialogStage1.close();
	}

	protected void progressShow(Stage stage) {
		progressIndicator = new ProgressIndicator();
		Label label = new Label("数据加载中, 请稍后...");
        label.setTextFill(Color.BLUE);
        //label.getStyleClass().add("progress-bar-root");
        progressIndicator.setProgress(-1F);
		
		VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setBackground(Background.EMPTY);
        vBox.getChildren().addAll(progressIndicator,label);
        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UNDECORATED);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setAlwaysOnTop(true);
        Scene scene = new Scene(vBox);
        scene.setFill(null);
        dialogStage.setScene(scene);
        dialogStage1 = dialogStage;
        dialogStage.show();
	}

	public void sleepThread() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		        
		alertRoot.hideWithAnimation();
	}
	
	private static void showAlert(Stage stage, String msg) {
		JFXAlert alert = new JFXAlert((Stage)stage.getScene().getWindow());
        alert.setOverlayClose(false);
        alert.setSize(200, 200);
        JFXDialogLayout layout = new JFXDialogLayout();
        JFXSpinner root = new JFXSpinner();
        //layout.setOpacity(0.1);
        //layout.setHeading(new Label("温馨小提示"));
        //Label content = new Label(msg);
        layout.setBody(root);
//        JFXButton closeButton = new JFXButton("关  闭");
//        closeButton.setStyle("-fx-background-color: GREEN;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;");
//        closeButton.setOnAction(event -> {
//        	alert.hideWithAnimation();
//        });
//        layout.setActions(closeButton);
        alert.setContent(layout);
        alertRoot = alert;
        alert.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
