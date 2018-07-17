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
import com.cvte.util.BtnUtil;
import com.cvte.util.CheckBoxChangeUtil;
import com.cvte.util.CircleTask;
import com.cvte.util.ComboxChangeUtil;
import com.cvte.util.ImageTask;
import com.cvte.util.InputStreamCacher;
import com.cvte.util.LabelUtil;
import com.cvte.util.MaskUtil;
import com.cvte.util.PaintTask;
import com.cvte.util.RadioChangeRepaintUtil;
import com.cvte.util.SlideChangeUtil;
import com.cvte.util.TabChangeRepaintUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXSlider.IndicatorPosition;
import com.jfoenix.effects.JFXDepthManager;

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
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
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
* @date: 2018年5月27日 上午12:55:45 
*/
public class MarkMix {

	public static void notMix(Stage stage) {
		long startstage = System.currentTimeMillis();
		System.out.println("开始==============");
		//inputStream重复使用
//		FileInputStream fis = null;
//		try {
//			fis = new FileInputStream(new File(Constant.AnalysisMix.getImgPath()));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		InputStreamCacher cacher = new InputStreamCacher(fis);
//		InputStreamCacher cacher = Constant.cacher;
		
//		ImageTask imgTask = new ImageTask();
//		imgTask.setCacher(cacher);
//		Thread imgThread = new Thread(imgTask);
//		imgThread.start();
		
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);
		
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
        
        
        //4个 tab 内容中canvas
        List<Canvas> tabCanvas = new ArrayList<Canvas>();
        
        Tab tab1 = new Tab();
        tab1.setText("视   盘");
        Canvas canvas1 = new Canvas(530, 530);

        tabCanvas.add(canvas1);
        VBox pane1 = new VBox();
        pane1.setAlignment(Pos.CENTER);
        pane1.getChildren().add(canvas1);
        JFXRippler rippler5 = new JFXRippler(pane1);
        //rippler5.setMinSize(600, 600);
        //JFXRippler rippler7 = new JFXRippler(canvas, RipplerMask.RECT, RipplerPos.FRONT);
        tab1.setContent(rippler5);
        tab1.getContent().setScaleY(1);
        JFXDepthManager.setDepth(rippler5, 5);
        tabPane.getTabs().add(tab1);
        
        Tab tab2 = new Tab();
        tab2.setText("视   杯");
        Canvas canvas2 = new Canvas(530, 530);
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
    	tabCanvas.add(canvas3);

        VBox pane3 = new VBox();
        pane3.setAlignment(Pos.CENTER);
        pane3.getChildren().add(canvas3);
        JFXRippler rippler7 = new JFXRippler(pane3);
        tab3.setContent(rippler7);
        JFXDepthManager.setDepth(rippler7, 5);
        tabPane.getTabs().add(tab3);
        
        Tab tab4 = new Tab();
        tab4.setText("AVG");
        Canvas canvas4 = new Canvas(530, 530);    	
    	tabCanvas.add(canvas4);
    	
        VBox pane4 = new VBox();
        pane4.setAlignment(Pos.CENTER);
        pane4.getChildren().add(canvas4);
        JFXRippler rippler8 = new JFXRippler(pane4);
        tab4.setContent(rippler8);
        JFXDepthManager.setDepth(rippler8, 5);
        tabPane.getTabs().add(tab4);
        
        Tab tab5 = new Tab();
        tab5.setText("MASK");
        Canvas canvas5 = new Canvas(530, 530);
    	
    	tabCanvas.add(canvas5);        
        VBox pane5 = new VBox();
        pane5.setAlignment(Pos.CENTER);
        pane5.getChildren().add(canvas5);
        JFXRippler rippler9 = new JFXRippler(pane5);
        tab5.setContent(rippler9);
        JFXDepthManager.setDepth(rippler9, 5);
        tabPane.getTabs().add(tab5);
        
        Tab tab6 = new Tab();
        tab6.setText("ALL");
        Canvas canvas6 = new Canvas(530, 530);
    	
    	tabCanvas.add(canvas6);
    	//初始化Constant TabCanvas
    	Constant.TabCanvas = tabCanvas;
        
