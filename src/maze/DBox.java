package maze;

import settings.Labels;

/**
 * Departure Box in Maze
 */
public class DBox extends MBox {
	/**
	 * Creates new Departure Box at coordinates (x,y)
	 * @param x The x coordinate of the DBox
	 * @param y The y coordinate of the DBox
	 */
	public DBox(int x, int y) {
		super(x, y, Labels.DEPARTURE);
	}
}
