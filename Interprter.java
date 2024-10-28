import java.io.IOException;
import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Interprter {



    public static void ListCommand() throws IOException {
        Path dir = Path.of(SystemProperties.currentDirectory);
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        for (Path file : stream) {
            String fileName = file.getFileName().toString();
            if(fileName.charAt(0) != '.') {
                System.out.println(fileName);
            }
        }
    }
    public static void MakeDirectory(String directoryName) throws IOException {

        // The name of the new directory to be created
        String currentDirectory = SystemProperties.currentDirectory;
        // Full Path (current directory + new Directory Name)
        String directoryPath = currentDirectory + File.separator + directoryName;
        // create the new file
        File directory = new File(directoryPath);
        // Try to insert it in the directory
        boolean directoryCreatedSuccessfully = directory.mkdirs();
        if(directoryCreatedSuccessfully) {
            StatusReporter.SuccessMessage("Created Sucessfully");
        }
        else {
            StatusReporter.ErrorMessage("Error: Cannot create directory. " +
                    "\nPossible reasons:" +
                    "\n[1] The directory already exists. " +
                    "\n[2] The path is invalid or inaccessible due to permissions.");

        }
    }
    public static void RemoveFile(String fileName) throws IOException
    {
        String currentDirectory = SystemProperties.currentDirectory;
        String directoryPath = currentDirectory + File.separator + fileName;
        File file = new File(directoryPath);
        if(!file.exists() || (!file.delete())) {
            String errorMessage = "Failed to delete file\n";
            StatusReporter.ErrorMessage(errorMessage);
        }
        else {
            StatusReporter.SuccessMessage(fileName + " Removed Successfully");
        }
    }

    public static void RemoveFileRecursively(File fileToBeDeleted) throws IOException {
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

    public static void Pipe() {}


}
