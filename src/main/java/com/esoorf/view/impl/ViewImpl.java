package com.esoorf.view.impl;

import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.esoorf.constant.Views;
import com.esoorf.view.View;
import com.esoorf.view.impl.element.impl.FootprintElement;
import com.esoorf.view.impl.panel.impl.ActionPanel;
import com.esoorf.view.impl.panel.impl.ApplyActionPanel;
import com.esoorf.view.impl.panel.impl.HeaderPanel;
import com.esoorf.view.impl.panel.impl.LocationPanel;
import com.esoorf.view.impl.panel.impl.LockedPanel;
import com.esoorf.view.impl.panel.impl.UnlockedPanel;
import com.esoorf.view.palette.Palette;
import com.esoorf.view.palette.impl.MainPalette;

@SuppressWarnings("serial")
public class ViewImpl extends JFrame implements View{
	public JSplitPane panel;
	public JSplitPane locationLayer;
	public JSplitPane contentLayer;
	public JPanel vLayer;
	
	Views view;
	Palette palette;
	
	int width= 500;
	int height= 500;
	ViewImpl() {
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
		this.vLayer.setLayout(new GridLayout(1, 0));
		
		this.vLayer.add(UnlockedPanel.getInstance().panel);
		this.vLayer.add(LockedPanel.getInstance().panel);
		
		//
		this.contentLayer= new JSplitPane();
		this.contentLayer.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.contentLayer.setDividerSize(0);
		this.contentLayer.setDividerLocation(height-200);
		
		this.contentLayer.setTopComponent(this.vLayer);
		this.contentLayer.setBottomComponent(ActionPanel.getInstance().panel);
		
		//
		this.locationLayer= new JSplitPane();
		this.locationLayer.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.locationLayer.setDividerSize(0);
		
		
		this.locationLayer.setTopComponent(LocationPanel.getInstance().panel);
		this.locationLayer.setBottomComponent(this.contentLayer);
		
		//
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setDividerSize(0);
		
		this.panel.setTopComponent(HeaderPanel.getInstance().panel);
		this.panel.setBottomComponent(this.locationLayer);
		this.add(this.panel);
		
		this.setPalette(new MainPalette());
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
	
	public JSplitPane getPanel() {
		return this.panel;
	}

	public Palette getPalette() {
		return this.palette;
	}
	
	public void setPalette(Palette p) {
		this.palette= p;
		
		this.vLayer.setBorder(palette.getBorder());
		this.contentLayer.setBorder(palette.getBorder());
		this.locationLayer.setBorder(palette.getBorder());
		this.panel.setBorder(palette.getBorder());
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
				{
					fpe.setPalette(aap.getPalette());
					if(fpe.getIsSelected())
						fpe.setIsSelected(true);
				}
				
				break;
		}
		
		this.applyHeight();
	}
	
	// singleton
	private static ViewImpl instance;
	public static synchronized ViewImpl getInstance() {
		if(instance==null)
			instance= new ViewImpl();
		return instance;
	}
}
