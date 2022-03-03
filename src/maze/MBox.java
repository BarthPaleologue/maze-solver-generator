package maze;

import dijkstra.VertexInterface;
import maze.exceptions.BoxLabelException;
import settings.Labels;

/**
 * Abstract Maze Box
 */
public abstract class MBox implements VertexInterface {
	private final int x, y;

	private final char label;
	
	public MBox(int x, int y, char label) {
		this.x = x;
		this.y = y;

		this.label = label;
	}

	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public char getLabel() {
		return label;
	}

	/**
	 * Creates the right type of box given a label
	 * @param label the given label
	 * @param x the x coordinate of the box
	 * @param y the y coordinate of the box
	 * @return The new box created from the label
	 * @throws BoxLabelException if the label is unknown => check settings.Labels to check available labels
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
		return "[" + x + ";" + y + ";" + label + "]";
	}
}
