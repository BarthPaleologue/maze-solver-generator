package ui;

import javax.swing.Box;

import maze.EBox;
import maze.Maze;
import maze.WBox;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class CBox extends Box implements MouseListener {

	Maze maze;
	int x;
	int y;
	
	public CBox(int axis, Maze maze, int x, int y) {
		super(axis);
		this.maze = maze;
		this.x = x;
		this.y = y;
		addMouseListener(this);
		// TODO Auto-generated constructor stub
	}
	
	public static Color getColorFromLabel(char label) {
		Color color;
		switch(label) {
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
		return color;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		setBackground(Color.RED);
		switch(e.getButton()) {
		case 1:
			setBackground(Color.DARK_GRAY);
			maze.setCell(x, y, new WBox(maze, x, y));
			break;
		case 2:
			break;
		case 3:
			setBackground(Color.white);
			maze.setCell(x, y, new EBox(maze, x, y));
			break;
		default:
			setBackground(Color.pink);
		}
		//// 1 = LC ; 2 = MC ; 3 = RC
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
