package dijkstra;

public interface PiInterface {
	/**
	 * Gets the distance to the origin of the given vertex
	 * @param vertex The given vertex you want the distance of
 	 * @return The distance to the origin of the given vertex
	 */
	int get(VertexInterface vertex);

	/**
	 * Sets the distance of a given vertex to the origin to the given value
	 * @param vertex The given vertex to set the distance to the origin to
	 * @param value The given value to set the distance to
	 */
	void put(VertexInterface vertex, int value);

	/**
	 * Returns whereas the vertex is contained in the Pi or not
	 * @param vertex the vertex to look for
	 * @return true if the vertex is in the Pi, false otherwise
	 */
	boolean contains(VertexInterface vertex);
}
