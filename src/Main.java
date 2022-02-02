import maze.Maze;
import maze.MazeReadingException;
import ui.Window;

public class Main {
	
	public static void main(String[] args) {
		Maze maze = new Maze();
		
		Window w = new Window("PROJEEEET");

		try {
			// initialisation du labyrinthe à partir d'un fichier
			maze.initFromTextFile("data/labyrinthe.txt");
			w.initMazeUI(maze);
		
		} catch(MazeReadingException e) {
			e.printStackTrace();
		}
	}
}
