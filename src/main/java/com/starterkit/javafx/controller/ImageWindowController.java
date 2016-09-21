package com.starterkit.javafx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.starterkit.javafx.context.Context;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class ImageWindowController {

	private static final Logger LOG = Logger.getLogger(ImageWindowController.class);

	@FXML
	private Button previousButton;
	@FXML
	private Button nextButton;
	@FXML
	private Button editButton;
	@FXML
	private ImageView mainImageView;
	@FXML
	private Label currentPositionLabel;
	@FXML
	private Label picturesQuantityLabel;
	@FXML
	private ToggleButton slideshowButton;
	@FXML
	private MenuButton slideshowIntervalButton;

	private Context model = Context.getInstance();

	private Integer position = new Integer(0);

	private ArrayList<File> listOfPictures = model.getListOfPictures();

	public ArrayList<File> getListOfPictures() {
		return listOfPictures;
	}

	public void setListOfPictures(ArrayList<File> listOfPictures) {
		this.listOfPictures = listOfPictures;
	}

	@FXML
	private void initialize() {
		LOG.debug("initialize(): " + this.getClass().getName());

		for (int i = 0; i < listOfPictures.size(); i++) {
			if (listOfPictures.get(i).getAbsolutePath().equals(model.getCurrentPicturePath())) {
				position = i;
			}

			initilizeToggleButton();
			
		}

		picturesQuantityLabel.setText(String.valueOf(getListOfPictures().size()));
		Integer pos = position + 1;
		currentPositionLabel.setText(pos.toString());
		showImage();
	}

	private void initilizeToggleButton() {
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2500), ae -> slideshow()));
		timeline.setCycleCount(Animation.INDEFINITE);
		ToggleGroup group = new ToggleGroup();

		slideshowButton.setToggleGroup(group);

		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle toggle, Toggle new_toggle) {
				if (new_toggle == null) {
					timeline.stop();
				} else {
					timeline.play();
				}
				;
			}
		});
	}

	private void slideshow() {
		nextButtonAction();
	}

	private void showImage() {

		ArrayList<File> file = getListOfPictures();

		Image img;
		try {
			img = new Image(new FileInputStream(file.get(position)));
			mainImageView.setImage(img);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void previousButtonAction() {
		if (position - 1 < 0) {
			position = listOfPictures.size() - 1;
		} else {
			position--;
		}
		currentPositionLabel.setText(Integer.toString(position + 1));
		showImage();
	}

	@FXML
	public void nextButtonAction() {
		if (position + 1 > listOfPictures.size() - 1) {
			position = 0;
		} else {
			position++;
		}
		currentPositionLabel.setText(Integer.toString(position + 1));
		showImage();
	}

	@FXML
	public void editButtonAction() {
		LOG.debug("Entering 'Edit' button action.");
		Runtime rs = Runtime.getRuntime();

		try {
			LOG.debug("Opening file.");
			rs.exec(new String[] { "mspaint", listOfPictures.get(position).toString() });
		} catch (IOException e) {
			System.out.println(e);
		}
		LOG.debug("Leaving 'Edit' button action.");
	}

}
