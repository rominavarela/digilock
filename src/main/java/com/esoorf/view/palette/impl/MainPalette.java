package com.esoorf.view.palette.impl;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.esoorf.view.palette.Palette;

public class MainPalette implements Palette{

	public Color getForeground() {
		return new Color(111, 0, 111);
	}

	public Color getBackground() {
		return new Color(250, 250, 250);
	}
	
	public Border getBorder() {
		return BorderFactory.createMatteBorder(1, 1, 1, 1, this.getBackground());
	}
	
	public Border getBorder(int top, int left, int bottom, int right) {
		return BorderFactory.createMatteBorder(top, left, bottom, right, this.getBackground());
	}

	public Color getActiveForeground() {
		return new Color(50, 50, 50);
	}

	public Color getActiveBackground() {
		return this.getBackground();
	}

	public Border getActiveBorder(int top, int left, int bottom, int right) {
		return BorderFactory.createMatteBorder(top, left, bottom, right, new Color(222,222,222));
	}

	public Font getTitleFont() {
		return new Font(Font.SANS_SERIF, Font.PLAIN, 20);
	}

	public Font getSubFont() {
		return new Font(Font.SANS_SERIF, Font.PLAIN, 10);
	}

	public Font getFont() {
		return new Font(Font.SANS_SERIF, Font.PLAIN, 12);
	}

	public Font getActiveFont() {
		return new Font(Font.SANS_SERIF, Font.BOLD, 10);
	}
}
