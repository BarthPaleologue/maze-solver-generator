package dijkstra;

import java.util.ArrayList;
import java.util.Arrays;

import maze.Maze;

public class Dijkstra {
	public static int[][][] compute(Maze maze) {
		ASet markedSet = new ASet();
		ASet visitedSet = new ASet();
		
		int[][][] Previous = new int[maze.getWidth()][maze.getHeight()][2];
		int[][] PI = new int[maze.getWidth()][maze.getHeight()];
		
		for(int i = 0; i < maze.getWidth(); ++i) {
			for(int j = 0; j < maze.getHeight(); ++j) {
				PI[i][j] = -1;
				Previous[i][j][0] = -1;
				Previous[i][j][1] = -1;
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
					Previous[voisin.getX()][voisin.getY()][0] = pivot.getX();
					Previous[voisin.getX()][voisin.getY()][1] = pivot.getY();
				} else if(PI[pivot.getX()][pivot.getY()] + 1 < PI[voisin.getX()][voisin.getY()]) {
					// si sommet déjà marqué
					PI[voisin.getX()][voisin.getY()] = PI[pivot.getX()][pivot.getY()] + 1;
					Previous[voisin.getX()][voisin.getY()][0] = pivot.getX();
					Previous[voisin.getX()][voisin.getY()][1] = pivot.getY();
				}
			}
			
			visitedSet.add(pivot);
		}
		
		System.out.println();
		String output = Arrays.deepToString(Previous).replace("]],", "]],\n");
		System.out.println(output);
		
		System.out.println();
		String output2 = Arrays.deepToString(PI).replace("],", "],\n");
		System.out.println(output2);
		
		
		
		return Previous;
	}
}
