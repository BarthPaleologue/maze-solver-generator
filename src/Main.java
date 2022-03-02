import sound.MakeSound;
import ui.ControlWindow;

public class Main {
	
	public static void main(String[] args) {
		ControlWindow mainWindow = new ControlWindow();

		// a bit of fun : you can disable it if you want
		MakeSound player = new MakeSound();
		player.OH();
	}
}
