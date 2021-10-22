package maze;
import java.util.ArrayList;

import dijkstra.VertexInterface;

public interface MazeInterface {
	
	public ArrayList<VertexInterface> getNeighbors(VertexInterface vertex);
	
	public ArrayList<VertexInterface> getAllVertices();
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);
	public int getWeight(VertexInterface src, VertexInterface dst);
}
