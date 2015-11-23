package com.esoorf.view.panel;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.esoorf.constant.Actions;
import com.esoorf.constant.Views;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.View;
import com.esoorf.view.component.FileElement;

public class ActionPanel {
	public JPanel panel;
	public JLabel actionButton;
	
	Actions actions;
	
	private ActionPanel() {
		this.actionButton= new JLabel(" ");
		
		this.panel= new JPanel();
		this.panel.add(actionButton);
		this.panel.setBorder(ColorPalette.grossBorder);
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
								if(fe.getFile().isFile())
									selectedElements.add(fe);
						}
						
						ApplyActionPanel aap= ApplyActionPanel.getInstance();
						aap.setActions(actions);
						aap.setSelectedElements(selectedElements);
						View.getInstance().setView(Views.APPLY);
						
						break;
						
					case UNLOCK:
						break;
				}
			}
		});
	}
	
	//getters and setters
	public void setActions(Actions a){
		this.actions= a;
		switch(actions)
		{
			case NONE:
				this.actionButton.setText("");
				this.panel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				this.panel.setBackground(ColorPalette.bgColor);
				break;
				
			case LOCK:
				this.actionButton.setText("LOCK >>");
				this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				this.panel.setBackground(ColorPalette.hardSelectionBg);
				break;
				
			case UNLOCK:
				this.actionButton.setText("<< UNLOCK");
				this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				this.panel.setBackground(ColorPalette.hardSelectionBg);
				break;
		}
	}
	
	public Actions getAction(){
		return this.actions;
	}
	
	// singleton
	private static ActionPanel instance;
	public static synchronized ActionPanel getInstance() {
		if(instance==null)
			instance= new ActionPanel();
		return instance;
	}
}
