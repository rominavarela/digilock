package com.esoorf.view.impl.element.impl;

import java.io.File;

import com.esoorf.constant.Actions;
import com.esoorf.io.DirectoryUtils;
import com.esoorf.view.impl.panel.impl.LockedPanel;
import com.esoorf.view.palette.Palette;
import com.esoorf.view.palette.impl.UnlockedPalette;

public class UnlockedFileElement extends FileElement{
	
	public UnlockedFileElement(File f){
		super();
		this.setActions(Actions.LOCK);
		this.file= f;
		
		DirectoryUtils dir= DirectoryUtils.getInstance();
		
		if(f.equals(dir.getWorkingDirectory()))
		{
			prefix= "   *";
		}
		else if(f.isDirectory())
		{
			prefix= "   "+f.getAbsolutePath().substring(
					dir.getWorkingDirectory()
					.getAbsolutePath().length()
					);
		}
		else
		{
			prefix= "   "+this.file.getParent()
					.substring(dir.getWorkingDirectory().getPath().length());
			sufix= "      "+f.getName();
		}
		
		this.prefixLabel.setText(prefix);
		if(sufix.isEmpty())
			this.panel.setDividerLocation(40);
		else
			this.sufixLabel.setText(sufix);
		
		this.setPalette(new UnlockedPalette());
	}
	
	public Palette getPalette() {
		return new UnlockedPalette();
	}
	
	public void setPalette(Palette p) {
		this.palette= p;
	}
	
	@Override
	public void setIsSelected(boolean b) {
		this.isSelected= b;
		
		if(this.isSelected)
		{
			this.panel.setBackground(palette.getActiveBackground());
			if(this.isDirectory())
				this.panel.setBorder(palette.getActiveBorder(0, 0, 1, 0));
			this.prefixLabel.setForeground(palette.getActiveForeground());
			this.prefixLabel.setBorder(palette.getActiveBorder(10, 0, 0, 0));
			this.sufixLabel.setForeground(palette.getActiveForeground());
			this.sufixLabel.setBorder(palette.getActiveBorder(0, 0, 10, 0));
		}
		else
		{
			this.panel.setBackground(palette.getBackground());
			this.panel.setBorder(palette.getBorder(0, 0, 1, 0));
			this.prefixLabel.setForeground(palette.getForeground());
			this.prefixLabel.setBorder(palette.getBorder(10, 0, 0, 0));
			this.sufixLabel.setForeground(palette.getForeground());
			this.sufixLabel.setBorder(palette.getBorder(0, 0, 10, 0));
		}
		
		if(this.isDirectory())
		{
			FileElement pointer= this.getNext();
			
			while(pointer!=null)
			{
				if(pointer.getParent().getFile().equals(this.getFile()))
					pointer.setIsSelected(b);
				pointer= pointer.getNext();
			}
		}
	}

	@Override
	public void deselectedOthers() {
		for(FileElement fe: LockedPanel.getInstance().getFileElements())
			if(!fe.isFile())
				fe.setIsSelected(false);
	}

}
