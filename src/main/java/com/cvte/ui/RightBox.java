package com.cvte.ui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.cvte.util.TableViewUtil;
import com.jfoenix.controls.JFXScrollPane;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RightBox {

	public static void setBox(StackPane rightDrawerPane) {
		JFXScrollPaneMark1 pane = new JFXScrollPaneMark1();
		Label scrollTitle = new Label("图片列表");
		pane.getBottomBar().getChildren().add(scrollTitle);
        scrollTitle.setStyle("-fx-text-fill:WHITE; -fx-font-size: 20;");
        JFXScrollPane.smoothScrolling((ScrollPane) pane.getChildren().get(0));
        StackPane.setMargin(scrollTitle, new Insets(10, 10, 10, 10));
        StackPane.setAlignment(scrollTitle, Pos.CENTER);
		VBox main = new VBox();
		main.setMaxWidth(300);
		main.setSpacing(18);
		main.setAlignment(Pos.CENTER);
		
		VBox box1 = new VBox();
		TableViewUtil.createTable(box1);
        
        main.getChildren().addAll(box1);
        pane.setContent(main);
        rightDrawerPane.getChildren().add(pane);
	}
	
	public static void setLabelText(Label label, String text) {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("开始执行 = " + text + "=" + label.getText());
				Platform.runLater(() -> label.setText(text));
			}
			
		};
		
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.submit(runnable);
	}

}
