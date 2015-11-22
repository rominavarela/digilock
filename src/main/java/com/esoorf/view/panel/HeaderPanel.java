package com.esoorf.view.panel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;

public class HeaderPanel {
	public JSplitPane panel;
	
	JLabel titleLabel;
	JPanel titlePanel;
	
	JLabel subtitleLabel;
	JPanel subtitlePanel;
	
	HeaderPanel() {
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setBorder(ColorPalette.border);
		
		this.titleLabel= new JLabel("Digital Locker");
		this.titleLabel.setForeground(ColorPalette.primaryColor);
		this.titleLabel.setFont(FontPalette.titleFont);
		this.titlePanel= new JPanel();
		this.titlePanel.add(titleLabel);
		this.titlePanel.setBackground(ColorPalette.bgColor);
		
		this.subtitleLabel= new JLabel("by eSoorf");
		this.subtitleLabel.setForeground(ColorPalette.secondaryColor);
		this.subtitleLabel.setFont(FontPalette.subtitleFont);
		this.subtitlePanel= new JPanel();
		this.subtitlePanel.add(subtitleLabel);
		this.subtitlePanel.setBackground(ColorPalette.bgColor);
		
		this.panel.setTopComponent(this.titlePanel);
		this.panel.setBottomComponent(this.subtitlePanel);
		this.panel.setDividerSize(0);
	}
	
	// singleton
	private static HeaderPanel instance;
	public static synchronized HeaderPanel getInstance() {
		if(instance==null)
			instance= new HeaderPanel();
		return instance;
	}
}
