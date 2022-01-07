package ui;

import javax.swing.Box;

import maze.Maze;

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
			break;
		case 2:
			break;
		case 3:
			setBackground(Color.white);
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
