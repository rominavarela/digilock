package com.esoorf.view.panel;

import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.esoorf.io.DirectoryUtils;
import com.esoorf.model.FileGroup;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;
import com.esoorf.view.component.FileElement;
import com.esoorf.view.component.LockedFileElement;

public class LockedPanel {
	public JSplitPane panel;
	
	JLabel titleLabel;
	JPanel titlePanel;
	
	JPanel lockedList;
	JScrollPane lockedPane;
	
	ArrayList<FileElement> fileElements;
	
	LockedPanel() {
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setBorder(ColorPalette.border);
		
		this.titleLabel= new JLabel("Locked");
		this.titleLabel.setForeground(ColorPalette.contentColor);
		this.titleLabel.setFont(FontPalette.contentFont);
		this.titlePanel= new JPanel();
		this.titlePanel.add(titleLabel);
		this.titlePanel.setBackground(ColorPalette.bgColor);
		
		this.lockedList= new JPanel();
		this.lockedList.setLayout(new GridLayout(0, 1));
		
		this.fileElements= new ArrayList<FileElement>();
		this.updateList();
		this.lockedPane= new JScrollPane(this.lockedList);
		
		this.panel.setTopComponent(this.titlePanel);
		this.panel.setBottomComponent(this.lockedPane);
		this.panel.setDividerSize(0);
	}
	
	public void updateList() {
		this.lockedList.removeAll();
		this.fileElements.clear();
		
		DirectoryUtils dir= DirectoryUtils.getInstance();
		FileElement prev = null;
		for(FileGroup group: dir.getLockedGroups())
		{
			FileElement root= new LockedFileElement(group);
			this.fileElements.add(root);
			if(prev!=null)
				prev.setNext(root);
			prev= root;
			
			for(File f: group.getFiles())
			{
				FileElement fe= new LockedFileElement(f);
				fe.setParent(root);
				this.fileElements.add(fe);
				
				prev.setNext(fe);
				prev= fe;
			}
		}
		
		for(FileElement fe: this.fileElements)
			this.lockedList.add(fe.panel);
		this.lockedList.revalidate();
		this.lockedList.repaint();
	}
	
	public ArrayList<FileElement> getFileElements() {
		return this.fileElements;
	}
	
	// singleton
	private static LockedPanel instance;
	public static synchronized LockedPanel getInstance() {
		if(instance==null)
			instance= new LockedPanel();
		return instance;
	}
}
