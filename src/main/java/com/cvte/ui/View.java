package com.cvte.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;
import com.cvte.entity.LineData;
import com.cvte.util.ViewUtil;
import com.cvte.util.BtnUpdateUtil;
import com.cvte.util.CheckBoxChangeUtil;
import com.cvte.util.ComboxViewUtil;
import com.cvte.util.LabelUtil;
import com.cvte.util.RadioChangeRepaintUtil;
import com.cvte.util.RadioViewRepaintUtil;
import com.cvte.util.SlideChangeUtil;
import com.cvte.util.TabViewRepaintUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.effects.JFXDepthManager;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** 
* @author: jan 
* @date: 2018年5月27日 上午12:56:13 
*/
public class View {

	public static void mixImage(Stage stage) {
		HBox root = new HBox();

		HBox leftEmpty = new HBox();
		leftEmpty.setMaxWidth(10);
		leftEmpty.setMinWidth(10);
		root.getChildren().add(leftEmpty);

		VBox leftContent = new VBox();
		leftContent.setMaxWidth(560);
		leftContent.setMinWidth(560);
		leftContent.setMaxHeight(620);
		leftContent.setMinHeight(620);
		leftContent.setStyle("-fx-padding:8;");
		leftContent.setAlignment(Pos.CENTER);

		JFXTabPane tabPane = new JFXTabPane();
		tabPane.setPrefSize(550, 620);
		// tabPane.setMinSize(600, 590);
		// tabPane.setMaxSize(600, 600);

		// 4个 tab 内容中canvas
		List<Canvas> tabCanvas = new ArrayList<Canvas>();

		Tab tab1 = new Tab();
		tab1.setText("视   盘");
		Canvas canvas1 = new Canvas(530, 530);
//		double width1 = 530 * 4;
//		double height1 = 530 * 4;
		GraphicsContext gc1 = canvas1.getGraphicsContext2D();
		RadioViewRepaintUtil.tab1Repaint(gc1);
		
		
		tabCanvas.add(canvas1);
		VBox pane1 = new VBox();
		pane1.setAlignment(Pos.CENTER);
		pane1.getChildren().add(canvas1);
		//Ellipse ellipse = new Ellipse(relativeX1,pan.getRadius() * pan.getScaleX(), relativeY1, pan.getRadius() * pan.getScaleY());
		//pane1.getChildren().add(ellipse);
		JFXRippler rippler5 = new JFXRippler(pane1);
		// JFXRippler rippler7 = new JFXRippler(canvas, RipplerMask.RECT,
		// RipplerPos.FRONT);
		tab1.setContent(rippler5);
		JFXDepthManager.setDepth(rippler5, 5);
		tabPane.getTabs().add(tab1);

		Tab tab2 = new Tab();
		tab2.setText("视   杯");
		Canvas canvas2 = new Canvas(530, 530);
//		double width2 = 530 * 4;
//		double height2 = 530 * 4;
//		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
//		File fileBei = new File(Constant.AnalysisView.getImgPath());
//		InputStream fisBei = null;
//		try {
//			fisBei = new FileInputStream(fileBei);
//		} catch (FileNotFoundException e4) {
//			e4.printStackTrace();
//		}
//		Image img2 = new Image(fisBei, width2, height2, true, true);
//		// 获取当前图片label视盘数据
//		CircleData bei = Constant.ViewBeiAvg;
//		System.out.println("bei = " + bei);
//		gc2.drawImage(img2, (bei.getLeft() + bei.getRadius() * bei.getScaleX()) * 4 - 265,
//				(bei.getTop() + bei.getRadius() * bei.getScaleY()) * 4 - 265, 530, 530, 0, 0, 530, 530);
//		gc2.setLineWidth(bei.getStrokeWidth());
//		gc2.save();
//		gc2.setStroke(Color.rgb(0, 0, 255));
//		double relativeX2 = bei.getLeft() * 4 - ((bei.getLeft() + bei.getRadius() * bei.getScaleX()) * 4 - 265);
//		double relativeY2 = bei.getTop() * 4 - ((bei.getTop() + bei.getRadius() * bei.getScaleY()) * 4 - 265);
//		gc2.setTransform(new Affine(new Rotate(bei.getAngle(), relativeX2, relativeY2)));
//		gc2.strokeOval(relativeX2, relativeY2, bei.getRadius() * bei.getScaleX() * 2 * 4,
//				bei.getRadius() * bei.getScaleY() * 2 * 4);
//		gc2.restore();
		tabCanvas.add(canvas2);
		VBox pane2 = new VBox();
		pane2.setAlignment(Pos.CENTER);
		pane2.getChildren().add(canvas2);
		JFXRippler rippler6 = new JFXRippler(pane2);
		tab2.setContent(rippler6);
		JFXDepthManager.setDepth(rippler6, 5);
		tabPane.getTabs().add(tab2);

		Tab tab3 = new Tab();
		tab3.setText("黄 斑 中 心");
		Canvas canvas3 = new Canvas(530, 530);
//		double width3 = 530 * 4;
//		double height3 = 530 * 4;
//		GraphicsContext gc3 = canvas3.getGraphicsContext2D();
//		FileInputStream fisCenter = null;
//		try {
//			fisCenter = new FileInputStream(new File(Constant.AnalysisView.getImgPath()));
//		} catch (FileNotFoundException e3) {
//			e3.printStackTrace();
//		}
//		Image img3 = new Image(fisCenter, width3, height3, true, true);
//		LineData line = Constant.ViewCenterAvg;
//		gc3.drawImage(img3, (line.getLeft() + line.getWidth() * line.getScaleX() / 2) * 4 - 265,
//				(line.getTop() + line.getHeight() * line.getScaleY() / 2) * 4 - 265, 530, 530, 0, 0, 530, 530);
//		gc3.setLineWidth(line.getStrokeWidth());
//		// 两条线
//		// gc3.save();
//		gc3.setStroke(Color.rgb(255, 0, 255));
//		double relativeX7 = line.getLeft() * 4 - ((line.getLeft() + line.getWidth() * line.getScaleX() / 2) * 4 - 265);
//		double relativeY7 = line.getTop() * 4 - ((line.getTop() + line.getHeight() * line.getScaleY() / 2) * 4 - 265);
//		//System.out.println("line = " + line);
//		//System.out.println(relativeX7 + "=" + relativeY7);
//		gc3.strokeLine(relativeX7, relativeY7 + line.getHeight() * line.getScaleY() * 2,
//				relativeX7 + line.getWidth() * line.getScaleX() * 4,
//				relativeY7 + line.getHeight() * line.getScaleY() * 2);
//		// gc3.restore();
//		// gc3.save();
//		gc3.strokeLine(relativeX7 + line.getWidth() * line.getScaleX() * 2, relativeY7,
//				relativeX7 + line.getWidth() * line.getScaleX() * 2,
//				relativeY7 + line.getHeight() * line.getScaleY() * 4);
//		// gc3.restore();
		tabCanvas.add(canvas3);

		VBox pane3 = new VBox();
		pane3.setAlignment(Pos.CENTER);
		pane3.getChildren().add(canvas3);
		JFXRippler rippler7 = new JFXRippler(pane3);
		tab3.setContent(rippler7);
		JFXDepthManager.setDepth(rippler7, 5);
		tabPane.getTabs().add(tab3);

		Tab tab4 = new Tab();
		tab4.setText("ALL");
		Canvas canvas4 = new Canvas(530, 530);
//		double width4 = 530;
//		double height4 = 530;
//		GraphicsContext gc4 = canvas4.getGraphicsContext2D();
//		File fileAll = new File(Constant.AnalysisView.getImgPath());
//		InputStream fisAll = null;
//		try {
//			fisAll = new FileInputStream(fileAll);
//		} catch (FileNotFoundException e2) {
//			e2.printStackTrace();
//		}
//		Image img4 = new Image(fisAll, width4, height4, true, true);
//		// 获取当前图片label视盘数据
//		CircleData allpan = Constant.ViewPanAvg;
//		gc4.drawImage(img4, 0, 0);
//		gc4.save();
//		gc4.setLineWidth(allpan.getStrokeWidth());
//		gc4.setStroke(Color.rgb(51, 171, 160));
//		double relativeX5 = allpan.getLeft();
//		double relativeY5 = allpan.getTop();
//		gc4.setTransform(new Affine(new Rotate(allpan.getAngle(), relativeX5, relativeY5)));
//		gc4.strokeOval(relativeX5, relativeY5, allpan.getRadius() * allpan.getScaleX() * 2,
//				allpan.getRadius() * allpan.getScaleY() * 2);
//		gc4.restore();
//
//		// 获取当前图片label视杯数据
//		CircleData allbei = Constant.ViewBeiAvg;
//		gc4.save();
//		gc4.setLineWidth(allbei.getStrokeWidth());
//		gc4.setStroke(Color.rgb(0, 0, 255));
//		double relativeX4 = allbei.getLeft();
//		double relativeY4 = allbei.getTop();
//		gc4.setTransform(new Affine(new Rotate(allbei.getAngle(), relativeX4, relativeY4)));
//		gc4.strokeOval(relativeX4, relativeY4, allbei.getRadius() * allbei.getScaleX() * 2,
//				allbei.getRadius() * allbei.getScaleY() * 2);
//		gc4.restore();
//
//		// 获取当前图片label 黄斑中心数据
//		LineData allLine = Constant.ViewCenterAvg;
//		gc4.setLineWidth(line.getStrokeWidth());
//		// 两条线
//		// gc3.save();
//		gc4.setStroke(Color.rgb(255, 0, 255));
//		double relativeX8 = allLine.getLeft();
//		double relativeY8 = allLine.getTop();
//		gc4.strokeLine(relativeX8, relativeY8 + allLine.getHeight() * allLine.getScaleY() / 2,
//				relativeX8 + allLine.getWidth() * allLine.getScaleX(),
//				relativeY8 + allLine.getHeight() * allLine.getScaleY() / 2);
//		// gc3.restore();
//		// gc3.save();
//		gc4.strokeLine(relativeX8 + allLine.getWidth() * allLine.getScaleX() / 2, relativeY8,
//				relativeX8 + allLine.getWidth() * allLine.getScaleX() / 2,
//				relativeY8 + allLine.getHeight() * allLine.getScaleY());
//		// gc3.restore();

		tabCanvas.add(canvas4);
		// 初始化Constant TabCanvas
		Constant.TabCanvas = tabCanvas;

		VBox pane4 = new VBox();
		pane4.setAlignment(Pos.CENTER);
		pane4.getChildren().add(canvas4);
		JFXRippler rippler8 = new JFXRippler(pane4);
		tab4.setContent(rippler8);
		JFXDepthManager.setDepth(rippler8, 5);
		tabPane.getTabs().add(tab4);

		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(0);

		// 监听tab change
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab) {
				System.out.println("change 了  = " + oldTab.getText() + "  " + newTab.getText());
				// 重绘右侧
				TabViewRepaintUtil.tabRepaint(newTab.getText());
			}
		});

		HBox hbox = new HBox();
		hbox.getChildren().addAll(tabPane);
		hbox.setSpacing(50);
		hbox.setMaxWidth(600);
		hbox.setAlignment(Pos.CENTER);
		leftContent.getChildren().add(hbox);

		HBox bottombox = new HBox();
		bottombox.setStyle("-fx-padding:30;");
		bottombox.setSpacing(5);
		bottombox.setAlignment(Pos.CENTER);

		HBox radioBox = new HBox();
		radioBox.setStyle("-fx-padding:15;");
		radioBox.setAlignment(Pos.CENTER);
		final ToggleGroup group = new ToggleGroup();

		JFXRadioButton javaRadio = new JFXRadioButton("平  均");
		javaRadio.setPadding(new Insets(10));
		javaRadio.setSelected(true);
		javaRadio.setStyle("-fx-text-fill: GREEN;-fx-font-size: 20;");
		javaRadio.setToggleGroup(group);
		javaRadio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("平均radio change 了 " + javaRadio.getText());
				RadioViewRepaintUtil.avgRepaint();
			}
		});

		JFXRadioButton jfxRadio = new JFXRadioButton("重  叠");
		jfxRadio.setPadding(new Insets(10));
		jfxRadio.setStyle("-fx-text-fill: RED;-fx-font-size: 20;");
		jfxRadio.setToggleGroup(group);
		Constant.radio = jfxRadio;
		jfxRadio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				System.out.println("重叠radio change 了 " + jfxRadio.getText());
				RadioViewRepaintUtil.overLapRepaint();
			}
		});
		
		JFXRadioButton maskRadio = new JFXRadioButton("Mask");
        maskRadio.setPadding(new Insets(10));
        maskRadio.setStyle("-fx-text-fill: RED;-fx-font-size: 18;");
        maskRadio.setToggleGroup(group);
        Constant.maskradio = maskRadio;

        radioBox.getChildren().add(javaRadio);
        radioBox.getChildren().add(maskRadio);
        radioBox.getChildren().add(jfxRadio);
        radioBox.setSpacing(5);
        
