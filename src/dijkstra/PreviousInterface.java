package dijkstra;
	
public interface PreviousInterface {
	/**
	 * Gets the father of the given vertex
	 * @param vertex The given vertex to get the previous vertex of
	 * @return The father vertex of the given vertex
	 */
	VertexInterface get(VertexInterface vertex);

	/**
	 * Sets the previousVertex as the father of the vertex
	 * @param vertex The vertex to set the father
	 * @param previousVertex The vertex that will become the father of the given vertex
	 */
	void set(VertexInterface vertex, VertexInterface previousVertex);
}
