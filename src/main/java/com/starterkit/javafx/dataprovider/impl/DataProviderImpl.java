package com.starterkit.javafx.dataprovider.impl;

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.starterkit.javafx.dataprovider.DataProvider;

public class DataProviderImpl implements DataProvider {

	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);

	@Override
	public ArrayList<File> loadPictures(String path) {
		LOG.debug("Entered 'loadPictures'");

		File folder = new File(path);
		File[] files = folder.listFiles();
		ArrayList<File> listOfFiles =  new ArrayList<File>(); 

		for (File file : files) {
			listOfFiles.add(file);
		}
		
		LOG.debug("Leaving 'loadPictures'");
		return listOfFiles;
	}
}
