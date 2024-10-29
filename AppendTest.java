import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import org.OS.Append;

public class AppendTest {

    private Append appender;
    private File testFile;

    @BeforeEach
    void setUp() throws IOException {
        appender = new Append();
        testFile = new File("testFile.txt");
        if (testFile.exists()) testFile.delete(); // Clean up any previous test runs
        testFile.createNewFile();
    }

    @Test
    void testAppendContentToFile() throws IOException {
        String content = "Hello, world!";

        // Append content to file
        appender.appendToFile(testFile.getPath(), content);

        // Verify the file contains the appended content
        String fileContent = new String(java.nio.file.Files.readAllBytes(testFile.toPath()));
        assertTrue(fileContent.contains(content));

        System.out.println("Appended content to file: " + fileContent);
    }
}

