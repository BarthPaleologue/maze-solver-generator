package ui;

import maze.Maze;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel {
    private final Maze maze;
    private final int x;
    private final int y;
    public CellPanel(Maze maze, int x, int y) {
        super();
        this.maze = maze;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public static Color getColorFromLabel(char label) {
        switch(label) {
            case 'A':
                return Color.RED;
            case 'D':
                return Color.BLUE;
            case 'W':
                return Color.DARK_GRAY;
            case 'E':
                return Color.WHITE;
            default:
                return Color.PINK;
        }
    }
}
