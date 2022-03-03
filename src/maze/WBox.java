package maze;

import settings.Labels;

/**
 * Wall Box in Maze
 */
public class WBox extends MBox {
	public WBox(int x, int y) {
		super(x, y, Labels.WALL);
	}
}
