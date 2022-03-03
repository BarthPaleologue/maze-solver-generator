package ui;

import maze.MazeInterface;
import sound.MakeSound;
import settings.SoundTypes;
import ui.vue.CellVuePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ControlPanel extends JPanel implements MouseListener {
    final ControlWindow parentWindow;
    public ControlPanel(ControlWindow parentWindow) {
        this.parentWindow = parentWindow;
        setLayout(new BorderLayout());
        addMouseListener(this);
    }
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component cc = SwingUtilities.getDeepestComponentAt(e.getComponent(), e.getX(), e.getY());
        if(cc == this || cc.getParent() == this) return; // si on click sur le MazePanel ou le ControlPanel => on a pas clické sur une case

        MazeInterface maze = parentWindow.getMaze();

        // le cast est faisable car on ne peut clicker que sur des CellPanel dans le MazePanel
        CellVuePanel clickedCell = (CellVuePanel) cc;

        int cellX = clickedCell.getGridX();
        int cellY = clickedCell.getGridY();

        //// 1 = Left Click ; 2 = Middle Click ; 3 = Right Click
        switch(e.getButton()) {
            case 1:
                switch (parentWindow.getEditionState()) {
                    case EditionState.DEPARTURE:
                        maze.setStartPoint(cellX, cellY);
                        parentWindow.setEditionState(EditionState.WALL);
                        MakeSound.play(SoundTypes.POKE_SOUND);
                        break;
                    case EditionState.ARRIVAL:
                        maze.setEndPoint(cellX, cellY);
                        parentWindow.setEditionState(EditionState.WALL);
                        MakeSound.play(SoundTypes.POKE_SOUND);
                        break;
                    case EditionState.WALL:
                        maze.placeWallAt(cellX, cellY);
                        MakeSound.play(SoundTypes.POKE_SOUND);
                        break;
                }
                break;
            case 2:
                break;
            case 3:
                maze.placeEmptyAt(cellX, cellY);
                MakeSound.play(SoundTypes.REMOVE_SOUND);
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
}
