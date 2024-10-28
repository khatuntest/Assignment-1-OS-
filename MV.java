import java.io.File;

public class MV {
    public void Mv(String source, String destination) {
        File sourceFile = new File(source);
        File destinationFile = new File(destination);

        // Check if source file exists
        if (!sourceFile.exists()) {
            System.out.println("Source file does not exist.");
            return;
        }

        // Check if the destination already exists
        if (destinationFile.exists()) {
            System.out.println("Destination file already exists. Operation cannot proceed.");
            return;
        }

        // Check if the operation is a rename
        boolean isRename = destinationFile.getParent().equals(sourceFile.getParent())
                && !destinationFile.getName().equals(sourceFile.getName());

        // Attempt to rename/move the file
        if (sourceFile.renameTo(destinationFile)) {
            if (isRename) {
                System.out.println("File renamed successfully to " + destination + "\n");
            } else {
                System.out.println("File moved successfully to " + destination + "\n");
            }
        } else {
            System.out.println("Failed to move/rename the file.\n");
        }
    }
}
