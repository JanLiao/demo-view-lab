package com.cvte.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.cvte.cons.Constant;
import com.cvte.entity.CircleData;
import com.cvte.entity.LineData;
import com.cvte.entity.Mask;
import com.cvte.util.BtnUpdateUtil;
import com.cvte.util.CheckBoxChangeUtil;
import com.cvte.util.CircleTask;
import com.cvte.util.ComboxChangeUtil;
import com.cvte.util.LabelUtil;
import com.cvte.util.MaskUtil;
import com.cvte.util.PaintTask;
import com.cvte.util.RadioChangeRepaintUtil;
import com.cvte.util.SlideChangeUtil;
import com.cvte.util.TabChangeRepaintUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXDrawersStack;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXDrawer.DrawerDirection;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** 
* @author: jan 
* @date: 2018年5月27日 上午12:56:13 
*/
public class MarkUpdate2 {

	public static void mixImage(Stage stage) {
		long startstage = System.currentTimeMillis();
		JFXDrawersStack drawersStack = new JFXDrawersStack();
		HBox root = new HBox();
		root.setAlignment(Pos.CENTER);
		//root.setPrefSize(620, 620);
		
		VBox leftContent = new VBox();
		leftContent.setMaxWidth(560);
		leftContent.setMinWidth(560);
		leftContent.setMaxHeight(620);
		leftContent.setMinHeight(620);
		leftContent.setStyle("-fx-padding:8;");
		leftContent.setAlignment(Pos.CENTER);

		JFXTabPane tabPane = new JFXTabPane();
		tabPane.setPrefSize(550, 620);
        tabPane.setMinSize(570, 490);

		// 4个 tab 内容中canvas
		List<Canvas> tabCanvas = new ArrayList<Canvas>();

		Tab tab1 = new Tab();
		tab1.setText("视   盘");
		Canvas canvas1 = new Canvas(530, 530);
		
		tabCanvas.add(canvas1);
		VBox pane1 = new VBox();
		pane1.setAlignment(Pos.CENTER);
		pane1.getChildren().add(canvas1);
		JFXRippler rippler5 = new JFXRippler(pane1);
		// JFXRippler rippler7 = new JFXRippler(canvas, RipplerMask.RECT,
		// RipplerPos.FRONT);
		tab1.setContent(rippler5);
		JFXDepthManager.setDepth(rippler5, 5);
		tabPane.getTabs().add(tab1);
		
		SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
		selectionModel.select(0);
		//selectionModel.select(5);

		// 监听tab change
		tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab) {
				System.out.println("change 了  = " + oldTab.getText() + "  " + newTab.getText());
				// 重绘右侧
				TabChangeRepaintUtil.tabRepaint(newTab.getText());
			}
		});
		leftContent.getChildren().add(tabPane);
		root.getChildren().add(leftContent);

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
		for (int i = 0; i < Constant.AnalysisMix.getAllLabelData().size(); i++) {
			String s = Constant.AnalysisMix.getAllLabelData().get(i);
			//System.out.println("s = " + s);
			//System.out.println(" == " + Constant.AnalysisMix.getStandUser());
			if(Constant.AnalysisMix.getStandUser() != null) {
				if(Constant.AnalysisMix.getStandUser().equals(s.split("=")[0])) {
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

		jfxCombo.setPromptText("未选择");
		jfxCombo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				int index = jfxCombo.getSelectionModel().getSelectedIndex();
				System.out.println(jfxCombo.getItems().get(index).getText());
				ComboxChangeUtil.reTextOverLap(jfxCombo.getItems().get(index).getText());
			}
		});
		area2.getChildren().add(jfxCombo);

		String tmpstr = Constant.AnalysisMix.getImgName();
        Label lbt = new Label("");
        if(tmpstr.length() > 10) {
        	lbt.setText("  当前: " + tmpstr.substring(0, 10) + "...");
        }else {
        	lbt.setText("  当前: " + tmpstr);
        }
        lbt.setTooltip(new Tooltip(tmpstr));
        lbt.setStyle("-fx-text-fill: #9370db;-fx-font-size: 18;");
        area2.getChildren().add(lbt);
        
        Label labelText = new Label("     " + (Constant.AnalysisMix.getFlag() + 1) 
        + " / " + Constant.AnalysisMix.getImgNums());
        labelText.setStyle("-fx-text-fill: GREEN;-fx-font-size: 18;");
		labelText.setAlignment(Pos.CENTER);
		area2.getChildren().add(labelText);
		area1.setLeft(area2);

		String mixStr = "";
		if (Constant.AnalysisMix.getImgMap()
				.get(Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag())) == 0) {
			mixStr = "未融合";
		} else {
			mixStr = "已融合";
		}
		Label analysis = new Label(mixStr);
		if ("已融合".equals(mixStr)) {
			analysis.setStyle("-fx-text-fill: RED;-fx-font-size: 20;");
		} else {
			analysis.setStyle("-fx-text-fill: GREEN;-fx-font-size: 20;");
		}
		area1.setRight(analysis);

		rightContent.getChildren().add(area1);

		// ScrollPane添加画布
		JFXScrollPaneMark pane = new JFXScrollPaneMark();
		pane.setMinSize(660, 530);
		Label scrollTitle = new Label("所有专家标注列表");
		// pane.getTopBar().getChildren().add(scrollTitle);
		pane.getBottomBar().getChildren().add(scrollTitle);
		scrollTitle.setStyle("-fx-text-fill:WHITE; -fx-font-size: 28;");
		JFXScrollPaneMark.smoothScrolling((ScrollPane) pane.getChildren().get(0));
		StackPane.setMargin(scrollTitle, new Insets(0, 0, 0, 30));
		StackPane.setAlignment(scrollTitle, Pos.CENTER_LEFT);
		//showScroll(pane);

		HBox btnBox = new HBox();
		btnBox.setStyle("-fx-padding:20;");
		btnBox.setSpacing(30);
		btnBox.setMinHeight(50);
		btnBox.setAlignment(Pos.CENTER);
				
		// Drawer right
		JFXDrawer rightDrawer = new JFXDrawer();
		StackPane rightDrawerPane = new StackPane();
		rightDrawerPane.setStyle("-fx-background-color: rgb(250, 250, 250);" + "-fx-text-fill: rgba(0, 0, 0, 0.87);");
		RightBox.setBox(rightDrawerPane);
		rightDrawer.setDirection(DrawerDirection.RIGHT);
		rightDrawer.setDefaultDrawerSize(250);
		rightDrawer.setSidePane(rightDrawerPane);
		rightDrawer.setOverLayVisible(false);
		rightDrawer.setResizableOnDrag(true);
		
		JFXHamburger h4 = new JFXHamburger();
		HamburgerNextArrowBasicTransition burgerTask3 = new HamburgerNextArrowBasicTransition(h4);
		// burgerTask3.setStyle("-fx-background-color: GREEN;-fx-pref-height: 15px;");
		burgerTask3.setRate(-1);
		h4.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> {
			burgerTask3.setRate(burgerTask3.getRate() * -1);
			burgerTask3.play();
			System.out.println(999);
			drawersStack.toggle(rightDrawer);
		});
		btnBox.getChildren().add(h4);		
		
		JFXButton button1 = new JFXButton("上一张");
		button1.setStyle(
				"-fx-background-color: rgb(24, 128, 56);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				BtnUpdateUtil.preImage(stage);
			}
		});
		btnBox.getChildren().add(button1);

		JFXButton button2 = new JFXButton("下一张");
		button2.setStyle(
				"-fx-background-color: rgb(24, 128, 56);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				BtnUpdateUtil.nextImage(stage);
			}
		});
		btnBox.getChildren().add(button2);

		JFXButton button3 = new JFXButton("修   改");
		button3.setStyle(
				"-fx-background-color: rgb(77,102,204);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				BtnUpdateUtil.saveImage(stage);
			}
		});
		btnBox.getChildren().add(button3);

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
		drawersStack.setContent(root);

		// 组装
		// root.getChildren().addAll(leftContent, rightContent);
		Scene scene = new Scene(drawersStack, 1260, 640);
		stage.setScene(scene);
		// 添加窗体拉伸效果
		// DrawUtil.addDrawFunc(stage, root);
		// 显示
		stage.setResizable(false);
		Screen screen = Screen.getPrimary();  
        Rectangle2D bounds = screen.getVisualBounds();  
        System.out.println(bounds.getMinX() + "=" + bounds.getMinY());
        System.out.println(bounds.getWidth() + "=" + bounds.getHeight());
		stage.show();
		ExecutorService executor = Executors.newFixedThreadPool(10);
        CircleTask task1 = new CircleTask();
        //task1.setCacher(cacher);
        //executor.execute(task1);
        executor.submit(task1);
		//TabChangeRepaintUtil.tabRepaint("ALL");
        for(int i = 0; i < Constant.canvasList.size(); i++) {
        	PaintTask task = new PaintTask();
        	task.setCanvas(Constant.canvasList.get(i));
        	task.setNum(i);
        	//executor.execute(task);
        	executor.submit(task);
        }

        System.out.println("界面打开总耗时 = " + (System.currentTimeMillis() - startstage));
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
		
		private static void showScroll(JFXScrollPaneMark pane) {
			VBox box = new VBox();
			box.setSpacing(20);
	    	int size = Constant.AnalysisMix.getAllLabelData().size();
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
	        	inner.getChildren().add(canvas);
	        	canvasList.add(canvas);
	        	
	        	VBox innerBox = new VBox();
	        	innerBox.setSpacing(30);
	        	innerBox.setAlignment(Pos.CENTER);
	        	Label innerLabel = new Label(Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0]);
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
	        	jfxCheckBox.setSelected(false);
	        	
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
	    	TabChangeRepaintUtil.recoverCheckBox();
	    	System.out.println("为空 ==" + Constant.AnalysisMix.getStandUser());
	    	//覆盖率恢复
	    	if(Constant.AnalysisMix.getStandUser() != null) {
	    		System.out.println("selected = " + Constant.AnalysisMix.getStandUser());
	    		ComboxChangeUtil.reTextOverLap(Constant.AnalysisMix.getStandUser());
	    	}

	        // demo: draw a line of the canvas size and a rectangle of the viewport size => the rectangle must always be in the center
	    	pane.setContent(box);
		}
	}

