package com.esoorf.view.impl.panel.impl;

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
import com.esoorf.view.impl.ViewImpl;
import com.esoorf.view.impl.element.impl.FileElement;
import com.esoorf.view.impl.element.impl.FootprintElement;
import com.esoorf.view.impl.panel.Panel;
import com.esoorf.view.palette.Palette;

public class ApplyActionPanel implements Panel{
	Palette palette;
	
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
		this.listLabel= new JLabel("Selected files:");
		this.listPanel= new JPanel();
		this.listPanel.add(listLabel);
		
		this.fileList= new JTextArea();
		this.fileList.setEditable(false);
		this.fileListPanel= new JScrollPane(this.fileList);
		
		this.lPanel= new JSplitPane();
		this.lPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.lPanel.setDividerSize(0);
		
		this.lPanel.setTopComponent(this.listPanel);
		this.lPanel.setBottomComponent(this.fileListPanel);
		
		//
		this.footprintLabel= new JLabel("Footprint:");
		this.footprintPanel= new JPanel();
		this.footprintPanel.add(footprintLabel);
		
		this.footprintList= new JPanel();
		this.footprintList.setLayout(new GridLayout(0, 1));
		
		this.footprintListPanel= new JScrollPane(this.footprintList);
		
		this.panelTop= new JSplitPane();
		this.panelTop.setOrientation(JSplitPane.VERTICAL_SPLIT);
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
		this.actionPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		this.cancelButton= new JLabel("cancel");
		
		this.cancelPanel= new JPanel();
		this.cancelPanel.add(cancelButton);
		this.cancelPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.cancelPanel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {
				ViewImpl.getInstance().setView(Views.MAIN);
			}
		});
		
		this.panelBottom= new JPanel();
		this.panelBottom.setLayout(new GridLayout(1, 0));
		
		this.panelBottom.add(this.cancelPanel);
		this.panelBottom.add(this.actionPanel);
		
		//
		this.rPanel= new JSplitPane();
		this.rPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.rPanel.setDividerSize(0);
		
		this.rPanel.setTopComponent(this.panelTop);
		this.rPanel.setBottomComponent(this.panelBottom);
		
		this.panel= new JPanel();
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
	public JPanel getPanel() {
		return this.panel;
	}
	public Palette getPalette() {
		return this.palette;
	}
	public Actions getActions() {
		return this.actions;
	}
	public ArrayList<FileElement> getSelectedElements() {
		return this.selectedElements;
	}
	public ArrayList<FootprintElement> getFootprintElements() {
		return this.footprintElements;
	}
	
	public void setPalette(Palette p) {
		this.palette= p;

		this.listLabel.setFont(palette.getFont());
		this.listLabel.setForeground(palette.getForeground());
		this.listPanel.setBackground(palette.getBackground());
		this.lPanel.setBorder(palette.getBorder());
		this.footprintLabel.setFont(palette.getFont());
		this.footprintLabel.setForeground(palette.getForeground());
		this.footprintPanel.setBackground(palette.getBackground());
		this.panelTop.setBorder(palette.getBorder());
		this.actionPanel.setBorder(palette.getBorder(10, 0, 10, 0));
		this.actionPanel.setBackground(palette.getActiveBackground());
		this.actionPanel.setForeground(palette.getActiveForeground());
		this.cancelPanel.setBorder(palette.getBorder(10, 0, 10, 0));
		this.panelBottom.setBorder(palette.getBorder());
		this.rPanel.setBorder(palette.getBorder());
		this.panel.setBorder(palette.getBorder());
		
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
