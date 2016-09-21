package com.starterkit.javafx.dataprovider.data;

public class PictureVO {

	private String fileName;
	private String fileWeigth;

	public PictureVO() {
	}

	public PictureVO(String fileName, String fileWeigth) {
		super();
		this.fileName = fileName;
		this.fileWeigth = fileWeigth;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getfileWeigth() {
		return fileWeigth;
	}

	public void setfileWeigth(String fileWeigth) {
		this.fileWeigth = fileWeigth;
	}
}
