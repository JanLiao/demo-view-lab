package com.cvte.ui;

import java.io.File;
import com.cvte.cons.Constant;
import com.cvte.entity.DirData;
import com.cvte.entity.TaskDir;
import com.cvte.util.DialogUtil;
import com.cvte.util.DirUtil;
import com.cvte.util.DragUtil;
import com.cvte.util.ReadCSV;
import com.cvte.util.ViewUtil;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/** 
* @author: jan 
* @date: 2018年5月21日 下午2:45:36 
*/
public class Main extends Application {
	private Stage stageNew;
	//private JFXScrollPane scroll;
	//private JFXListView<Label> table;
	//private JFXTreeTableView<DirData> treeViewTable;

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {
		stageNew = stage;
		//stage.initStyle(StageStyle.TRANSPARENT);
		
		VBox root = new VBox();
        root.setId("root");
        // 引入样式
        root.getStylesheets().add(Main.class.getResource("/com/cvte/css/style-main.css").toString());
        
        //顶部
        VBox top = new VBox();
        top.setId("top");
        top.setPrefSize(900,26);
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
        
        BorderPane content = new BorderPane();
        
        JFXTreeTableColumn<DirData, String> idColumn = new JFXTreeTableColumn<>("序号");
        idColumn.setPrefWidth(40);
        idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DirData, String> param) -> {
            if (idColumn.validateValue(param)) {
                return param.getValue().getValue().id;
            } else {
                return idColumn.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<DirData, String> dirColumn = new JFXTreeTableColumn<>("数据路径");
        dirColumn.setPrefWidth(170);
        dirColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DirData, String> param) -> {
            if (dirColumn.validateValue(param)) {
                return param.getValue().getValue().dir;
            } else {
                return dirColumn.getComputedValue(param);
            }
        });
        
        JFXTreeTableColumn<DirData, String> imgPathColumn = new JFXTreeTableColumn<>("图片路径");
        imgPathColumn.setPrefWidth(170);
        imgPathColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DirData, String> param) -> {
            if (imgPathColumn.validateValue(param)) {
                return param.getValue().getValue().imgPath;
            } else {
                return imgPathColumn.getComputedValue(param);
            }
        });

        JFXTreeTableColumn<DirData, String> numsColumn = new JFXTreeTableColumn<>("专家数");
        numsColumn.setPrefWidth(50);
        numsColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DirData, String> param) -> {
            if (numsColumn.validateValue(param)) {
                return param.getValue().getValue().nums;
            } else {
                return numsColumn.getComputedValue(param);
            }
        });
        
        JFXTreeTableColumn<DirData, String> imgColumn = new JFXTreeTableColumn<>("图片总数");
        imgColumn.setPrefWidth(60);
        imgColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DirData, String> param) -> {
            if (imgColumn.validateValue(param)) {
                return param.getValue().getValue().imgNums;
            } else {
                return imgColumn.getComputedValue(param);
            }
        });
        
        JFXTreeTableColumn<DirData, String> timeColumn = new JFXTreeTableColumn<>("添加时间");
        timeColumn.setPrefWidth(130);
        timeColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DirData, String> param) -> {
            if (timeColumn.validateValue(param)) {
                return param.getValue().getValue().addtime;
            } else {
                return timeColumn.getComputedValue(param);
            }
        });
        
