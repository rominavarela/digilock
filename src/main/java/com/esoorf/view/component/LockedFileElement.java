package com.esoorf.view.component;

import java.io.File;

import com.esoorf.constant.Actions;
import com.esoorf.io.DirectoryUtils;
import com.esoorf.model.FileGroup;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.panel.LockedPanel;
import com.esoorf.view.panel.UnlockedPanel;

public class LockedFileElement extends FileElement{
	
	public LockedFileElement(FileGroup fg) {
		super();
		this.setActions(Actions.UNLOCK);
		
		prefix= "   "+fg.getHashFootprint();
		sufix= "      "+fg.getUserFootprint();
		
		this.prefixLabel.setText(prefix);
		this.sufixLabel.setText(sufix);
		
		this.panel.setBorder(ColorPalette.topSpace);
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
	}

	@Override
	public void setIsSelected(boolean b) {
		this.isSelected= b;
		
		if(this.isSelected)
		{
			if(this.isFile())
			{
				this.panel.setBackground(ColorPalette.softSelectionBg2);
				this.prefixLabel.setForeground(ColorPalette.softSelectionColor2);
				this.sufixLabel.setForeground(ColorPalette.softSelectionColor2);
			}
			else
			{
				this.panel.setBackground(ColorPalette.hardSelectionBg2);
				this.prefixLabel.setForeground(ColorPalette.hardSelectionColor2);
				this.sufixLabel.setForeground(ColorPalette.hardSelectionColor2);
			}
		}
		else
		{
			this.panel.setBackground(ColorPalette.bgColor);
			this.prefixLabel.setForeground(ColorPalette.contentColor);
			this.sufixLabel.setForeground(ColorPalette.contentColor);
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
