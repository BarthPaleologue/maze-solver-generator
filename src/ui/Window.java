package ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;

import org.w3c.dom.events.MouseEvent;

import maze.Maze;

public class Window extends JFrame {
	JPanel mazePanel;
	CBox[][] UIGrid;
	Maze maze;
	
	public Window(String title) {
		super(title);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.add(mainPanel);
		
		mazePanel = new JPanel();
		mainPanel.add(mazePanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		mainPanel.add(buttonPanel);
		
		JButton b1 = new JButton("Compute");//creating instance of JButton 
		b1.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		             // this makes sure the button you are pressing is the button variable
		             if(e.getSource() == b1) {
		            	 display(maze.solve());
		             }
		       }
		 });
		buttonPanel.add(b1);
		
		JButton b2 = new JButton("wesh");//creating instance of JButton  		
		buttonPanel.add(b2);
		
		JButton b3 = new JButton("alors");//creating instance of JButton  		
		buttonPanel.add(b3);
		
		JButton b4 = new JButton("comment");//creating instance of JButton  		
		buttonPanel.add(b4);
		
		JButton b5 = new JButton("va");//creating instance of JButton  		
		buttonPanel.add(b5);
	}
	
	public void load(Maze maze) {
		this.maze = maze;
		
		int width = maze.getWidth();
		int height = maze.getHeight();
		
		mazePanel.setLayout(new GridLayout(width, height));
		
		UIGrid = new CBox[maze.getWidth()][maze.getHeight()];
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				CBox box = new CBox(BoxLayout.X_AXIS, this.maze, i, j);
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
				mazePanel.add(box);
				
				UIGrid[i][j] = box;
				
			}
		}
		
		this.setSize(600, 650);
		this.setVisible(true);
	}
	
	public void setCellColor(int x, int y, Color color) {
		UIGrid[x][y].setBackground(color);
	}
	
	public void display(ArrayList<int[]> path) {
		// on affiche le chemin à l'envers (on a trouvé le plus court de fin vers déb
		for(int i = path.size() - 1; i >= 0; i--) {
			int[] pathStone = path.get(i);
			//System.out.println("["+pathStone[0]+";"+pathStone[1]+"]");
			
			// on allume la case du chemin
			this.setCellColor(pathStone[0], pathStone[1], Color.GREEN);
			
			// petit délai pour l'effet de progression
			/*try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}
}
