package dijkstra;

import java.util.Arrays;
import java.util.Hashtable;

public class Previous implements PreviousInterface {
	
	private final int[][][] tab;
	private final Hashtable<Integer[], VertexInterface> htab;
	
	Previous(int width, int height) {
		tab = new int[width][height][2];
		htab = new Hashtable<Integer[], VertexInterface>();
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				tab[i][j][0] = -1;
				tab[i][j][1] = -1;
			}
		}
	}

	public int[] get(VertexInterface vertex) {
		return tab[vertex.getX()][vertex.getY()];
	}
	
	public int[] get(int x, int y) {
		return tab[x][y];
	}

	public void set(VertexInterface vertex, VertexInterface previousVertex) {
		tab[vertex.getX()][vertex.getY()][0] = previousVertex.getX();
		tab[vertex.getX()][vertex.getY()][1] = previousVertex.getY();
	}
	
	@Override
	public String toString() {
		return Arrays.deepToString(tab).replace("]],", "]],\n");
	}

}
