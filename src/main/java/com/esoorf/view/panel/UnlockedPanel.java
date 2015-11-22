package com.esoorf.view.panel;

import java.awt.GridLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.esoorf.io.DirectoryUtils;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;
import com.esoorf.view.component.FileElement;

public class UnlockedPanel {
	public JSplitPane panel;
	
	JLabel titleLabel;
	JPanel titlePanel;
	
	JPanel unlockedList;
	JScrollPane unlockedPane;
	
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
		this.updateList();
		this.unlockedPane= new JScrollPane(this.unlockedList);
		
		this.panel.setTopComponent(this.titlePanel);
		this.panel.setBottomComponent(this.unlockedPane);
		this.panel.setDividerSize(0);
	}
	
	public void updateList() {
		this.unlockedList.removeAll();
		for(File f:DirectoryUtils.getInstance().getUnlockedFiles())
		{
			FileElement fileElement= new FileElement(f);
			this.unlockedList.add(fileElement.panel);
		}
		
		this.unlockedList.revalidate();
		this.unlockedList.repaint();
	}
	
	// singleton
	private static UnlockedPanel instance;
	public static synchronized UnlockedPanel getInstance() {
		if(instance==null)
			instance= new UnlockedPanel();
		return instance;
	}
}
