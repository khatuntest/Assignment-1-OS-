package org.example;

import java.io.File;
import java.io.IOException;

public class MakeDirectoryCommand {

    public boolean MakeDirectory(String directoryName) throws IOException {

        // The name of the new directory to be created
        String currentDirectory = SystemProperties.currentDirectory;
        // Full Path (current directory + new Directory Name)
        String directoryPath = currentDirectory + File.separator + directoryName;
        // create the new file
        File directory = new File(directoryPath);
        // Try to insert it in the directory
        boolean directoryCreatedSuccessfully = directory.mkdirs();
        if(!directoryCreatedSuccessfully) {
            StatusReporter.ErrorMessage("Error: Cannot create directory. " +
                    "\nPossible reasons:" +
                    "\n[1] The directory already exists. " +
                    "\n[2] The path is invalid or inaccessible due to permissions.");
            return false;
        }
        return true;
    }
}
