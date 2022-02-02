package maze;

import dijkstra.VertexInterface;

public abstract class MBox implements VertexInterface {
	private final int _x;
	private final int _y;
	
	private final char _label;
	
	public MBox(int x, int y, char label) {
		_x = x;
		_y = y;

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
	
	@Override
	public String toString() {
		return "[" + _x + ";" + _y + ";" + _label + "]";
	}
}
