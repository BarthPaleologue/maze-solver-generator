package dijkstra;
	
public interface PreviousInterface {
	VertexInterface get(VertexInterface vertex);
	void set(VertexInterface vertex, VertexInterface previousVertex);
}
