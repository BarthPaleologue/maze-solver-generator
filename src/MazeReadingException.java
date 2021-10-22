
public class MazeReadingException extends Exception {
	public MazeReadingException() {
		super("Erreur lors de la lecture du fichier labyrinthe");
	}
	public MazeReadingException(String fileName) {
		super("Erreur lors de la lecture du fichier : " + fileName);
	}
}
