package de.florian_timm.de.imageStation;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

public class ImageStationGUI extends JFrame {

	public ImageStationGUI() {
		super("ImageStation");
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);

		Container cp = this.getContentPane();
		
		ImageViewer imageViewer = new ImageViewer(this);
		
		cp.add(imageViewer, BorderLayout.CENTER);
		
		
		this.setPreferredSize(new Dimension(400, 500));
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		
		
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ImageStationGUI();

	}

}
