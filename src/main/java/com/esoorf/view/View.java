package com.esoorf.view;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import com.esoorf.view.panel.HeaderPanel;
import com.esoorf.view.panel.LocationPanel;
import com.esoorf.view.panel.LockedPanel;
import com.esoorf.view.panel.UnlockedPanel;

@SuppressWarnings("serial")
public class View extends JFrame{
	public JSplitPane panel;
	public JSplitPane layer1;
	public JSplitPane layer2;
	
	View() {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setTitle("Digital Locker");
		super.setVisible(true);
		super.setSize(500, 500);
		
		//
		this.layer2= new JSplitPane();
		this.layer2.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.layer2.setBorder(ColorPalette.border);
		this.layer2.setDividerSize(2);
		this.layer2.setDividerLocation(230);
		
		this.layer2.setLeftComponent(UnlockedPanel.getInstance().panel);
		this.layer2.setRightComponent(LockedPanel.getInstance().panel);
		
		//
		this.layer1= new JSplitPane();
		this.layer1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.layer1.setBorder(ColorPalette.border);
		this.layer1.setDividerSize(0);
		
		
		this.layer1.setTopComponent(LocationPanel.getInstance().panel);
		this.layer1.setBottomComponent(this.layer2);
		
		//
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setBorder(ColorPalette.border);
		this.panel.setDividerSize(0);
		
		this.panel.setTopComponent(HeaderPanel.getInstance().panel);
		this.panel.setBottomComponent(this.layer1);
		this.add(this.panel);
	}
	
	// singleton
	private static View instance;
	public static synchronized View getInstance() {
		if(instance==null)
			instance= new View();
		return instance;
	}
}
