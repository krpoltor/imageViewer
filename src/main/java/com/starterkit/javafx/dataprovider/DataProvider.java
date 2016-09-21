package com.starterkit.javafx.dataprovider;

import java.io.File;

import com.starterkit.javafx.dataprovider.impl.DataProviderImpl;

public interface DataProvider {
	
	/**
	 * Instance of this interface.
	 */
	DataProvider INSTANCE = new DataProviderImpl();
	
	
	File[] loadPictures(String path);

}
