package com.esoorf.view.impl.element.impl;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.esoorf.view.impl.element.Element;
import com.esoorf.view.palette.Palette;
import com.esoorf.view.palette.impl.MainPalette;

public class FootprintElement implements Element{
	public JPanel panel;
	Palette palette;
	
	public JLabel auxLabel;
	public JPanel auxPanel;
	
	JLabel footprintLabel;
	JLabel key1Label;
	JLabel key2Label;
	
	JTextField footprintInput;
	JPasswordField key1Input;
	JPasswordField key2Input;
	
	String hashFootprint;
	boolean isSelected;
	
	public FootprintElement(String footprint) {
		this.palette= new MainPalette();
		
		if(footprint.isEmpty())
			this.auxLabel= new JLabel("+");
		else
			this.auxLabel= new JLabel(footprint);
		
		this.auxLabel.setFont(palette.getFont());
		this.auxPanel= new JPanel();
		this.auxPanel.add(auxLabel);
		
		this.footprintLabel= new JLabel("name:");
		this.footprintLabel.setFont(palette.getFont());
		this.footprintInput= new JTextField();
		if(!footprint.isEmpty())
		{
			this.footprintInput.setText(footprint);
			this.footprintInput.setEditable(false);
		}
		
		this.key1Label= new JLabel("password:");
		this.key1Label.setFont(palette.getFont());
		this.key1Input= new JPasswordField();
		
		this.key2Label= new JLabel("");
		this.key2Label.setFont(palette.getFont());
		this.key2Input= new JPasswordField();
		
		this.panel= new JPanel();
		this.panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
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
		else if(footprint.length()>22)
		{
			JOptionPane.showMessageDialog(null, "Footprint name is too long (max 22)");
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
	public Component getPanel() {
		return this.panel;
	}
	public Palette getPalette() {
		return this.palette;
	}
	public String getHashFootprint() {
		return this.hashFootprint;
	}
	public boolean getIsSelected() {
		return this.isSelected;
	}
	
	public void setPalette(Palette p){
		this.palette= p;
		
		this.auxLabel.setForeground(palette.getForeground());
		this.auxPanel.setBackground(palette.getBackground());
		
		this.footprintLabel.setForeground(palette.getActiveForeground());
		this.footprintLabel.setBackground(palette.getActiveBackground());
		
		this.footprintInput.setBorder(palette.getBorder(0, 0, 1, 0));
		this.footprintInput.setForeground(palette.getActiveForeground());
		this.footprintInput.setBackground(palette.getActiveBackground());
		
		this.key1Label.setForeground(palette.getForeground());
		this.key1Input.setBorder(palette.getBorder(0, 0, 1, 0));
		this.key1Input.setForeground(palette.getActiveForeground());
		this.key1Input.setBackground(palette.getActiveBackground());
		
		this.key2Label.setForeground(palette.getForeground());
		this.key2Input.setBorder(palette.getBorder(0, 0, 1, 0));
		this.key2Input.setForeground(palette.getActiveForeground());
		this.key2Input.setBackground(palette.getActiveBackground());
		
		this.panel.setBorder(palette.getBorder(0, 0, 1, 0));
		this.panel.setBackground(palette.getBackground());
	}
	
	public void setHashFootprint(String s) {
		this.hashFootprint= s;
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
			this.panel.setBorder(palette.getActiveBorder(3, 3, 3, 3));
			this.panel.setBackground(palette.getActiveBackground());
			
			if(this.footprintInput.isEditable())
			{
				if(this.footprintInput.getText().isEmpty())
					this.footprintInput.setText("my-footprint");
				this.footprintInput.requestFocus();
				this.footprintInput.selectAll();
			}
			else
			{
				this.key1Input.requestFocus();
				this.key1Input.selectAll();
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
			this.panel.setBorder(palette.getActiveBorder(0, 0, 1, 0));
			this.panel.setBackground(palette.getBackground());
			
			this.panel.revalidate();
			this.panel.repaint();
		}
	}
}
