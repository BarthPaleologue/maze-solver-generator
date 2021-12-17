import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import dijkstra.Dijkstra;
import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeReadingException;

public class Main {
	public static void main(String[] args) {
		
		JFrame w = new JFrame("PROJEEEET");
		w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		w.add(mainPanel);
		
		
		Maze maze = new Maze();
		try {
			// initialisation du labyrinthe à partir d'un fichier
			maze.initFromTextFile("data/labyrinthe.txt");
			
			mainPanel.setLayout(new GridLayout(maze.getWidth(), maze.getHeight()));
			
			Box[][] UIGrid = new Box[maze.getWidth()][maze.getHeight()];
			
			for(int i = 0; i < maze.getWidth(); i++) {
				for(int j = 0; j < maze.getHeight(); j++) {
					Box box = new Box(BoxLayout.X_AXIS);
					box.setBorder(BorderFactory.createLineBorder(Color.black));
					box.setBounds(i, j, 1, 1);
					box.setOpaque(true);

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
					
					box.setBackground(color);
					mainPanel.add(box);
					
					UIGrid[i][j] = box;
					
				}
			}
			
			w.setSize(600, 600);
			w.setVisible(true);
			
			int[][][] previous = Dijkstra.compute(maze);
			
			VertexInterface startPoint = maze.getStartPoint();

			VertexInterface endPoint = maze.getEndPoint();

			int[] coords = {endPoint.getX(), endPoint.getY()};
			
			ArrayList<int[]> path = new ArrayList<>();
			
			// détermination du plus court chemin trouvé
			while(true) {
				int[] newCoords = {previous[coords[0]][coords[1]][0],previous[coords[0]][coords[1]][1]};
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
			
			// on affiche le chemin à l'envers (on a trouvé le plus court de fin vers déb
			for(int i = path.size() - 1; i >= 0; i--) {
				int[] pathStone = path.get(i);
				//System.out.println("["+pathStone[0]+";"+pathStone[1]+"]");
				
				// on allume la case du chemin
				UIGrid[pathStone[0]][pathStone[1]].setBackground(Color.GREEN);
				
				// petit délai pour l'effet de progression
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
