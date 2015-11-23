package com.esoorf.view.component;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.event.MouseInputListener;

import com.esoorf.constant.Actions;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;
import com.esoorf.view.panel.ActionPanel;

public abstract class FileElement {
	public JSplitPane panel;
	
	JLabel prefixLabel;
	JLabel sufixLabel;
	String prefix= "";
	String sufix = "";
	
	FileElement parent;
	File file;
	FileElement next;
	boolean isSelected;
	Actions actions;
	
	public FileElement(){
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.panel.setBorder(ColorPalette.softBorder);
		
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
				deselectedOthers();
				setIsSelected(isSelected);
				ActionPanel.getInstance().setActions(actions);
			}
		});
		
		this.panel.setTopComponent(this.prefixLabel);
		this.panel.setBottomComponent(this.sufixLabel);
		this.panel.setDividerSize(0);
	}
	
	public String getDescription(){
		return this.prefix + "\n" + this.sufix;
	}
	
	public boolean isFile() {
		return (this.file==null) ? 
				false : 
				this.file.isFile();
	}
	
	public boolean isDirectory() {
		return (this.file==null) ? 
				false : 
				this.file.isDirectory();
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
	
	public void setParent(FileElement fe) {
		this.parent= fe;
	}
	public void setFile(File f) {
		this.file= f;
	}
	public void setNext(FileElement fe) {
		this.next= fe;
	}
	public void setActions(Actions a) {
		this.actions= a;
	}
	
	public abstract void deselectedOthers();
	public abstract void setIsSelected(boolean b);
}
