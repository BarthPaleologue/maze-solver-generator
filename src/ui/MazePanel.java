package ui;

import maze.EBox;
import maze.GraphInterface;
import maze.Maze;
import maze.WBox;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MazePanel extends JPanel implements MouseListener {
    private int nbCellsX;
    private int nbCellsY;

    private GraphInterface maze = null;

    private boolean departureMode = false;
    private boolean arrivalMode = false;

    private CellPanel[][] UIGrid = null;

    private ArrayList<ChangeListener> listeners;

    public MazePanel(int nbCellsX, int nbCellsY) {
        super();

        this.nbCellsX = nbCellsX;
        this.nbCellsY = nbCellsY;

        addMouseListener(this);

        listeners = new ArrayList<>();
    }

    public void initMazeUI(Maze maze) {
        removeAll(); // remove components of last maze (necessary when changing maze size)
        setLayout(new GridLayout(maze.getWidth(), maze.getHeight()));
        nbCellsX = maze.getWidth();
        nbCellsY = maze.getHeight();

        this.maze = maze;

        UIGrid = new CellPanel[nbCellsX][nbCellsY];

        for(int i = 0; i < nbCellsX; i++) {
            for(int j = 0; j < nbCellsY; j++) {
                CellPanel cell = new CellPanel(maze, i, j);
                cell.setBackground(CellPanel.getColorFromLabel(maze.getCell(i,j).getLabel()));
                add(cell);

                UIGrid[i][j] = cell;
            }
        }
        stateChanges();
    }

    public void setDepartureMode(boolean flag) {
        if(flag && arrivalMode) arrivalMode = false;
        departureMode = flag;
    }
    public void setArrivalMode(boolean flag) {
        if(flag && departureMode) departureMode = false;
        arrivalMode = flag;
    }

    public void setCellColor(int x, int y, Color color) {
        UIGrid[x][y].setBackground(color);
    }

    public void reset() {
        for(int i = 0; i < nbCellsX; i++) {
            for(int j = 0; j < nbCellsY; j++) {
                Color originalColor = CellPanel.getColorFromLabel(maze.getCell(i, j).getLabel());
                UIGrid[i][j].setBackground(originalColor);
            }
        }
        stateChanges();
    }

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
    }

    public void stateChanges() {
        ChangeEvent e = new ChangeEvent(this);
        for(ChangeListener listener: listeners) {
            listener.stateChanged(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO: j'ai inversé x et y oups
        int cellX = e.getY() * nbCellsX / getHeight();
        int cellY = e.getX() * nbCellsY / getWidth();

        //// 1 = LC ; 2 = MC ; 3 = RC
        switch(e.getButton()) {
            case 1:
                if(departureMode) {
                    maze.setStartPoint(cellX, cellY);
                    departureMode = false;
                } else if(arrivalMode) {
                    maze.setEndPoint(cellX, cellY);
                    arrivalMode = false;
                } else {
                    maze.setCell(cellX, cellY, new WBox(cellX, cellY));
                }
                break;
            case 2:
                break;
            case 3:
                if(maze.getCell(cellX, cellY) != maze.getStartPoint() && maze.getCell(cellX, cellY) != maze.getEndPoint())
                    maze.setCell(cellX, cellY, new EBox(cellX, cellY));
                break;
            default:
        }
        reset();
        stateChanges();
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
}
