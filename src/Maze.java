import java.util.ArrayList;

public class Maze implements MazeInterface {
	
	private final ArrayList<VertexInterface>[][] graph;
	
	private final VertexInterface[][] vertexMatrix;
	
	private final int _w;
	private final int _h;
	
	public Maze(int w, int h) {
		_w = w;
		_h = h;
		
		graph = new ArrayList[_w][_h];
		
		vertexMatrix = new VertexInterface[_w][_h];
		
		for(int x = 0; x < _w; ++x) {
			for(int y = 0; y < _h; ++y) {
				graph[x][y] = new ArrayList<VertexInterface>();
			}
		}
	}
	
	public ArrayList<VertexInterface> getNeighbors(int x, int y) {
		return graph[x][y];
	}
	
	public ArrayList<VertexInterface> getNeighbors(VertexInterface vertex) {
		return graph[((MBox)vertex).getX()][((MBox)vertex).getY()];
	}
	
	public ArrayList<VertexInterface> getAllVertices() {
		ArrayList<VertexInterface> vertexList = new ArrayList<>();
		for(int x = 0; x < _w; ++x) {
			for(int y = 0; y < _h; ++y) {
				vertexList.add(vertexMatrix[x][y]);
			}
		}
		return vertexList;
	}
	
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
		return graph[((MBox)vertex).getX()][((MBox)vertex).getY()];
	}
	
	public int getWeight(VertexInterface src, VertexInterface dst) {
		return 0;
	}
}
