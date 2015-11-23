package com.esoorf.view;

import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.esoorf.constant.Views;
import com.esoorf.view.component.FootprintElement;
import com.esoorf.view.panel.ActionPanel;
import com.esoorf.view.panel.ApplyActionPanel;
import com.esoorf.view.panel.HeaderPanel;
import com.esoorf.view.panel.LocationPanel;
import com.esoorf.view.panel.LockedPanel;
import com.esoorf.view.panel.UnlockedPanel;

@SuppressWarnings("serial")
public class View extends JFrame{
	public JSplitPane panel;
	public JSplitPane locationLayer;
	public JSplitPane contentLayer;
	public JPanel vLayer;
	
	Views view;
	
	int width= 500;
	int height= 500;
	View() {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setTitle("Digital Locker");
		super.setVisible(true);
		super.setSize(width, height);
		this.view= Views.MAIN;
		super.addComponentListener(new ComponentListener() {
			public void componentShown(ComponentEvent e) {}
			public void componentMoved(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
			public void componentResized(ComponentEvent e) {
				int h= getHeight();
				
				if(h!=height)
				{
					height= h;
					applyHeight();
				}
			}
		});
		
		//
		this.vLayer= new JPanel();
		this.vLayer.setBorder(ColorPalette.border);
		this.vLayer.setLayout(new GridLayout(1, 0));
		
		this.vLayer.add(UnlockedPanel.getInstance().panel);
		this.vLayer.add(LockedPanel.getInstance().panel);
		
		//
		this.contentLayer= new JSplitPane();
		this.contentLayer.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.contentLayer.setBorder(ColorPalette.border);
		this.contentLayer.setDividerSize(0);
		this.contentLayer.setDividerLocation(height-200);
		
		this.contentLayer.setTopComponent(this.vLayer);
		this.contentLayer.setBottomComponent(ActionPanel.getInstance().panel);
		
		//
		this.locationLayer= new JSplitPane();
		this.locationLayer.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.locationLayer.setBorder(ColorPalette.border);
		this.locationLayer.setDividerSize(0);
		
		
		this.locationLayer.setTopComponent(LocationPanel.getInstance().panel);
		this.locationLayer.setBottomComponent(this.contentLayer);
		
		//
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setBorder(ColorPalette.border);
		this.panel.setDividerSize(0);
		
		this.panel.setTopComponent(HeaderPanel.getInstance().panel);
		this.panel.setBottomComponent(this.locationLayer);
		this.add(this.panel);
	}
	
	public void applyHeight() {
		switch(this.getView())
		{
			case MAIN:
				this.contentLayer.setDividerLocation(this.height-200);
				break;
				
			case APPLY:
				ApplyActionPanel.getInstance().rPanel.setDividerLocation(this.height-155);
				break;
		}
	}
	
	//getters and setters
	public Views getView(){
		return this.view;
	}
	
	public void setView(Views view) {
		this.view= view;
		switch(this.getView())
		{
			case MAIN:
				this.panel.setBottomComponent(this.locationLayer);
				
				this.locationLayer.revalidate();
				this.locationLayer.repaint();
				break;
				
			case APPLY:
				ApplyActionPanel aap= ApplyActionPanel.getInstance();
				this.panel.setBottomComponent(aap.panel);
				
				this.locationLayer.revalidate();
				this.locationLayer.repaint();
				
				for(FootprintElement fpe: aap.getFootprintElements())
					if(fpe.getIsSelected())
					{
						fpe.setIsSelected(true);
						break;
					}
				
				break;
		}
		
		this.applyHeight();
	}
	
	// singleton
	private static View instance;
	public static synchronized View getInstance() {
		if(instance==null)
			instance= new View();
		return instance;
	}
}
