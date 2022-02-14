package dijkstra;

import java.util.Arrays;
import java.util.Hashtable;

public class Previous implements PreviousInterface {
	
	private final Hashtable<VertexInterface, VertexInterface> htab;
	
	Previous() {
		htab = new Hashtable<>();
	}

	public VertexInterface get(VertexInterface vertex) {
		return htab.get(vertex);
	}

	public void set(VertexInterface vertex, VertexInterface previousVertex) {
		htab.put(vertex, previousVertex);
	}
	
	@Override
	public String toString() {
		return htab.toString();
	}

}
