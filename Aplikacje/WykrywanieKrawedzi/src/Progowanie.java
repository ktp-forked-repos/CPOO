import java.awt.image.BufferedImage;

//Klasa progujca obraz wynikowy
public class Progowanie {

	public BufferedImage imgP;

	public Progowanie(BufferedImage img, int prog) {

		int height = img.getHeight();
		int width = img.getWidth();
		imgP = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		int red = 0;
		int green = 0;
		int blue = 0;

		for (int x = 0; x < width; x++) {
			try {

				for (int y = 0; y < height; y++) {
					int color = img.getRGB(x, y);

					red = this.getRed(color);
					green = this.getGreen(color);
					blue = this.getBlue(color);

					// Etap progowania
					if ((red + green + green) / 3 < (int) (prog)) {
						imgP.setRGB(x, y, this.mixColor(0, 0, 0));
					} else {
						imgP.setRGB(x, y, this.mixColor(255, 255, 255));
					}

				}
			} catch (Exception e) {
				e.getMessage();
			}
		}

	}

	// Metoda zwracajaca obraz po progowaniu
	public BufferedImage getImgP() {
		return imgP;
	}

	private int mixColor(int red, int green, int blue) {
		return red << 16 | green << 8 | blue;
	}

	public int getRed(int color) {
		return (color & 0x00ff0000) >> 16;
	}

	public int getGreen(int color) {
		return (color & 0x0000ff00) >> 8;
	}

	public int getBlue(int color) {
		return (color & 0x000000ff) >> 0;

	}

}
