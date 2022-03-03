package ui.menuBar.mazeFileMenu;

import ui.ControlWindow;

import javax.swing.*;

public class MazeFileMenu extends JMenu {
    public MazeFileMenu(ControlWindow parentWindow) {
        super("Maze File");

        add(new NewEmptyMazeItem(parentWindow));
        add(new NewRandomPrimMazeItem(parentWindow));
        add(new OpenMazeFileItem(parentWindow));
        add(new SaveMazeFileItem(parentWindow));
    }
}
