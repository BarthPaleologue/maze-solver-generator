package ui.vue;

import javax.swing.*;
import java.awt.*;

public class CellVuePanel extends JPanel {
    private final int x,y;
    public CellVuePanel(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(0.1f)));
    }

    /**
     * Get the x coordinate of the cell in the grid
     * @return The x coordinate of the cell in the grid
     */
    public int getGridX() {
        return x;
    }

    /**
     * Get the y coordinate of the cell in the grid
     * @return The y coordinate of the cell in the grid
     */
    public int getGridY() {
        return y;
    }
}
