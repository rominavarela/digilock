package com.esoorf.io;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import com.esoorf.constant.Offsets;
import com.esoorf.constant.Words;
import com.esoorf.model.FileGroup;

public class DirectoryUtils {
	File workingDirectory;
	ArrayList<FileGroup> lockedGroups;
	ArrayList<File> unlockedFiles;
	boolean recursiveLookup;
	
	DirectoryUtils() {
		String absolutePath = 
				getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
        
		File ROOT = new File(absolutePath);
		this.workingDirectory=new File(ROOT.getAbsolutePath().replace("%20", " "));
		
		this.lockedGroups= new ArrayList<FileGroup>();
		this.unlockedFiles= new ArrayList<File>();
		
		this.updateFileGroups();
	}
	
	public String chooseDirectory() {
		JFileChooser chooser = new JFileChooser(); 
	    //chooser.setDialogTitle("Pick your flash direction.");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);
	    
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
	    	this.workingDirectory= chooser.getSelectedFile();
	    
	    this.updateFileGroups();
		return this.workingDirectory.getAbsolutePath();
	}
	
	public ArrayList<File> getDirectoryContent(File dir, boolean recursiveLookup) {
		ArrayList<File> content = new ArrayList<File>();
		
		if ( dir.exists() && dir.isDirectory() )
		{
			for ( File f: dir.listFiles() )
			{
				content.add(f);
				if(recursiveLookup && f.isDirectory())
					content.addAll(this.getDirectoryContent(f, true));
			}
		}
		
		return content;
	}
	
	public void updateFileGroups() {
		this.lockedGroups.clear();
		this.unlockedFiles.clear();
		
		ArrayList<File> files= this.getDirectoryContent(
				this.workingDirectory,
				this.recursiveLookup);
		
		String footprint="";
		for(File f:files)
			if((footprint=getFootprint(f)).isEmpty())
				this.unlockedFiles.add(f);
			else
			{
				System.out.println("footprint: "+ footprint);
				
				FileGroup g= null;
				for(FileGroup group: this.lockedGroups)
					if(group.getFootprint().contentEquals(footprint))
						g= group;
				
				if(g==null)
				{
					g= new FileGroup();
					g.setIsLooked(true);
					g.setFootprint(footprint);
					g.setUserFootprint(footprint.substring(Offsets.userFootprint, Offsets.hashFootprint));
					g.setHashFootprint(footprint.substring(Offsets.hashFootprint, Offsets.data));
					g.setFiles(new ArrayList<File>());
					this.lockedGroups.add(g);
					

					System.out.println("fp: "+ g.getUserFootprint());
					System.out.println("hash: "+ g.getHashFootprint());
				}
				
				g.getFiles().add(f);
			}
		
	}
	
	public String getFootprint(File f) {
		try
		{
			FileInputStream		fin=  null;
			byte[] 				footprintBytes= null;
			
			footprintBytes = new byte[Offsets.data];
			fin = new FileInputStream(f);
		    fin.read(footprintBytes,0,Offsets.data);
		    fin.close();
		    
		    String footprint= new String(footprintBytes);
		    if(footprint.startsWith(Words.DigiLock.name()))
		    	return footprint;
		    
		} catch(Exception ex){ }
		
		return "";
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
