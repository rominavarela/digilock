package com.esoorf.view.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;

public class LockedPanel {
	public JSplitPane panel;
	
	JLabel titleLabel;
	JPanel titlePanel;
	
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
		
		this.panel.setTopComponent(this.titlePanel);
		this.panel.setBottomComponent(new JPanel());
		this.panel.setDividerSize(0);
	}
	
	// singleton
	private static LockedPanel instance;
	public static synchronized LockedPanel getInstance() {
		if(instance==null)
			instance= new LockedPanel();
		return instance;
	}
}
