package com.starterkit.javafx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

import org.apache.log4j.Logger;

import com.starterkit.javafx.context.Context;
import com.starterkit.javafx.dataprovider.DataProvider;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

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
public class MainWindowController {

	private static final Logger LOG = Logger.getLogger(MainWindowController.class);

	@FXML
	private Button openButton;

	@FXML
	private TilePane mainWindowTilePane;

	private DataProvider dataprovider = DataProvider.INSTANCE;

	private Context model = Context.getInstance();

	@FXML
	private void initialize() {
		LOG.debug("initialize(): " + this.getClass().getName());
	}

	@FXML
	public void openButtonAction(ActionEvent event) {
		LOG.debug("'Open' button clicked!");
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setDialogTitle("Specify folder");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);

		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			LOG.debug("getCurrentDirectory(): " + chooser.getCurrentDirectory());
			LOG.debug("getSelectedFile() : " + chooser.getSelectedFile());

			model.setListOfPictures(dataprovider.loadPictures(chooser.getSelectedFile().toString()));

			// setListOfPictures();

			// LOG.debug("List of pictures: " +
			// Context.getInstance().getListOfPictures());

			ArrayList<File> pictures = model.getListOfPictures();

			for (final File file : pictures) {
				ImageView imageView;
				imageView = createImageView(file, event);
				mainWindowTilePane.getChildren().addAll(imageView);
			}

		} else {
			LOG.debug("No Selection ");
		}

		// showImage();
		// currentPositionLabel.setText(Integer.toString(position + 1));
		// picturesQuantityLabel.setText(Integer.toString(listOfPictures.length));
		// model.clearListOfPictures();
		LOG.debug("Leaving 'Open' button action.");

		// ======================================

		// Runnable backgroundTask = new Runnable() {
		//
		// /**
		// * This method will be executed in a background thread.
		// */
		// @Override
		// public void run() {
		// LOG.debug("backgroundTask.run() called");
		//
		// /*
		// * Get the data.
		// */
		//
		// /*
		// * Add an event(runnable) to the event queue.
		// */
		// Platform.runLater(new Runnable() {
		//
		// /**
		// * This method will be executed in the JavaFX Application
		// * Thread.
		// */
		// @Override
		// public void run() {
		// LOG.debug("Platform.runLater(Runnable.run()) called");
		//
		// /*
		// * Copy the result to model.
		// */
		//
		// /*
		// * Reset sorting in the result table.
		// */
		// }
		// });
		// }
		// };
		//
		// /*
		// * Start the background task. In real life projects some framework
		// * manages threads. You should never create a thread on your own.
		// */
		// new Thread(backgroundTask).start();
	};

	private ImageView createImageView(final File imageFile, ActionEvent actionEvent) {
		// DEFAULT_THUMBNAIL_WIDTH is a constant you need to define
		// The last two arguments are: preserveRatio, and use smooth (slower)
		// resizing

		ImageView imageView = null;
		try {
			final Image image = new Image(new FileInputStream(imageFile), 150, 0, true, true);
			imageView = new ImageView(image);
			imageView.setFitWidth(150);
			imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent mouseEvent) {

					if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {

						if (mouseEvent.getClickCount() == 2) {
							LOG.debug("Clicked 2 times!");
							LOG.debug(imageFile);
							LOG.debug(mouseEvent.getPickResult().getIntersectedNode());
							// Context.getInstance().setCurrentPicture(mouseEvent.);
							model.setCurrentPicturePath(imageFile.getAbsolutePath());
							try {
								Parent root1 = FXMLLoader.load(
										getClass().getClassLoader()
												.getResource("com/starterkit/javafx/view/imageWindow.fxml"),
										ResourceBundle.getBundle("com/starterkit/javafx/bundle/base"));
								Stage stage = new Stage();
								stage.setTitle("Image Viewer");
								stage.setScene(new Scene(root1));
								stage.show();

								// hide this current window
								// ((Node)
								//
								// (actionEvent.getSource())).getScene().getWindow().hide();
							} catch (IOException e) {
								e.printStackTrace();
							}

						}
					}
				}
			});
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		return imageView;
	}

}
