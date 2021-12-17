package dijkstra;
	
public interface PreviousInterface {
	public int[] get(VertexInterface vertex);
	public void set(VertexInterface vertex, VertexInterface previousVertex);
}
