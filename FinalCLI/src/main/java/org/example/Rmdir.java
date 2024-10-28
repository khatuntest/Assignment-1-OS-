package org.example;

import java.io.*;
import java.nio.file.*;


public class Rmdir {
    public void removeDir(String dirName){
        Path dirPath = Paths.get("").toAbsolutePath().resolve(dirName);
        if(!Files.exists(dirPath)){
            StatusReporter.ErrorMessage("Error: Directory does not exist - " + dirName);
            return;
        }
        try{
            Files.delete(dirPath);
            StatusReporter.SuccessMessage("Directory removed: " + dirName);
        }catch (DirectoryNotEmptyException e) {
            StatusReporter.ErrorMessage("Error: Directory is not empty - " + dirName);
        } catch (NoSuchFileException e) {
            StatusReporter.ErrorMessage("Error: Directory does not exist - " + dirName);
        } catch (IOException e) {
            StatusReporter.ErrorMessage("Error occurred while removing directory: " + e.getMessage());
        }
    }

}