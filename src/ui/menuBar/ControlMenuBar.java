package ui.menuBar;

import ui.ControlWindow;
import ui.menuBar.buttons.SetArrivalButton;
import ui.menuBar.buttons.SetDepartureButton;
import ui.menuBar.buttons.ToggleSoundButton;
import ui.menuBar.mazeFileMenu.MazeFileMenu;

import javax.swing.*;

public class ControlMenuBar extends JMenuBar {

    public ControlMenuBar(ControlWindow parentWindow) {
        MazeFileMenu fileMenu = new MazeFileMenu(parentWindow);
        add(fileMenu);

        add(new SetDepartureButton(parentWindow));
        add(new SetArrivalButton(parentWindow));
        add(new ToggleSoundButton());
    }
}
