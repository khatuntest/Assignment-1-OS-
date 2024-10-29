import org.OS.command;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.OS.PWD;

public class PWDTest {

    private PWD pwd;

    @BeforeEach
    void setUp() {
        pwd = new PWD();
        // Set command.CurDir to simulate an environment
        command.CurDir = "C:\\Users\\Tag\\IdeaProjects\\Commands";
    }

    @Test
    void testPwdOutput() {
        // Capture the output of pwd.Pwd() using a PrintStream
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        // Call the Pwd function
        pwd.Pwd();

        // Verify the printed output
        assertEquals("C:\\Users\\Tag\\IdeaProjects\\Commands", outContent.toString().trim());

        // Restore System.out
        System.setOut(System.out);
    }
}
