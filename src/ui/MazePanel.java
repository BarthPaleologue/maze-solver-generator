package ui;

import maze.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MazePanel extends JPanel implements MouseListener {
    private int nbCellsX;
    private int nbCellsY;

    private MazeInterface maze = null;

    private CellPanel[][] UIGrid = null;

    private final Window parentWindow;

    public MazePanel(int nbCellsX, int nbCellsY, Window parentWindow) {
        super();

        this.nbCellsX = nbCellsX;
        this.nbCellsY = nbCellsY;

        addMouseListener(this);

        this.parentWindow = parentWindow;
    }

    public void initMazeUI(MazeInterface maze) {
        removeAll(); // remove components of last maze (necessary when changing maze size)

        nbCellsX = maze.getWidth();
        nbCellsY = maze.getHeight();
        setLayout(new GridLayout(nbCellsX, nbCellsY));


        this.maze = maze;

        UIGrid = new CellPanel[nbCellsX][nbCellsY];

        for(int i = 0; i < nbCellsX; i++) {
            for(int j = 0; j < nbCellsY; j++) {
                CellPanel cell = new CellPanel();
                this.add(cell);
                UIGrid[i][j] = cell;
            }
        }
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

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO: j'ai inversé x et y oups
        int cellX = e.getY() * nbCellsX / getHeight();
        int cellY = e.getX() * nbCellsY / getWidth();

        //// 1 = Left Click ; 2 = Middle Click ; 3 = Right Click
        switch(e.getButton()) {
            case 1:
                switch (parentWindow.getEditionState()) {
                    case EditionState.DEPARTURE:
                        maze.setStartPoint(cellX, cellY);
                        parentWindow.setEditionState(EditionState.WALL);
                        break;
                    case EditionState.ARRIVAL:
                        maze.setEndPoint(cellX, cellY);
                        parentWindow.setEditionState(EditionState.WALL);
                        break;
                    case EditionState.WALL:
                        if(maze.getCell(cellX, cellY) != maze.getStartPoint() && maze.getCell(cellX, cellY) != maze.getEndPoint())
                            maze.setCell(cellX, cellY, new WBox(cellX, cellY));
                        break;
                }
                break;
            case 2:
                break;
            case 3:
                if(maze.getCell(cellX, cellY) != maze.getStartPoint() && maze.getCell(cellX, cellY) != maze.getEndPoint())
                    maze.setCell(cellX, cellY, new EBox(cellX, cellY));
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    public void updateGridColors() {
        for(int i = 0; i < nbCellsX; i++) {
            for(int j = 0; j < nbCellsY; j++) {
                Color originalColor = CellPanel.getColorFromLabel(maze.getCell(i, j).getLabel());
                setCellColor(i, j, originalColor);
            }
        }
    }
}
