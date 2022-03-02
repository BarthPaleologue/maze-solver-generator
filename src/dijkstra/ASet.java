package dijkstra;

import java.util.HashSet;
import java.util.Iterator;

public class ASet extends HashSet<VertexInterface> implements ASetInterface {

	/**
	 * Returns whereas the vertex is in the set or not
	 * @param vertex the vertex to search for
	 * @return true if the set contains the vertex and false otherwise
	 */
	@Override
	public boolean contains(VertexInterface vertex) {
		return super.contains(vertex);
	}

	/**
	 * Returns the number of vertices in the set
 	 * @return the number of vertices in the set
	 */
	@Override
	public int length() {
		return super.size();
	}

	/**
	 * Removes the given vertex from the set
	 * @param vertex the vertex to remove
	 */
	@Override
	public void remove(VertexInterface vertex) {
		super.remove(vertex);
	}

	/**
	 * Returns an iterator over the set
	 * @return an iterator over the set
	 */
	@Override
	public Iterator<VertexInterface> getIterator() {
		return super.iterator();
	}
}
