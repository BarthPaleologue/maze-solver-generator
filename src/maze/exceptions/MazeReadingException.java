package maze.exceptions;

public class MazeReadingException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6669770188914919564L;

	public MazeReadingException(String fileName, Throwable cause) {
		super("Error while reading file : " + fileName, cause);
	}
}
