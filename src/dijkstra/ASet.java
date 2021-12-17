package dijkstra;

import java.util.ArrayList;

public class ASet implements ASetInterface {
	
	private ArrayList<VertexInterface> list = new ArrayList<>();

	public void add(VertexInterface vertex) {
		list.add(vertex);
	}

	public boolean contains(VertexInterface vertex) {
		return list.contains(vertex);
	}
	
	public int length() {
		return list.size();
	}
	
	public VertexInterface get(int index) {
		return list.get(index);
	}
	
	public void remove(VertexInterface vertex) {
		list.remove(vertex);
	}

}
