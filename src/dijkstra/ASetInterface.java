package dijkstra;

public interface ASetInterface {
	void add(VertexInterface vertex);
	boolean contains(VertexInterface vertex);
	int length();
	void remove(VertexInterface vertex);
	VertexInterface get(int index);
}
