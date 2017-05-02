package util;

import java.io.File;
import java.io.FilenameFilter;

public class FileUtils {
	static final String[] EXTENSIONS = new String[] { "gif", "png", "bmp" };
	static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
		@Override
		public boolean accept(final File dir, final String name) {
			for (final String ext : EXTENSIONS) {
				if (name.endsWith('.' + ext)) return true;
			}
			return false;
		}
	};
	
	public static File[] retrieveImagesFromDir(File dir) {
		return dir.isDirectory() ? dir.listFiles(IMAGE_FILTER) : new File[0];
	}
}