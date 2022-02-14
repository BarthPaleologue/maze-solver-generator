package maze;

public class BoxLabelException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3055356911994221862L;

	public BoxLabelException(char label, int x, int y) {
		super("Invalid label : " + label + " at ["+x+";"+y+"]");
	}
}
