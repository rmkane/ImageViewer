import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

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

	public static void main(String[] args) {
		File dir = new File("C:/Users/rmkane2/Pictures");
		
		for (final File i : retrieveImagesFromDir(dir)) {
			try {
				System.out.println(new ImageInfo(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}