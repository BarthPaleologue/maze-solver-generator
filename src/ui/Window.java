package ui;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dijkstra.VertexInterface;
import maze.Maze;

public class Window extends JFrame implements ChangeListener {
	private final MazePanel mazePanel;
	private Maze maze;
	public static int DEFAULT_WIDTH = 600;
	public static int DEFAULT_HEIGHT = 670;
	
	public Window(String title) {
		super(title);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

		JMenuBar menuBar = new Menu(maze);
		this.add(menuBar);
		this.setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("Maze File");
		JButton departureButton = new JButton("Set departure");
		JButton arrivalButton = new JButton("Set Arrival");
		menuBar.add(fileMenu);
		menuBar.add(departureButton);
		menuBar.add(arrivalButton);

		JMenuItem newEmptyItem = new JMenuItem("New empty maze");
		JMenuItem openFileItem = new JMenuItem("Open maze file");
		JMenuItem saveFileItem = new JMenuItem("Save maze to file");
		fileMenu.add(newEmptyItem);
		fileMenu.add(openFileItem);
		fileMenu.add(saveFileItem);

		int width = Integer.parseInt((String)JOptionPane.showInputDialog(
				this,
				"Maze Width :",
				"Initialization Phase",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"10"));

		int height = Integer.parseInt((String) JOptionPane.showInputDialog(
				this,
				"Maze Height :",
				"Initialization Phase",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				"10"));

		maze = new Maze(width, height);
		maze.addListener(this);
		mazePanel = new MazePanel(maze.getWidth(), maze.getHeight());
		mazePanel.addChangeListener(this);
		this.add(mazePanel);

		newEmptyItem.addActionListener(e -> {
			int newWidth = Integer.parseInt((String)JOptionPane.showInputDialog(
					this,
					"Maze Width :",
					"Initialization Phase",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					"10"));

			int newHeight = Integer.parseInt((String) JOptionPane.showInputDialog(
					this,
					"Maze Height :",
					"Initialization Phase",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					"10"));
			maze.initEmpty(newWidth, newHeight);
			initMazeUI();
		});

		departureButton.addActionListener(e -> mazePanel.setDepartureMode(true));

		arrivalButton.addActionListener(e -> mazePanel.setArrivalMode(true));

		saveFileItem.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser("./data");
			int returnVal = chooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				 String fileAbsPath = chooser.getSelectedFile().getAbsolutePath();
				 String dirAbsPath = new File(".").getAbsolutePath();
				 int len = dirAbsPath.length();
				 String fileRelativePath = fileAbsPath.substring(len - 1);
				 System.out.println("You chose to save to this file: " + fileAbsPath + " " + fileRelativePath);

				 maze.saveToTextFile(fileRelativePath);
			}
		});

		openFileItem.addActionListener(e -> {
		    JFileChooser chooser = new JFileChooser("./data");
			int returnVal = chooser.showOpenDialog(this);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String fileAbsPath = chooser.getSelectedFile().getAbsolutePath();
				String dirAbsPath = new File(".").getAbsolutePath();
				int len = dirAbsPath.length();
				String fileRelativePath = fileAbsPath.substring(len-1);
				System.out.println("You chose to open this file: " +
					fileAbsPath + " " + fileRelativePath);

				maze.initFromTextFile(fileRelativePath);
				if(maze.getWidth() > 0 && maze.getHeight() > 0) {
					initMazeUI();
				}

			}
		});
		this.setVisible(true);

		initMazeUI();
	}
	
	public void initMazeUI() {
		mazePanel.initMazeUI(maze);
		this.pack();
	}
	
	public void displayPath(ArrayList<VertexInterface> path) {
		for(int i = 0; i < path.size(); i++) {
			mazePanel.setCellColor(path.get(i).getX(), path.get(i).getY(), Color.GREEN);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == mazePanel) displayPath(maze.solve());
	}
}
