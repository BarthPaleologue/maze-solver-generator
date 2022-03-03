package maze.exceptions;

public class MazeReadingException extends Exception {
	public MazeReadingException(String fileName, Throwable cause) {
		super("Error while reading file : " + fileName, cause);
	}
}
