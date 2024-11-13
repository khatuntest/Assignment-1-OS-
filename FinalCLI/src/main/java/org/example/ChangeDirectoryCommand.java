package org.example;

import java.io.File;

public class ChangeDirectoryCommand {

    private String currentDirection;
    public ChangeDirectoryCommand(){
        currentDirection = SystemProperties.currentDirectory;
    }
    public void changeDir(String path){
        File newDirection;

        if (path.equals("..")) { // Move up one directory
            newDirection = new File(currentDirection).getParentFile();
        } else if (path.equals("~")) { // Go to home directory
            newDirection = new File(System.getProperty("user.home"));
        } else { // change the path
            newDirection = new File(path);
            if (!newDirection.isAbsolute()) {
                newDirection = new File(currentDirection, path);
            }
        }
        if (newDirection != null && newDirection.exists() && newDirection.isDirectory()) {
            currentDirection = newDirection.getAbsolutePath();
            System.out.println( currentDirection);
        } else {
            System.out.println("Error: The system cannot find the path specified.");
        }
        SystemProperties.currentDirectory =  currentDirection;
    }
    public String getCurrentDirection() {
        return currentDirection;
    }
}
