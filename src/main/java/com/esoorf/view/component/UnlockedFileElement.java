package com.esoorf.view.component;

import java.io.File;

import com.esoorf.constant.Actions;
import com.esoorf.io.DirectoryUtils;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.panel.LockedPanel;

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
		
	}
	
	@Override
	public void setIsSelected(boolean b) {
		this.isSelected= b;
		
		if(this.isSelected)
		{
			if(this.isDirectory())
			{
				this.panel.setBackground(ColorPalette.hardSelectionBg);
				this.prefixLabel.setForeground(ColorPalette.hardSelectionColor);
				this.prefixLabel.setBorder(ColorPalette.hardSelectionTopSpace);
				this.sufixLabel.setForeground(ColorPalette.hardSelectionColor);
				this.sufixLabel.setBorder(ColorPalette.hardSelectionBottomSpace);
			}
			else
			{
				this.panel.setBackground(ColorPalette.softSelectionBg);
				this.prefixLabel.setForeground(ColorPalette.softSelectionColor);
				this.prefixLabel.setBorder(ColorPalette.softSelectionTopSpace);
				this.sufixLabel.setForeground(ColorPalette.softSelectionColor);
				this.sufixLabel.setBorder(ColorPalette.softSelectionBottomSpace);
			}
		}
		else
		{
			this.panel.setBackground(ColorPalette.bgColor);
			this.prefixLabel.setForeground(ColorPalette.contentColor);
			this.prefixLabel.setBorder(ColorPalette.topSpace);
			this.sufixLabel.setForeground(ColorPalette.contentColor);
			this.sufixLabel.setBorder(ColorPalette.bottomSpace);
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
