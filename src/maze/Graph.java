package maze;

import java.util.ArrayList;

import dijkstra.GraphInterface;
import dijkstra.VertexInterface;

public class Graph implements GraphInterface {
	private ArrayList<ArrayList<ArrayList<VertexInterface>>> graph;
	public Graph() {
		graph = new ArrayList<ArrayList<ArrayList<VertexInterface>>>();
	}
	public void addEmptyColumn() {
		graph.add(new ArrayList<ArrayList<VertexInterface>>());
	}
	public void addCellAtColumn(int j) {
		graph.get(j).add(new ArrayList<VertexInterface>());
	}
	public void addNeighborAt(int x, int y, VertexInterface v) {
		graph.get(x).get(y).add(v);
	}
	public ArrayList<VertexInterface> getNeighbors(int x, int y) {
		return graph.get(x).get(y);
	}
	public ArrayList<VertexInterface> getNeighbors(VertexInterface vertex) {
		return graph.get(((MBox)vertex).getX()).get(((MBox)vertex).getY());
	}
	@Override
	public String toString() {
		String res = "";
		for(int x = 0; x < graph.size(); x++) {
			for(int y = 0; y < graph.get(0).size(); y++) {
				res += graph.get(x).get(y).toString();
				res += " | ";
			}
			res += "\n";
		}
		return res;
	}
}
