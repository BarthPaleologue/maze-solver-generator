package dijkstra;

import java.util.HashSet;
import java.util.Iterator;

public class ASet extends HashSet<VertexInterface> implements ASetInterface {
	public boolean contains(VertexInterface vertex) {
		return super.contains(vertex);
	}
	public void remove(VertexInterface vertex) {
		super.remove(vertex);
	}
	public Iterator<VertexInterface> getIterator() {
		return super.iterator();
	}
}
