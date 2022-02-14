import maze.GraphInterface;
import maze.Maze;
import maze.exceptions.MazeReadingException;

public class MainTest {

	public static void main(String[] args) throws MazeReadingException {
		GraphInterface maze = new Maze(10, 10);
		maze.initFromTextFile("data/labyrinthe.txt");
		maze.saveToTextFile("data/labyrinthe2.txt");
	}

}
