import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maze implements MazeInterface {
	
	private final ArrayList<ArrayList<ArrayList<VertexInterface>>> graph;
	
	private final ArrayList<ArrayList<VertexInterface>> vertexMatrix;
	
	private final int _w;
	private final int _h;
	
	public Maze(int w, int h) {
		_w = w;
		_h = h;
		
		graph = new ArrayList<ArrayList<ArrayList<VertexInterface>>>();
		
		vertexMatrix = new ArrayList<ArrayList<VertexInterface>>();
	}
	
	public final void initFromTextFile(String fileName) throws MazeReadingException, IOException {

		FileReader fr = null;
		BufferedReader br = null;
		
		try {
		 // https://www.javatpoint.com/java-bufferedreader-class
		 fr = new FileReader(fileName);    
         br = new BufferedReader(fr);    
         
         int j = 0;
         while (true) {
            String line = br.readLine();
        	System.out.println(line);
            if(line == null) break;
            vertexMatrix.add(new ArrayList<VertexInterface>());
            graph.add(new ArrayList<ArrayList<VertexInterface>>());
            for(int i = 0; i < line.length(); i++) {
            	char label = line.charAt(i);
            	
            	VertexInterface box;
            	
            	if(label == 'A') box = new ABox(this, i, j);
            	else if(label == 'W') box = new WBox(this, i, j);
            	else if(label == 'D') box = new DBox(this, i, j);
            	else if(label == 'E') box = new EBox(this, i, j);
            	else {
            		throw new Exception("Invalid box label");
            	}
            	
            	vertexMatrix.get(vertexMatrix.size() - 1).add(box);
            	
            	graph.get(j).add(new ArrayList<VertexInterface>());
            }
            j++;
         }
		} catch(Exception e) {
			throw new MazeReadingException(fileName);
		} finally {
			br.close();    
			fr.close(); 
		}
         
          
         
         // Initialisation du graph
         for(int x = 0; x < vertexMatrix.size(); x++) {
        	 for(int y = 0; y < vertexMatrix.size(); y++) {
        		 VertexInterface vertex = vertexMatrix.get(x).get(y);
        		 char label = vertex.getLabel();
        		 
        		 if(label == 'W') break; // un mur n'a pas de voisins
        		 
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
         
         System.out.println(graph);

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
}
