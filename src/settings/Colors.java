package settings;

import java.awt.*;

public class Colors {
    // the colors are not final because I like Easter eggs
    public static Color WALL_COLOR;
    public static Color EMPTY_COLOR;
    public static Color DEPARTURE_COLOR;
    public static Color ARRIVAL_COLOR;
    public static Color ERROR_COLOR;
    public static Color PATH_COLOR;

    /**
     * Returns the associated color for the given label (a switch statement is faster at runtime than an if statement on a char therefore I chose to use the switch even though it was discouraged)
     * @param label the given label to get the color of
     * @return the color corresponding to the given label
     */
    public static Color getColorFromLabel(char label) {
        switch(label) {
            case Labels.ARRIVAL:
                return ARRIVAL_COLOR;
            case Labels.DEPARTURE:
                return DEPARTURE_COLOR;
            case Labels.WALL:
                return WALL_COLOR;
            case Labels.EMPTY:
                return EMPTY_COLOR;
            default:
                return ERROR_COLOR;
        }
    }

    public static void setDefaultColorScheme() {
        WALL_COLOR = Color.DARK_GRAY;
        EMPTY_COLOR = Color.WHITE;
        DEPARTURE_COLOR = Color.BLUE;
        ARRIVAL_COLOR = Color.RED;
        ERROR_COLOR = Color.PINK;
        PATH_COLOR = Color.GREEN;
    }

    public static void setUSSRColorScheme() {
        setDefaultColorScheme();
        WALL_COLOR = Color.YELLOW;
        EMPTY_COLOR = Color.RED;
        DEPARTURE_COLOR = Color.BLACK;
        ARRIVAL_COLOR = Color.BLACK;
        PATH_COLOR = new Color(128, 0, 0);
    }

    public static void setUkraineColorScheme() {
        setDefaultColorScheme();
        WALL_COLOR = new Color(254, 214, 47);
        EMPTY_COLOR = new Color(1, 90, 185);
        DEPARTURE_COLOR = Color.WHITE;
    }
}
