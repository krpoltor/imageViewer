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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
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
	@FXML
	private ScrollPane scrollPane;

	private int interval = 1000;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	AnimatedZoomOperator zoomOperator = new AnimatedZoomOperator();

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

		initializePosition();
		initilizeToggleButton();
		initializeZoom();

		initializeLabels();
		showImage();
	}

	private void initializeLabels() {
		LOG.debug("Entering initializeLabels()");
		picturesQuantityLabel.setText(String.valueOf(getListOfPictures().size()));
		Integer pos = position + 1;
		currentPositionLabel.setText(pos.toString());
		LOG.debug("Exiting initializeLabels()");
	}

	private void initializePosition() {
		LOG.debug("Entering initializePosition()");
		for (int i = 0; i < listOfPictures.size(); i++) {
			if (listOfPictures.get(i).getAbsolutePath().equals(model.getCurrentPicturePath())) {
				position = i;
			}
		}
		LOG.debug("Exiting initializePosition()");
	}

	private void initializeZoom() {
		LOG.debug("Entering initializeZoom()");
		mainImageView.setOnScroll(new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				double zoomFactor = 1.5;
				if (event.getDeltaY() <= 0) {
					// zoom out
					zoomFactor = 1 / zoomFactor;
				}
				zoomOperator.zoom(mainImageView, zoomFactor, event.getSceneX(), event.getSceneY());
			}
		});
		LOG.debug("Exiting initializeZoom()");
	}

	private void initilizeToggleButton() {
		LOG.debug("Entering initilizeToggleButton()");
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(getInterval()), ae -> slideshow()));
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
		LOG.debug("Exiting initilizeToggleButton()");
	}

	private void slideshow() {
		LOG.debug("Entering slideshow()");
		nextButtonAction();
		LOG.debug("Exiting slideshow()");
	}

	private void showImage() {
		LOG.debug("Entering showImage()");

		ArrayList<File> file = getListOfPictures();

		Image img;
		try {
			img = new Image(new FileInputStream(file.get(position)));
			mainImageView.setImage(img);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		LOG.debug("Exiting showImage()");
	}

	@FXML
	public void previousButtonAction() {
		LOG.debug("Entering previousButtonAction()");
		if (position - 1 < 0) {
			position = listOfPictures.size() - 1;
		} else {
			position--;
		}
		currentPositionLabel.setText(Integer.toString(position + 1));
		showImage();
		LOG.debug("Exiting previousButtonAction()");
	}

	@FXML
	public void nextButtonAction() {
		LOG.debug("Entering nextButtonAction()");
		if (position + 1 > listOfPictures.size() - 1) {
			position = 0;
		} else {
			position++;
		}
		currentPositionLabel.setText(Integer.toString(position + 1));
		showImage();
		LOG.debug("Exiting nextButtonAction()");
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

	@FXML
	public void set1SecInterval() {
		setInterval(1000);
		initilizeToggleButton();
		LOG.debug("Set interval: "+ getInterval());
	}

	@FXML
	public void set2SecInterval() {
		setInterval(2000);
		initilizeToggleButton();
		LOG.debug("Set interval: "+ getInterval());
	}

	@FXML
	public void set5SecInterval() {
		setInterval(5000);
		initilizeToggleButton();
		LOG.debug("Set interval: "+ getInterval());
	}

}
