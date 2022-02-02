package ui;

import javax.swing.*;

import maze.EBox;
import maze.Maze;
import maze.WBox;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;

public class CBox extends Box implements MouseListener {

	private final Maze maze;
	private final int x;
	private final int y;
	
	public CBox(Maze maze, int x, int y) {
		super(BoxLayout.X_AXIS);
		this.maze = maze;
		this.x = x;
		this.y = y;
		addMouseListener(this);
		this.setOpaque(true);
	}
	
	public static Color getColorFromLabel(char label) {
		Color color;
		switch(label) {
			case 'A':
				color = Color.RED;
				break;
			case 'D':
				color = Color.BLUE;
				break;
			case 'W':
				color = Color.DARK_GRAY;
				break;
			case 'E':
				color = Color.WHITE;
				break;
			default:
				color = Color.PINK;
		}
		return color;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//// 1 = LC ; 2 = MC ; 3 = RC
		switch(e.getButton()) {
		case 1:
			setBackground(Color.DARK_GRAY);
			maze.setCell(x, y, new WBox(maze, x, y));
			break;
		case 2:
			break;
		case 3:
			setBackground(Color.WHITE);
			maze.setCell(x, y, new EBox(maze, x, y));
			break;
		default:
			setBackground(Color.PINK);
		}
		repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}
}
