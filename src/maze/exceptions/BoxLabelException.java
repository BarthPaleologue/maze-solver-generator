package maze.exceptions;

public class BoxLabelException extends Exception {
	public BoxLabelException(char label, int x, int y) {
		super("Invalid label : " + label + " at ["+x+";"+y+"]");
	}
}
