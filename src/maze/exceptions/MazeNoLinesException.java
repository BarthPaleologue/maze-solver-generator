package maze.exceptions;

public class MazeNoLinesException extends MazeReadingException {
    public MazeNoLinesException(String filename) {
        super(filename, new Throwable("There are no lines in the file you wanted to load"));
    }
}
