package maze;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import dijkstra.Dijkstra;

import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import maze.exceptions.*;
import settings.Labels;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Maze implements GraphInterface, MazeInterface {

	private ArrayList<VertexInterface[]> vertexMatrix;

	public static final int DEFAULT_WIDTH = 30;
	public static final int DEFAULT_HEIGHT = 30;
	
	private VertexInterface startPoint = null;
	private VertexInterface endPoint = null;

	private final ArrayList<ChangeListener> listeners = new ArrayList<>();
	
	private int width = 0;
	private int height = 0;

	/**
	 * Init an empty maze of dimensions width x height
	 * @param width the width of the empty maze
	 * @param height the height of the empty maze
	 */
	public void initEmpty(int width, int height) {
		this.width = width;
		this.height = height;
		startPoint = null;
		endPoint = null;
		vertexMatrix = new ArrayList<>();
		for(int y = 0; y < height; y++) {
			vertexMatrix.add(new VertexInterface[width]);
			for(int x = 0; x < width; x++) {
				vertexMatrix.get(y)[x] = new EBox(x, y);
			}
		}
		stateChanges();
	}

	public void initRandomPrim(int width, int height) {
		this.width = width;
		this.height = height;
		startPoint = null;
		endPoint = null;
		vertexMatrix = new ArrayList<>();
		for(int y = 0; y < height; y++) {
			vertexMatrix.add(new VertexInterface[width]);
			for(int x = 0; x < width; x++) {
				vertexMatrix.get(y)[x] = new WBox(x, y);
			}
		}

		ArrayList<VertexInterface> marked = new ArrayList<>();
		ArrayList<VertexInterface> walls = new ArrayList<>();
		Random rd = new Random();
		VertexInterface start = getCell(rd.nextInt(width), rd.nextInt(height));
		walls.addAll(getWallsAround(start));

		while (walls.size() > 0) {
			int index = rd.nextInt(walls.size());
			VertexInterface rdCell = walls.get(index);
			int x = rdCell.getX();
			int y = rdCell.getY();

			int nbExploredNeighbors = 0;
			if(exists(x-1, y) && marked.contains(getCell(x-1,y))) nbExploredNeighbors++;
			if(exists(x+1, y) && marked.contains(getCell(x+1,y))) nbExploredNeighbors++;
			if(exists(x, y-1) && marked.contains(getCell(x,y-1))) nbExploredNeighbors++;
			if(exists(x, y+1) && marked.contains(getCell(x,y+1))) nbExploredNeighbors++;

			ArrayList<VertexInterface> neighbors = getWallsAround(rdCell);

			if(nbExploredNeighbors < 2) {
				setCell(x, y, new EBox(x, y));
				walls.addAll(neighbors);
			}
			walls.remove(rdCell);
			marked.addAll(neighbors);
		}
		stateChanges();
	}

	/**
	 * Init maze from text file
	 * @param filePath the path to the maze file (see readme for formatting)
	 */
	public void initFromTextFile(String filePath) {
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			 fr = new FileReader(filePath);
	         br = new BufferedReader(fr);

			 VertexInterface newStartPoint  = null;
			 VertexInterface newEndPoint = null;

			// initialisation de la matrice des vertices
			 ArrayList<VertexInterface[]> newVertexMatrix = new ArrayList<>();
	         
	         int y = 0; // compteur de lignes
			 int lineLength = -1;
	         for(String line = br.readLine(); line != null; line = br.readLine()) {
				 if(lineLength != -1 && line.length() != lineLength) throw new MazeRectangleException();
				 lineLength = line.length();

				 // on ajoute une nouvelle ligne à la matrice
				 newVertexMatrix.add(new VertexInterface[line.length()]);
	            
				 for(int x = 0; x < line.length(); x++) {
					 char label = line.charAt(x);
					 VertexInterface box = MBox.CreateFromLabel(label, x, y);

					 if(label == Labels.ARRIVAL) {
						 if(newEndPoint == null) newEndPoint = box;
						 else throw new MazeMultipleEndPointException();
					 }

					 if(label == Labels.DEPARTURE) {
						 if(newStartPoint == null) newStartPoint = box;
						 else throw new MazeMultipleStartPointException();
					 }

					 newVertexMatrix.get(y)[x] = box;
	            }
	            y++;
	         }
			 if(y == 0) throw new MazeNoLinesException(filePath);

			 // une fois la matrice du labyrinthe créée, on set la width et la height en attributs
			height = newVertexMatrix.size();
			width = newVertexMatrix.get(0).length;
			endPoint = newEndPoint;
			startPoint = newStartPoint;
			vertexMatrix = newVertexMatrix;

			stateChanges();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    
		}
	}

	/**
	 * Saves the current maze state matrix to a given text file
	 * @param filePath the path to the file you want to save to
	 */
	public void saveToTextFile(String filePath) {
		try (PrintWriter writer = new PrintWriter(filePath)) {
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					writer.append(getCell(x, y).getLabel());
				}
				writer.append('\n');
			}
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the vertex at coordinates (x,y)
	 * @param x x coordinate of the vertex to retrieve
	 * @param y y coordinate of the vertex to retrieve
	 * @return the vertex at coordinates (x,y)
	 */
	public VertexInterface getCell(int x, int y) {
		return vertexMatrix.get(y)[x];
	}

	/**
	 * Determines if the vertex at the given coordinates exists and is not a wall
	 * @param x the x coordinate of the vertex to test
	 * @param y the y coordinate of the vertex to test
	 * @return if the vertex at (x,y) exists AND is not a wall
	 */
	private boolean existsAndNotWall(int x, int y) {
		return exists(x, y) && getCell(x, y).getLabel() != Labels.WALL;
	}

	/**
	 * Returns whereas the cell at coordinates (x,y) exists in the maze
	 * @param x the x coordinate to check
	 * @param y the y coordinate to check
	 * @return true if the cell does exist, false otherwise
	 */
	private boolean exists(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	/**
	 * Tests is cell at coordinates (x,y) is a wall
	 * @param x the x coordinate to test for
	 * @param y the y coordinate to test for
	 * @return true if the corresponding cell exists and is a wall, false otherwise
	 */
	private boolean existsAndIsWall(int x, int y) {
		return exists(x, y) && getCell(x, y).getLabel() == Labels.WALL;
	}

	/**
	 * Returns the list of all known neighbors of a given vertex
	 * @param vertex the vertex you want to know the neighbors of
	 * @return the list of its neighbors
	 */
	public ArrayList<VertexInterface> getNeighbors(VertexInterface vertex) {
		int x = vertex.getX();
		int y = vertex.getY();

		ArrayList<VertexInterface> neighborList = new ArrayList<>();

		if(vertex.getLabel() == Labels.WALL) return neighborList;

		if(existsAndNotWall(x-1, y)) neighborList.add(getCell(x-1, y));
		if(existsAndNotWall(x+1, y)) neighborList.add(getCell(x+1, y));
		if(existsAndNotWall(x, y-1)) neighborList.add(getCell(x, y-1));
		if(existsAndNotWall(x, y+1)) neighborList.add(getCell(x, y+1));

		return neighborList;
	}

	/**
	 * Get neighboring walls of a vertex (useful for prim random algorithm)
	 * @param vertex the vertex to get the walls around of
	 * @return the list of the walls neighboring the given cell
	 */
	private ArrayList<VertexInterface> getWallsAround(VertexInterface vertex) {
		int x = vertex.getX();
		int y = vertex.getY();

		ArrayList<VertexInterface> wallList = new ArrayList<>();

		if(existsAndIsWall(x-1, y)) wallList.add(getCell(x-1, y));
		if(existsAndIsWall(x+1, y)) wallList.add(getCell(x+1, y));
		if(existsAndIsWall(x, y-1)) wallList.add(getCell(x, y-1));
		if(existsAndIsWall(x, y+1)) wallList.add(getCell(x, y+1));

		return wallList;
	}

	/**
	 * Set cell at coordinates x y in the maze to specified vertex and regenerate the graph representation of the maze
	 * @param x coordinate of the vertex to change
	 * @param y coordinate of the vertex to change
	 * @param vertex the vertex to put at the given coordinates
	 */
	private void setCell(int x, int y, VertexInterface vertex) {
		vertexMatrix.get(y)[x] = vertex;
		stateChanges();
	}

	/**
	 * Set new departure point in the maze
	 * @param x coordinate of the new departure point
	 * @param y coordinate of the new departure point
	 */
	public void setStartPoint(int x, int y) {
		if(startPoint != null) {
			// si le start point existe déjà, on le remplace par une case vide
			setCell(startPoint.getX(), startPoint.getY(), new EBox(startPoint.getX(), startPoint.getY()));
		}
		// si on place le start point sur le end point, on supprime le end point
		if(doesOverlapEndPoint(x, y)) endPoint = null;

		startPoint = new DBox(x, y);
		setCell(x, y, startPoint);
	}

	/**
	 * Get Departure Point of the Maze
	 * @return the departure point of the maze
	 */
	public VertexInterface getStartPoint() {
		return startPoint;
	}

	/**
	 * Changes arrival point in the maze
	 * @param x coordinate of new arrival point
	 * @param y coordinate of new arrival point
	 */
	public void setEndPoint(int x, int y) {
		if(endPoint != null) {
			// on supprime l'ancien endPoint
			setCell(endPoint.getX(), endPoint.getY(), new EBox(endPoint.getX(), endPoint.getY()));
		}
		if(doesOverlapStartPoint(x, y)) startPoint = null;

		endPoint = new ABox(x, y);
		setCell(x, y, endPoint);
	}

	/**
	 * Returns the width of the maze
	 * @return the width of the maze
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the maze
	 * @return the height of the maze
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the shortest path between the departure point and the arrival point in the maze
	 * @return the shortest path between the departure point and the arrival point in the maze
	 */
	public ArrayList<VertexInterface> getShortestPath() {
		ArrayList<VertexInterface> path = new ArrayList<>();

		if(startPoint != null && endPoint != null) {

			PreviousInterface previous = Dijkstra.compute(this);

			// détermination du plus court chemin trouvé
			VertexInterface currentVertex = endPoint; // on part de l'arrivée pour revenir au début
			System.out.println("Shortest path : ");
			System.out.print(currentVertex);
			while (true) {
				VertexInterface nextVertex = previous.get(currentVertex);
				System.out.print(" - " + nextVertex);

				// si on est revenu au début, on a fini de remonter le chemin
				if (nextVertex == startPoint) break;

				if (nextVertex == null) {
					// si le chemin s'arrête brusquement
					System.out.println();
					System.out.println("There is no path between the start point and the end point");
					break;
				}

				path.add(nextVertex);

				currentVertex = nextVertex;
			}
			System.out.println();
		}
		return path;
	}

	/**
	 * Place wall cell at given coordinates (will remove startPoint & endPoint if overlap)
	 * @param x the x coordinate at which you want to place a wall cell
	 * @param y the y coordinate at which you want to place a wall cell
	 */
	public void placeWallAt(int x, int y) {
		if(doesOverlapStartPoint(x, y)) startPoint = null;
		if(doesOverlapEndPoint(x, y)) endPoint = null;
		setCell(x, y, new WBox(x, y));
	}

	/**
	 * Place empty cell at given coordinates (will remove startPoint & endPoint if overlap)
	 * @param x the x coordinate at which you want to place an empty cell
	 * @param y the y coordinate at which you want to place an empty cell
	 */
	public void placeEmptyAt(int x, int y) {
		if(doesOverlapStartPoint(x, y)) startPoint = null;
		if(doesOverlapEndPoint(x, y)) endPoint = null;
		setCell(x, y, new EBox(x, y));
	}

	/**
	 * Check if the given coordinates match those of the start point. Set the start point to null if true
	 * @param x the x coordinate to check the overlap for
	 * @param y the y coordinate to check the overlap for
	 */
	private boolean doesOverlapStartPoint(int x, int y) {
		return startPoint != null && startPoint.getX() == x && startPoint.getY() == y;
	}

	/**
	 * Check if the given coordinates match those of the end point. Set the end point to null if true
	 * @param x the x coordinate to check the overlap for
	 * @param y the y coordinate to check the overlap for
	 */
	private boolean doesOverlapEndPoint(int x, int y) {
		return endPoint != null && endPoint.getX() == x && endPoint.getY() == y;
	}

	/**
	 * Add change listener to the maze
	 * @param listener the listener to add to the maze
	 */
	public void addListener(ChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Notify listener a change of state in the maze
	 */
	private void stateChanges() {
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener listener: listeners) {
			listener.stateChanged(e);
		}
	}
} 
