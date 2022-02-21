package ui;

import maze.GraphInterface;

import javax.swing.*;
import java.io.File;

public class Menu extends JMenuBar {
    public Menu(GraphInterface maze) {
        /*JMenu fileMenu = new JMenu("Maze File");
        JButton departureButton = new JButton("Set departure");
        JButton arrivalButton = new JButton("Set Arrival");
        add(fileMenu);
        add(departureButton);
        add(arrivalButton);

        JMenuItem newEmptyItem = new JMenuItem("New empty maze");
        JMenuItem openFileItem = new JMenuItem("Open maze file");
        JMenuItem saveFileItem = new JMenuItem("Save maze to file");
        fileMenu.add(newEmptyItem);
        fileMenu.add(openFileItem);
        fileMenu.add(saveFileItem);

        newEmptyItem.addActionListener(e -> {
            int newWidth = Integer.parseInt((String)JOptionPane.showInputDialog(
                    this,
                    "Maze Width :",
                    "Initialization Phase",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "10"));

            int newHeight = Integer.parseInt((String) JOptionPane.showInputDialog(
                    this,
                    "Maze Height :",
                    "Initialization Phase",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "10"));
            maze.initEmpty(newWidth, newHeight);
            initMazeUI();
        });

        departureButton.addActionListener(e -> mazePanel.setDepartureMode(true));

        arrivalButton.addActionListener(e -> mazePanel.setArrivalMode(true));

        saveFileItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser("./data");
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String fileAbsPath = chooser.getSelectedFile().getAbsolutePath();
                String dirAbsPath = new File(".").getAbsolutePath();
                int len = dirAbsPath.length();
                String fileRelativePath = fileAbsPath.substring(len - 1);
                System.out.println("You chose to save to this file: " + fileAbsPath + " " + fileRelativePath);

                maze.saveToTextFile(fileRelativePath);
            }
        });

        openFileItem.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser("./data");
            int returnVal = chooser.showOpenDialog(this);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String fileAbsPath = chooser.getSelectedFile().getAbsolutePath();
                String dirAbsPath = new File(".").getAbsolutePath();
                int len = dirAbsPath.length();
                String fileRelativePath = fileAbsPath.substring(len-1);
                System.out.println("You chose to open this file: " +
                        fileAbsPath + " " + fileRelativePath);

                maze.initFromTextFile(fileRelativePath);
                if(maze.getWidth() > 0 && maze.getHeight() > 0) {
                    initMazeUI();
                }

            }
        });*/
    }
}
