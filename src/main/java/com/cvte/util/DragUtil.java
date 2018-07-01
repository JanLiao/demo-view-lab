package com.cvte.util;

import javafx.scene.Node;
import javafx.stage.Stage;

/** 
* @author: jan 
* @date: 2018年5月21日 上午1:45:46 
*/
public class DragUtil {
	public static void addDragListener(Stage stage,Node root) {
        new DragListener(stage).enableDrag(root);
    }
}
