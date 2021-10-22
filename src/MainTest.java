import java.io.IOException;

public class MainTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Maze maze = new Maze(10, 10);
		maze.initFromTextFile("data/labyrinthe.txt");
	}

}
