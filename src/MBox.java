import java.util.ArrayList;

public abstract class MBox implements VertexInterface {
	private final int _x;
	private final int _y;
	
	private Maze _maze;
	
	private String _label;
	
	public MBox(Maze maze, int x, int y) {
		_x = x;
		_y = y;
		
		_maze = maze;
	}
	
	public int getX() {
		return _x;
	}
	
	public int getY() {
		return _y;
	}
	
	public String getLabel() {
		return _label;
	}
	
	public ArrayList<VertexInterface> getNeighbors() {
		return _maze.getNeighbors(this);
	}
}
