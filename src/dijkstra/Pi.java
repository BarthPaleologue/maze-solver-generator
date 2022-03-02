package dijkstra;

import java.util.Hashtable;

public class Pi extends Hashtable<VertexInterface, Integer> implements PiInterface {
	public int get(VertexInterface vertex) { return super.get(vertex); }
	public void set(VertexInterface vertex, int value) { super.put(vertex, value); }
	public boolean contains(VertexInterface vertex) { return super.containsKey(vertex); }
}
