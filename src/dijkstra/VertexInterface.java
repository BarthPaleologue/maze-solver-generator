package dijkstra;

public interface VertexInterface {
	/**
	 * Returns the label of the vertex
	 * @return The label of the vertex
	 */
	char getLabel();

	/**
	 * Returns the x coordinate of the vertex in the graph
	 * @return
	 */
	int getX();

	/**
	 * Returns the y coordinate of the vertex in the graph
	 * @return
	 */
	int getY();
}
