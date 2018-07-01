package com.cvte.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.cvte.cons.Constant;
import com.cvte.util.ColorUtil;
import com.cvte.util.DataInit;
import com.cvte.util.DragUtil;
import com.cvte.util.ImageListInit;
import com.cvte.util.ImageTask;
import com.cvte.util.InputStreamCacher;
import com.cvte.util.MD5Util;
import com.cvte.util.PropertyUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** 
* @author: jan 
* @date: 2018年5月21日 上午12:46:59 
*/
public class Login extends Application {
	
	private static final String FX_LABEL_FLOAT_TRUE = "-fx-label-float:true;";
	private JFXTextField textField;
	private JFXPasswordField passwordField;
	private Label loginText;
	private Stage stageOld;

	@Override
	public void start(Stage stage) throws Exception {
		stageOld = stage;
		Constant.stage = stage;
		stage.initStyle(StageStyle.TRANSPARENT);

		VBox root = new VBox();
        root.setId("root");
        // 引入样式
        root.getStylesheets().add(Login.class.getResource("/com/cvte/css/style.css").toString());
        
        //顶部
        VBox top = new VBox();
        top.setId("top");
        top.setPrefSize(420,26);
        // 标题栏
        AnchorPane title = new AnchorPane();
        Label min = new Label();
        min.setPrefWidth(42);
        min.setPrefHeight(28);
        min.setId("minHide");
        title.getChildren().add(min);
        AnchorPane.setRightAnchor(min, 36.0);
        AnchorPane.setTopAnchor(min, -1.0);
        min.setOnMouseClicked(new EventHandler<MouseEvent>() {
        	public void handle(MouseEvent e) {
        		stage.setIconified(true);
        	}
        });
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
        
        passwordField = new JFXPasswordField();
        passwordField.setStyle(FX_LABEL_FLOAT_TRUE);
        passwordField.setPromptText("密  码");
        content.getChildren().add(passwordField);
                
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER_RIGHT);
        loginText = new Label("");
        loginText.setTextFill(Color.RED);
        //loginText.setTextAlignment(TextAlignment.RIGHT);
        loginText.setFont(new Font(16));
        hbox.getChildren().add(loginText);
        
        JFXCheckBox jfxCheckBox = new JFXCheckBox("记住密码");
        PropertyUtil prop = new PropertyUtil();
        prop.loadProperty();
        if("1".equals(PropertyUtil.rem)) {
        	textField.setText(PropertyUtil.Account);
        	passwordField.setText("admin123456");
        	jfxCheckBox.setSelected(true);
        }
        jfxCheckBox.selectedProperty().addListener(new ChangeListener<Boolean>() {  
    		@Override  
    		public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {  
    			PropertyUtil.write(jfxCheckBox);
    		}
    	});
        hbox.getChildren().add(jfxCheckBox);
        content.getChildren().add(hbox);
        
        JFXButton button = new JFXButton("登       录");
        button.getStyleClass().add("button-raised");
        content.getChildren().add(button);
        
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
		if("".equals(account) || "".equals(password)) {
			loginText.setText("用户名或密码不能为空");
		}
		else {
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
				System.out.println("相等,登录成功");
				Constant.User = account;
				Constant.InitStyle = 1;
				loginText.setText("登录成功");
				
				//label 颜色初始化
				ColorUtil.initColor();
				//登录成功初始化数据
				DataInit.loadData();
								
//				stageOld.close();
//				//LabelDemo demo = new LabelDemo();
//				Main demo = new Main();
//				try {
//					demo.start(stageOld);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}
			else {
				stageOld.close();
				//LabelDemo demo = new LabelDemo();
//				Main demo = new Main();
//				try {
//					demo.start(stageOld);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
				loginText.setText("用户名不存在或密码错误");
			}
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
