package com.esoorf.model;

import java.io.File;
import java.util.ArrayList;

public class FileGroup {
	boolean isLocked;
	String watermark;
	ArrayList<File> files;
	
	//getters and setters
	public boolean getIsLooked() {
		return this.isLocked;
	}
	public String getWatermark() {
		return this.watermark;
	}
	public ArrayList<File> getFiles() {
		return this.files;
	}
	
	public void setIsLooked(boolean b) {
		this.isLocked= b;
	}
	public void setWatermark(String s) {
		this.watermark= s;
	}
	public void setFiles(ArrayList<File> arr) {
		this.files= arr;
	}
}
