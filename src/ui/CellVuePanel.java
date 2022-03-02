package ui;

import javax.swing.*;
import java.awt.*;

public class CellVuePanel extends JPanel {
    private final int x,y;
    public CellVuePanel(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public int getGridX() {
        return x;
    }
    public int getGridY() {
        return y;
    }
}
