package com.esoorf.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class ColorPalette {
	public static Color primaryColor= new Color(130, 40, 120);
	public static Color secondaryColor= new Color(100, 100, 100);
	public static Color contentColor= new Color(50, 50, 50);
	
	public static Color bgColor= new Color(250, 250, 250);
	public static Border border= BorderFactory.createMatteBorder(1, 1, 1, 1, bgColor);
	public static Border softBorder= BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(222,222,222));
}
