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

    public int getGridX() {
        return x;
    }
    public int getGridY() {
        return y;
    }
}
