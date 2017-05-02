package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageInfo {
	private String location;
	private String name;
	private int width;
	private int height;
	private long size;

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public long getSize() {
		return size;
	}

	public ImageInfo(File image) throws IOException {
		BufferedImage img = ImageIO.read(image);
		this.location = image.getAbsolutePath();
		this.name = image.getName();
		this.width = img.getWidth();
		this.height = img.getHeight();
		this.size = image.length();
	}

	@Override
	public String toString() {
		return String.format(
			"{ location: %s, name: %s, width: %s, height: %s, size: %s }",
			location, name, width, height, size);
	}
}