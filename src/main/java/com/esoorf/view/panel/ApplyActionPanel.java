package com.esoorf.view.panel;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

import com.esoorf.constant.Actions;
import com.esoorf.constant.Views;
import com.esoorf.io.FileLocker;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;
import com.esoorf.view.View;
import com.esoorf.view.component.FileElement;
import com.esoorf.view.component.FootprintElement;

public class ApplyActionPanel {
	public JPanel panel;
	public JSplitPane lPanel;
	public JSplitPane rPanel;
	
	JLabel listLabel;
	JPanel listPanel;
	JTextArea fileList;
	JScrollPane fileListPanel;
	
	JSplitPane panelTop;
	JLabel footprintLabel;
	JPanel footprintPanel;
	JPanel footprintList;
	JScrollPane footprintListPanel;
	
	JPanel panelBottom;
	JPanel actionPanel;
	JLabel actionButton;
	JPanel cancelPanel;
	JLabel cancelButton;
	
	Actions actions;
	ArrayList<FileElement> selectedElements;
	ArrayList<FootprintElement> footprintElements;
	
	ApplyActionPanel() {
		
		//
		this.listLabel= new JLabel("Selected files:");
		this.listLabel.setForeground(ColorPalette.contentColor);
		this.listLabel.setFont(FontPalette.contentFont);
		this.listPanel= new JPanel();
		this.listPanel.add(listLabel);
		this.listPanel.setBackground(ColorPalette.bgColor);
		
		this.fileList= new JTextArea();
		this.fileList.setEditable(false);
		this.fileListPanel= new JScrollPane(this.fileList);
		
		this.lPanel= new JSplitPane();
		this.lPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.lPanel.setBorder(ColorPalette.border);
		this.lPanel.setDividerSize(0);
		
		this.lPanel.setTopComponent(this.listPanel);
		this.lPanel.setBottomComponent(this.fileListPanel);
		
		//
		this.footprintLabel= new JLabel("Footprint:");
		this.footprintLabel.setForeground(ColorPalette.contentColor);
		this.footprintLabel.setFont(FontPalette.contentFont);
		this.footprintPanel= new JPanel();
		this.footprintPanel.add(footprintLabel);
		this.footprintPanel.setBackground(ColorPalette.bgColor);
		
		this.footprintList= new JPanel();
		this.footprintList.setLayout(new GridLayout(0, 1));
		
		this.footprintListPanel= new JScrollPane(this.footprintList);
		
		this.panelTop= new JSplitPane();
		this.panelTop.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panelTop.setBorder(ColorPalette.border);
		this.panelTop.setDividerSize(0);
		
		this.panelTop.setTopComponent(this.footprintPanel);
		this.panelTop.setBottomComponent(this.footprintListPanel);
		
		//
		this.actionButton= new JLabel(" ");
		this.actionButton.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				applyActions();
			}
		});
		
		this.actionPanel= new JPanel();
		this.actionPanel.add(actionButton);
		this.actionPanel.setBorder(ColorPalette.grossBorder);
		this.actionPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.actionPanel.setBackground(ColorPalette.hardSelectionBg);
		
		this.cancelButton= new JLabel("cancel");
		
		this.cancelPanel= new JPanel();
		this.cancelPanel.add(cancelButton);
		this.cancelPanel.setBorder(ColorPalette.grossBorder);
		this.cancelPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.cancelPanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				View.getInstance().setView(Views.MAIN);
			}
		});
		
		this.panelBottom= new JPanel();
		this.panelBottom.setBorder(ColorPalette.border);
		this.panelBottom.setLayout(new GridLayout(1, 0));
		
		this.panelBottom.add(this.cancelPanel);
		this.panelBottom.add(this.actionPanel);
		
		//
		this.rPanel= new JSplitPane();
		this.rPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.rPanel.setBorder(ColorPalette.border);
		this.rPanel.setDividerSize(0);
		
		this.rPanel.setTopComponent(this.panelTop);
		this.rPanel.setBottomComponent(this.panelBottom);
		
		this.panel= new JPanel();
		this.panel.setBorder(ColorPalette.border);
		this.panel.setLayout(new GridLayout(1, 0));
		this.panel.add(this.lPanel);
		this.panel.add(this.rPanel);
	}
	
	@SuppressWarnings("incomplete-switch")
	public void applyActions() {
		switch(this.actions)
		{
			case LOCK:
				String footprint= null;
				String key= null;
				for(FootprintElement fpe: this.getFootprintElements())
				{
					if(fpe.getIsSelected())
					{
						footprint= fpe.getFootprint();
						key= fpe.getKey();
						break;
					}
				}
				
				if(footprint==null || key==null)
					return;
				
				//
				try
				{
					FileLocker locker= new FileLocker(footprint,key);
					for(FileElement fe: this.selectedElements)
						locker.lock(fe.getFile());
				}
				catch(Exception ex){}
				
				
				break;
			
			case UNLOCK:
				break;
		}
	}
	
	//getters and setters
	public Actions getActions() {
		return this.actions;
	}
	public ArrayList<FileElement> getSelectedElements() {
		return this.selectedElements;
	}
	public ArrayList<FootprintElement> getFootprintElements() {
		return this.footprintElements;
	}
	
	public void setActions(Actions a) {
		this.actions= a;
		this.actionButton.setText(a.name());
	}
	public void setSelectedElements(ArrayList<FileElement> selectedElements) {
		this.selectedElements= selectedElements;
		
		String s="\n";
		for(FileElement fe: this.selectedElements)
			s+= fe.getDescription()+"\n";
		
		this.fileList.setText(s);
		this.fileList.select(0, 0);
	}
	public void setFootprintElements(ArrayList<FootprintElement> arr) {
		this.footprintElements= arr;
		
		this.footprintList.removeAll();
		for(FootprintElement fpe: arr)
		{
			this.footprintList.add(fpe.panel);
			fpe.auxPanel.addMouseListener(new MouseListener() {
				public void mouseReleased(MouseEvent e) {}
				public void mousePressed(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {
					for(FootprintElement fpe: footprintElements)
					{
						if(fpe.auxPanel.equals(e.getSource()))
							fpe.setIsSelected(true);
						else if(fpe.getIsSelected())
							fpe.setIsSelected(false);
					}
				}
			});
		}
		
		if(footprintElements.size()>1)
			this.footprintElements.get(1).setIsSelected(true);
		else
		{
			this.footprintElements.get(0).setIsSelected(true);
			this.footprintList.add(new JLabel(""));
			this.footprintList.add(new JLabel(""));
		}
	}
	
	// singleton
	private static ApplyActionPanel instance;
	public static synchronized ApplyActionPanel getInstance() {
		if(instance==null)
			instance= new ApplyActionPanel();
		return instance;
	}
}
