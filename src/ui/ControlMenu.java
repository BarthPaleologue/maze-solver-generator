package ui;

import javax.swing.*;
import java.io.File;

public class ControlMenu extends JMenuBar {

    public ControlMenu(ControlWindow parentWindow) {

        JMenu fileMenu = new JMenu("Maze File");
        JButton departureButton = new JButton("Set departure");
        JButton arrivalButton = new JButton("Set Arrival");
        this.add(fileMenu);
        this.add(departureButton);
        this.add(arrivalButton);

        JMenuItem newEmptyMazeItem = new JMenuItem("New empty maze");
        JMenuItem newRandomPrimMazeItem = new JMenuItem("New random maze (Prim)");
        JMenuItem openMazeFileItem = new JMenuItem("Open maze file");
        JMenuItem saveMazeFileItem = new JMenuItem("Save maze to file");

        fileMenu.add(newEmptyMazeItem);
        fileMenu.add(newRandomPrimMazeItem);
        fileMenu.add(openMazeFileItem);
        fileMenu.add(saveMazeFileItem);

        newEmptyMazeItem.addActionListener(e -> parentWindow.initEmptyMaze());

        newRandomPrimMazeItem.addActionListener(e -> parentWindow.initRandomPrimMaze());

        departureButton.addActionListener(e -> parentWindow.setEditionState(EditionState.DEPARTURE));
        arrivalButton.addActionListener(e -> parentWindow.setEditionState(EditionState.ARRIVAL));

        saveMazeFileItem.addActionListener(e -> {
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

        openMazeFileItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(Path.MAZE_DIR);
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String fileAbsPath = chooser.getSelectedFile().getAbsolutePath();
                String dirAbsPath = new File(".").getAbsolutePath();
                String fileRelativePath = fileAbsPath.substring(dirAbsPath.length() - 1);
                if(fileRelativePath.equals("data/ussr.txt")) System.out.println("WELCOME COMRADE");
                System.out.println("You chose to open this file: " +
                        fileAbsPath + " " + fileRelativePath);

                parentWindow.loadMaze(fileRelativePath);
            }
        });
    }
}
