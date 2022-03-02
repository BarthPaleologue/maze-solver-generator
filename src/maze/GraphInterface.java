package maze;
import java.util.ArrayList;

import dijkstra.VertexInterface;

public interface GraphInterface {
	ArrayList<VertexInterface> getNeighbors(VertexInterface vertex);
	VertexInterface getStartPoint();
}
