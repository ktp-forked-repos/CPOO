import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class Filtr {
	public BufferedImage im = null;
	private WritableRaster raster = null;
	private int width = 0;
	private int height = 0;
	private int pixels[] = new int[3];
	private int calosc[];
	public int min = 0;
	public int max = 0;

	public Filtr(BufferedImage img) {
		im = img;
		raster = im.getRaster();
		width = raster.getWidth();
		height = raster.getHeight();
		calosc = new int[width * height];
	}

	// Metoda filtrujaca obraz
	public void filtr() {
		/*
		 * Poszczegolne wagi filtru
		 * 
		 * |-1 -1 -1 | |-1 8 -1 | |-1 -1 -1 |
		 */

		int w1[] = new int[3];
		int w2[] = new int[3];
		int w3[] = new int[3];
		int w4[] = new int[3];
		int w5[] = new int[3];
		int w6[] = new int[3];
		int w7[] = new int[3];
		int w8[] = new int[3];
		int w9[] = new int[3];

		// Wartosci minimalna i maksymalna obrazu po filtracji
		int min = 0;
		int max = 0;

		for (int i = 1; i < width - 1; i++) {
			for (int j = 1; j < height - 1; j++) {
				raster.getPixel(i, j, w5);
				raster.getPixel(i + 1, j + 1, w1);
				raster.getPixel(i + 1, j, w2);
				raster.getPixel(i + 1, j - 1, w3);
				raster.getPixel(i, j + 1, w4);
				raster.getPixel(i, j - 1, w6);
				raster.getPixel(i - 1, j + 1, w7);
				raster.getPixel(i - 1, j, w8);
				raster.getPixel(i - 1, j - 1, w9);
				// Wynikowy piksel po filtracji
				calosc[(i - 1) * height + (j - 1)] = 8 * w5[0] - w1[0] - w2[0]
						- w3[0] - w4[0] - w6[0] - w7[0] - w8[0] - w9[0];
			}
		}

		// Obliczanie minimum
		min = calosc[0];
		for (int i = 0; i < calosc.length; i++) {
			if (calosc[i] < min)
				min = calosc[i];
		}
		// Obliczanie maksimum
		max = calosc[0];
		for (int i = 0; i < calosc.length; i++) {
			if (calosc[i] > max)
				max = calosc[i];
		}

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// Normalizowanie wartosci pikseli
				calosc[i * height + j] = (int) ((double) (calosc[i * height + j] - min)
						/ (max - min) * 256);
				pixels[0] = pixels[1] = pixels[2] = calosc[i * height + j];
				raster.setPixel(i, j, pixels);
			}

		}
		// Progowanie obrazu wynikowego
		Progowanie p = new Progowanie(im, 105);
		im = p.getImgP();
	}
}
