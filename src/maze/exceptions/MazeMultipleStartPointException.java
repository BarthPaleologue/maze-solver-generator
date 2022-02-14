package maze.exceptions;

public class MazeMultipleStartPointException extends Exception {
    public MazeMultipleStartPointException() {
        super("You cannot have multiple departure points in your maze !");
    }
}
