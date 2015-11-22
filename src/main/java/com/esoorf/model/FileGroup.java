package com.esoorf.model;

import java.io.File;
import java.util.ArrayList;

public class FileGroup {
	boolean isLocked;
	String footprint;
	ArrayList<File> files;
	
	//getters and setters
	public boolean getIsLooked() {
		return this.isLocked;
	}
	public String getFootprint() {
		return this.footprint;
	}
	public ArrayList<File> getFiles() {
		return this.files;
	}
	
	public void setIsLooked(boolean b) {
		this.isLocked= b;
	}
	public void setFootprint(String s) {
		this.footprint= s;
	}
	public void setFiles(ArrayList<File> arr) {
		this.files= arr;
	}
}
