package com.esoorf.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import com.esoorf.constant.Offsets;
import com.esoorf.constant.Words;
import com.ryanchapin.util.HashGenerator;
import com.ryanchapin.util.HashGenerator.HashAlgorithm;


public class FileLocker {
	
	String lockerFootprint;
	String userFootprint;
	String key;
	
	HashGenerator hashGenerator;
	String hashFootprint;
	
	public FileLocker(String footprint,String key) throws IllegalStateException, NoSuchAlgorithmException{
		this.lockerFootprint = Words.DigiLock.name();
		this.userFootprint= footprint;
		this.key= key;
		
		this.hashGenerator= new HashGenerator(HashAlgorithm.MD5SUM);
		this.hashFootprint = hashGenerator.createHash(this.key.toCharArray());
		System.out.println("hash: "+this.hashFootprint);
	}
	
	public void lock(File f) throws IOException {
		System.out.println("Locking "+f.getName());
		FileInputStream		fin=  null;
		FileOutputStream	fout= null;
		byte[] 				data= null;
		
		int fileLen = (int) f.length();
		data = new byte[fileLen+Offsets.data];
		fin = new FileInputStream(f);
	    fin.read(data,Offsets.data,fileLen);
	    fin.close();
	    
	    //insert footprints
	    for( int i=0; i<lockerFootprint.length(); i++)
	    	data[i+Offsets.lockerFootprint]=(byte)lockerFootprint.charAt(i);
	    for( int i=0; i<userFootprint.length(); i++)
	    	data[i+Offsets.userFootprint]=(byte)userFootprint.charAt(i);
	    for( int i=0; i<hashFootprint.length(); i++)
	    	data[i+Offsets.hashFootprint]=(byte)hashFootprint.charAt(i);
	    
	    //alterate data
	    for( int i=0; i<fileLen; i++)
	    {
	    	data[i+Offsets.data]=(byte) (Byte.MAX_VALUE-data[i+Offsets.data]);
		}
	    
	    fout = new FileOutputStream(f); 
	    fout.write(data);
	    fout.close();
	}
}
