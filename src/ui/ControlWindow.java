package ui;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeInterface;
import settings.Colors;
import sound.MakeSound;
import settings.SoundTypes;
import ui.menuBar.ControlMenuBar;
import ui.vue.MazeVuePanel;

public class ControlWindow extends JFrame implements ChangeListener {
	private final MazeVuePanel mazeVuePanel;
	private final MazeInterface maze;
	public static final int DEFAULT_WIDTH = 600;
	public static final int DEFAULT_HEIGHT = 670;

	private int gridWidth = 0;
	private int gridHeight = 0;

	private int editionState = EditionState.WALL;
	
	public ControlWindow() {
		super("MAZE SOLVER");
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

		setupMenuBar();

		ControlPanel mazeControlPanel = new ControlPanel(this);
		this.add(mazeControlPanel);

		this.mazeVuePanel = new MazeVuePanel(this);

		mazeControlPanel.add(mazeVuePanel, BorderLayout.CENTER);

		Colors.setDefaultColorScheme();

		maze = new Maze();
		maze.addListener(this);
		initRandomPrimMaze();

		this.pack();
		this.setVisible(true);
	}

	/**
	 * Sets up the menu bar of the window and returns it
	 */
	private void setupMenuBar() {
		JMenuBar menuBar = new ControlMenuBar(this);
		this.add(menuBar);
		this.setJMenuBar(menuBar);
	}

	/**
	 * The window will ask the new dimensions to the user and will create the panels appropriately
	 */
	private void initGridFromUserInput() {
		getMazeWidthHeightFromUser();
		Colors.setDefaultColorScheme();
		mazeVuePanel.initMazeUI(gridWidth, gridHeight);
		this.pack();
	}

	/**
	 * Loads the maze from a given file
	 * @param filePath the relative path to the maze file
	 */
	public void loadMaze(String filePath) {
		if(filePath.contains("ussr")) {
			// Nothing to see here, or is it ?
			MakeSound.reset();
			MakeSound.play(SoundTypes.USSR_ANTHEM);
			Colors.setUSSRColorScheme();
		} else if(filePath.contains("ukraine")) {
			// You are still reading, aren't you ?
			MakeSound.reset();
			MakeSound.play(SoundTypes.UKRAINIAN_ANTHEM);
			Colors.setUkraineColorScheme();
		} else {
			MakeSound.killAllPlaying();
			Colors.setDefaultColorScheme();
		}
		maze.initFromTextFile(filePath);
	}

	/**
	 * The window will ask the user for new dimensions and then display an empty maze of said dimensions
	 */
	public void initEmptyMaze() {
		initGridFromUserInput();
		MakeSound.killAllPlaying();
		maze.initEmpty(gridWidth, gridHeight);
	}

	/**
	 * The window will ask the user for new dimensions and then display a randomly generated maze of said dimensions using Prim algorithm
	 */
	public void initRandomPrimMaze() {
		initGridFromUserInput();
		MakeSound.killAllPlaying();
		maze.initRandomPrim(gridWidth, gridHeight);
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
			mazeVuePanel.setCellColor(path.get(i).getX(), path.get(i).getY(), Colors.PATH_COLOR);
		}
	}

	/**
	 * Sets the maze edition state to a new state
	 * @param vueState The new maze edition state
	 */
	public void setEditionState(int vueState) {
		this.editionState = vueState;
	}

	/**
	 * Returns current edition state
	 * @return the current maze edition state
	 */
	public int getEditionState() {
		return editionState;
	}

	/**
	 * Updates vue state to match model state
	 */
	public void updateVue() {
		mazeVuePanel.updateGridColors();
		displayPath(maze.getShortestPath());
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if(e.getSource() == maze) {
			if(gridWidth != maze.getWidth() || gridHeight != maze.getHeight()) {
				gridWidth = maze.getWidth();
				gridHeight = maze.getHeight();
				mazeVuePanel.initMazeUI(gridWidth, gridHeight);
				this.pack();
			}
			updateVue();
		}
	}

	public MazeInterface getMaze() {
		return maze;
	}

	public Color getColorFromCoords(int x, int y) {
		return Colors.getColorFromLabel(maze.getCell(x, y).getLabel());
	}

	private int promptIntFromUser(String promptTitle, String promptText, int defaultValue) {
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

	private void getMazeWidthHeightFromUser() {
		gridWidth = promptIntFromUser("New Maze","Enter new maze width :", Maze.DEFAULT_WIDTH);
		while (gridWidth <= 0) gridWidth = promptIntFromUser("New Maze","WIDTH MUST BE POSITIVE ! Try again : ", Maze.DEFAULT_WIDTH);

		gridHeight = promptIntFromUser("New Maze","Enter new maze height : ", Maze.DEFAULT_HEIGHT);
		while (gridHeight <= 0) gridHeight = promptIntFromUser("New Maze","HEIGHT MUST BE POSITIVE ! Try again : ", Maze.DEFAULT_WIDTH);
	}

}
