package maze;

import settings.Labels;

/**
 * Arrival Box in Maze
 */
public class ABox extends MBox {
	/**
	 * Creates new Arrival Box at coordinates (x,y)
	 * @param x The x coordinate of the ABox
	 * @param y The y coordinate of the ABox
	 */
	public ABox(int x, int y) {
		super(x, y, Labels.ARRIVAL);
	}
}
