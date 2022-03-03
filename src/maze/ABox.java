package maze;

import settings.Labels;

/**
 * Arrival Box in Maze
 */
public class ABox extends MBox {
	public ABox(int x, int y) {
		super(x, y, Labels.ARRIVAL);
	}
}
