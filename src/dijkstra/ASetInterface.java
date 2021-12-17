package dijkstra;

public interface ASetInterface {
	public void add(VertexInterface vertex);
	public boolean contains(VertexInterface vertex);
	public int length();
	public void remove(VertexInterface vertex);
	public VertexInterface get(int index);
}
