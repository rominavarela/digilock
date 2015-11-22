package com.esoorf.view.component;

import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.esoorf.view.ColorPalette;
import com.esoorf.view.FontPalette;

public class FootprintElement {
	public JPanel panel;
	
	public JLabel auxLabel;
	public JPanel auxPanel;
	
	JLabel footprintLabel;
	JLabel key1Label;
	JLabel key2Label;
	
	JTextField footprintInput;
	JPasswordField key1Input;
	JPasswordField key2Input;
	
	boolean isSelected;
	
	public FootprintElement(String footprint) {
		
		if(footprint.isEmpty())
			this.auxLabel= new JLabel("+");
		else
			this.auxLabel= new JLabel(footprint);
		
		this.auxLabel.setForeground(ColorPalette.contentColor);
		this.auxLabel.setFont(FontPalette.contentFont);
		this.auxPanel= new JPanel();
		this.auxPanel.add(auxLabel);
		this.auxPanel.setBackground(ColorPalette.bgColor);
		
		this.footprintLabel= new JLabel("name:");
		this.footprintLabel.setForeground(ColorPalette.contentColor);
		this.footprintLabel.setFont(FontPalette.contentFont);
		this.footprintInput= new JTextField();
		if(!footprint.isEmpty())
		{
			this.footprintInput.setText(footprint);
			this.footprintInput.setEditable(false);
			this.footprintInput.setForeground(ColorPalette.secondaryColor);
		}
		this.footprintInput.setBorder(ColorPalette.softBorder);
		this.footprintInput.setForeground(ColorPalette.contentColor);
		this.footprintInput.setBackground(ColorPalette.hardSelectionBg);
		
		this.key1Label= new JLabel("password:");
		this.key1Label.setForeground(ColorPalette.contentColor);
		this.key1Label.setFont(FontPalette.contentFont);
		this.key1Input= new JPasswordField();
		this.key1Input.setBorder(ColorPalette.softBorder);
		this.key1Input.setForeground(ColorPalette.hardSelectionColor);
		this.key1Input.setBackground(ColorPalette.hardSelectionBg);
		
		this.key2Label= new JLabel("");
		this.key2Label.setForeground(ColorPalette.contentColor);
		this.key2Label.setFont(FontPalette.contentFont);
		this.key2Input= new JPasswordField();
		this.key2Input.setBorder(ColorPalette.softBorder);
		this.key2Input.setForeground(ColorPalette.hardSelectionColor);
		this.key2Input.setBackground(ColorPalette.hardSelectionBg);
		
		this.panel= new JPanel();
		this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.panel.setBorder(ColorPalette.softBorder);
		this.panel.setBackground(ColorPalette.bgColor);
		
		this.panel.setLayout(new GridLayout(0, 1));
		this.panel.add(new JLabel(" "));
		this.panel.add(this.auxPanel);
		this.panel.add(new JLabel(" "));
	}
	

	public String getFootprint(){
		String footprint= this.footprintInput.getText();
		if(footprint.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Footprint name is empty");
			this.footprintInput.selectAll();
			return null;
		}
		
		return footprint;
	}
	
	@SuppressWarnings("deprecation")
	public String getKey(){
		String key1= this.key1Input.getText();
		String key2= this.key2Input.getText();
		
		if(key1.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Password field is empty");
			this.key1Input.selectAll();
			return null;
		}
		else if(key2.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Password field is empty");
			this.key2Input.selectAll();
			return null;
		}
		else if(!key1.contentEquals(key2))
		{
			JOptionPane.showMessageDialog(null, "Password fields mismatch");
			this.key2Input.selectAll();
			return null;
		}
		
		return key1;
	}
	
	//getters and setters
	public boolean getIsSelected() {
		return this.isSelected;
	}
	
	public void setIsSelected(boolean b) {
		this.isSelected= b;
		if(this.isSelected)
		{
			this.panel.removeAll();
			
			this.panel.setLayout(new GridLayout(0, 2));
			this.panel.add(this.footprintLabel);
			this.panel.add(this.footprintInput);
			this.panel.add(this.key1Label);
			this.panel.add(this.key1Input);
			this.panel.add(this.key2Label);
			this.panel.add(this.key2Input);
			this.panel.setBorder(ColorPalette.hardBorder);
			this.panel.setBackground(ColorPalette.hardSelectionBg);
			
			if(this.footprintInput.isEditable())
			{
				if(this.footprintInput.getText().isEmpty())
					this.footprintInput.setText("my-footprint");
				this.footprintInput.requestFocus();
				this.footprintInput.selectAll();
			}
			
			this.panel.revalidate();
			this.panel.repaint();
		}
		else
		{
			this.panel.removeAll();
			
			this.panel.setLayout(new GridLayout(0, 1));
			this.panel.add(new JLabel(" "));
			this.panel.add(this.auxPanel);
			this.panel.add(new JLabel(" "));
			this.panel.setBorder(ColorPalette.softBorder);
			this.panel.setBackground(ColorPalette.bgColor);
			
			this.panel.revalidate();
			this.panel.repaint();
		}
	}
}
