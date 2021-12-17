import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dijkstra.Dijkstra;
import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeReadingException;

public class Main {
	public static void main(String[] args) {
		
		JFrame w = new JFrame("PROJEEEET");
		JPanel mainPanel = new JPanel();
		
		int width = 10;
		int height = 10;
		mainPanel.setLayout(new GridLayout(width, height));
		
		Box[][] UIGrid = new Box[width][height];
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				
				Box testBox = new Box(BoxLayout.X_AXIS);
				testBox.setBorder(BorderFactory.createLineBorder(Color.black));
				testBox.setBounds(i*width, j*height, width, height);
				testBox.setBackground(Color.white);
				testBox.setOpaque(true);
				mainPanel.add(testBox);
				
				UIGrid[i][j] = testBox;
			}
		}
		
		w.add(mainPanel);
		
		w.setSize(600, 600);
		w.setVisible(true);
		
		
		Maze maze = new Maze();
		try {
			maze.initFromTextFile("data/labyrinthe.txt");
			
			for(int i = 0; i < maze.getWidth(); i++) {
				for(int j = 0; j < maze.getHeight(); j++) {
					Color color;
					switch(maze.getCell(i, j).getLabel()) {
						case 'A':
							color = Color.red;
							break;
						case 'D':
							color = Color.blue;
							break;
						case 'W':
							color = Color.DARK_GRAY;
							break;
						case 'E':
							color = Color.white;
							break;
						default:
							color = Color.pink;
					}
					UIGrid[i][j].setBackground(color);
					
				}
			}

			Dijkstra dijkstra = new Dijkstra();
			
			int[][][] previous = dijkstra.Dijkstra(maze);
			
			VertexInterface startPoint = maze.getStartPoint();

			VertexInterface endPoint = maze.getEndPoint();

			boolean arrived = false;
			int[] coords = {maze.getEndPoint().getX(), maze.getEndPoint().getY()};
			
			ArrayList<int[]> path = new ArrayList<>();
			
			while(true) {
				int[] newCoords = {previous[coords[1]][coords[0]][0],previous[coords[1]][coords[0]][1]};
				if(startPoint.getX() == newCoords[0] && startPoint.getY() == newCoords[1]) {
					break;
				}
				
				if(newCoords[0] == -1 && newCoords[1] == -1) {
					System.out.println("Could not find a path");
					break;
				}
				
				path.add(newCoords);
				
				coords[0] = newCoords[0];
				coords[1] = newCoords[1];
			}
			
			for(int i = path.size() - 1; i >= 0; i--) {
				int[] pathStone = path.get(i);
				System.out.println("["+pathStone[0]+";"+pathStone[1]+"]");
				UIGrid[pathStone[1]][pathStone[0]].setBackground(Color.GREEN);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		
		
		} catch(MazeReadingException e) {
			e.printStackTrace();
		}
	}
}
