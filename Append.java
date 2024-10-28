import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Append {
    public void appendToFile(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
            System.out.println("Output appended to " + filePath);
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
