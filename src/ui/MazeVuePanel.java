package ui;

import javax.swing.*;
import java.awt.*;

public class MazeVuePanel extends JPanel {
    private int nbCellsX;
    private int nbCellsY;

    private CellPanel[][] UIGrid;

    Window parentWindow;

    public MazeVuePanel(int nbCellsX, int nbCellsY, Window parentWindow) {
        super();
        this.nbCellsX = nbCellsX;
        this.nbCellsY = nbCellsY;

        this.parentWindow = parentWindow;
    }

    /**
     * Init the grid UI given the width and the height of the maze
     * @param nbCellsX the number of cells along the X Axis
     * @param nbCellsY the number of cells along the Y Axis
     */
    public void initMazeUI(int nbCellsX, int nbCellsY) {
        removeAll(); // remove components of last maze (necessary when changing maze size)

        this.nbCellsX = nbCellsX;
        this.nbCellsY = nbCellsY;
        setLayout(new GridLayout(nbCellsX, nbCellsY));

        UIGrid = new CellPanel[nbCellsX][nbCellsY];

        for(int i = 0; i < nbCellsX; i++) {
            for(int j = 0; j < nbCellsY; j++) {
                CellPanel cell = new CellPanel(i,j);
                this.add(cell);
                UIGrid[i][j] = cell;
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
        for(int i = 0; i < nbCellsX; i++) {
            for(int j = 0; j < nbCellsY; j++) {
                Color originalColor = parentWindow.getColorFromCoords(i, j);
                setCellColor(i, j, originalColor);
            }
        }
    }
}
