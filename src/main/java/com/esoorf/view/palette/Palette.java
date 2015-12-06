package com.esoorf.view.palette;

import java.awt.Color;
import java.awt.Font;

import javax.swing.border.Border;

public interface Palette {
	public Color getForeground();
	public Color getBackground();
	public Border getBorder();
	public Border getBorder(int top, int left, int bottom, int right);
	
	public Color getActiveForeground();
	public Color getActiveBackground();
	public Border getActiveBorder(int top, int left, int bottom, int right);
	
	public Font getTitleFont();
	public Font getSubFont();
	public Font getFont();
	public Font getActiveFont();
}
