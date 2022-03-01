package maze;

import java.awt.*;

public class Labels {
    public static final char WALL = 'W';
    public static final char EMPTY = 'E';
    public static final char DEPARTURE = 'D';
    public static final char ARRIVAL = 'A';

    /**
     * Returns the associated color for the given label
     * @param label the given label to get the color of
     * @return the color corresponding to the given label
     */
    public static Color getColorFromLabel(char label) {
        switch(label) {
            case Labels.ARRIVAL:
                return Color.RED;
            case Labels.DEPARTURE:
                return Color.BLUE;
            case Labels.WALL:
                return Color.DARK_GRAY;
            case Labels.EMPTY:
                return Color.WHITE;
            default:
                return Color.PINK;
        }
    }
}
