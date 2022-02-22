package maze;
import java.util.ArrayList;

import dijkstra.VertexInterface;
import maze.exceptions.MazeReadingException;

public interface GraphInterface {
	void initEmpty(int width, int height);
	void initFromTextFile(String fileName) throws MazeReadingException;
	void saveToTextFile(String fileName);
	VertexInterface getCell(int x, int y);
	ArrayList<VertexInterface> getNeighbors(VertexInterface vertex);
	void setCell(int x, int y, VertexInterface cell);
	void setStartPoint(int x, int y);
	VertexInterface getStartPoint();
	void setEndPoint(int x, int y);
	VertexInterface getEndPoint();
	int getWidth();
	int getHeight();
	ArrayList<VertexInterface> getShortestPath();
}
