package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeReadingException;
import sound.MakeSound;

public class Window extends JFrame {
	private final JPanel mazePanel;
	private CBox[][] UIGrid;
	private Maze maze;
	public static int DEFAULT_WIDTH = 600;
	public static int DEFAULT_HEIGHT = 650;
	
	public Window(String title) {
		super(title);

		// just a little bit of fun
		MakeSound player = new MakeSound();
		//player.OH();
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.add(mainPanel);
		
		mazePanel = new JPanel();
		mainPanel.add(mazePanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		mainPanel.add(buttonPanel);
		
		JButton b1 = new JButton("Compute"); 
		b1.addActionListener(e -> {
			  reset();
			  display(maze.solve());
		});
		buttonPanel.add(b1);
		
		JButton b2 = new JButton("Reset");
		b2.addActionListener(e -> reset());
		buttonPanel.add(b2);
		
		JButton b3 = new JButton("Save");//creating instance of JButton  
		b3.addActionListener(e -> {
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
		buttonPanel.add(b3);
		
		JButton b4 = new JButton("Load");//creating instance of JButton  	
		b4.addActionListener(e -> {
		    JFileChooser chooser = new JFileChooser("./data");
			int returnVal = chooser.showOpenDialog(mainPanel);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String fileAbsPath = chooser.getSelectedFile().getAbsolutePath();
				String dirAbsPath = new File(".").getAbsolutePath();
				int len = dirAbsPath.length();
				String fileRelativePath = fileAbsPath.substring(len-1);
				System.out.println("You chose to open this file: " +
					fileAbsPath + " " + fileRelativePath);
				try {
					maze.initFromTextFile(fileRelativePath);
					initMazeUI(maze);
					reset();
				} catch (MazeReadingException e1) {
					e1.printStackTrace();
				}
			}
		});
		buttonPanel.add(b4);
	}
	
	public void initMazeUI(Maze maze) {
		this.maze = maze;
		
		int width = this.maze.getWidth();
		int height = this.maze.getHeight();

		mazePanel.removeAll(); // remove components of last maze (necessary when changing maze size)
		mazePanel.setLayout(new GridLayout(width, height));
		
		UIGrid = new CBox[maze.getWidth()][maze.getHeight()];
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				CBox box = new CBox(this.maze, i, j);
				box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				box.setBounds(i, j, 1, 1);

				Color color = CBox.getColorFromLabel(maze.getCell(i, j).getLabel());
				
				box.setBackground(color);
				mazePanel.add(box);
				
				UIGrid[i][j] = box;
				
			}
		}
		
		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		this.setVisible(true);
	}
	
	public void setCellColor(int x, int y, Color color) {
		UIGrid[x][y].setBackground(color);
	}
	
	public void display(ArrayList<VertexInterface> path) {
		// on affiche le chemin à l'envers (on a trouvé le plus court de fin vers déb
		for(int i = path.size() - 1; i >= 0; i--) {
			VertexInterface nextCell = path.get(i);
			// on allume la case du chemin
			this.setCellColor(nextCell.getX(), nextCell.getY(), Color.GREEN);
		}
	}
	
	public void reset() {
		for(int i = 0; i < maze.getWidth(); i++) {
			for(int j = 0; j < maze.getHeight(); j++) {
				Color originalColor = CBox.getColorFromLabel(maze.getCell(i, j).getLabel());
				UIGrid[i][j].setBackground(originalColor);
			}
		}
		repaint();
	}
}
