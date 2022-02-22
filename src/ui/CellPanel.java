package ui;

import javax.swing.*;
import java.awt.*;

import maze.Labels;

public class CellPanel extends JPanel {
    public CellPanel() {
        super();
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

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
