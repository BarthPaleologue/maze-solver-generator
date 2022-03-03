package ui.menuBar.mazeFileMenu;

import settings.Path;
import ui.ControlWindow;

import javax.swing.*;
import java.io.File;

public class SaveMazeFileItem extends JMenuItem {
    public SaveMazeFileItem(ControlWindow parentWindow) {
        super("Save maze to file");
        addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(Path.MAZE_DIR);
            chooser.setSelectedFile(new File(Path.DEFAULT_FILENAME));
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                // Computes relative path using dark magic (kidding I'm just subtracting strings)
                String fileAbsPath = chooser.getSelectedFile().getAbsolutePath();
                String dirAbsPath = new File(".").getAbsolutePath();
                String fileRelativePath = fileAbsPath.substring(dirAbsPath.length() - 1);
                System.out.println("You chose to save to this file: " + fileAbsPath + " " + fileRelativePath);

                parentWindow.saveMaze(fileRelativePath);
            }
        });
    }
}
