package com.esoorf.view.palette.impl;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class UnlockedPalette extends MainPalette{
	
	@Override
	public Color getForeground() {
		return new Color(50, 50, 50);
	}
	
	@Override
	public Color getActiveForeground() {
		return new Color(0,0,0);
	}
	
	@Override
	public Color getActiveBackground() {
		return new Color(0,222,222);
	}
	
	@Override
	public Border getActiveBorder(int top, int left, int bottom, int right) {
		return BorderFactory.createMatteBorder(top, left, bottom, right, getActiveBackground());
	}
}
