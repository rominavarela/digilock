package com.esoorf.view.component;

import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.event.MouseInputListener;

import com.esoorf.io.DirectoryUtils;
import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;

public class FileElement {
	public JSplitPane panel;
	
	File file;
	boolean isSelected;
	JLabel prefixLabel;
	JLabel sufixLabel;
	
	public FileElement(File f){
		this.file= f;
		
		this.panel= new JSplitPane();
		this.panel.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.panel.setBorder(ColorPalette.softBorder);
		this.panel.setBackground(ColorPalette.bgColor);
		
		String prefix= f.getParent().substring(
				DirectoryUtils.getInstance().getWorkingDirectory()
				.getAbsolutePath().length()
				);
		this.prefixLabel= new JLabel(prefix);
		this.prefixLabel.setForeground(ColorPalette.contentColor);
		this.prefixLabel.setFont(FontPalette.subtitleFont);
		
		this.sufixLabel= new JLabel("   "+f.getName());
		this.sufixLabel.setForeground(ColorPalette.contentColor);
		this.sufixLabel.setFont(FontPalette.contentFont);
		
		this.panel.addMouseListener(new MouseInputListener() {
			public void mouseMoved(MouseEvent arg0) {}
			public void mouseDragged(MouseEvent arg0) {}
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Click");
			}
		});
		
		this.panel.setTopComponent(this.prefixLabel);
		this.panel.setBottomComponent(this.sufixLabel);
		this.panel.setDividerSize(0);
	}
}
