package dijkstra;

import java.util.Iterator;

public interface ASetInterface {
	/**
	 * Adds the given vertex to the set
	 * @param vertex The vertex to add to the set
	 * @return true if the given vertex was not in the set, false otherwise
	 */
	boolean add(VertexInterface vertex);

	/**
	 * Checks if the given vertex is in the set
	 * @param vertex The given vertex to look for
	 * @return true if the given vertex is in the set, false otherwise
	 */
	boolean contains(VertexInterface vertex);

	/**
	 * Returns the number of vertices in the set
	 * @return The number of vertices in the set
	 */
	int size();

	/**
	 * Removes the given vertex from the set
	 * @param vertex The vertex to remove
	 */
	void remove(VertexInterface vertex);

	/**
	 * Returns an iterator over the set
	 * @return An iterator over the set
	 */
	Iterator<VertexInterface> getIterator();
}
