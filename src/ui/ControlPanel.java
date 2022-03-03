package ui;

import maze.MazeInterface;
import settings.Labels;
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
        if(cc == this || cc.getParent() == this) return; // si on click sur le MazeVuePanel ou le ControlPanel => on a pas clické sur une case

        // le cast est faisable car on ne peut clicker que sur des CellPanel dans le MazeVuePanel
        CellVuePanel clickedCell = (CellVuePanel) cc;

        int x = clickedCell.getGridX();
        int y = clickedCell.getGridY();

        MazeInterface maze = parentWindow.getMaze();

        //// 1 = Left Click ; 2 = Middle Click ; 3 = Right Click
        switch(e.getButton()) {
            case 1:
                switch (parentWindow.getEditionState()) {
                    case EditionState.DEPARTURE:
                        maze.setStartPointAt(x, y);
                        parentWindow.setEditionState(EditionState.WALL);
                        MakeSound.play(SoundTypes.POKE_SOUND);
                        break;
                    case EditionState.ARRIVAL:
                        maze.setEndPointAt(x, y);
                        parentWindow.setEditionState(EditionState.WALL);
                        MakeSound.play(SoundTypes.POKE_SOUND);
                        break;
                    case EditionState.WALL:
                        maze.toggleWallAt(x, y);
                        if(maze.getLabelAt(x, y) == Labels.WALL)
                            MakeSound.play(SoundTypes.POKE_SOUND);
                        else MakeSound.play(SoundTypes.REMOVE_SOUND);
                        break;
                }
                break;
            case 2:
                break;
            case 3:
                maze.placeEmptyAt(x, y);
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
