package maze;

import dijkstra.VertexInterface;
import maze.exceptions.BoxLabelException;

/**
 * Abstract Maze Box
 */
public abstract class MBox implements VertexInterface {
	private final int _x;
	private final int _y;
	
	private final char _label;
	
	public MBox(int x, int y, char label) {
		_x = x;
		_y = y;

		_label = label;
	}

	/**
	 * Returns the x coordinate of the box
	 * @return the x coordinate of the box
	 */
	public int getX() {
		return _x;
	}

	/**
	 * Returns the y coordinate of the box
	 * @return the y coordinate of the box
	 */
	public int getY() {
		return _y;
	}

	/**
	 * Returns the label attached to the box
	 * @return the label attached to the box
	 */
	public char getLabel() {
		return _label;
	}

	/**
	 * Creates the right type of box given a label
	 * @param label the given label
	 * @param x the x coordinate of the box
	 * @param y the y coordinate of the box
	 * @return The new box created from the label
	 * @throws BoxLabelException if the label is unknown => check maze.Labels to check available labels
	 */
	public static VertexInterface CreateFromLabel(char label, int x, int y) throws BoxLabelException {
		switch(label) {
			case Labels.ARRIVAL:
				return new ABox(x, y);
			case Labels.WALL:
				return new WBox(x, y);
			case Labels.DEPARTURE:
				return new DBox(x, y);
			case Labels.EMPTY:
				return new EBox(x, y);
			default:
				throw new BoxLabelException(label, x, y);
		}
	}
	
	@Override
	public String toString() {
		return "[" + _x + ";" + _y + ";" + _label + "]";
	}
}
