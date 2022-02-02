import maze.Maze;
import maze.MazeReadingException;

public class MainTest {

	public static void main(String[] args) throws MazeReadingException {
		Maze maze = new Maze();
		maze.initFromTextFile("data/labyrinthe.txt");
		maze.saveToTextFile("data/labyrinthe2.txt");
	}

}
