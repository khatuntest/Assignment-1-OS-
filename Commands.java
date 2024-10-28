import java.util.Scanner;

public class Commands {
   private final Scanner scanner = new Scanner(System.in);
   public static String CurDir = System.getProperty("user.dir");
    MV mv = new MV(); // Move command
    CD cd = new CD(); // Change directory command
    Append appender = new Append(); // Append command
    PWD pwd = new PWD(); // pwd command
    public void run() {
        while (true) {
            System.out.print(CurDir + " > "); // Display the current directory
            String commandLine = scanner.nextLine().trim();
            if (commandLine.contains(">>")) {
                String[] parts = commandLine.split(">>");
                String filePath = parts[0].trim();
                String content = parts[1].trim();

                appender.appendToFile(filePath, content);
            }
            else {
                String[] parts = commandLine.split(" ");

                if (parts.length == 0) {
                    System.out.println("Command not recognized.");
                    continue; // Skip to the next iteration if no command is entered
                }

                String command = parts[0].trim();

                switch (command) {
                    case "mv":
                        if (parts.length != 3) {
                            System.out.println("Usage: mv <source_path> <destination_path>");
                        } else {
                            String sourcePath = parts[1];
                            String destinationPath = parts[2];
                            mv.Mv(sourcePath, destinationPath);
                        }
                        break;

                    case "cd":
                        if (parts.length != 2) {
                            System.out.println("Usage: cd <directory>");
                        } else {
                            cd.changeDir(parts[1]);
                            System.out.println("Changed directory to: " + cd.getCurrentDirection());
                        }
                        break;
                    case "pwd":
                        pwd.Pwd();
                        break;

                    case "exit":
                        System.out.println("Exiting...");
                        scanner.close();
                        return;

                    default:
                        System.out.println("Command not recognized.");
                }
            }
        }
    }
}
