package dijkstra;

import java.util.ArrayList;
import java.util.Arrays;

import maze.Maze;

public class Dijkstra {
	public static Previous compute(Maze maze) {
		ASet markedSet = new ASet();
		ASet visitedSet = new ASet();
		
		Previous previous = new Previous(maze.getWidth(), maze.getHeight());
		
		int[][] PI = new int[maze.getWidth()][maze.getHeight()];
		
		for(int i = 0; i < maze.getWidth(); ++i) {
			for(int j = 0; j < maze.getHeight(); ++j) {
				PI[i][j] = -1;
			}
		}
		
		
		VertexInterface startPoint = maze.getStartPoint();
		
		markedSet.add(startPoint);
		PI[startPoint.getX()][startPoint.getY()] = 0;
		
	
		while(markedSet.length() > 0) {
			// trouver le pivot
			VertexInterface pivot = null;
			for(int i = 0; i < markedSet.length(); i++) {
				VertexInterface pivotCandidat = markedSet.get(i);
				if(pivot == null) pivot = pivotCandidat;
				else {
					if(PI[pivotCandidat.getX()][pivotCandidat.getY()] < PI[pivot.getX()][pivot.getY()]) {
						pivot = pivotCandidat;
					}
				}
			}
			
			markedSet.remove(pivot);
			
			ArrayList<VertexInterface> voisins = maze.getNeighbors(pivot);
			
			for(VertexInterface voisin: voisins) {
				if(!markedSet.contains(voisin) && !visitedSet.contains(voisin)) {
					markedSet.add(voisin);
				}
				if(PI[voisin.getX()][voisin.getY()] == -1) {
					// si sommet à l'infini
					PI[voisin.getX()][voisin.getY()] = PI[pivot.getX()][pivot.getY()] + 1;
					previous.set(voisin, pivot);
				} else if(PI[pivot.getX()][pivot.getY()] + 1 < PI[voisin.getX()][voisin.getY()]) {
					// si sommet déjà marqué
					PI[voisin.getX()][voisin.getY()] = PI[pivot.getX()][pivot.getY()] + 1;
					previous.set(voisin, pivot);
				}
			}
			
			visitedSet.add(pivot);
		}
		
		System.out.println();
		System.out.println(previous.toString());
		
		System.out.println();
		String output2 = Arrays.deepToString(PI).replace("],", "],\n");
		System.out.println(output2);
		
		
		
		return previous;
	}
}
