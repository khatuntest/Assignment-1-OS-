import java.io.FileWriter;
import java.io.IOException;

public class redirect {
    public void redirectToFile(String fileName, String content) throws IOException {
        // Open the file in write mode which will overwrite existing content
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("Content written to " + fileName);
        }
    }
}
