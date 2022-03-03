package ui.menuBar.mazeFileMenu;

import settings.Path;
import ui.ControlWindow;

import javax.swing.*;
import java.io.File;

public class OpenMazeFileItem extends JMenuItem {
    public OpenMazeFileItem(ControlWindow parentWindow) {
        super("Open maze file");
        addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(Path.MAZE_DIR);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String fileAbsPath = chooser.getSelectedFile().getAbsolutePath();
                String dirAbsPath = new File(".").getAbsolutePath();
                String fileRelativePath = "./" + fileAbsPath.substring(dirAbsPath.length() - 1);
                System.out.println("You chose to open this file: " +
                        fileAbsPath + " " + fileRelativePath);

                parentWindow.loadMaze(fileRelativePath);
            }
        });
    }
}