//        JFXTreeTableColumn<DirData, JFXButton> delColumn = new JFXTreeTableColumn<>("操作");
//        delColumn.setPrefWidth(100);
//        delColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DirData, JFXButton> param) -> {
//        	return param.getValue().getValue().button;
//        });

        timeColumn.setCellFactory((TreeTableColumn<DirData, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));
        timeColumn.setOnEditCommit((CellEditEvent<DirData, String> t) -> t.getTreeTableView()
                                                                          .getTreeItem(t.getTreeTablePosition()
                                                                                        .getRow())
                                                                          .getValue().addtime.set(t.getNewValue()));

        dirColumn.setCellFactory((TreeTableColumn<DirData, String> param) -> new GenericEditableTreeTableCell<>(
            new TextFieldEditorBuilder()));
        dirColumn.setOnEditCommit((CellEditEvent<DirData, String> t) -> t.getTreeTableView()
                                                                      .getTreeItem(t.getTreeTablePosition()
                                                                                    .getRow())
                                                                      .getValue().dir.set(t.getNewValue()));
        
        imgPathColumn.setCellFactory((TreeTableColumn<DirData, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));
        imgPathColumn.setOnEditCommit((CellEditEvent<DirData, String> t) -> t.getTreeTableView()
                                                                          .getTreeItem(t.getTreeTablePosition()
                                                                                        .getRow())
                                                                          .getValue().imgPath.set(t.getNewValue()));

        numsColumn.setCellFactory((TreeTableColumn<DirData, String> param) -> new GenericEditableTreeTableCell<>(
            new TextFieldEditorBuilder()));
        numsColumn.setOnEditCommit((CellEditEvent<DirData, String> t) -> t.getTreeTableView()
                                                                      .getTreeItem(t.getTreeTablePosition()
                                                                                    .getRow())
                                                                      .getValue().nums.set(t.getNewValue()));

        idColumn.setCellFactory((TreeTableColumn<DirData, String> param) -> new GenericEditableTreeTableCell<>(
            new TextFieldEditorBuilder()));
        idColumn.setOnEditCommit((CellEditEvent<DirData, String> t) -> t.getTreeTableView()
                                                                       .getTreeItem(t.getTreeTablePosition()
                                                                                     .getRow())
                                                                       .getValue().id.set(t.getNewValue()));
        
        imgColumn.setCellFactory((TreeTableColumn<DirData, String> param) -> new GenericEditableTreeTableCell<>(
                new TextFieldEditorBuilder()));
        imgColumn.setOnEditCommit((CellEditEvent<DirData, String> t) -> t.getTreeTableView()
                                                                           .getTreeItem(t.getTreeTablePosition()
                                                                                         .getRow())
                                                                           .getValue().imgNums.set(t.getNewValue()));


        // data
        ObservableList<DirData> datas = FXCollections.observableArrayList();
        //datas.add(new DirData("1", "23", "CD 1", "450", "2018-3-2"));
        if(Constant.DirList.size() != 0) {
        	for(TaskDir dir : Constant.DirList) {
        		if(dir.getDeleted() == 0) {
        			//ObservableValue<JFXButton> btn = ObservableValue<JFXButton>();
        			datas.add(new DirData(dir.getId(), dir.getPath(), dir.getImgPath(),
        					dir.getNums(), dir.getImgNums(), dir.getAddTime()));
        		}
        		else {
        			System.out.println(dir.getPath() + "  已被删除");
        		}
        	}
        }

        // build tree
        final TreeItem<DirData> item = new RecursiveTreeItem<>(datas, RecursiveTreeObject::getChildren);

        JFXTreeTableView<DirData> treeView = new JFXTreeTableView<>(item);
        treeView.setShowRoot(false);
        treeView.setEditable(true);
        treeView.getColumns().setAll(idColumn, dirColumn, imgPathColumn, numsColumn, imgColumn, timeColumn);
        //treeViewTable = treeView;

        JFXScrollPane pane = new JFXScrollPane();
        //scroll = pane;
        pane.setContent(treeView);
        pane.setMaxWidth(650);
        pane.setMaxHeight(460);
        pane.setStyle("-fx-padding:5;");
        pane.setAlignment(Pos.CENTER);
        
        Label scrollTitle = new Label("标注数据信息");
        //pane.getTopBar().getChildren().add(scrollTitle);
        pane.getBottomBar().getChildren().add(scrollTitle);
        scrollTitle.setStyle("-fx-text-fill:WHITE; -fx-font-size: 28;");
        JFXScrollPane.smoothScrolling((ScrollPane) pane.getChildren().get(0));

        StackPane.setMargin(scrollTitle, new Insets(0, 0, 0, 30));
        StackPane.setAlignment(scrollTitle, Pos.CENTER_LEFT);
        content.setCenter(pane);
        
        VBox rightBox = new VBox();
        rightBox.setSpacing(40);
        rightBox.setStyle("-fx-background-color:WHITE;-fx-padding:30;");
        rightBox.setMinWidth(200);
        rightBox.setAlignment(Pos.CENTER);
        
        Label label = new Label();
        label.setStyle("-fx-text-fill:rgb(77,102,204); -fx-font-size: 20;");
        label.setAlignment(Pos.CENTER);
        label.setText("你好：" + Constant.User);
        rightBox.getChildren().add(label);
        
        JFXButton button = new JFXButton("图片标注数据");
        button.setStyle("-fx-background-color: rgb(24, 128, 56);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.57em;-fx-pref-width: 120;");
        rightBox.getChildren().add(button);
        
        button.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		//选择需分析标注数据文件夹
        		openDirectory(datas);
        	}
        });
        
        JFXButton button1 = new JFXButton("分析融合数据");
        button1.setStyle("-fx-background-color: rgb(238, 124, 8);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.57em;-fx-pref-width: 120;");
        rightBox.getChildren().add(button1);
        
        JFXButton button2 = new JFXButton("查看融合数据");
        button2.setDisable(true);
        button2.setStyle("-fx-background-color: rgb(77,102,204);-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.57em;-fx-pref-width: 120;");
        rightBox.getChildren().add(button2);
        
        JFXButton logout = new JFXButton("退出");
        logout.setStyle("-fx-background-color: RED;-fx-text-fill: WHITE;-fx-font-size: 16px;-fx-padding: 0.7em 0.57em;-fx-pref-width: 120;");
        rightBox.getChildren().add(logout);
        content.setRight(rightBox);
        
        button1.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		if(Constant.AnalysisMix.getImgList().size() == 0) {
        			showAlert(stage, "当前没有可分析融合图片");
        		}
        		else {
        			//ProgressShow pro = new ProgressShow();
//        			try {
//        				pro.start(Constant.stage);
//					} catch (Exception e2) {
//						e2.printStackTrace();
//					}
        			Stage stagenew = new Stage();
            		Mark mark = new Mark();
            		try {
    					mark.start(stagenew);
    				} catch (Exception e1) {
    					e1.printStackTrace();
    				}
            		stage.close();
            		//pro.cancelProgressBar();
        		}
        	}
        });
        
        button2.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		ViewUtil.viewInit();
        		if("".equals(Constant.AnalysisView.getImgName()) || Constant.AnalysisView.getFlag() == -1 
        				|| Constant.AnalysisView.getImgName() == null) {
        			showAlert(stage, "当前没有已融合图片数据");
        		}
        		else {
        			//LoadingView.progressShow();
        			Stage stagenew = new Stage();
            		MarkView view = new MarkView();
            		try {
    					view.start(stagenew);
    				} catch (Exception e1) {
    					e1.printStackTrace();
    				}
            		stage.close();
        		}
        	}
        });
        
        logout.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		Stage stagenew = new Stage();
        		stagenew.initStyle(StageStyle.DECORATED);
        		Logout logout = new Logout();
        		try {
        			Constant.User = "";
					logout.start(stagenew);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
        		stage.close();
        	}
        });
        
        // 组装
        root.getChildren().addAll(top, content);
        Scene scene = new Scene(root, 860, 520);
        //scene.getStylesheets().add(Main.class.getResource("/com/cvte/css/jfoenix-components.css").toExternalForm());
        stage.setScene(scene);
        // 拖动监听器
        DragUtil.addDragListener(stage, top);
        // 添加窗体拉伸效果
        //DrawUtil.addDrawFunc(stage, root);
        // 显示
        stage.show();
	}
	
	@SuppressWarnings("rawtypes")
	protected void showAlert(Stage stage, String msg) {
		JFXAlert alert = new JFXAlert((Stage)stage.getScene().getWindow());
        alert.setOverlayClose(false);
        alert.setSize(320, 160);
        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setHeading(new Label("温馨小提示"));
        Label content = new Label(msg);
        layout.setBody(content);
        JFXButton closeButton = new JFXButton("关  闭");
        closeButton.setStyle("-fx-background-color: GREEN;-fx-text-fill: WHITE;-fx-font-size: 15px;-fx-padding: 0.5em 0.50em;");
        closeButton.setOnAction(event -> alert.hideWithAnimation());
        layout.setActions(closeButton);
        alert.setContent(layout);
        alert.show();
	}

	public void openDirectory(ObservableList<DirData> datas) {
		Stage fileStage = null;
		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    DirectoryChooser folderChooser = new DirectoryChooser();
	    folderChooser.setTitle("标注数据文件夹");
	    File selectedFile = folderChooser.showDialog(fileStage);
	    if(selectedFile == null) {
	    	System.out.println("未选择文件夹");
	    }
	    else {
	    	String dir = selectedFile.getAbsolutePath().replace('\\', '/');
		    System.out.println("selected dir = " + dir);
		    
		    //选择图片文件夹
		    String imgPath = openDataDir();
		    if("".equals(imgPath)) {
		    	System.out.println("图片路径未选择");
		    	DialogUtil.showAlert(stageNew, "未选择图片路径");
		    }
		    else {
		    	String p1 = new File(dir).listFiles()[0].getName();
		    	String p2 = dir + "/" + p1 + "/file/imgAllLabel";
		    	String[] record = ReadCSV.readOneRecord(p2);
		    	System.out.println("第一张图片名=" + record[3]);
		    	String[] str = record[3].split("___");
		    	boolean checkExist = false;
		    	if(str.length == 1) {
		    		String[] s = str[0].split("_");
		    		File ff = new File(imgPath + "/" + s[0] + "/" + s[1]);
		    		if(ff.exists()) {
		    			checkExist = true;
		    		}
		    	}
		    	else {
		    		if("1".equals(str[0])) {
		    			String[] s = str[1].split("_");
		    			File ff = new File(imgPath + "/" + s[0] + "/" + s[1]);
		    			if(ff.exists()) {
			    			checkExist = true;
			    		}
		    		}
		    		else if("2".equals(str[0])) {
		    			File ff = new File(imgPath + "/" + str[1]);
		    			if(ff.exists()) {
			    			checkExist = true;
			    		}
		    		}
		    	}
		    	if(checkExist) {
		    		//添加文件夹数据
			    	DirData dirData = DirUtil.addDir(dir, imgPath);
			    	datas.add(dirData);
		    	}
		    	else {
		    		System.out.println("图片路径选择有误");
			    	DialogUtil.showAlert(stageNew, "标注数据与图片不符");
		    	}
		    }
	        //更新Constant.AnalysisMix数据  imgMap标记 
	        //LabelDataUtil.addData(dir);   //已转移到DirUtil.addDir()处理
	    }
	}
	
	private String openDataDir() {
		String imgPath = "";
		Stage fileStage = null;
	    DirectoryChooser folderChooser = new DirectoryChooser();
	    folderChooser.setTitle("标注图片文件夹");
	    File selectedFile = folderChooser.showDialog(fileStage);
	    if(selectedFile == null) {
	    	System.out.println("未选择文件夹");
	    }
	    else {
	    	String dir = selectedFile.getAbsolutePath().replace('\\', '/');
		    System.out.println("selected data dir = " + dir);
		    imgPath = dir;
	    }
		return imgPath;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
