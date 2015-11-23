package com.esoorf.view.panel;

import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.esoorf.io.DirectoryUtils;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;
import com.esoorf.view.component.FileElement;
import com.esoorf.view.component.UnlockedFileElement;

public class UnlockedPanel {
	public JSplitPane panel;
	
	JLabel titleLabel;
	JPanel titlePanel;
	
	JPanel unlockedList;
	JScrollPane unlockedPane;
	
	ArrayList<FileElement> fileElements;
	
	UnlockedPanel() {
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setBorder(ColorPalette.border);
		
		this.titleLabel= new JLabel("Unlocked");
		this.titleLabel.setForeground(ColorPalette.contentColor);
		this.titleLabel.setFont(FontPalette.contentFont);
		this.titlePanel= new JPanel();
		this.titlePanel.add(titleLabel);
		this.titlePanel.setBackground(ColorPalette.bgColor);
		
		this.unlockedList= new JPanel();
		this.unlockedList.setLayout(new GridLayout(0, 1));
		
		this.fileElements= new ArrayList<FileElement>();
		this.updateList();
		this.unlockedPane= new JScrollPane(this.unlockedList);
		
		this.panel.setTopComponent(this.titlePanel);
		this.panel.setBottomComponent(this.unlockedPane);
		this.panel.setDividerSize(0);
	}
	
	public void updateList() {
		this.unlockedList.removeAll();
		this.fileElements.clear();
		
		DirectoryUtils dir= DirectoryUtils.getInstance();
		FileElement root= new UnlockedFileElement(dir.getWorkingDirectory());
		this.fileElements.add(root);

		FileElement parent= root;
		FileElement pointer= parent;
		for(File f:dir.getUnlockedFiles())
		{
			//set item
			FileElement child= new UnlockedFileElement(f);
			this.fileElements.add(child);
			
			//set parent
			while(!parent.getFile().equals(
					child.getFile().getParentFile()))
				parent= parent.getParent();
			child.setParent(parent);
			
			if(f.isDirectory())
				parent= child;
			
			//set next
			pointer.setNext(child);
			pointer= child;
		}
		
		for(FileElement fe: this.fileElements)
			this.unlockedList.add(fe.panel);
		this.unlockedList.revalidate();
		this.unlockedList.repaint();
	}
	
	public ArrayList<FileElement> getFileElements() {
		return this.fileElements;
	}
	
	// singleton
	private static UnlockedPanel instance;
	public static synchronized UnlockedPanel getInstance() {
		if(instance==null)
			instance= new UnlockedPanel();
		return instance;
	}
}
