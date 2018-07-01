package com.cvte.ui;

import com.cvte.util.DragUtil;
import com.cvte.util.MD5Util;
import com.cvte.util.PropertyUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXSnackbar.SnackbarEvent;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/** 
* @author: jan 
* @date: 2018年5月21日 上午12:46:59 
*/
public class Mune extends Application {
	
	private static final String FX_LABEL_FLOAT_TRUE = "-fx-label-float:true;";
	private JFXTextField textField;
	private JFXPasswordField passwordField;
	private RequiredFieldValidator validator;

	@Override
	public void start(Stage stage) throws Exception {
		stage.initStyle(StageStyle.TRANSPARENT);

		VBox root = new VBox();
        root.setId("root");
        // 引入样式
        root.getStylesheets().add(Mune.class.getResource("/com/cvte/css/style.css").toString());
        
        //顶部
        VBox top = new VBox();
        top.setId("top");
        top.setPrefSize(300,26);
        // 标题栏
        AnchorPane title = new AnchorPane();
       /* HBox box = new HBox();
        Button minButton = new Button("—");
        Button amxButton = new Button("口");
        Button closeButton = new Button("X");
        minButton.setStyle("-fx-base: rgb(243,243,243); -fx-border-color: rgb(243,243,243); -fx-border-width: 0.1; "
        + "-fx-max-height: infinity;-fx-text-fill: white ; -fx-border-image-insets: 0;");
        amxButton.setStyle("-fx-base: rgb(243,243,243); -fx-border-color: rgb(243,243,243); -fx-border-width: 0.1; "
        + "-fx-max-height: infinity;-fx-text-fill: white ; -fx-border-image-insets: 0;");
        closeButton.setStyle("-fx-base: rgb(255,128,128); -fx-border-color: rgb(243,243,243); -fx-border-width: 0.1; "
        + "-fx-max-height: infinity;-fx-text-fill: white ; -fx-border-image-insets: 0;");
        box.getChildren().add(minButton);
        box.getChildren().add(closeButton);
        AnchorPane.setRightAnchor(box, 0.0);
        AnchorPane.setTopAnchor(box, 5.0);*/
        Label close = new Label();
        close.setPrefWidth(42);
        close.setPrefHeight(28);
        close.setId("winClose");//winClose css样式Id
        title.getChildren().add(close);
        AnchorPane.setRightAnchor(close, 1.0);
        AnchorPane.setTopAnchor(close, -1.0);
        top.getChildren().add(title);
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	public void handle(MouseEvent e) {
        		stage.close();
        	}
        });
        
        // 内容
        VBox content = new VBox();
        content.setSpacing(30);
        content.setStyle("-fx-background-color:WHITE;-fx-padding:80;");
        content.setPrefWidth(420);
        content.setMinHeight(360);
        
        textField = new JFXTextField();
        textField.setPromptText("用  户  名");
        content.getChildren().add(textField);
        
//        validator = new RequiredFieldValidator();
//        validator.setMessage("用 户 名 和 密 码 不 能 为 空");
//        passwordField.getValidators().add(validator);
        
        passwordField = new JFXPasswordField();
        passwordField.setStyle(FX_LABEL_FLOAT_TRUE);
        passwordField.setPromptText("密  码");
        content.getChildren().add(passwordField);
        
        Text loginText = new Text();
        content.getChildren().add(loginText);
        
        JFXButton button = new JFXButton("登       录");
        button.getStyleClass().add("button-raised");
        content.getChildren().add(button);
        
//        StackPane pane = new StackPane();
//        JFXSnackbar bar = new JFXSnackbar(pane);
//        bar.enqueue(new SnackbarEvent("Notification Msg"));
//        content.getChildren().add(bar);
        
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent e) {
        		if(e.getCode() == KeyCode.ENTER) {
        			login();
        		}
        	}
        });
        
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent e) {
        		if(e.getCode() == KeyCode.ENTER) {
        			login();
        		}
        	}
        });
        
        passwordField.setOnKeyPressed(new EventHandler<KeyEvent>() {
        	public void handle(KeyEvent e) {
        		if(e.getCode() == KeyCode.ENTER) {
        			login();
        		}
        	}
        });
        
        button.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		login();
        	}
        });
        
        // 组装
        root.getChildren().addAll(top, content);
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        // 拖动监听器
        DragUtil.addDragListener(stage, top);
        // 添加窗体拉伸效果
        //DrawUtil.addDrawFunc(stage, root);
        // 显示
        stage.show();
	}
	
	public void login() {
		String account = textField.getText().trim();
		String password = passwordField.getText().trim();
		System.out.println(account + "  =  " + password);
		PropertyUtil pro = new PropertyUtil();
		pro.loadProperty();
		String pwd = "";
		try {
			pwd = MD5Util.getMD5(password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(account.equals(PropertyUtil.Account) 
				&& pwd.equals(PropertyUtil.Password)) {
			System.out.println("相等");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
