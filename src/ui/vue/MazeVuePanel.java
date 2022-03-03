package ui.vue;

import ui.ControlWindow;

import javax.swing.*;
import java.awt.*;

public class MazeVuePanel extends JPanel {
    private int nbCellsX = 0;
    private int nbCellsY = 0;

    private CellVuePanel[][] UIGrid;

    final ControlWindow parentWindow;

    public MazeVuePanel(ControlWindow parentWindow) {
        super();
        this.parentWindow = parentWindow;
    }

    /**
     * Init the grid UI given the width and the height of the maze
     * @param nbCellsX the number of cells along the X Axis
     * @param nbCellsY the number of cells along the Y Axis
     */
    public void initMazeUI(int nbCellsX, int nbCellsY) {
        removeAll();

        this.nbCellsX = nbCellsX;
        this.nbCellsY = nbCellsY;
        setLayout(new GridLayout(nbCellsY, nbCellsX));

        UIGrid = new CellVuePanel[nbCellsX][nbCellsY];

        for(int y = 0; y < nbCellsY; y++) {
            for(int x = 0; x < nbCellsX; x++) {
                CellVuePanel cell = new CellVuePanel(x, y);
                this.add(cell);
                UIGrid[x][y] = cell;
            }
        }

        repaint();
    }

    /**
     * Set the cell at the given coordinates to the corresponding color
     * @param x the first coordinate of the cell
     * @param y the second coordinate of the cell
     * @param color The color to assign to the cell
     */
    public void setCellColor(int x, int y, Color color) {
        UIGrid[x][y].setBackground(color);
    }

    /**
     * Updates the color of the grid to match the current maze state
     */
    public void updateGridColors() {
        for(int x = 0; x < nbCellsX; x++) {
            for(int y = 0; y < nbCellsY; y++) {
                setCellColor(x, y, parentWindow.getColorFromCoords(x, y));
            }
        }
    }
}
