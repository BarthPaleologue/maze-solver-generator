import maze.Maze;
import maze.MazeInterface;
import ui.Path;

public class MainTest {

	public static void main(String[] args) {
		MazeInterface maze = new Maze(10, 10);
		maze.initFromTextFile(Path.MAZE_DIR + "labyrinthe.txt");
		maze.saveToTextFile(Path.MAZE_DIR + "labyrinthe2.txt");
	}

}