//        JFXSlider horRightSlider = new JFXSlider();
//        //horRightSlider.setMinWidth(500);
//        //horRightSlider.setIndicatorPosition(IndicatorPosition.RIGHT);
//        radioBox.getChildren().add(horRightSlider);
//        horRightSlider.valueProperty().addListener(new ChangeListener<Number>() {  
//    		@Override  
//    		public void changed(ObservableValue<? extends Number> ov, Number old, Number newvalue) {  
//    			
//            	double s = horRightSlider.getValue();
//    			Constant.SlideValue = s;
//    			SlideChangeUtil.repaint(s);
//    		}
//    	});
        //horRightSlider.setValue(Double.parseDouble(Constant.AnalysisView.getPercent()));

        maskRadio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				System.out.println("maskradio change 了 " + maskRadio.getText());
				RadioViewRepaintUtil.maskRepaint();
			}
        });
        
		bottombox.getChildren().add(radioBox);
		// bottombox.getChildren().add(allcheckbox);
		leftContent.getChildren().add(bottombox);
		root.getChildren().add(leftContent);

		// leftContent.setCenter(hbox);
		// root.setCenter(leftContent);

		HBox centerContent = new HBox();
		centerContent.setMinWidth(20);
		centerContent.setMaxWidth(20);
		root.getChildren().add(centerContent);

		VBox rightContent = new VBox();
		rightContent.setStyle("-fx-padding:8;");
		rightContent.setMaxWidth(640);
		rightContent.setMinWidth(640);
		rightContent.setMaxHeight(620);
		rightContent.setMinHeight(620);

		BorderPane area1 = new BorderPane();
		HBox area2 = new HBox();
		area1.setMinHeight(40);
		area1.setMaxHeight(40);
		Label stand = new Label("标   准:  ");
		stand.setStyle("-fx-text-fill: GREEN;-fx-font-size: 20;");
		stand.setAlignment(Pos.CENTER);
		area2.getChildren().add(stand);

		JFXComboBox<Label> jfxCombo = new JFXComboBox<Label>();
		jfxCombo.setMinHeight(25);
		jfxCombo.setMaxHeight(25);
		jfxCombo.setStyle("-fx-font-size: 16;");
		for (int i = 0; i < Constant.AnalysisView.getAllLabelData().size(); i++) {
			String s = Constant.AnalysisView.getAllLabelData().get(i);
			//System.out.println("s = " + s);
			//System.out.println(" == " + Constant.AnalysisMix.getStandUser());
			if(Constant.AnalysisView.getStandUser() != null) {
				if(Constant.AnalysisView.getStandUser().equals(s.split("=")[0])) {
					//System.out.println("s = " + s);
					//System.out.println(Constant.AnalysisMix.getStandUser());
					jfxCombo.getItems().add(new Label(s.split("=")[0]));
					jfxCombo.getSelectionModel().select(i);
				}
				else {
					jfxCombo.getItems().add(new Label(s.split("=")[0]));
				}
			}
			else {
				jfxCombo.getItems().add(new Label(s.split("=")[0]));
			}
		}

		jfxCombo.setPromptText("请选择一个作为标准");
		jfxCombo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int index = jfxCombo.getSelectionModel().getSelectedIndex();
				System.out.println(jfxCombo.getItems().get(index).getText());
				ComboxViewUtil.reTextOverLap(jfxCombo.getItems().get(index).getText());
			}
		});
		area2.getChildren().add(jfxCombo);

		Label labelText = new Label(
				"      当前:" + (Constant.AnalysisView.getFlag() + 1) + " / " + Constant.AnalysisMix.getImgNums());
		labelText.setStyle("-fx-text-fill: GREEN;-fx-font-size: 20;");
		labelText.setAlignment(Pos.CENTER);
		area2.getChildren().add(labelText);
		area1.setLeft(area2);

		Label analysis = new Label("已融合");
		analysis.setStyle("-fx-text-fill: RED;-fx-font-size: 20;");
		area1.setRight(analysis);

		rightContent.getChildren().add(area1);

		// ScrollPane添加画布
		JFXScrollPaneMark pane = new JFXScrollPaneMark();
		pane.setMinSize(620, 530);
		Label scrollTitle = new Label("所有专家标注列表");
		// pane.getTopBar().getChildren().add(scrollTitle);
		pane.getBottomBar().getChildren().add(scrollTitle);
		scrollTitle.setStyle("-fx-text-fill:WHITE; -fx-font-size: 28;");
		JFXScrollPaneMark.smoothScrolling((ScrollPane) pane.getChildren().get(0));
		StackPane.setMargin(scrollTitle, new Insets(0, 0, 0, 30));
		StackPane.setAlignment(scrollTitle, Pos.CENTER_LEFT);
		showScroll(pane);

		HBox btnBox = new HBox();
		btnBox.setStyle("-fx-padding:20;");
		btnBox.setSpacing(30);
		btnBox.setMinHeight(50);
		btnBox.setAlignment(Pos.CENTER);
		JFXButton button1 = new JFXButton("上一张");
		button1.setStyle(
				"-fx-background-color: rgb(24, 128, 56);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ViewUtil.preImage(stage);
			}
		});
		btnBox.getChildren().add(button1);

		JFXButton button2 = new JFXButton("下一张");
		button2.setStyle(
				"-fx-background-color: rgb(24, 128, 56);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				ViewUtil.nextImage(stage);
			}
		});
		btnBox.getChildren().add(button2);

		JFXButton button4 = new JFXButton("返   回");
		button4.setStyle(
				"-fx-background-color: RED;-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
		button4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				Stage stagenew = new Stage();
        		stagenew.initStyle(StageStyle.TRANSPARENT);
        		Main main = new Main();
        		try {
					main.start(stagenew);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        		stage.close();
			}
		});
		btnBox.getChildren().add(button4);

		rightContent.getChildren().add(pane);
		rightContent.getChildren().add(btnBox);
		root.getChildren().add(rightContent);

		if(Constant.AnalysisView.getMask() == null) {
			BtnUpdateUtil.viewInit();
		}
		Constant.CurrTab = 0;
		// 组装
		// root.getChildren().addAll(leftContent, rightContent);
		Scene scene = new Scene(root, 1260, 640);
		stage.setScene(scene);
		stage.setResizable(false);
		// 添加窗体拉伸效果
		// DrawUtil.addDrawFunc(stage, root);
		// 显示
		stage.show();
		}
		
		private static void showScroll(JFXScrollPaneMark pane) {
			VBox box = new VBox();
			box.setSpacing(20);
	    	int size = Constant.AnalysisView.getAllLabelData().size();
	    	List<Canvas> canvasList = new ArrayList<Canvas>();
	    	List<JFXCheckBox> checkboxList = new ArrayList<JFXCheckBox>();
	    	List<Label> labelList = new ArrayList<Label>();
	    	List<ChangeListener<Boolean>> listenerList = new ArrayList<ChangeListener<Boolean>>();
	    	for(int i = 0; i < size; i++) {
	    		    		
	    		HBox inner = new HBox();
	    		inner.setSpacing(5);
	    		inner.setAlignment(Pos.CENTER);
//	    		VBox vbox = new VBox();
//	    		vbox.setMinSize(100, 500);
//	    		inner.getChildren().add(vbox);
	    		Canvas canvas = new Canvas(530, 530);
	    		GraphicsContext gc = canvas.getGraphicsContext2D();
	    		FileInputStream fis = null;
				try {
					fis = new FileInputStream(new File(Constant.AnalysisView.getImgPath()));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
	    		Image innerImg = new Image(fis, 530*4, 530*4, true, true);
	    		//获取当前图片label视盘数据
	            CircleData solepan = LabelUtil.getSoleLabelDataView(i);
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
	        	inner.getChildren().add(canvas);
	        	canvasList.add(canvas);
	        	
	        	VBox innerBox = new VBox();
	        	innerBox.setSpacing(30);
	        	innerBox.setAlignment(Pos.CENTER);
	        	Label innerLabel = new Label(Constant.AnalysisView.getAllLabelData().get(i).split("=")[0]);
	        	innerLabel.setStyle("-fx-text-fill:rgb(77,102,204);-fx-font-size: 18px;");
	        	innerBox.getChildren().add(innerLabel);
	        	Label innerLabel1 = new Label("覆盖率: 1.0");
	        	innerLabel1.setStyle("-fx-text-fill:RED");
	        	labelList.add(innerLabel1);
	        	innerBox.getChildren().add(innerLabel1);
	        	HBox labbox = new HBox();
	        	Label innerLabel3 = new Label("重叠色");
	        	innerLabel3.setStyle("-fx-text-fill:" + Constant.ColorList.get(i).getColorStr());
	        	Label innerlabel2 = new Label("");
	        	innerlabel2.setMinSize(15, 15);
	        	innerlabel2.setStyle("-fx-background-color:" + Constant.ColorList.get(i).getColorStr());
	        	labbox.getChildren().add(innerLabel3);
	        	labbox.getChildren().add(innerlabel2);
	        	innerBox.getChildren().add(labbox);
	        	JFXCheckBox jfxCheckBox = new JFXCheckBox("融合");
	        	checkboxList.add(jfxCheckBox);
	        	//还原CheckBox状态
	        	//jfxCheckBox.setSelected(false);
	        	jfxCheckBox.setDisable(true);
	        	
	        	ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {  
	        		@Override  
	        		public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {  
	        			CheckBoxChangeUtil.repaint();
	        		}
	        	};
	        	listenerList.add(listener);
	        	jfxCheckBox.selectedProperty().addListener(listener);  
	        	innerBox.getChildren().add(jfxCheckBox);
	        	inner.getChildren().add(innerBox);
	        	box.getChildren().add(inner);
	    	}
	    	Constant.canvasList = canvasList;
	    	Constant.CheckBoxList = checkboxList;
	    	Constant.LabelList = labelList;
	    	Constant.ListenerList = listenerList;
	    	
	    	//恢复CheckBox状态
	    	TabViewRepaintUtil.recoverCheckBox();
	    	//覆盖率恢复
	    	if(Constant.AnalysisView.getStandUser() != null) {
	    		ComboxViewUtil.reTextOverLap(Constant.AnalysisView.getStandUser());
	    	}

	        // demo: draw a line of the canvas size and a rectangle of the viewport size => the rectangle must always be in the center
	    	pane.setContent(box);
		}
	}

