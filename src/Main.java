import java.awt.Color;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import dijkstra.Dijkstra;
import dijkstra.Previous;
import dijkstra.VertexInterface;
import maze.Maze;
import maze.MazeReadingException;
import ui.Window;

public class Main {
	
	public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
		Maze maze = new Maze();
		
		Window w = new Window("PROJEEEET");

		try {
			// initialisation du labyrinthe à partir d'un fichier
			maze.initFromTextFile("data/labyrinthe.txt");
			
			w.load(maze);
		
		} catch(MazeReadingException e) {
			e.printStackTrace();
		}
	}
}
