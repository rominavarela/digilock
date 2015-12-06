package com.esoorf.view;

import java.awt.Component;

import com.esoorf.view.palette.Palette;

public interface View {
	public Component getPanel();
	public Palette getPalette();
	public void setPalette(Palette p);
}
