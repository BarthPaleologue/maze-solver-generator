import maze.Maze;
import sound.MakeSound;
import ui.Window;

public class Main {
	
	public static void main(String[] args) {
		Window mainWindow = new Window("PROJEEEET");

		// just a little bit of fun
		MakeSound player = new MakeSound();
		player.OH();

		/*try {
			maze.initFromTextFile("data/labyrinthe.txt");
		} catch(MazeReadingException e) {
			e.printStackTrace();
		}*/
	}
}
