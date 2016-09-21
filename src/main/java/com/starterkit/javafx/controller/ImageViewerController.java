package com.starterkit.javafx.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import org.apache.log4j.Logger;

import com.starterkit.javafx.dataprovider.DataProvider;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for the person search screen.
 * <p>
 * The JavaFX runtime will inject corresponding objects in the @FXML annotated
 * fields. The @FXML annotated methods will be called by JavaFX runtime at
 * specific points in time.
 * </p>
 *
 * @author Leszek
 */
public class ImageViewerController {

	private static final Logger LOG = Logger.getLogger(ImageViewerController.class);

	@FXML
	private Button openButton;
	@FXML
	private Button previousButton;
	@FXML
	private Button nextButton;
	@FXML
	private Button editButton;
	@FXML
	private ImageView mainImageView;

	private DataProvider dataprovider = DataProvider.INSTANCE;

	private File[] listOfPictures;
	
	public File[] getListOfPictures() {
		return listOfPictures;
	}

	public void setListOfPictures(File[] listOfPictures) {
		this.listOfPictures = listOfPictures;
	}

	private Integer position = new Integer(0);

	@FXML
	private void initialize() {
		LOG.debug("initialize(): " + this.getClass().getName());

	}

	private void showImage() {

		File[] file = getListOfPictures();

		Image img;
		try {
			img = new Image(new FileInputStream(file[position]));
			mainImageView.setImage(img);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void openButtonAction() {
		LOG.debug("'Open' button clicked!");
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("choosertitle");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			LOG.debug("getCurrentDirectory(): " + chooser.getCurrentDirectory());
			LOG.debug("getSelectedFile() : " + chooser.getSelectedFile());

			setListOfPictures(dataprovider.loadPictures(chooser.getSelectedFile().toString()));

			LOG.debug("List of pictures: " + listOfPictures.toString());

		} else {
			LOG.debug("No Selection ");
		}

		showImage(listOfPictures[0]);
		LOG.debug("Leaving 'Open' button action.");
	}

	@FXML
	public void previousButtonAction() {
		
		
		
	}

	@FXML
	public void nextButtonAction() {
	}

	@FXML
	public void editButtonAction() {
	}

}
