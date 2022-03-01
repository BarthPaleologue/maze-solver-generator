package ui;

import javax.swing.*;
import java.awt.*;

public class CellPanel extends JPanel {
    private final int x,y;
    public CellPanel(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        setBackground(Color.WHITE);
    }

    public int getGridX() {
        return x;
    }
    public int getGridY() {
        return y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
