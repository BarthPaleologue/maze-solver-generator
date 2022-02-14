package ui;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import dijkstra.VertexInterface;
import maze.Maze;

public class Window extends JFrame {
	private final MazePanel mazePanel;
	private Maze maze;
	public static int DEFAULT_WIDTH = 600;
	public static int DEFAULT_HEIGHT = 660;
	
	public Window(String title) {
		super(title);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.add(mainPanel);

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
		mazePanel = new MazePanel(maze.getWidth(), maze.getHeight());
		mainPanel.add(mazePanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		mainPanel.add(buttonPanel);

		JButton emptyButton = new JButton("Empty");
		emptyButton.addActionListener(e -> {
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
		buttonPanel.add(emptyButton);

		JButton departureButton = new JButton("Set Departure");
		departureButton.addActionListener(e -> {
			mazePanel.setDepartureMode(true);
		});
		buttonPanel.add(departureButton);

		JButton arrivalButton = new JButton("Set Arrival");
		arrivalButton.addActionListener(e -> {
			mazePanel.setArrivalMode(true);
		});
		buttonPanel.add(arrivalButton);
		
		JButton computeButton = new JButton("Compute");
		computeButton.addActionListener(e -> {
			  reset();
			  displayPath(maze.solve());
		});
		//computeButton.setEnabled(false);
		buttonPanel.add(computeButton);
		
		JButton clearPathButton = new JButton("Clear Path");
		clearPathButton.addActionListener(e -> reset());
		buttonPanel.add(clearPathButton);
		
		JButton saveButton = new JButton("Save");//creating instance of JButton
		saveButton.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser("./data");
			int returnVal = chooser.showOpenDialog(mainPanel);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				 String fileAbsPath = chooser.getSelectedFile().getAbsolutePath();
				 String dirAbsPath = new File(".").getAbsolutePath();
				 int len = dirAbsPath.length();
				 String fileRelativePath = fileAbsPath.substring(len - 1);
				 System.out.println("You chose to save to this file: " + fileAbsPath + " " + fileRelativePath);

				 maze.saveToTextFile(fileRelativePath);
			}
		});
		buttonPanel.add(saveButton);
		
		JButton loadButton = new JButton("Load");//creating instance of JButton
		loadButton.addActionListener(e -> {
		    JFileChooser chooser = new JFileChooser("./data");
			int returnVal = chooser.showOpenDialog(mainPanel);
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
		buttonPanel.add(loadButton);
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
	
	public void reset() {
		mazePanel.reset();
	}
}
