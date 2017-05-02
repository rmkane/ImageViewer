package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.ImageInfo;
import util.FileUtils;

public class MainPanel extends JPanel {
	private static final long serialVersionUID = 3297439851964876410L;

	private JFileChooser chooser;
	private File currDir;
	private List<ImageInfo> paths = new ArrayList<ImageInfo>();
	private int imageIndex = 0;

	private JTextField pathTxt;
	private JLabel nameLabel;
	private ImagePanel imagePanel;
	private JButton prevButton;
	private JButton nextButton;
	private JLabel countLabel;

	public MainPanel() {
		super(new BorderLayout());
		
		Font font = new Font("Arial", Font.BOLD, 24);
		
		JPanel infoPanel = new JPanel(new BorderLayout());
		add(infoPanel, BorderLayout.NORTH);
		
		JLabel pathLbl = new JLabel("Path: ");
		infoPanel.add(pathLbl, BorderLayout.WEST);
		
		pathTxt = new JTextField();
		infoPanel.add(pathTxt, BorderLayout.CENTER);
		
		nameLabel = new JLabel("{{NAME}}", SwingConstants.CENTER);
		nameLabel.setFont(font);
		infoPanel.add(nameLabel, BorderLayout.SOUTH);
		
		imagePanel = new ImagePanel();
		imagePanel.setAlignmentX(SwingConstants.CENTER);
		add(imagePanel, BorderLayout.CENTER);

		JPanel buttonContainer = new JPanel(new FlowLayout());
		add(buttonContainer, BorderLayout.SOUTH);

		prevButton = new JButton("<< Prev");
		prevButton.addActionListener(prevAction);
		buttonContainer.add(prevButton);

		countLabel = new JLabel("0 of 0");
		buttonContainer.add(countLabel);
		
		nextButton = new JButton("Next >>");
		nextButton.addActionListener(nextAction);
		buttonContainer.add(nextButton);
	}

	private void loadImage() {
		nameLabel.setText(paths.get(imageIndex).getName());
		imagePanel.setImage(paths.get(imageIndex).getLocation());
		countLabel.setText(String.format("%d of %d", imageIndex + 1, paths.size()));
	}

	private void navigate(int direction) {
		if (!paths.isEmpty() && paths.size() > 1) {
			imageIndex = (imageIndex + direction + paths.size()) % paths.size();
			loadImage();
		}
	}

	private ActionListener prevAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			navigate(-1);
		}
	};

	private ActionListener nextAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			navigate(+1);
		}
	};

	private ActionListener loadAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if (chooser == null) {
				chooser = new JFileChooser();
				chooser.setDialogTitle("Select a directory");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setAcceptAllFileFilterUsed(false);
			}

			chooser.setCurrentDirectory(currDir != null ? currDir : new java.io.File("."));

			if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				currDir = chooser.getSelectedFile();

				pathTxt.setText(currDir.getAbsolutePath());
				
				paths.clear();
				for (File file : FileUtils.retrieveImagesFromDir(currDir)) {
					try {
						paths.add(new ImageInfo(file));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				imageIndex = 0;
				loadImage();
			}
		}
	};

	public ActionListener getLoadAction() {
		return loadAction;
	}
}
