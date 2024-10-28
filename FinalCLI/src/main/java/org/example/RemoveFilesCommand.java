package org.example;

import java.io.File;
import java.io.IOException;

public class RemoveFilesCommand {

    public void RemoveFile(File fileToBeDeleted) throws IOException
    {
        if(!fileToBeDeleted.exists() || (!fileToBeDeleted.delete())) {
            String errorMessage = "Failed to delete file\n";
            StatusReporter.ErrorMessage(errorMessage);
        }
        else {
            StatusReporter.SuccessMessage("File Removed Successfully");
        }
    }

    public void RemoveFileRecursively(File fileToBeDeleted) throws IOException {
        if(fileToBeDeleted == null)
            return;
        for(File currentFile : fileToBeDeleted.listFiles()) {
            if(currentFile.isDirectory()) {
                RemoveFileRecursively(currentFile);
            }
            currentFile.delete();
        }
        fileToBeDeleted.delete();
    }
}