        VBox pane6 = new VBox();
        pane6.setAlignment(Pos.CENTER);
        pane6.getChildren().add(canvas6);
        JFXRippler rippler10 = new JFXRippler(pane6);
        tab6.setContent(rippler10);
        JFXDepthManager.setDepth(rippler10, 5);
        tabPane.getTabs().add(tab6);
        
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);
        
        //监听tab change
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
			@Override
			public void changed(ObservableValue<? extends Tab> old, Tab oldTab, Tab newTab) {
				System.out.println("change 了  = " + oldTab.getText() + "  " + newTab.getText());
				//重绘右侧
				TabChangeRepaintUtil.tabRepaint(newTab.getText());
			}
        });
        
        
        //main.getChildren().add(tabPane);
        HBox hbox = new HBox();
		hbox.getChildren().addAll(tabPane);
		hbox.setSpacing(50);
		hbox.setMaxWidth(600);
		hbox.setAlignment(Pos.CENTER);
        StackPane panes = new StackPane();
        panes.getChildren().add(hbox);
        StackPane.setMargin(root, new Insets(350));
        panes.setStyle("-fx-background-color:WHITE");
        leftContent.getChildren().add(panes);
        
        VBox bottombox = new VBox();
        bottombox.setStyle("-fx-padding:10;");
        bottombox.setSpacing(5);
        bottombox.setAlignment(Pos.CENTER);
        
        HBox radioBox = new HBox();
        radioBox.setStyle("-fx-padding:10;");
        radioBox.setAlignment(Pos.CENTER);
        
        //保存方式
//        JFXComboBox<Label> saveModel = new JFXComboBox<Label>();
//        saveModel.setMinHeight(25);
//        saveModel.setMaxHeight(25);
//        saveModel.setStyle("-fx-font-size: 16;");
//        saveModel.getItems().add(new Label("两种"));
//        saveModel.getItems().add(new Label("平均"));
//        saveModel.getItems().add(new Label("Mask"));
//        saveModel.setPromptText("保存方式");
//        saveModel.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				int index = saveModel.getSelectionModel().getSelectedIndex();
//				System.out.println(saveModel.getItems().get(index).getText());
//				ComboxChangeUtil.saveModel(saveModel.getItems().get(index).getText());
//			}
//        });
//        radioBox.getChildren().add(saveModel);
        
        final ToggleGroup group = new ToggleGroup();

        JFXRadioButton javaRadio = new JFXRadioButton("平  均");
        javaRadio.setPadding(new Insets(10));
        javaRadio.setSelected(true);
        javaRadio.setStyle("-fx-text-fill: GREEN;-fx-font-size: 18;");
        javaRadio.setToggleGroup(group);
        Constant.avgradio = javaRadio;

        JFXRadioButton jfxRadio = new JFXRadioButton("重  叠");
        jfxRadio.setPadding(new Insets(10));
        jfxRadio.setStyle("-fx-text-fill: RED;-fx-font-size: 18;");
        jfxRadio.setToggleGroup(group);
        Constant.radio = jfxRadio;
        
        JFXRadioButton maskRadio = new JFXRadioButton("Mask");
        maskRadio.setPadding(new Insets(10));
        maskRadio.setStyle("-fx-text-fill: BLUE;-fx-font-size: 18;");
        maskRadio.setToggleGroup(group);
        Constant.maskradio = maskRadio;

        radioBox.getChildren().add(javaRadio);
        radioBox.getChildren().add(maskRadio);
        radioBox.getChildren().add(jfxRadio);
        radioBox.setSpacing(5);
        
        VBox vb = new VBox();
        vb.setSpacing(2);
        vb.setAlignment(Pos.CENTER);
        int vblv = (int)Constant.SlideValue*Constant.AnalysisMix.getAllLabelData().size()/100;
        Label vbLabel1 = new Label("" + vblv);
        Constant.fenzi = vbLabel1;
        vb.getChildren().add(vbLabel1);
        JFXSlider horRightSlider = new JFXSlider();
        horRightSlider.setDisable(true);
        Constant.slider = horRightSlider;
        vb.getChildren().add(horRightSlider);
        //horRightSlider.setMinWidth(500);
        //horRightSlider.setIndicatorPosition(IndicatorPosition.RIGHT);
        Label vbLabel2 = new Label("" + Constant.AnalysisMix.getAllLabelData().size());
        Constant.fenmu = vbLabel2;
        vb.getChildren().add(vbLabel2);
        radioBox.getChildren().add(vb);
        
        ChangeListener<Number> slideListen = new ChangeListener<Number>() {  
    		@Override  
    		public void changed(ObservableValue<? extends Number> ov, Number old, Number newvalue) {  
    			
            	double s = horRightSlider.getValue();
    			//Constant.SlideValue = s;
    			SlideChangeUtil.repaint(s);
    		}
    	};
    	Constant.SlideListener = slideListen;
    	horRightSlider.valueProperty().addListener(slideListen);
