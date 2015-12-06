package com.esoorf.view.impl.element.impl;

import java.io.File;

import com.esoorf.constant.Actions;
import com.esoorf.io.DirectoryUtils;
import com.esoorf.model.FileGroup;
import com.esoorf.view.impl.panel.impl.LockedPanel;
import com.esoorf.view.impl.panel.impl.UnlockedPanel;
import com.esoorf.view.palette.Palette;
import com.esoorf.view.palette.impl.LockedPalette;

public class LockedFileElement extends FileElement{
	
	public LockedFileElement(FileGroup fg) {
		super();
		this.setActions(Actions.UNLOCK);
		
		prefix= "   "+fg.getHashFootprint();
		sufix= "      "+fg.getUserFootprint();
		
		this.prefixLabel.setText(prefix);
		this.sufixLabel.setText(sufix);

		this.setPalette(new LockedPalette());
	}
	
	public LockedFileElement(File f) {
		super();
		this.setActions(Actions.UNLOCK);
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
		
		this.prefixLabel.setText("      "+prefix);
		if(sufix.isEmpty())
			this.panel.setDividerLocation(40);
		else
			this.sufixLabel.setText("      "+sufix);
		
		this.setPalette(new LockedPalette());
	}

	public Palette getPalette() {
		return new LockedPalette();
	}
	
	public void setPalette(Palette p) {
		this.palette= p;
	}
	
	@Override
	public void setIsSelected(boolean b) {
		this.isSelected= b;
		
		if(this.isSelected)
		{
			this.panel.setBackground(this.palette.getActiveBackground());
			if(!this.isFile())
				this.panel.setBorder(palette.getActiveBorder(0, 0, 1, 0));
			this.prefixLabel.setForeground(this.palette.getActiveForeground());
			this.sufixLabel.setForeground(this.palette.getActiveForeground());
		}
		else
		{
			this.panel.setBackground(this.palette.getBackground());
			this.panel.setBorder(palette.getBorder(0, 0, 1, 0));
			this.prefixLabel.setForeground(this.palette.getForeground());
			this.sufixLabel.setForeground(this.palette.getForeground());
		}
		
		if(!this.isFile())
		{
			FileElement pointer= this.next;
			while(pointer!=null)
			{
				if(pointer.isFile())
					pointer.setIsSelected(isSelected);
				else
					break;
				
				pointer=pointer.next;
			}
		}
	}

	@Override
	public void deselectedOthers() {
		if(this.isFile())
		{
			for(FileElement fe: LockedPanel.getInstance().getFileElements())
				if(!fe.isFile() && !fe.equals(this.parent))
					fe.setIsSelected(false);
		}
		else
		{
			for(FileElement fe: LockedPanel.getInstance().getFileElements())
				if(!fe.isFile() && !fe.equals(this))
					fe.setIsSelected(false);
		}
		
		UnlockedPanel.getInstance().getFileElements().get(0).setIsSelected(false);
	}

}
