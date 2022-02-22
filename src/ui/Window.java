package ui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeInterface;

public class Window extends JFrame implements ChangeListener {
	private final MazePanel mazePanel;
	private final MazeInterface maze;
	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 670;

	private int mazeWidth = 0;
	private int mazeHeight = 0;

	private int editionState = EditionState.WALL;
	
	public Window() {
		super("MAZE SOLVER");
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

		JMenuBar menuBar = new Menu(this);
		this.add(menuBar);
		this.setJMenuBar(menuBar);

		String widthString = (String)JOptionPane.showInputDialog(
				this,
				"Maze Width :",
				"Initialization Phase",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				String.valueOf(Maze.DEFAULT_WIDTH));
		int width = widthString != null ? Integer.parseInt(widthString) : Maze.DEFAULT_WIDTH;
		width = Math.max(1, width);

		String heightString = (String) JOptionPane.showInputDialog(
				this,
				"Maze Height :",
				"Initialization Phase",
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				String.valueOf(Maze.DEFAULT_HEIGHT));
		int height = heightString != null ? Integer.parseInt(heightString) : Maze.DEFAULT_HEIGHT;
		height = Math.max(1, height);

		//TODO: j'ai inversé aled
		maze = new Maze(height, width);
		maze.addListener(this);

		mazePanel = new MazePanel(maze.getWidth(), maze.getHeight(), this);

		this.add(mazePanel);

		this.setVisible(true);

		initMazeUI();
	}

	/**
	 * Given a maze, initialize the vue grid and packs the window
	 */
	public void initMazeUI() {
		mazeWidth = maze.getWidth();
		mazeHeight = maze.getHeight();

		mazePanel.initMazeUI(maze);

		this.pack();
	}

	/**
	 * Loads the maze from a given file
	 * @param filePath the relative path to the maze file
	 */
	public void loadMaze(String filePath) {
		maze.initFromTextFile(filePath);
	}

	public void initEmptyMaze(int width, int height) {
		maze.initEmpty(width, height);
	}

	/**
	 * Saves the current maze state to the given file
	 * @param filePath the relative path to the file to save the maze to
	 */
	public void saveMaze(String filePath) {
		maze.saveToTextFile(filePath);
	}

	/**
	 * Given a set of vertex, highlights their position in the maze
	 * @param path the list of vertex to highlight in the vue
	 */
	public void displayPath(ArrayList<VertexInterface> path) {
		for(int i = 0; i < path.size(); i++) {
			mazePanel.setCellColor(path.get(i).getX(), path.get(i).getY(), Color.GREEN);
		}
	}

	public void setEditionState(int vueState) {
		this.editionState = vueState;
	}

	public int getEditionState() {
		return editionState;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == maze) {
			if(maze.getWidth() != mazeWidth || maze.getHeight() != mazeHeight) {
				// si le labyrinthe a changé de dimensions
				if(maze.getWidth() > 0 && maze.getHeight() > 0) {
					initMazeUI();
				}
			}
			mazePanel.updateGridColors();
			displayPath(maze.getShortestPath());
		}
	}
}
