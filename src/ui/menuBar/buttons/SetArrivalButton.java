package ui.menuBar.buttons;

import ui.ControlWindow;
import ui.EditionState;

import javax.swing.*;

public class SetArrivalButton extends JButton {
    public SetArrivalButton(ControlWindow parentWindow) {
        super("Set Arrival");
        addActionListener(e -> parentWindow.setEditionState(EditionState.ARRIVAL));
    }
}
