package com.esoorf.view.impl.panel.impl;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.event.MouseInputListener;

import com.esoorf.io.DirectoryUtils;
import com.esoorf.model.FileGroup;
import com.esoorf.view.impl.element.impl.FootprintElement;
import com.esoorf.view.impl.panel.Panel;
import com.esoorf.view.palette.Palette;
import com.esoorf.view.palette.impl.MainPalette;

public class LocationPanel implements Panel{
	Palette palette;
	
	public JSplitPane panel;
	
	public JSplitPane locationPane;
	JLabel locationLabel;
	JLabel locationButton;
	
	public JSplitPane lookupPane;
	JLabel lookupLabel;
	JCheckBox lookupButton;
	
	LocationPanel() {
		this.locationLabel= new JLabel("Location:   ");
		
		this.locationButton= new JLabel(DirectoryUtils.getInstance().getWorkingDirectory().getAbsolutePath());
		this.locationButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.locationButton.addMouseListener(
			new MouseInputListener() {
				public void mouseMoved(MouseEvent arg0) {}
				public void mouseDragged(MouseEvent arg0) {}
				public void mouseReleased(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {
					locationButton.setText(DirectoryUtils.getInstance().chooseDirectory());
					updateAll();
				}
		});
		
		this.locationPane= new JSplitPane();
		this.locationPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		
		this.locationPane.setLeftComponent(this.locationLabel);
		this.locationPane.setRightComponent(this.locationButton);
		this.locationPane.setDividerSize(0);
		
		//
		this.lookupLabel= new JLabel("Lookup:     ");
		
		this.lookupButton= new JCheckBox("recursive");
		this.lookupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.lookupButton.addMouseListener(
				new MouseInputListener() {
					public void mouseMoved(MouseEvent arg0) {}
					public void mouseDragged(MouseEvent arg0) {}
					public void mouseReleased(MouseEvent e) {}
					public void mousePressed(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					public void mouseClicked(MouseEvent e) {
						updateAll();
					}
			});
		
		this.lookupPane= new JSplitPane();
		this.lookupPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		
		this.lookupPane.setLeftComponent(this.lookupLabel);
		this.lookupPane.setRightComponent(this.lookupButton);
		this.lookupPane.setDividerSize(0);
		
		//
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		this.panel.setTopComponent(this.locationPane);
		this.panel.setBottomComponent(this.lookupPane);
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
		
		this.locationLabel.setFont(palette.getFont());
		this.locationLabel.setForeground(palette.getActiveForeground());
		
		this.locationButton.setFont(palette.getFont());
		this.locationButton.setForeground(palette.getActiveForeground());
		
		this.locationPane.setBorder(palette.getBorder());
		this.locationPane.setBackground(palette.getBackground());
		
		//
		this.lookupLabel.setFont(palette.getFont());
		this.lookupLabel.setForeground(palette.getActiveForeground());
		
		this.lookupButton.setFont(palette.getFont());
		this.lookupButton.setForeground(palette.getActiveForeground());
		this.lookupButton.setBackground(palette.getBackground());
		
		this.lookupPane.setBorder(palette.getBorder());
		this.lookupPane.setBackground(palette.getBackground());
		
		//
		this.panel.setBorder(palette.getBorder());
		this.panel.setBackground(palette.getBackground());
	}
	
	public void updateAll(){
		DirectoryUtils dir = DirectoryUtils.getInstance();
		dir.setRecursiveLookup(lookupButton.isSelected());
		dir.updateFileGroups();
		
		UnlockedPanel.getInstance().updateList();
		LockedPanel.getInstance().updateList();
		
		ArrayList<FootprintElement> fpe= new ArrayList<FootprintElement>();
		fpe.add(new FootprintElement(""));
		for(FileGroup lockedGroup: dir.getLockedGroups()){
			FootprintElement fe= new FootprintElement(lockedGroup.getUserFootprint());
			fe.setHashFootprint(lockedGroup.getFootprint());
			fpe.add(fe);
		}
		
		ApplyActionPanel.getInstance().setFootprintElements(fpe);
	}
	
	// singleton
	private static LocationPanel instance;
	public static synchronized LocationPanel getInstance() {
		if(instance==null)
			instance= new LocationPanel();
		return instance;
	}
}
