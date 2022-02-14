package maze.exceptions;

public class MazeMultipleEndPointException extends Exception {
    public MazeMultipleEndPointException() {
        super("You cannot have multiple arrival points in your maze !");
    }
}
