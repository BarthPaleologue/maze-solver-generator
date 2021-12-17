package maze;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import dijkstra.VertexInterface;

public class Maze implements MazeInterface {
	
	private final ArrayList<ArrayList<ArrayList<VertexInterface>>> graph;
	
	private final ArrayList<ArrayList<VertexInterface>> vertexMatrix;
	
	private VertexInterface startPoint;
	private VertexInterface endPoint;
	
	private int width;
	private int height;
	
	public Maze() {
		
		graph = new ArrayList<ArrayList<ArrayList<VertexInterface>>>();
		
		vertexMatrix = new ArrayList<ArrayList<VertexInterface>>();
	}
	
	public final void initFromTextFile(String fileName) throws MazeReadingException {

		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			 // https://www.javatpoint.com/java-bufferedreader-class
			 fr = new FileReader(fileName);    
	         br = new BufferedReader(fr);    
	         
	         int j = 0;
	         for(String line = br.readLine(); line != null; line = br.readLine()) {
	        	 
	        	// construction des matrices vides
	            vertexMatrix.add(new ArrayList<VertexInterface>());
	            graph.add(new ArrayList<ArrayList<VertexInterface>>());
	            
	            for(int i = 0; i < line.length(); i++) {
	            	
	            	char label = line.charAt(i);
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
	            	
	            	vertexMatrix.get(vertexMatrix.size() - 1).add(box);
	            	
	            	// ajout d'une liste de voisins vide
	            	graph.get(j).add(new ArrayList<VertexInterface>());
	            }
	            j++;
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
		height = vertexMatrix.get(0).size();
         
        // Initialisation du graph
		for(int x = 0; x < vertexMatrix.size(); ++x) {
        	 for(int y = 0; y < vertexMatrix.get(x).size(); ++y) {
        		 VertexInterface vertex = vertexMatrix.get(x).get(y);
        		 char label = vertex.getLabel();
        		 
        		 if(label == 'W') continue; // un mur n'a pas de voisins
        		 
        		 if(x > 0 && vertexMatrix.get(x-1).get(y).getLabel() != 'W') {
        			 // voisin de gauche accessible
        			 graph.get(x).get(y).add(vertexMatrix.get(x-1).get(y));
        		 }
        		 
        		 if(x < vertexMatrix.size() - 1 && vertexMatrix.get(x+1).get(y).getLabel() != 'W') {
        			// voisin de droite accessible
        			 graph.get(x).get(y).add(vertexMatrix.get(x+1).get(y));
        		 }
        		 
        		 if(y > 0 && vertexMatrix.get(x).get(y-1).getLabel() != 'W') {
        			 // voisin du bas accessible
        			 graph.get(x).get(y).add(vertexMatrix.get(x).get(y-1));
        		 }
        		 
        		 if(y < vertexMatrix.get(x).size() - 1 && vertexMatrix.get(x).get(y+1).getLabel() != 'W') {
        			// voisin du haut accessible
        			 graph.get(x).get(y).add(vertexMatrix.get(x).get(y+1));
        		 }
        	 }
         }

		String output = graph.toString().replace("[[[", "\n[[[");
		System.out.println(output);
	}
	
	public final void saveToTextFile(String fileName) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new File(fileName));
			for(int x = 0; x < vertexMatrix.size(); ++x) {
				for(int y = 0; y < vertexMatrix.get(0).size(); ++y) {
					writer.append(vertexMatrix.get(x).get(y).getLabel());
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
		return vertexMatrix.get(x).get(y);
	}
	
	public ArrayList<ArrayList<VertexInterface>> getGrid() {
		return vertexMatrix;
	}
	
	public ArrayList<VertexInterface> getNeighbors(int x, int y) {
		return graph.get(x).get(y);
	}
	
	public ArrayList<VertexInterface> getNeighbors(VertexInterface vertex) {
		return graph.get(((MBox)vertex).getX()).get(((MBox)vertex).getY());
	}
	
	public ArrayList<VertexInterface> getAllVertices() {
		ArrayList<VertexInterface> vertexList = new ArrayList<>();
		for(int x = 0; x < vertexMatrix.size(); ++x) {
			for(int y = 0; y < vertexMatrix.get(x).size(); ++y) {
				vertexList.add(vertexMatrix.get(x).get(y));
			}
		}
		return vertexList;
	}
	
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex) {
		return graph.get(((MBox)vertex).getX()).get(((MBox)vertex).getY());
	}
	
	public int getWeight(VertexInterface src, VertexInterface dst) {
		return 0;
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
} 
