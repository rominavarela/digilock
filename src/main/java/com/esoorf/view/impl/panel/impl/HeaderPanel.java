package com.esoorf.view.impl.panel.impl;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.esoorf.view.impl.panel.Panel;
import com.esoorf.view.palette.Palette;
import com.esoorf.view.palette.impl.MainPalette;

public class HeaderPanel implements Panel{
	Palette palette;
	
	public JSplitPane panel;
	
	JLabel titleLabel;
	JPanel titlePanel;
	
	JLabel subtitleLabel;
	JPanel subtitlePanel;
	
	HeaderPanel() {		
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		this.titleLabel= new JLabel("Digital Locker");
		this.titlePanel= new JPanel();
		this.titlePanel.add(titleLabel);
		
		this.subtitleLabel= new JLabel("by eSoorf");
		this.subtitlePanel= new JPanel();
		this.subtitlePanel.add(subtitleLabel);
		
		this.panel.setTopComponent(this.titlePanel);
		this.panel.setBottomComponent(this.subtitlePanel);
		this.panel.setDividerSize(0);
		
		this.setPalette(new MainPalette());
	}
	
	public Component getPanel() {
		return this.panel;
	}
	
	public Palette getPalette() {
		return this.palette;
	}
	
	public void setPalette(Palette p) {
		this.palette= p;
		
		this.panel.setBorder(palette.getBorder());
		
		this.titleLabel.setForeground(palette.getForeground());
		this.titleLabel.setFont(palette.getTitleFont());
		this.titlePanel.setBackground(palette.getBackground());
		
		this.subtitleLabel.setForeground(palette.getForeground());
		this.subtitleLabel.setFont(palette.getSubFont());
		this.subtitlePanel.setBackground(palette.getBackground());
	}
	
	// singleton
	private static HeaderPanel instance;
	public static synchronized HeaderPanel getInstance() {
		if(instance==null)
			instance= new HeaderPanel();
		return instance;
	}
}
