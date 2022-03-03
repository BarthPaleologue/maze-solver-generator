package maze;
import java.util.ArrayList;

import dijkstra.VertexInterface;

public interface GraphInterface {
	/**
	 * Returns the list of all known neighbors of a given vertex
	 * @param vertex The vertex you want to know the neighbors of
	 * @return The list of its neighbors
	 */
	ArrayList<VertexInterface> getNeighbors(VertexInterface vertex);

	/**
	 * Gets the departure point of the maze
	 * @return The departure point of the maze
	 */
	VertexInterface getStartPoint();
}
