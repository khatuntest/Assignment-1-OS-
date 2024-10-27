package org.example;

import java.io.*;
import java.nio.file.*;


public class Rmdir {
    public void removeDir(String dirName){
        Path dirPath = Paths.get("").toAbsolutePath().resolve(dirName);
        if(!Files.exists(dirPath)){
            System.out.println("Error: Directory does not exist - " + dirName);
            return;
        }
        try{
            Files.delete(dirPath);
            System.out.println("Directory removed: " + dirName);
        }catch (DirectoryNotEmptyException e) {
            System.out.println("Error: Directory is not empty - " + dirName);
        } catch (NoSuchFileException e) {
            System.out.println("Error: Directory does not exist - " + dirName);
        } catch (IOException e) {
            System.out.println("Error occurred while removing directory: " + e.getMessage());
        }
    }

}



//private void removeDirectory(String dirName) {
//    try {
//        Path dirPath = currentPath.resolve(dirName);
//        Files.delete(dirPath);
//        System.out.println("Directory removed: " + dirName);
//    } catch (IOException e) {
//        System.err.println("Error removing directory: " + e.getMessage());
//    }
//}
