package ui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dijkstra.VertexInterface;
import maze.Labels;
import maze.Maze;
import maze.MazeInterface;

public class Window extends JFrame implements ChangeListener {
	private final ControlPanel mazeControlPanel;
	private final MazeVuePanel mazeVuePanel;
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

		setupMenuBar();
		maze = setupMaze();
		this.mazeControlPanel = setupControlPanel();
		this.mazeVuePanel = setupMazeVuePanel();

		this.mazeControlPanel.add(mazeVuePanel, BorderLayout.CENTER);

		this.pack();

		this.setVisible(true);
	}

	private JMenuBar setupMenuBar() {
		JMenuBar menuBar = new Menu(this);
		this.add(menuBar);
		this.setJMenuBar(menuBar);

		return menuBar;
	}

	private MazeInterface setupMaze() {
		int width = promptIntFromUser("New Maze","Enter new maze width", Maze.DEFAULT_WIDTH);
		width = Math.max(1, width);

		int height = promptIntFromUser("New Maze", "Enter new maze height", Maze.DEFAULT_HEIGHT);
		height = Math.max(1, height);

		MazeInterface maze = new Maze(width, height);
		maze.addListener(this);

		mazeWidth = maze.getWidth();
		mazeHeight = maze.getHeight();

		return maze;
	}

	private ControlPanel setupControlPanel() {
		ControlPanel mazeControlPanel = new ControlPanel(this);
		mazeControlPanel.setLayout(new BorderLayout());
		this.add(mazeControlPanel);

		return mazeControlPanel;
	}

	private MazeVuePanel setupMazeVuePanel() {
		MazeVuePanel mazeVuePanel = new MazeVuePanel(maze.getWidth(), maze.getHeight(), this);
		mazeVuePanel.initMazeUI(maze.getWidth(), maze.getHeight());
		return mazeVuePanel;
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
			mazeVuePanel.setCellColor(path.get(i).getX(), path.get(i).getY(), Color.GREEN);
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
					mazeWidth = maze.getWidth();
					mazeHeight = maze.getHeight();
					mazeVuePanel.initMazeUI(mazeWidth, mazeHeight);
					this.pack();
				}
			}
			mazeVuePanel.updateGridColors();
			displayPath(maze.getShortestPath());
		}
	}

	public MazeInterface getMaze() {
		return maze;
	}

	public Color getColorFromCoords(int x, int y) {
		return Labels.getColorFromLabel(maze.getCell(x, y).getLabel());
	}

	public int promptIntFromUser(String promptTitle, String promptText, int defaultValue) {
		String resultString = (String) JOptionPane.showInputDialog(
				this,
				promptText,
				promptTitle,
				JOptionPane.PLAIN_MESSAGE,
				null,
				null,
				String.valueOf(defaultValue));
		return resultString != null ? Integer.parseInt(resultString) : defaultValue;
	}
}
