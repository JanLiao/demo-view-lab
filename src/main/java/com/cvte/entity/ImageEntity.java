package com.cvte.entity;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ImageEntity extends RecursiveTreeObject<ImageEntity> {

	public final StringProperty id;
	
	public final StringProperty imgName;
	
	public final ObjectProperty view;
	
	public ImageEntity(String id, String imgName, Object view) {
		this.id = new SimpleStringProperty(id);
		this.imgName = new SimpleStringProperty(imgName);
		this.view = new SimpleObjectProperty(view);
	}
}
