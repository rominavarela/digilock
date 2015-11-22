package com.esoorf.view.panel;

import java.awt.Cursor;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.event.MouseInputListener;

import com.esoorf.io.DirectoryUtils;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;

public class LocationPanel {
	public JSplitPane panel;
	
	public JSplitPane locationPane;
	JLabel locationLabel;
	JLabel locationButton;
	
	public JSplitPane lookupPane;
	JLabel lookupLabel;
	JCheckBox lookupButton;
	
	LocationPanel() {
		
		//
		this.locationLabel= new JLabel("Location:   ");
		this.locationLabel.setFont(FontPalette.contentFont);
		this.locationLabel.setForeground(ColorPalette.contentColor);
		
		this.locationButton= new JLabel(DirectoryUtils.getInstance().getWorkingDirectory().getAbsolutePath());
		this.locationButton.setFont(FontPalette.contentFont);
		this.locationButton.setForeground(ColorPalette.contentColor);
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
					locationButton.setText(
							DirectoryUtils.getInstance().chooseDirectory());
					UnlockedPanel.getInstance().updateList();
				}
		});
		
		this.locationPane= new JSplitPane();
		this.locationPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.locationPane.setBorder(ColorPalette.border);
		this.locationPane.setBackground(ColorPalette.bgColor);
		
		this.locationPane.setLeftComponent(this.locationLabel);
		this.locationPane.setRightComponent(this.locationButton);
		this.locationPane.setDividerSize(0);
		
		//
		this.lookupLabel= new JLabel("Lookup:     ");
		this.lookupLabel.setFont(FontPalette.contentFont);
		this.lookupLabel.setForeground(ColorPalette.contentColor);
		
		this.lookupButton= new JCheckBox("recursive");
		this.lookupButton.setFont(FontPalette.contentFont);
		this.lookupButton.setForeground(ColorPalette.contentColor);
		this.lookupButton.setBackground(ColorPalette.bgColor);
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
						DirectoryUtils.getInstance().setRecursiveLookup(
								lookupButton.isSelected());
						DirectoryUtils.getInstance().updateFileGroups();
						UnlockedPanel.getInstance().updateList();
					}
			});
		
		this.lookupPane= new JSplitPane();
		this.lookupPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		this.lookupPane.setBorder(ColorPalette.border);
		this.lookupPane.setBackground(ColorPalette.bgColor);
		
		this.lookupPane.setLeftComponent(this.lookupLabel);
		this.lookupPane.setRightComponent(this.lookupButton);
		this.lookupPane.setDividerSize(0);
		
		//
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setBorder(ColorPalette.border);
		this.panel.setBackground(ColorPalette.bgColor);
		
		this.panel.setTopComponent(this.locationPane);
		this.panel.setBottomComponent(this.lookupPane);
		this.panel.setDividerSize(0);
	}
	
	// singleton
	private static LocationPanel instance;
	public static synchronized LocationPanel getInstance() {
		if(instance==null)
			instance= new LocationPanel();
		return instance;
	}
}
