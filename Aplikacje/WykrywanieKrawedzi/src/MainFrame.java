import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame implements ActionListener {
	private JButton wgraj;
	private JComboBox filtr;
	private JButton zapisz;
	private JButton przetwarzaj;
	private JFileChooser fileChooser;
	private File plik = null;
	private BufferedImage image = null;
	private int imageWidth = 0;
	private int imageHeight = 0;
	private DrawImage drawImage;

	public MainFrame() {
		super("CPOO - wykrywanie krawędzi");

		setLayout(null);
		// setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 650);
		setLocation(400, 100);
		setVisible(true);

		wgraj = new JButton("Wgraj");
		wgraj.setBounds(getWidth() * 1 / 5 - 50, 5, 100, 20);
		wgraj.addActionListener(this);
		add(wgraj);

		String[] filtry = { "Filtr", "Sobel", "Canny" };
		filtr = new JComboBox(filtry);
		filtr.setBounds(getWidth() * 2 / 5 - 75, 5, 150, 20);
		add(filtr);

		przetwarzaj = new JButton("przetwarzaj");
		przetwarzaj.setBounds(getWidth() * 3 / 5 - 75, 5, 150, 20);
		przetwarzaj.addActionListener(this);
		add(przetwarzaj);

		zapisz = new JButton("zapisz");
		zapisz.setBounds(getWidth() * 4 / 5 - 50, 5, 100, 20);
		zapisz.addActionListener(this);
		add(zapisz);

		drawImage = new DrawImage();
		drawImage.setBounds(0, 30, getWidth(), getHeight() - 30);
		add(drawImage);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == wgraj) {
			openImage();
			drawImage.repaint();
		} else if (e.getSource() == zapisz && plik != null) {
			saveImage();
		} else if (e.getSource() == przetwarzaj) {
			if (filtr.getSelectedItem() == "Filtr") {
				Filtr filtrImage = new Filtr(image);
				filtrImage.filtr();
				image = filtrImage.im;
			} else if (filtr.getSelectedItem() == "Sobel") {
				Sobel sobel = new Sobel();
				image = sobel.process(image);
			} else if (filtr.getSelectedItem() == "Canny") {
				Canny canny = new Canny(0.5f, 1f);
				image = canny.process(image);
			}
			drawImage.repaint();
		}
	}

	private void openImage() {
		fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG, BMP & GIF Images", "jpg", "gif", "bmp");
		fileChooser.setFileFilter(filter);
		int returnValue = fileChooser.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				plik = fileChooser.getSelectedFile();
				image = ImageIO.read(plik);
				imageWidth = image.getWidth();
				imageHeight = image.getHeight();
			} catch (IOException ioe) {
				ioe.getCause();
				System.out.println("error IOStream in openImage");
			}
		}
	}

	private void saveImage() {
		int returnValue = fileChooser.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				plik = fileChooser.getSelectedFile();
				ImageIO.write(image, "JPG", new File(plik.getAbsolutePath()));
			} catch (IOException ioe) {
				ioe.getMessage();
				System.out.println("error IOStream in saveImage");
			}
		}
	}

	public class DrawImage extends JComponent {
		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.drawImage(image, null, (getWidth() - imageWidth) / 2,
					(getHeight() - imageHeight) / 2);
		}
	}
}