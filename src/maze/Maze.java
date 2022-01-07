package maze;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import dijkstra.Dijkstra;
import dijkstra.Previous;
import dijkstra.VertexInterface;

public class Maze implements GraphInterface {
	
	private ArrayList<VertexInterface>[][] graph;
	
	private final ArrayList<VertexInterface[]> vertexMatrix;
	
	private VertexInterface startPoint;
	private VertexInterface endPoint;
	
	private int width;
	private int height;
	
	public Maze() {
		
		graph = null;
		
		vertexMatrix = new ArrayList<VertexInterface[]>();
	}
	
	public void initFromTextFile(String fileName) throws MazeReadingException {

		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			 // https://www.javatpoint.com/java-bufferedreader-class
			 fr = new FileReader(fileName);    
	         br = new BufferedReader(fr);    
	         
	         int i = 0;
	         for(String line = br.readLine(); line != null; line = br.readLine()) {
	        	 
	            vertexMatrix.add(new VertexInterface[line.length()]);
	            
	            for(int j = 0; j < line.length(); j++) {
	            	
	            	char label = line.charAt(j);
	            	VertexInterface box;
	            	
	            	switch(label) {
		            	case 'A':
		            		box = new ABox(this, i, j);
		            		endPoint = box;
		            		break;
		            	case 'W':
		            		box = new WBox(this, i, j);
		            		break;
		            	case 'D':
		            		box = new DBox(this, i, j);
		            		startPoint = box;
		            		break;
		            	case 'E':
		            		box = new EBox(this, i, j);
		            		break;
		            	default:
		            		throw new BoxLabelException(label, i, j);		
	            	}
	            	
	            	// tous les problèmes d'indices viennent d'ici lol
	            	vertexMatrix.get(i)[j] = box;
	            }
	            i++;
	         }
		} catch(BoxLabelException e) {
			e.printStackTrace();
		} catch(Exception e) {
			new MazeReadingException(fileName, e).printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}    
		}
		
		width = vertexMatrix.size();
		height = vertexMatrix.get(0).length;
		
		graph = new ArrayList[width][height];
         
        // Initialisation du graph
		for(int x = 0; x < width; ++x) {
        	 for(int y = 0; y < height; ++y) {
        		 graph[x][y] = new ArrayList<VertexInterface>();
        		 
        		 VertexInterface vertex = vertexMatrix.get(x)[y];
        		 char label = vertex.getLabel();
        		 
        		 if(label == 'W') continue; // un mur n'a pas de voisins
        		 
        		 if(x > 0 && vertexMatrix.get(x-1)[y].getLabel() != 'W') {
        			 // voisin de gauche accessible
        			 graph[x][y].add(vertexMatrix.get(x-1)[y]);
        		 }
        		 
        		 if(x < width - 1 && vertexMatrix.get(x+1)[y].getLabel() != 'W') {
        			// voisin de droite accessible
        			graph[x][y].add(vertexMatrix.get(x+1)[y]);
        		 }
        		 
        		 if(y > 0 && vertexMatrix.get(x)[y-1].getLabel() != 'W') {
        			 // voisin du bas accessible
        			 graph[x][y].add(vertexMatrix.get(x)[y-1]);
        		 }
        		 
        		 if(y < height - 1 && vertexMatrix.get(x)[y+1].getLabel() != 'W') {
        			// voisin du haut accessible
        			 graph[x][y].add(vertexMatrix.get(x)[y+1]);
        		 }
        	 }
         }

		String output = Arrays.deepToString(graph).replace("[[[", "\n[[[");
		System.out.println(output);
	}
	
	public final void saveToTextFile(String fileName) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(fileName));
			for(int x = 0; x < width; ++x) {
				for(int y = 0; y < height; ++y) {
					writer.append(vertexMatrix.get(x)[y].getLabel());
				}
				writer.append('\n');
			}                                               
			writer.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			writer.close(); 
		}
	}
	
	public VertexInterface getCell(int x, int y) {
		return vertexMatrix.get(x)[y];
	}
	
	public ArrayList<VertexInterface> getNeighbors(VertexInterface vertex) {
		// todo remettre les index dans le bon sens
		return graph[vertex.getX()][vertex.getY()];
	}
	
	public VertexInterface getStartPoint() {
		return startPoint;
	}
	
	public VertexInterface getEndPoint() {
		return endPoint;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public ArrayList<int[]> solve() {
		Previous previous = Dijkstra.compute(this);
		
		VertexInterface startPoint = this.getStartPoint();

		VertexInterface endPoint = this.getEndPoint();
		
		int[] coords = {endPoint.getX(), endPoint.getY()};
		
		ArrayList<int[]> path = new ArrayList<>();
		
		// détermination du plus court chemin trouvé
		while(true) {
			int[] newCoords = previous.get(coords[0], coords[1]);
			if(startPoint.getX() == newCoords[0] && startPoint.getY() == newCoords[1]) {
				// si on est revenu au début
				break;
			}
			
			if(newCoords[0] == -1 && newCoords[1] == -1) {
				// si on a échoué à revenir au début
				System.out.println("Could not find a path");
				break;
			}
			
			path.add(newCoords);
			
			coords[0] = newCoords[0];
			coords[1] = newCoords[1];
		}
		
		return path;
	}
} 
