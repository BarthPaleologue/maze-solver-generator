package maze;

import settings.Labels;

/**
 * Empty Box in Maze
 */
public class EBox extends MBox {
	/**
	 * Creates new Empty Box at coordinates (x,y)
	 * @param x The x coordinate of the EBox
	 * @param y The y coordinate of the EBox
	 */
	public EBox(int x, int y) {
		super(x, y, Labels.EMPTY);
	}
}
