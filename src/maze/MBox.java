package maze;
import java.util.ArrayList;

import dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface {
	private final int _x;
	private final int _y;
	
	private Maze _maze;
	
	private char _label;
	
	public MBox(Maze maze, int x, int y, char label) {
		_x = x;
		_y = y;
		
		_maze = maze;

		_label = label;
	}
	
	public int getX() {
		return _x;
	}
	
	public int getY() {
		return _y;
	}
	
	public char getLabel() {
		return _label;
	}
	
	public ArrayList<VertexInterface> getNeighbors() {
		return _maze.getNeighbors(this);
	}
	
	@Override
	public String toString() {
		return "[" + _x + ";" + _y + ";" + _label + "]";
	}
}
