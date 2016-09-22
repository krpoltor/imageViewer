package com.starterkit.javafx.context;

import java.io.File;
import java.util.ArrayList;

public class Context {
	private final static Context instance = new Context();
	private ArrayList<File> listOfPictures;
	private String currentPicturePath;

	public static Context getInstance() {
		return instance;
	}

	public ArrayList<File> getListOfPictures() {
		return listOfPictures;
	}

	public void setListOfPictures(ArrayList<File> listOfPictures) {
		this.listOfPictures = listOfPictures;
	}

	public String getCurrentPicturePath() {
		return currentPicturePath;
	}

	public void setCurrentPicturePath(String currentPicturePath) {
		this.currentPicturePath = currentPicturePath;
	}

	public void clearListOfPictures() {
		listOfPictures.clear();
	}
}