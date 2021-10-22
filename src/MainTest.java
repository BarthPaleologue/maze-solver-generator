public class MainTest {

	public static void main(String[] args) throws MazeReadingException {
		// TODO Auto-generated method stub
		Maze maze = new Maze();
		maze.initFromTextFile("data/labyrinthe.txt");
		maze.saveToTextFile("data/labyrinthe2.txt");
	}

}
