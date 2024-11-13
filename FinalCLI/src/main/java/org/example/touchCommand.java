import java.io.File;
import java.io.IOException;

public class touchCommand {
    public void touch(String filename) throws IOException {
        String dir = System.getProperty("user.dir");
        File file = new File(dir,filename);
        if (file.exists()) {
            // Update the last modified timestamp
            boolean success = file.setLastModified(System.currentTimeMillis());
            if (!success) {
                System.out.println("Failed to update last modified time for " + filename);
            } else {
                System.out.println("Updated last modified time for " + filename);
            }
        } else {
            // Create a new file
            boolean created = file.createNewFile();
            if (created) {
                System.out.println("File created: " + filename);
            } else {
                System.out.println("Failed to create file: " + filename);
            }
        }
    }
}

