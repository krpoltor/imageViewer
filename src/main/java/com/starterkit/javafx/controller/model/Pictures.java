package com.starterkit.javafx.controller.model;

import java.util.ArrayList;
import java.util.List;

import com.starterkit.javafx.dataprovider.data.PictureVO;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Pictures {

	private final StringProperty fileName = new SimpleStringProperty();
	private final StringProperty fileWeigth = new SimpleStringProperty();

	private final ListProperty<PictureVO> result = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<>()));

	public StringProperty getFileName() {
		return fileName;
	}

	public final void setFileName(String value) {
		fileName.set(value);
	}

	public StringProperty fileNameProperty() {
		return fileName;
	}

	public StringProperty getFileWeigth() {
		return fileWeigth;
	}

	public final void setFileWeigth(String value) {
		fileWeigth.set(value);
	}

	public StringProperty fileWeigthProperty() {
		return fileWeigth;
	}

	public ListProperty<PictureVO> getResult() {
		return result;
	}

	public final void setResult(List<PictureVO> value) {
		result.setAll(value);
	}

	public ListProperty<PictureVO> resultProperty() {
		return result;
	}

}
