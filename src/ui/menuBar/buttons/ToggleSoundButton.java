package ui.menuBar.buttons;

import sound.MakeSound;

import javax.swing.*;

public class ToggleSoundButton extends JButton {
    public ToggleSoundButton() {
        super("Toggle Sound");
        addActionListener(e -> MakeSound.toggleSilence());
    }
}
