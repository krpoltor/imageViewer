package com.starterkit.javafx.dataprovider.impl;

import java.io.File;

import org.apache.log4j.Logger;

import com.starterkit.javafx.dataprovider.DataProvider;

public class DataProviderImpl implements DataProvider {
	
	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);

	@Override
	public File[] loadPictures(String path) {
		LOG.debug("Entered 'loadPictures'");
		
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		LOG.debug("Leaving 'loadPictures'");
		return listOfFiles;
	}
}
