import java.awt.image.BufferedImage;

public class Sobel {
	private SobelEdge sobelEdge;

	public Sobel() {
		sobelEdge = new SobelEdge();
	}

	public BufferedImage process(BufferedImage imageBefore) {
		BufferedImage imageAfter = sobelEdge.sobelEdgeDetection(imageBefore);
		return imageAfter;
	}
}