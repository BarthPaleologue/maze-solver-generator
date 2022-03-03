package ui.menuBar.buttons;

import ui.ControlWindow;
import ui.EditionState;

import javax.swing.*;

public class SetDepartureButton extends JButton {
    public SetDepartureButton(ControlWindow parentWindow) {
        super("Set departure");
        addActionListener(e -> parentWindow.setEditionState(EditionState.DEPARTURE));
    }
}
