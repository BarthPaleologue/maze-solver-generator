package maze;
import java.util.ArrayList;

import dijkstra.VertexInterface;

public interface GraphInterface {
	
	public ArrayList<VertexInterface> getNeighbors(VertexInterface vertex);
	

}
