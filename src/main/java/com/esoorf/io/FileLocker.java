package com.esoorf.io;

import java.io.File;

public class FileLocker {
	public void lock(String key,File f){
		System.out.println("Locking "+f.getName());
	}
	
	// singleton
	private static FileLocker instance;
	public static synchronized FileLocker getInstance() {
		if(instance==null)
			instance= new FileLocker();
		return instance;
	}
}
