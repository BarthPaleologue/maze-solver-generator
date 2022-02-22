package maze;
import java.util.ArrayList;

import dijkstra.VertexInterface;
import maze.exceptions.MazeReadingException;

public interface GraphInterface {
	ArrayList<VertexInterface> getNeighbors(VertexInterface vertex);
	VertexInterface getStartPoint();
}
