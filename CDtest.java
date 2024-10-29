import org.OS.CD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CDtest {

    private CD cd; // Your CD command class

    @BeforeEach
    void setUp() {
        cd = new CD(); // Initialize CD object before each test
        cd.changeDir("C:\\Users\\Tag\\IdeaProjects\\Commands"); // Set initial directory
    }

    @Test
    void testChangeToParentDirectory() {
        // Simulate 'cd ..' to move to parent directory
        cd.changeDir(".."); // Assuming ".." is a valid way to move up one level
        assertEquals("C:\\Users\\Tag\\IdeaProjects", cd. getCurrentDirection()); // Verify current directory
        System.out.println("Changed directory to: " + cd.getCurrentDirection());
    }

    @Test
    void testChangeToSpecificDirectory() {
        // Simulate 'cd C:\\Users\\Tag\\IdeaProjects\\Commands' to move to a specific directory
        cd.changeDir("C:\\Users\\Tag\\IdeaProjects\\Commands"); // Change to specified path
        assertEquals("C:\\Users\\Tag\\IdeaProjects\\Commands", cd.getCurrentDirection()); // Verify current directory
        System.out.println("Changed directory to: " + cd.getCurrentDirection());
    }
}
