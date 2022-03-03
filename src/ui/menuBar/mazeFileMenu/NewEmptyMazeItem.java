package ui.menuBar.mazeFileMenu;

import ui.ControlWindow;

import javax.swing.*;

public class NewEmptyMazeItem extends JMenuItem {
    public NewEmptyMazeItem(ControlWindow parentWindow) {
        super("New empty maze");
        addActionListener(e -> parentWindow.initEmptyMaze());
    }
}
