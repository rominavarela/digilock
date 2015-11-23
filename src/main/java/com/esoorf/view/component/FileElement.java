package com.esoorf.view.component;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.event.MouseInputListener;

import com.esoorf.constant.Actions;
import com.esoorf.io.DirectoryUtils;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;
import com.esoorf.view.panel.ActionPanel;

public class FileElement {
	public JSplitPane panel;
	private JLabel prefixLabel;
	private JLabel sufixLabel;
	private String prefix= "";
	private String sufix = "";
	
	private FileElement parent;
	private File file;
	private FileElement next;
	private boolean isSelected;
	private Actions actions;
	
	public FileElement(File f){
		this.file= f;
		this.actions= Actions.NONE;
		
		DirectoryUtils dir= DirectoryUtils.getInstance();
		
		if(f.equals(dir.getWorkingDirectory()))
		{
			prefix= "*";
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
		
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.prefixLabel= new JLabel(prefix);
		this.prefixLabel.setFont(FontPalette.subtitleFont);
		
		this.sufixLabel= new JLabel(sufix);
		this.sufixLabel.setFont(FontPalette.contentFont);
		
		this.setIsSelected(false);
		this.panel.addMouseListener(new MouseInputListener() {
			public void mouseMoved(MouseEvent arg0) {}
			public void mouseDragged(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {
				isSelected= !isSelected;
				setIsSelected(isSelected);
			}
		});
		
		this.panel.setTopComponent(this.prefixLabel);
		this.panel.setBottomComponent(this.sufixLabel);
		if(sufix.isEmpty())
			this.panel.setDividerLocation(40);
		this.panel.setDividerSize(0);
	}
	
	//getters and setters
	public FileElement getParent() {
		return this.parent;
	}
	public File getFile() {
		return this.file;
	}
	public FileElement getNext() {
		return this.next;
	}
	public boolean getIsSelected() {
		return this.isSelected;
	}
	public Actions getActions() {
		return this.actions;
	}
	public String getDescription() {
		return this.prefix + "\n" + this.sufix;
	}
	
	public void setParent(FileElement fe) {
		this.parent= fe;
	}
	public void setNext(FileElement fe) {
		this.next= fe;
	}
	public void setIsSelected(boolean b){
		this.isSelected= b;
		
		if(this.isSelected)
		{
			if(this.getFile().isDirectory())
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
			
			ActionPanel.getInstance().setActions(this.getActions());
		}
		else
		{
			this.panel.setBackground(ColorPalette.bgColor);
			this.prefixLabel.setForeground(ColorPalette.contentColor);
			this.prefixLabel.setBorder(ColorPalette.topSpace);
			this.sufixLabel.setForeground(ColorPalette.contentColor);
			this.sufixLabel.setBorder(ColorPalette.bottomSpace);
		}
		
		if(this.getFile().isDirectory())
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
	public void setActions(Actions a){
		this.actions= a;
	}
}
