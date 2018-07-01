package com.cvte.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRippler;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.effects.JFXDepthManager;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author jan
 * @date 2018年4月6日 上午12:03:08
 * @version V1.0 
 */
public class LabelDemo extends Application {
	
	private static final String FX_BACKGROUND_COLOR_WHITE = "-fx-background-color:WHITE;";
	private static final String FX_BACKGROUND_COLOR_WHITE1 = "-fx-background-color:WHITE;";

	public void start(Stage stage) {
		
		GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        //grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setPadding(new Insets(20, 20, 20, 20));

        Text scenetitle = new Text("Welcome");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        

//        Label userName = new Label("User Name:");
//        grid.add(userName, 0, 1);
//
//        TextField userTextField = new TextField();
//        grid.add(userTextField, 1, 1);
//
//        Label pw = new Label("Password:");
//        grid.add(pw, 0, 2);
//
//        PasswordField pwBox = new PasswordField();
//        grid.add(pwBox, 1, 2);
//
//        Button btn = new Button("Sign in");
//        HBox hbBtn = new HBox(10);
//        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
//        hbBtn.getChildren().add(btn);
//        grid.add(hbBtn, 1, 4);
//
//        final Text actiontarget = new Text();
//        grid.add(actiontarget, 1, 6);
//
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//
//            @Override
//            public void handle(ActionEvent e) {
//                //actiontarget.setFill(Color.FIREBRICK);
//            	actiontarget.setFill(Color.GREEN);
//                actiontarget.setText("Sign in button pressed");
//            }
//        });
		
//		final VBox main = new VBox();
//        main.setSpacing(30);
//        main.setStyle("-fx-background-color:WHITE;-fx-padding:40;");
//        FlowPane main = new FlowPane();
//        main.setVgap(20);
//        main.setHgap(20);
        
        //ButtonDemo.class.getResource("1.jpg")
        //Label l5 = new Label("IMG");
        Image image = new Image(getClass().getResourceAsStream("/com/cvte/resources/1.jpg"));
        ImageView iv1 = new ImageView();
        iv1.setImage(image);
        iv1.setStyle(FX_BACKGROUND_COLOR_WHITE);
        iv1.setFitHeight(300);
        iv1.setFitWidth(300);
        //l5.setPadding(new Insets(180));
        JFXRippler rippler5 = new JFXRippler(iv1);
        //grid.getChildren().add(rippler5);
        grid.add(rippler5, 2, 1);
        JFXDepthManager.setDepth(rippler5, 5);

        JFXButton button = new JFXButton("好");
        button.getStyleClass().add("button-raised");
        //grid.getChildren().add(button);
        grid.add(button, 0, 4);
        
        JFXButton button1 = new JFXButton("不好");
        button1.getStyleClass().add("button-raised");
        //grid.getChildren().add(button1);
        grid.add(button1, 4, 4);
        
        JFXTabPane tabPane = new JFXTabPane();
        tabPane.setPrefSize(300, 200);
        
        Tab tab = new Tab();
        //tab.setStyle("-fx-background-color:WHITE;-fx-text-fill:BLACK;");
        tab.setText("Tab 1");
        System.out.println("text = " + tab.getText());
        tab.setContent(new Label("Content"));
        tabPane.getTabs().add(tab);
        
        Tab tab1 = new Tab();
        tab1.setText("Tab 2");
        tab1.setContent(new Label("TAB_01"));
        tabPane.getTabs().add(tab1);
        
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);
        //tabPane.setStyle("-fx-background-color:BLACK");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(tabPane);
        hbox.setSpacing(50);
        hbox.setAlignment(Pos.CENTER);

        StackPane pane = new StackPane();
        pane.getChildren().add(grid);
        pane.getChildren().add(hbox);
        StackPane.setMargin(grid, new Insets(100));
        pane.setStyle("-fx-background-color:WHITE");

        final Scene scene = new Scene(pane, 800, 600);
        scene.getStylesheets().add(LabelDemo.class.getResource("/com/cvte/css/jfoenix-components.css").toExternalForm());
        stage.setTitle("标  注  系  统");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/cvte/resources/3.jpg")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

	
}
