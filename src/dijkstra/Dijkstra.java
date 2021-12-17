package dijkstra;

import java.util.ArrayList;
import java.util.Arrays;

import maze.Maze;

public class Dijkstra {
	public int[][][] Dijkstra(Maze maze) {
		ASet markedSet = new ASet();
		ASet visitedSet = new ASet();
		
		int[][][] Previous = new int[maze.getWidth()][maze.getHeight()][2];
		int[][] PI = new int[maze.getWidth()][maze.getHeight()];
		
		for(int i = 0; i < maze.getWidth(); ++i) {
			for(int j = 0; j < maze.getHeight(); ++j) {
				PI[j][i] = -1;
				Previous[j][i][0] = -1;
				Previous[j][i][1] = -1;
			}
		}
		
		
		VertexInterface startPoint = maze.getStartPoint();
		
		markedSet.add(startPoint);
		PI[startPoint.getY()][startPoint.getX()] = 0;
		
	
		while(markedSet.length() > 0) {
			// trouver le pivot
			VertexInterface pivot = null;
			for(int i = 0; i < markedSet.length(); i++) {
				VertexInterface pivotCandidat = markedSet.get(i);
				if(pivot == null) pivot = pivotCandidat;
				else {
					if(PI[pivotCandidat.getY()][pivotCandidat.getX()] < PI[pivot.getY()][pivot.getX()]) {
						pivot = pivotCandidat;
					}
				}
			}
			
			markedSet.remove(pivot);
			
			ArrayList<VertexInterface> voisins = maze.getNeighbors(pivot.getY(), pivot.getX());
			
			for(VertexInterface voisin: voisins) {
				if(!markedSet.contains(voisin) && !visitedSet.contains(voisin)) {
					markedSet.add(voisin);
				}
				if(PI[voisin.getY()][voisin.getX()] == -1) {
					PI[voisin.getY()][voisin.getX()] = PI[pivot.getY()][pivot.getX()] + 1;
					Previous[voisin.getY()][voisin.getX()][0] = pivot.getX();
					Previous[voisin.getY()][voisin.getX()][1] = pivot.getY();
				} else if(PI[pivot.getY()][pivot.getX()] + 1 < PI[voisin.getY()][voisin.getX()]) {
					PI[voisin.getY()][voisin.getX()] = PI[pivot.getY()][pivot.getX()] + 1;
					Previous[voisin.getY()][voisin.getX()][0] = pivot.getX();
					Previous[voisin.getY()][voisin.getX()][1] = pivot.getY();
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
