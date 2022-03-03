package maze;

import settings.Labels;

/**
 * Wall Box in Maze
 */
public class WBox extends MBox {
	/**
	 * Creates new Wall Box at coordinates (x,y)
	 * @param x The x coordinate of the WBox
	 * @param y The y coordinate of the WBox
	 */
	public WBox(int x, int y) {
		super(x, y, Labels.WALL);
	}
}
