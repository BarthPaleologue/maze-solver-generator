package dijkstra;

import maze.GraphInterface;

import java.util.Iterator;

public class Dijkstra {
	/**
	 * Computes the previous object for the current maze and its start point
	 * @param maze the maze to computes Dijkstra algorithm for, starting at the maze's start point
	 * @return the previous object allowing to find the path back to the start point
	 */
	public static PreviousInterface compute(GraphInterface maze) {
		ASetInterface markedSet = new ASet();
		ASetInterface visitedSet = new ASet();
		
		PreviousInterface previous = new Previous();

		PiInterface pi = new Pi();

		VertexInterface startPoint = maze.getStartPoint();
		markedSet.add(startPoint);
		pi.set(startPoint, 0);
		
	
		while(markedSet.length() > 0) {
			// finding pivot
			VertexInterface pivot = null;
			Iterator<VertexInterface> markedIt = markedSet.getIterator();
			while (markedIt.hasNext()) {
				VertexInterface pivotCandidate = markedIt.next();
				if(pivot == null || pi.get(pivotCandidate) < pi.get(pivot)) pivot = pivotCandidate;
			}
			
			markedSet.remove(pivot);
			
			for(VertexInterface neighbor: maze.getNeighbors(pivot)) {
				if(!markedSet.contains(neighbor) && !visitedSet.contains(neighbor)) {
					markedSet.add(neighbor);
				}
				if(!pi.contains(neighbor)) {
					pi.set(neighbor, pi.get(pivot) + 1);
					previous.set(neighbor, pivot);
				} else if(pi.get(pivot) + 1 < pi.get(neighbor)) {
					pi.set(neighbor, pi.get(neighbor) + 1);
					previous.set(neighbor, pivot);
				}
			}
			
			visitedSet.add(pivot);
		}

		return previous;
	}
}
