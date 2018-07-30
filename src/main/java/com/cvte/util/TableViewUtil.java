package com.cvte.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.cvte.entity.ImageEntity;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TableViewUtil {

	@SuppressWarnings("unchecked")
	public static void createTable(VBox right) {
		
		JFXTreeTableColumn<ImageEntity, String> idColumn = new JFXTreeTableColumn<>("序号");
		idColumn.setPrefWidth(40);
		idColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ImageEntity, String> param)
				-> {
            if (idColumn.validateValue(param)) {
                return param.getValue().getValue().id;
            } else {
                return idColumn.getComputedValue(param);
            }
        });
		
		JFXTreeTableColumn<ImageEntity, String> imgColumn = new JFXTreeTableColumn<>("图片名");
		imgColumn.setPrefWidth(190);
		imgColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ImageEntity, String> param)
				-> {
            if (imgColumn.validateValue(param)) {
                return param.getValue().getValue().imgName;
            } else {
                return imgColumn.getComputedValue(param);
            }
        });
		
		JFXTreeTableColumn<ImageEntity, Object> viewColumn = new JFXTreeTableColumn<>("操作");
		viewColumn.setPrefWidth(50);
		viewColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<ImageEntity, Object> param)
				-> {
            if (viewColumn.validateValue(param)) {
                return param.getValue().getValue().view;
            } else {
                return viewColumn.getComputedValue(param);
            }
        });
		
	    
	    imgColumn.setCellFactory((TreeTableColumn<ImageEntity, String> param) 
				-> new GenericEditableTreeTableCell<>(
	            new TextFieldEditorBuilder()));
	    imgColumn.setOnEditCommit((CellEditEvent<ImageEntity, String> t) 
	    		-> t.getTreeTableView().getTreeItem(t.getTreeTablePosition()
	    				.getRow()).getValue().imgName.set(t.getNewValue()));
		
		// data
        ObservableList<ImageEntity> images = FXCollections.observableArrayList();
        // 该行以下代码可复用
        String rootpath = System.getProperty("user.dir").replace('\\', '/');
        String dirpath = rootpath + "/admin/mixlabel/dir";
        List<String[]> list = ReadCSV.readCSV(dirpath);
        int num = 1;
        for(String[] s : list) {
        	if("0".equals(s[5])) {
        		File f = new File(s[7]);
        		if(f.exists()) {
        			File[] fts = new File(s[7]).listFiles();
        			File ft = new File(s[7] + "/" + fts[0].getName());
        			if(ft.isDirectory()) {
        				File[] tmp = new File(s[7]).listFiles();
        				for(File ff : tmp) {
        					String nam = ff.getName();
        					System.out.println(" kankan = " + s[7] + "/" + nam);
        					File[] files = new File(s[7] + "/" + nam).listFiles();
        					for(File fff : files) {
        						JFXButton btn12 = new JFXButton("查看");
        						btn12.setStyle("-fx-background-color:rgb(126, 87, 194); "
                						+ "-fx-text-fill: WHITE;");
            					images.add(new ImageEntity("" + num, nam + "_" + fff.getName(), btn12));
            					btn12.setOnAction(new EventHandler<ActionEvent>() {
            						@Override
            						public void handle(ActionEvent e) {
            							String csvName = "1___" + nam + "_" + fff.getName();
            							TableImageView.viewSelectImg(csvName);
            						}
            					});
            					num++;
        					}
        				}
        			}else {
        				File[] files = new File(s[7]).listFiles();
        				for(File ff : files) {
        					JFXButton btn12 = new JFXButton("查看");
        					btn12.setStyle("-fx-background-color:rgb(126, 87, 194); "
            						+ "-fx-text-fill: WHITE;");
        					images.add(new ImageEntity("" + num, ff.getName(), btn12));
        					btn12.setOnAction(new EventHandler<ActionEvent>() {
        						@Override
        						public void handle(ActionEvent e) {
        							String csvName = "2___" + ff.getName();
        							TableImageView.viewSelectImg(csvName);
        						}
        					});
        					num++;
        				}
        			}
        		}else {
        			File[] fts = new File(s[9]).listFiles();
        			File ft = new File(s[9] + "/" + fts[0].getName());
        			if(ft.isDirectory()) {
        				File[] tmp = new File(s[9]).listFiles();
        				for(File ff : tmp) {
        					String nam = ff.getName();
        					File[] files = new File(s[9] + "/" + nam).listFiles();
        					for(File fff : files) {
        						JFXButton btn12 = new JFXButton("查看");
        						btn12.setStyle("-fx-background-color:rgb(126, 87, 194); "
                						+ "-fx-text-fill: WHITE;");
            					images.add(new ImageEntity("" + num, nam + "_" + fff.getName(), btn12));
            					btn12.setOnAction(new EventHandler<ActionEvent>() {
            						@Override
            						public void handle(ActionEvent e) {
            							String csvName = "1___" + nam + "_" + fff.getName();
            							TableImageView.viewSelectImg(csvName);
            						}
            					});
            					num++;
        					}
        				}
        			}else {        				
        				File[] files = new File(s[9]).listFiles();
        				for(File ff : files) {
        					JFXButton btn12 = new JFXButton("查看");
        					btn12.setStyle("-fx-background-color:rgb(126, 87, 194); "
            						+ "-fx-text-fill: WHITE;");
        					images.add(new ImageEntity("" + num, ff.getName(), btn12));
        					btn12.setOnAction(new EventHandler<ActionEvent>() {
        						@Override
        						public void handle(ActionEvent e) {
        							String csvName = "2___" + ff.getName();
        							TableImageView.viewSelectImg(csvName);
        						}
        					});
        					num++;
        				}
        			}
        		}
        	}
        }

        // build tree
        final TreeItem<ImageEntity> root = new RecursiveTreeItem<>(images, 
        		RecursiveTreeObject::getChildren);

        JFXTreeTableView<ImageEntity> treeView = new JFXTreeTableView<>(root);
        treeView.setShowRoot(false);
        treeView.setEditable(true);
        treeView.setPrefHeight(590);
        //treeView.getStyleClass().add("edge-to-edge");
        treeView.getStyleClass().add("jan-table-noborder");
        treeView.getColumns().setAll(idColumn, imgColumn, viewColumn);
        right.getChildren().add(treeView);
	}

}
