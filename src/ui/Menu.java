package ui;

import maze.Maze;

import javax.swing.*;
import java.io.File;

public class Menu extends JMenuBar {

    public Menu(Window parentWindow) {

        JMenu fileMenu = new JMenu("Maze File");
        JButton departureButton = new JButton("Set departure");
        JButton arrivalButton = new JButton("Set Arrival");
        this.add(fileMenu);
        this.add(departureButton);
        this.add(arrivalButton);

        JMenuItem newEmptyMazeItem = new JMenuItem("New empty maze");
        JMenuItem openMazeFileItem = new JMenuItem("Open maze file");
        JMenuItem saveMazeFileItem = new JMenuItem("Save maze to file");

        fileMenu.add(newEmptyMazeItem);
        fileMenu.add(openMazeFileItem);
        fileMenu.add(saveMazeFileItem);

        newEmptyMazeItem.addActionListener(e -> {
            int newWidth = parentWindow.promptIntFromUser("New Maze","Enter new maze width :", Maze.DEFAULT_WIDTH);
            while (newWidth <= 0) newWidth = parentWindow.promptIntFromUser("New Maze","WIDTH MUST BE POSITIVE ! Try again : ", Maze.DEFAULT_WIDTH);

            int newHeight = parentWindow.promptIntFromUser("New Maze","Enter new maze height : ", Maze.DEFAULT_HEIGHT);
            while (newHeight <= 0) newHeight = parentWindow.promptIntFromUser("New Maze","HEIGHT MUST BE POSITIVE ! Try again : ", Maze.DEFAULT_WIDTH);

            parentWindow.initEmptyMaze(newWidth, newHeight);
        });

        departureButton.addActionListener(e -> parentWindow.setEditionState(EditionState.DEPARTURE));
        arrivalButton.addActionListener(e -> parentWindow.setEditionState(EditionState.ARRIVAL));

        saveMazeFileItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(Path.MAZE_DIR);
            chooser.setSelectedFile(new File(Path.DEFAULT_FILENAME));
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                // Computes relative path using dark magic (kidding I'm subtracting string)
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
