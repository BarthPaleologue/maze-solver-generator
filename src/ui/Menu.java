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

        //TODO: exception pour les valeurs négatives

        newEmptyMazeItem.addActionListener(e -> {
            int newWidth = parentWindow.promptIntFromUser("New Maze","Enter new maze width :", Maze.DEFAULT_WIDTH);
            newWidth = Math.max(1, newWidth);

            int newHeight = parentWindow.promptIntFromUser("New Maze","Enter new maze height : ", Maze.DEFAULT_HEIGHT);
            newHeight = Math.max(1, newHeight);

            parentWindow.initEmptyMaze(newWidth, newHeight);
        });

        departureButton.addActionListener(e -> parentWindow.setEditionState(EditionState.DEPARTURE));
        arrivalButton.addActionListener(e -> parentWindow.setEditionState(EditionState.ARRIVAL));

        saveMazeFileItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser(Path.MAZE_DIR);
            chooser.setSelectedFile(new File(Path.DEFAULT_FILENAME));
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                // calcul du chemin relatif à l'aide de soustractions de chaines de caractères
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
