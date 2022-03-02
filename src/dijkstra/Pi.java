package dijkstra;

import java.util.Hashtable;

public class Pi implements PiInterface {
	private final Hashtable<VertexInterface, Integer> htab = new Hashtable<>();

	public int get(VertexInterface vertex) { return htab.get(vertex); }
	public void set(VertexInterface vertex, int value) { htab.put(vertex, value); }
	public boolean contains(VertexInterface vertex) { return htab.containsKey(vertex); }
}
