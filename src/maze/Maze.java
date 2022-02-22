package maze;
import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dijkstra.Dijkstra;

import dijkstra.PreviousInterface;
import dijkstra.VertexInterface;
import maze.exceptions.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Maze implements GraphInterface, MazeInterface {

	private ArrayList<VertexInterface[]> vertexMatrix;

	public static final int DEFAULT_WIDTH = 10;
	public static final int DEFAULT_HEIGHT = 10;
	
	private VertexInterface startPoint = null;
	private VertexInterface endPoint = null;

	private final ArrayList<ChangeListener> listeners = new ArrayList<>();
	
	private int width = 0;
	private int height = 0;

	/**
	 * Instanciate a new empty maze object
	 * @param width the width of the maze
	 * @param height the height of the maze
	 */
	public Maze(int width, int height) {
		initEmpty(width, height);
	}

	/**
	 * Init an empty maze of dimensions width x height
	 * @param width the width of the empty maze
	 * @param height the height of the empty maze
	 */
	public void initEmpty(int width, int height) {
		this.width = width;
		this.height = height;
		this.startPoint = null;
		this.endPoint = null;
		vertexMatrix = new ArrayList<>();
		for(int x = 0; x < width; x++) {
			vertexMatrix.add(new VertexInterface[height]);
			for(int y = 0; y < height; y++) {
				vertexMatrix.get(x)[y] = new EBox(x, y);
			}
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

			// reset des width et height pour éviter les effets de bord en cas d'Exception
			startPoint = null;
			endPoint = null;
			width = 0;
			height = 0;

			// initialisation de la matrice des vertices
			 vertexMatrix = new ArrayList<>();
	         
	         int i = 0; // compteur de lignes
			 int lineLength = -1;
	         for(String line = br.readLine(); line != null; line = br.readLine()) {
				 if(lineLength != -1 && line.length() != lineLength) throw new MazeRectangleException();
				 lineLength = line.length();

				 // on ajoute une nouvelle ligne à la matrice
				 vertexMatrix.add(new VertexInterface[line.length()]);
	            
				 for(int j = 0; j < line.length(); j++) {
					 char label = line.charAt(j);
					 VertexInterface box = MBox.CreateFromLabel(label, i, j);

					 if(label == Labels.ARRIVAL && endPoint == null) endPoint = box;
					 else if(label == Labels.ARRIVAL) throw new MazeMultipleEndPointException();

					 if(label == Labels.DEPARTURE && startPoint == null) startPoint = box;
					 else if(label == Labels.DEPARTURE) throw new MazeMultipleStartPointException();

					 vertexMatrix.get(i)[j] = box;
	            }
	            i++;
	         }
			 if(i==0) throw new MazeNoLinesException(filePath);

			 // une fois la matrice du labyrinthe créée, on set la width et la height en attributs
			width = vertexMatrix.size();
			height = vertexMatrix.get(0).length;
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
		stateChanges();
	}

	/**
	 * Saves the current maze state matrix to a given text file
	 * @param filePath the path to the file you want to save to
	 */
	public void saveToTextFile(String filePath) {
		// shorter syntax that closes the writer automatically
		try (PrintWriter writer = new PrintWriter(filePath)) {
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					writer.append(vertexMatrix.get(x)[y].getLabel());
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
		//TODO: exceptions
		// Clamping x and y to usable range
		x = Math.max(0, Math.min(x, width - 1));
		y = Math.max(0, Math.min(y, height - 1));
		return vertexMatrix.get(x)[y];
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

		if(x > 0 && vertexMatrix.get(x-1)[y].getLabel() != Labels.WALL) {
			// voisin de gauche accessible
			neighborList.add(vertexMatrix.get(x-1)[y]);
		}

		if(x < width - 1 && vertexMatrix.get(x+1)[y].getLabel() != Labels.WALL) {
			// voisin de droite accessible
			neighborList.add(vertexMatrix.get(x+1)[y]);
		}

		if(y > 0 && vertexMatrix.get(x)[y-1].getLabel() != Labels.WALL) {
			// voisin du bas accessible
			neighborList.add(vertexMatrix.get(x)[y-1]);
		}

		if(y < height - 1 && vertexMatrix.get(x)[y+1].getLabel() != Labels.WALL) {
			// voisin du haut accessible
			neighborList.add(vertexMatrix.get(x)[y+1]);
		}
		return neighborList;
	}

	/**
	 * Set cell at coordinates x y in the maze to specified vertex and regenerate the graph representation of the maze
	 * @param x coordinate of the vertex to change
	 * @param y coordinate of the vertex to change
	 * @param vertex the vertex to put at the given coordinates
	 */
	public void setCell(int x, int y, VertexInterface vertex) {
		vertexMatrix.get(x)[y] = vertex;
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
		if(endPoint != null && x == endPoint.getX() && y == endPoint.getY()) endPoint = null;

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
			setCell(endPoint.getX(), endPoint.getY(), new EBox(endPoint.getX(), endPoint.getY()));
		}
		if(startPoint != null && x == startPoint.getX() && y == startPoint.getY()) startPoint = null;

		endPoint = new ABox(x, y);
		setCell(x, y, endPoint);
	}

	/**
	 * Returns the arrival point of the maze (VertexInterface)
	 * @return the vertex at the arrival coordinates of the maze
	 */
	public VertexInterface getEndPoint() {
		return endPoint;
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

		try {
			if (endPoint == null) throw new MazeEndPointException();
			if (startPoint == null) throw new MazeStartPointException();

			PreviousInterface previous = Dijkstra.compute(this);

			// détermination du plus court chemin trouvé
			VertexInterface currentVertex = this.getEndPoint(); // on part de l'arrivée pour revenir au début
			System.out.println("Shortest path : ");
			System.out.print(currentVertex);
			while (true) {
				VertexInterface nextVertex = previous.get(currentVertex);
				System.out.print(" - " + nextVertex);

				// si on est revenu au début, on a fini de remonter le chemin
				if (nextVertex == this.getStartPoint()) break;

				if (nextVertex == null) {
					// si le chemin s'arrête brusquement
					// TODO: en faire une exception ?
					System.out.println();
					System.out.println("There is no path between the start point and the end point");
					break;
				}

				path.add(nextVertex);

				currentVertex = nextVertex;
			}
			System.out.println();
		} catch(MazeStartPointException | MazeEndPointException e) {
			e.printStackTrace();
		}
		return path;
	}

	public void addListener(ChangeListener listener) {
		listeners.add(listener);
	}

	private void stateChanges() {
		ChangeEvent e = new ChangeEvent(this);
		for(ChangeListener listener: listeners) {
			listener.stateChanged(e);
		}
	}
} 