//        horRightSlider.valueProperty().addListener(new ChangeListener<Number>() {  
//    		@Override  
//    		public void changed(ObservableValue<? extends Number> ov, Number old, Number newvalue) {  
//    			
//            	double s = horRightSlider.getValue();
//    			//Constant.SlideValue = s;
//    			SlideChangeUtil.repaint(s);
//    		}
//    	});
        
        javaRadio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				System.out.println("平均radio change 了 " + javaRadio.getText());
				RadioChangeRepaintUtil.avgRepaint();
			}
        });
        
        jfxRadio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				
				System.out.println("重叠radio change 了 " + jfxRadio.getText());
				RadioChangeRepaintUtil.overLapRepaint();
			}
        });
        
        maskRadio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//重置mask curmask
				Mask mask = Constant.AnalysisMix.getMask();
				int[][] curPanMask = MaskUtil.getCurMask("pan", mask);
				int[][] curBeiMask = MaskUtil.getCurMask("bei", mask);
				Constant.AnalysisMix.getMask().setCurPanMask(curPanMask);
				Constant.AnalysisMix.getMask().setCurBeiMask(curBeiMask);
				System.out.println("maskradio change 了 " + maskRadio.getText());
				RadioChangeRepaintUtil.maskRepaint();
			}
        });

        bottombox.getChildren().add(radioBox);
        
        HBox hbb = new HBox();
        hbb.setSpacing(10);
        hbb.setAlignment(Pos.CENTER);
        
        List<JFXCheckBox> boxList = new ArrayList<JFXCheckBox>();
        List<Label> coverlist = new ArrayList<Label>();
        List<ChangeListener<Boolean>> lisList = new ArrayList<ChangeListener<Boolean>>();
        int size = Constant.AnalysisMix.getAllLabelData().size();
        for(int i = 0; i < size; i++) {
        	VBox hb = new VBox();
        	hb.setSpacing(10);
        	JFXCheckBox jfxCheckBox = new JFXCheckBox(Constant.AnalysisMix.getAllLabelData().get(i).split("=")[0]);
        	jfxCheckBox.setStyle("-fx-font-size : 16;");
        	jfxCheckBox.setStyle("-fx-text-fill:" + Constant.ColorList.get(i).getColorStr());
        	boxList.add(jfxCheckBox);
        	jfxCheckBox.setSelected(true);
        	ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {  
        		@Override  
        		public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {  
        			CheckBoxChangeUtil.checkboxChanged();
        		}
        	};
        	Label label = new Label("1.0");
        	label.setStyle("-fx-font-size : 16;");
        	coverlist.add(label);
        	hb.getChildren().addAll(jfxCheckBox, label);
        	lisList.add(listener);
        	jfxCheckBox.selectedProperty().addListener(listener);  
        	hbb.getChildren().add(hb);
        }
        Constant.LeftList = boxList;
        Constant.ListenerLeftList = lisList;
        Constant.CoverList = coverlist;
        bottombox.getChildren().add(hbb);
        
        //bottombox.getChildren().add(allcheckbox);
        leftContent.getChildren().add(bottombox);
        root.getChildren().add(leftContent);
        
        //leftContent.setCenter(hbox);
        //root.setCenter(leftContent);
        
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
        for(String s : Constant.AnalysisMix.getAllLabelData()) {
        	jfxCombo.getItems().add(new Label(s.split("=")[0]));
        }

        jfxCombo.setPromptText("请选择一个作为标准");
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
        
        Label labelText = new Label("      " + (Constant.AnalysisMix.getFlag() + 1) 
        + " / " + Constant.AnalysisMix.getImgNums());
        labelText.setStyle("-fx-text-fill: GREEN;-fx-font-size: 18;");
        labelText.setAlignment(Pos.CENTER);
        area2.getChildren().add(labelText);
        area1.setLeft(area2);
        
        String mixStr = "";
        if(Constant.AnalysisMix.getImgMap().get(Constant.AnalysisMix.getImgList().get(Constant.AnalysisMix.getFlag())) == 0) {
        	mixStr = "未融合";
        }
        else {
        	mixStr = "已融合";
        }
        Label analysis = new Label(mixStr);
        if("已融合".equals(mixStr)) {
        	analysis.setStyle("-fx-text-fill: RED;-fx-font-size: 20;");
        }
        else {
        	analysis.setStyle("-fx-text-fill: GREEN;-fx-font-size: 20;");
        }
        area1.setRight(analysis);
        
        rightContent.getChildren().add(area1);
        
        //ScrollPane添加画布
        JFXScrollPaneMark pane = new JFXScrollPaneMark();
        pane.setMinSize(640, 530);
        Label scrollTitle = new Label("所有专家标注列表");
        //pane.getTopBar().getChildren().add(scrollTitle);
        pane.getBottomBar().getChildren().add(scrollTitle);
        scrollTitle.setStyle("-fx-text-fill:WHITE; -fx-font-size: 28;");
        JFXScrollPaneMark.smoothScrolling((ScrollPane) pane.getChildren().get(0));
        StackPane.setMargin(scrollTitle, new Insets(0, 0, 0, 30));
        StackPane.setAlignment(scrollTitle, Pos.CENTER_LEFT);
