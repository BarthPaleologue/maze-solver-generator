package ui.menuBar.mazeFileMenu;

import ui.ControlWindow;

import javax.swing.*;

public class NewRandomPrimMazeItem extends JMenuItem {
    public NewRandomPrimMazeItem(ControlWindow parentWindow) {
        super("New random maze (Prim)");
        addActionListener(e -> parentWindow.initRandomPrimMaze());
    }
}
