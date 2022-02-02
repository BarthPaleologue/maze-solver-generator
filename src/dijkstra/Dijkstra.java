package dijkstra;

import maze.Maze;

public class Dijkstra {
	public static PreviousInterface compute(Maze maze) {
		ASetInterface markedSet = new ASet();
		ASetInterface visitedSet = new ASet();
		
		PreviousInterface previous = new Previous(maze.getWidth(), maze.getHeight());

		PiInterface pi = new Pi();

		VertexInterface startPoint = maze.getStartPoint();
		markedSet.add(startPoint);
		pi.set(startPoint, 0);
		
	
		while(markedSet.length() > 0) {
			// trouver le pivot
			VertexInterface pivot = null;
			for(int i = 0; i < markedSet.length(); i++) {
				VertexInterface pivotCandidat = markedSet.get(i);
				if(pivot == null) pivot = pivotCandidat;
				else {
					if(pi.get(pivotCandidat) < pi.get(pivot)) {
						pivot = pivotCandidat;
					}
				}
			}
			
			markedSet.remove(pivot);

			if(pivot == null) return previous;
			
			for(VertexInterface neighbor: maze.getNeighbors(pivot)) {
				if(!markedSet.contains(neighbor) && !visitedSet.contains(neighbor)) {
					markedSet.add(neighbor);
				}
				if(!pi.contains(neighbor)) {
					// si sommet à l'infini
					pi.set(neighbor, pi.get(pivot) + 1);

					previous.set(neighbor, pivot);
				} else if(pi.get(pivot) + 1 < pi.get(neighbor)) {
					// si sommet déjà marqué
					pi.set(neighbor, pi.get(neighbor)+1);

					previous.set(neighbor, pivot);
				}
			}
			
			visitedSet.add(pivot);
		}

		return previous;
	}
}
