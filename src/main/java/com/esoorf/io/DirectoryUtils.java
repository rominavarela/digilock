package com.esoorf.io;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import com.esoorf.model.FileGroup;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.IO;

public class DirectoryUtils {
	File workingDirectory;
	ArrayList<FileGroup> lockedGroups;
	ArrayList<File> unlockedFiles;
	boolean recursiveLookup;
	
	DirectoryUtils(){
		File ROOT = new File(IO.class.getResource("/").getFile());
		this.workingDirectory=new File(ROOT.getAbsolutePath().replace("%20", " "));
		
		this.lockedGroups= new ArrayList<FileGroup>();
		this.unlockedFiles= new ArrayList<File>();
		
		this.updateFileGroups();
	}
	
	public String chooseDirectory()
	{
		JFileChooser chooser = new JFileChooser(); 
	    //chooser.setDialogTitle("Pick your flash direction.");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	    	this.workingDirectory= chooser.getSelectedFile();
	    
	    this.updateFileGroups();
		return this.workingDirectory.getAbsolutePath();
	}
	
	public ArrayList<File> getDirectoryContent(File dir, boolean recursiveLookup)
	{
		ArrayList<File> content = new ArrayList<File>();
		
		if ( dir.exists() && dir.isDirectory() )
		{
			for ( File f: dir.listFiles() )
				if ( f.isFile())
					content.add(f);
				else if(recursiveLookup)
					content.addAll(this.getDirectoryContent(f, true));
		}
		
		return content;
	}
	
	public void updateFileGroups(){
		this.lockedGroups.clear();
		this.unlockedFiles.clear();
		
		ArrayList<File> files= this.getDirectoryContent(
				this.workingDirectory,
				this.recursiveLookup);
		
		int i=0;
		boolean flag= false;
		for(File f:files){
			if(flag)
			{
				String watermark= "watermark"+i;
				i= (i+1)%3;
				
				FileGroup g= null;
				for(FileGroup group: this.lockedGroups)
					if(group.getWatermark().contentEquals(watermark))
						g= group;
				
				if(g==null)
				{
					g= new FileGroup();
					g.setIsLooked(true);
					g.setWatermark(watermark);
					g.setFiles(new ArrayList<File>());
					this.lockedGroups.add(g);
				}
				
				g.getFiles().add(f);
			}
			else
				this.unlockedFiles.add(f);
			
			flag= !flag;
		}
		
	}
	
	//getters and setters
	public File getWorkingDirectory() {
		return this.workingDirectory;
	}
	public ArrayList<File> getUnlockedFiles() {
		return this.unlockedFiles;
	}
	public ArrayList<FileGroup> getLockedGroups() {	
		return this.lockedGroups;
	}
	public boolean getRecursiveLookup() {
		return this.recursiveLookup;
	}
	
	public void setWorkingDirectory(File f) {
		this.workingDirectory= f;
	}
	public void setUnlockedFiles(ArrayList<File> fg) {
		this.unlockedFiles= fg;
	}
	public void setLockedGroups(ArrayList<FileGroup> arr){	
		this.lockedGroups= arr;
	}
	public void setRecursiveLookup(boolean b){
		this.recursiveLookup= b;
	}
	
	// singleton
	private static DirectoryUtils instance;
	public static synchronized DirectoryUtils getInstance() {
		if(instance==null)
			instance= new DirectoryUtils();
		return instance;
	}
}
