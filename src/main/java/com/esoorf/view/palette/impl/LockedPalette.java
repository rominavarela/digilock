package com.esoorf.view.palette.impl;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class LockedPalette extends MainPalette{
	
	@Override
	public Color getForeground() {
		return new Color(50, 50, 50);
	}
	
	@Override
	public Color getActiveForeground() {
		return new Color(250,250,250);
	}
	
	@Override
	public Color getActiveBackground() {
		return new Color(111,0,111);
	}
	
	@Override
	public Border getActiveBorder(int top, int left, int bottom, int right) {
		return BorderFactory.createMatteBorder(top, left, bottom, right, getActiveBackground());
	}
}
