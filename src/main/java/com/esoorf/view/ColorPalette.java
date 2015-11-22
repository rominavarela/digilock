package com.esoorf.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class ColorPalette {
	public static Color primaryColor= new Color(130, 40, 120);
	public static Color secondaryColor= new Color(100, 100, 100);
	public static Color contentColor= new Color(50, 50, 50);
	public static Color hardSelectionColor= new Color(250,250,250);
	public static Color softSelectionColor= new Color(0,0,0);
	
	public static Color bgColor= new Color(250, 250, 250);
	public static Color hardSelectionBg= new Color(0,222,222);
	public static Color softSelectionBg= new Color(0,222,222);
	
	public static Border border= BorderFactory.createMatteBorder(1, 1, 1, 1, bgColor);
	public static Border grossBorder= BorderFactory.createMatteBorder(10, 0, 10, 0, bgColor);
	public static Border hardBorder= BorderFactory.createMatteBorder(3, 3, 3, 3, softSelectionBg);
	public static Border softBorder= BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(222,222,222));
	public static Border topSpace= BorderFactory.createMatteBorder(10, 0, 0, 0, bgColor);
	public static Border bottomSpace= BorderFactory.createMatteBorder(0, 0, 10, 0, bgColor);
	public static Border hardSelectionTopSpace= BorderFactory.createMatteBorder(10, 0, 0, 0, hardSelectionBg);
	public static Border hardSelectionBottomSpace= BorderFactory.createMatteBorder(0, 0, 10, 0, hardSelectionBg);
	public static Border softSelectionTopSpace= BorderFactory.createMatteBorder(10, 0, 0, 0, softSelectionBg);
	public static Border softSelectionBottomSpace= BorderFactory.createMatteBorder(0, 0, 10, 0, softSelectionBg);
}
