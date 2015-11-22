package com.esoorf;

import javax.swing.SwingUtilities;

import com.esoorf.view.View;

public class Main {
	static public void main(String argv[]) 
	{
		try{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					View.getInstance();
				}
			});
		}catch(Exception ex){}
	 }

}
