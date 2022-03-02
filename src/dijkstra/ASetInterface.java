package dijkstra;

import java.util.Iterator;

public interface ASetInterface {
	boolean add(VertexInterface vertex);
	boolean contains(VertexInterface vertex);
	int length();
	void remove(VertexInterface vertex);
	Iterator<VertexInterface> getIterator();
}
