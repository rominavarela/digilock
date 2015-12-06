package com.esoorf;

import javax.swing.SwingUtilities;

import com.esoorf.view.impl.ViewImpl;

public class Main {
	static public void main(String argv[]) 
	{
		try{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					ViewImpl.getInstance();
				}
			});
		}catch(Exception ex){}
	 }

}
