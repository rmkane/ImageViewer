package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = -8126782477347188349L;

	private TexturePaint paint;
	private JLabel imageLabel;

	public ImagePanel() {
		super(new BorderLayout());
		paint = createTiledPaint();
		
		imageLabel = new JLabel("", SwingConstants.CENTER);
		this.add(imageLabel, BorderLayout.CENTER);
	}

	public void setImage(String path) {
		try {
			imageLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(path)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(paint);
		g2d.fillRect(0, 0, getWidth(), getHeight());
	}

	private TexturePaint createTiledPaint() {
		return createTiledPaint(8, Color.LIGHT_GRAY, Color.WHITE);
	}

	private TexturePaint createTiledPaint(int size, Color color1, Color color2) {
		int tiles = 2;
		int width = tiles * size;
		int height = tiles * size;

		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();

		g.setColor(color2);
		g.fillRect(0, 0, width, height);

		g.setColor(color1);
		for (int y = 0; y < tiles; y++) {
			for (int x = 0; x < tiles; x++) {
				if ((y + x) % 2 == 0) {
					g.fillRect(size * x, size * y, size, size);
				}
			}
		}

		return new TexturePaint(image, new Rectangle(width, height));
	}
}