//        CircleTask task = new CircleTask();
//        task.setCacher(cacher);
//        task.setCanvas(canvas1);
//        Thread t = new Thread(task);
//        t.start();
        showScroll(pane);
        
        HBox btnBox = new HBox();
        btnBox.setStyle("-fx-padding:20;");
        btnBox.setSpacing(30);
        btnBox.setMinHeight(50);
        btnBox.setAlignment(Pos.CENTER);
        JFXButton button1 = new JFXButton("上一张");
        button1.setStyle("-fx-background-color: rgb(24, 128, 56);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
        button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				BtnUtil.preImage(stage);
			}
        });
        btnBox.getChildren().add(button1);
        
        JFXButton button2 = new JFXButton("下一张");
        button2.setStyle("-fx-background-color: rgb(24, 128, 56);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
        button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				BtnUtil.nextImage(stage);
			}
        });
        btnBox.getChildren().add(button2);
        
        JFXButton button3 = new JFXButton("保   存");
        button3.setStyle("-fx-background-color: rgb(77,102,204);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
        button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				BtnUtil.saveImage(stage);
			}
        });
        btnBox.getChildren().add(button3);
        
        JFXButton button4 = new JFXButton("返   回");
        button4.setStyle("-fx-background-color: RED;-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.50em;-fx-pref-width: 110;");
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
        
        // 组装
        //root.getChildren().addAll(leftContent, rightContent);
        Scene scene = new Scene(root, 1260, 640);
        stage.setScene(scene);
        // 添加窗体拉伸效果
        //DrawUtil.addDrawFunc(stage, root);
        // 显示
        stage.setResizable(false);
        Screen screen = Screen.getPrimary();  
        Rectangle2D bounds = screen.getVisualBounds();  
        System.out.println(bounds.getMinX() + "=" + bounds.getMinY());
        System.out.println(bounds.getWidth() + "=" + bounds.getHeight());
        Constant.CurrTab = 0;
        stage.show();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        CircleTask task1 = new CircleTask();
        //task1.setCacher(cacher);
        task1.setCanvas(canvas1);
        //executor.execute(task1);
        executor.submit(task1);
        for(int i = 0; i < Constant.canvasList.size(); i++) {
        	PaintTask task = new PaintTask();
        	task.setCanvas(Constant.canvasList.get(i));
        	task.setNum(i);
        	//executor.execute(task);
        	executor.submit(task);
        }
//        for(int i = 0; i < 7; i++) {
//        	PaintTask task = new PaintTask();
//    		task.setCacher(cacher);
//    		task.setCanvas(Constant.canvasList.get(i));
//    		task.setNum(i);
//    		Thread t = new Thread(task);
//            t.start();
//        }
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
    	long start = System.currentTimeMillis();
    	for(int i = 0; i < size; i++) {
    		HBox inner = new HBox();
    		inner.setSpacing(5);
    		inner.setAlignment(Pos.CENTER);
    		Canvas canvas = new Canvas(530, 530);
//    		PaintTask task = new PaintTask();
//    		task.setCacher(cacher);
//    		task.setCanvas(canvas);
//    		task.setNum(i);
//    		Thread t = new Thread(task);
//            t.start();
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
        	jfxCheckBox.setSelected(true);
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
    	long end = System.currentTimeMillis();
    	System.out.println("运行时间  = " + (end - start) + " ms");
    	Constant.canvasList = canvasList;
    	Constant.CheckBoxList = checkboxList;
    	Constant.LabelList = labelList;
    	Constant.ListenerList = listenerList;

        // demo: draw a line of the canvas size and a rectangle of the viewport size => the rectangle must always be in the center
    	pane.setContent(box);
	}

}
