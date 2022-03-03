package dijkstra;

import java.util.Hashtable;

public class Previous extends Hashtable<VertexInterface, VertexInterface> implements PreviousInterface {
	public VertexInterface get(VertexInterface vertex) {
		return super.get(vertex);
	}
	public void set(VertexInterface vertex, VertexInterface previousVertex) {
		super.put(vertex, previousVertex);
	}
}
