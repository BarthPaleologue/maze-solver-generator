package maze;
import java.util.ArrayList;

import dijkstra.VertexInterface;

public interface GraphInterface {
	// TODO : ajouter du matos
	ArrayList<VertexInterface> getNeighbors(VertexInterface vertex);
}
