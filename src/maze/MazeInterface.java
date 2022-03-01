package maze;

import dijkstra.VertexInterface;
import maze.exceptions.MazeReadingException;

import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public interface MazeInterface {
    void initEmpty(int width, int height);
    void initFromTextFile(String fileName);
    void saveToTextFile(String fileName);

    void setCell(int x, int y, VertexInterface cell);
    VertexInterface getCell(int x, int y);

    void setStartPoint(int x, int y);
    VertexInterface getStartPoint();

    void setEndPoint(int x, int y);
    VertexInterface getEndPoint();

    int getWidth();
    int getHeight();

    ArrayList<VertexInterface> getShortestPath();

    void placeWallAt(int x, int y);
    void placeEmptyAt(int x, int y);

    void addListener(ChangeListener listener);
}