import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class App implements Runnable {
	public static final String TITLE = "Image Viewer";
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	@Override
	public void run() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JFrame f = new JFrame(TITLE);
		MainPanel p = new MainPanel();
		JMenuBar m = createMenu(p.getLoadAction());

		p.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		f.setContentPane(p);
		f.setJMenuBar(m);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	private JMenuBar createMenu(ActionListener loadAction) {
		JMenuBar menuBar;
		JMenu fileMenu;
		JMenuItem loadMenu;

		menuBar = new JMenuBar();

		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		fileMenu.getAccessibleContext().setAccessibleDescription("File menu");
		menuBar.add(fileMenu);

		loadMenu = new JMenuItem("Load directory", KeyEvent.VK_L);
		loadMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		loadMenu.getAccessibleContext().setAccessibleDescription("Load files");
		loadMenu.addActionListener(loadAction);
		fileMenu.add(loadMenu);

		return menuBar;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new App());
	}
}
