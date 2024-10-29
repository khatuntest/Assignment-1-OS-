import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import org.OS.MV;

public class MVTest {

    private MV mv;
    private File sourceFile;
    private File destinationFile;

    @BeforeEach
    void setUp() throws IOException {
        mv = new MV();
        sourceFile = new File("sourceFile.txt");
        destinationFile = new File("destinationFile.txt");

        if (sourceFile.exists()) sourceFile.delete();
        if (destinationFile.exists()) destinationFile.delete();

        sourceFile.createNewFile(); // Create the source file for testing
    }

    @Test
    void testMoveFile() throws IOException {
        // Move the source file to the destination
        mv.mv(sourceFile.getPath(), destinationFile.getPath());

        // Verify that source file no longer exists and destination file exists
        assertFalse(sourceFile.exists());
        assertTrue(destinationFile.exists());

        System.out.println("Moved file to: " + destinationFile.getPath());
    }
}
