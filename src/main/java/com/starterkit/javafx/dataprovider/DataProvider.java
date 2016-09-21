package com.starterkit.javafx.dataprovider;

import java.io.File;
import java.util.ArrayList;

import com.starterkit.javafx.dataprovider.impl.DataProviderImpl;

public interface DataProvider {
	
	/**
	 * Instance of this interface.
	 */
	DataProvider INSTANCE = new DataProviderImpl();
	
	
	ArrayList<File> loadPictures(String path);

}
