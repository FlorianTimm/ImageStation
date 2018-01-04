package de.florian_timm.de.imageStation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageViewer extends JPanel {
	private static final long serialVersionUID = 1L;

	double kamHoehe = 1.0; // 3
	double kamWinkel = 3 / 180. * Math.PI;  //7.62
	double brennweite = 25; // 38
	double sensorhoehe = 24; // 24
	int pixelhoehe = 800;
	int horizontVersatz = 45;
	//String pfad = "G:\\AG_Verkehrsdaten\\DV-System\\Eigenentwicklungen\\ImageStation\\HVStr2014_PK_132.jpg";
	String pfad = "D:\\TestRadBefahrung\\Befahrung\\20171010_082251.JPG";
	
	int pixelbreite = 0; //wird errechnet
	int horizont = (int) (pixelhoehe / 2 - Math.tan(kamWinkel) * brennweite * pixelhoehe / sensorhoehe);

	public ImageViewer(ImageStationGUI gui) {
		super();
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(new Color(0x000000));
		g2d.fillRect(10, 10, 4, 4);
		g2d.drawLine(0, 0, 150, 150);

		try {
			FileInputStream fis = new FileInputStream(pfad);
			BufferedImage bild = ImageIO.read(fis);
			pixelbreite = (int) (800. / bild.getHeight() * bild.getWidth());
			g2d.drawImage(bild, 0, 0, pixelbreite, 800, this);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		g2d.setColor(new Color(0x000000));
		g2d.fillRect(pixelbreite / 2, 230, 4, 4);

		int halb = pixelbreite / 2 + horizontVersatz;

		int distP = calcQuerPixel(1);
		for (int i = -10; i <= 10; i++) {
			g2d.drawLine(i * distP + halb, pixelhoehe - 1, halb, horizont);
			g2d.drawString(i + " m", i * distP + halb - 7, pixelhoehe + 15);
		}

		for (int i = 0; i < 10; i++) {
			int reihe = calcHoriPixel(i);
			g2d.drawLine(0, reihe, pixelbreite, reihe);
			if (i < 20) {
				g2d.drawString(i + " m", 5, reihe - 1);
			}
		}

	}

	protected int calcHoriPixel(double horiDist) {
		double winkel = Math.atan(kamHoehe / horiDist);
		winkel -= kamWinkel;

		int pixel = (int) (Math.tan(winkel) * pixelhoehe * brennweite / sensorhoehe + pixelhoehe / 2);

		return pixel;
	}

	protected int calcQuerPixel(double querDist) {

		double maxWinkel = Math.atan(sensorhoehe / 2. / brennweite);
		maxWinkel += kamWinkel;
		double entf = kamHoehe / Math.sin(maxWinkel);
		int erg = (int) (querDist / entf * brennweite / sensorhoehe * pixelhoehe);

		return erg;
	}

}
