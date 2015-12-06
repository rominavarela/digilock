package com.esoorf.view.impl.panel.impl;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.esoorf.constant.Actions;
import com.esoorf.constant.Views;
import com.esoorf.view.impl.ViewImpl;
import com.esoorf.view.impl.element.impl.FileElement;
import com.esoorf.view.impl.panel.Panel;
import com.esoorf.view.palette.Palette;
import com.esoorf.view.palette.impl.MainPalette;

public class ActionPanel implements Panel{
	public JPanel panel;
	public JLabel actionButton;
	
	Actions actions;
	Palette palette;
	
	private ActionPanel() {
		this.actionButton= new JLabel(" ");
		this.palette= new MainPalette();
		
		this.panel= new JPanel();
		this.panel.add(actionButton);
		this.panel.setBorder(palette.getBorder(10, 0, 10, 0));
		this.setActions(Actions.NONE);
		
		this.panel.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			
			public void mouseClicked(MouseEvent e) {
				switch(actions)
				{
					case NONE:
						break;
						
					case LOCK:
						ArrayList<FileElement> selectedElements= new ArrayList<FileElement>();
						
						for(FileElement fe: UnlockedPanel.getInstance().getFileElements())
						{
							if(fe.getIsSelected())
								if(fe.isFile())
									selectedElements.add(fe);
						}
						
						ApplyActionPanel aap= ApplyActionPanel.getInstance();
						aap.setActions(actions);
						aap.setPalette(palette);
						aap.setSelectedElements(selectedElements);
						ViewImpl.getInstance().setView(Views.APPLY);
						
						break;
						
					case UNLOCK:
						break;
				}
			}
		});
	}
	
	//getters and setters
	public JPanel getPanel() {
		return this.panel;
	}

	public Palette getPalette() {
		return this.palette;
	}
	
	public Actions getAction(){
		return this.actions;
	}
	
	public void setPalette(Palette p) {
		this.palette= p;
		this.panel.setBackground(palette.getActiveBackground());
		this.actionButton.setForeground(palette.getActiveForeground());
	}
	
	public void setActions(Actions a){
		this.actions= a;
		switch(actions)
		{
			case NONE:
				this.actionButton.setText("");
				this.panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				break;
				
			case LOCK:
				this.actionButton.setText("LOCK >>");
				this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				break;
				
			case UNLOCK:
				this.actionButton.setText("<< UNLOCK");
				this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				break;
		}
	}
	
	// singleton
	private static ActionPanel instance;
	public static synchronized ActionPanel getInstance() {
		if(instance==null)
			instance= new ActionPanel();
		return instance;
	}
}
