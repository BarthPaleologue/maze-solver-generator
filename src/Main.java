import sound.MakeSound;
import ui.Window;

public class Main {
	
	public static void main(String[] args) {
		Window mainWindow = new Window();

		// a bit of fun : you can disable it if you want
		MakeSound player = new MakeSound();
		player.OH();
	}
}
