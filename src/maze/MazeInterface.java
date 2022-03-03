package maze;

import dijkstra.VertexInterface;

import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public interface MazeInterface {
    /**
     * Init an empty maze of dimensions width x height
     * @param width The width of the empty maze
     * @param height The height of the empty maze
     */
    void initEmpty(int width, int height);

    /**
     * Init a randomly generated maze of dimensions width x height using Prim randomization's algorithm
     * @param width The width of the new maze
     * @param height The height of the new maze
     */
    void initRandomPrim(int width, int height);

    /**
     * Init maze from text file
     * @param filePath The path to the maze file (see readme for formatting)
     */
    void initFromTextFile(String filePath);

    /**
     * Saves the current maze state matrix to a given text file
     * @param filePath The path to the file you want to save to
     */
    void saveToTextFile(String filePath);

    /**
     * Returns the vertex at coordinates (x,y)
     * @param x The x coordinate of the vertex to retrieve
     * @param y The y coordinate of the vertex to retrieve
     * @return The vertex at coordinates (x,y)
     */
    VertexInterface getCellAt(int x, int y);

    /**
     * Returns the label of the vertex at coordinates (x,y)
     * @param x The x coordinate of the vertex
     * @param y The y coordinate of the vertex
     * @return The label of the vertex at coordinates (x,y)
     */
    char getLabelAt(int x, int y);

    /**
     * Set new departure point in the maze
     * @param x The x coordinate of the new departure point
     * @param y The y coordinate of the new departure point
     */
    void setStartPointAt(int x, int y);

    /**
     * Changes arrival point in the maze
     * @param x The x coordinate of new arrival point
     * @param y The y coordinate of new arrival point
     */
    void setEndPointAt(int x, int y);

    /**
     * Returns the width of the maze
     * @return The width of the maze
     */
    int getWidth();

    /**
     * Returns the height of the maze
     * @return The height of the maze
     */
    int getHeight();

    /**
     * Returns the shortest path between the departure point and the arrival point in the maze
     * @return The shortest path between the departure point and the arrival point in the maze
     */
    ArrayList<VertexInterface> getShortestPath();

    /**
     * Place wall cell at given coordinates (will remove startPoint & endPoint if overlap)
     * @param x The x coordinate at which you want to place a wall cell
     * @param y The y coordinate at which you want to place a wall cell
     */
    void placeWallAt(int x, int y);

    /**
     * Place empty cell at given coordinates (will remove startPoint & endPoint if overlap)
     * @param x The x coordinate at which you want to place an empty cell
     * @param y The y coordinate at which you want to place an empty cell
     */
    void placeEmptyAt(int x, int y);

    /**
     * If the cell at (x,y) is a wall, creates empty cell in its place. Creates a wall otherwise
     * @param x The x coordinate of the cell to toggle
     * @param y The y coordinate of the cell to toggle
     */
    void toggleWallAt(int x, int y);

    /**
     * Add change listener to the maze
     * @param listener The listener to add to the maze
     */
    void addListener(ChangeListener listener);
}
