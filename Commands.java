import java.util.Scanner;

public class Commands {
   private final Scanner scanner = new Scanner(System.in);
   public static String CurDir = System.getProperty("user.dir");
    MV mv = new MV(); // Move command
    CD cd = new CD(); // Change directory command
    Append appender = new Append(); // Append command
    PWD pwd = new PWD(); // pwd command
    private static final ListDir  lsCommand = new ListDir() ;
    public void Help() {
        System.out.println("Available Commands:");
        System.out.println("1. pwd - Print the current working directory.");
        System.out.println("2. cd <directory> - Change the current directory to <directory>.");
        System.out.println("   Special Cases: Use '..' to go up one directory and '~' for home directory.");
        System.out.println("3. ls - List files and directories in the current directory.");
        System.out.println("4. ls -a - List all files, including hidden files.");
        System.out.println("5. ls -r - List files and directories in reverse order.");
        System.out.println("6. mkdir <directory> - Create a new directory with the specified name.");
        System.out.println("7. rmdir <directory> - Remove an empty directory.");
        System.out.println("8. touch <file> - Create a new empty file or update the timestamp of an existing file.");
        System.out.println("9. mv <source> <destination> - Move (or rename) a file or directory from <source> to <destination>.");
        System.out.println("10. rm <file> - Remove a file.");
        System.out.println("11. cat <file> - Display the contents of a file.");
        System.out.println("12. > <file> - Redirect output to a file, overwriting the file if it exists.");
        System.out.println("13. >> <file> - Redirect output to a file, appending to the file if it exists.");
        System.out.println("14. <command1> | <command2> - Pipe the output of <command1> as input to <command2>.");
    }
    public void run() {
        while (true) {
            System.out.print(CurDir + " > "); // Display the current directory
            String commandLine = scanner.nextLine().trim();
            if (commandLine.contains(">>")) {
                String[] parts = commandLine.split(">>", 2);
                String command = parts[0].trim();
                String filePath = parts[1].trim();
                // Handle "ls" command
                if (command.equals("ls")) {
                    String output = lsCommand.listCurrentDirectory();
                    appender.appendToFile(filePath, output); // Append output to the specified file
                } else {
                    System.out.println("Unsupported command: " + command);
                }
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
