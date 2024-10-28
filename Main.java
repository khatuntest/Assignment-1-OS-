import com.sun.net.httpserver.Authenticator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print(SystemProperties.currentDirectory + "> ");
            String line = scanner.nextLine();
            CommandParser parser = new CommandParser(line);
            try {
                if(parser.command.equals("ls")) {
                    ListCommand listCommand = new ListCommand();
                    if(parser.commandArgumants.isEmpty()) {
                        ArrayList<String> files = listCommand.NormalListCommand();
                        for(String file : files) {
                            System.out.println(file);
                        }
                    }
                }
                else if(parser.command.equals("mkdir")) {
                     if(parser.commandArgumants.isEmpty()) {
                         StatusReporter.ErrorMessage("Error: Directory Isn't Provided");
                         continue;
                     }
                     MakeDirectoryCommand makeDirectoryCommand = new MakeDirectoryCommand();
                     for(String newDirectory: parser.commandArgumants) {
                         if(makeDirectoryCommand.MakeDirectory(newDirectory)) {
                             StatusReporter.SuccessMessage(newDirectory + "Created Successfully");
                         }
                         else {
                             break;
                         }
                     }

                }
                else if(parser.command.equals("rm")) {
                     // Means this must be one file
                    RemoveFilesCommand removeFilesCommand = new RemoveFilesCommand();
                    if(parser.commandArgumants.size() == 1) {
                         String filePath = SystemProperties.currentDirectory + File.separator + parser.commandArgumants.get(0);
                         Path path = Paths.get(filePath);
                        if (!Files.exists(path)) {
                            StatusReporter.ErrorMessage("Error: The specified directory does not exist.");
                            continue;
                        }
                         if(Files.isDirectory(path)) {
                             StatusReporter.ErrorMessage("Error: Cannot Remove a Directory");
                             StatusReporter.ErrorMessage("HINT: Use rm -r");
                             continue;
                         }
                         removeFilesCommand.RemoveFile(new File(filePath));
                    }
                    else if(parser.commandArgumants.size() == 2) {
                        String filePath = SystemProperties.currentDirectory + File.separator + parser.commandArgumants.get(1);
                        Path path = Paths.get(filePath);
                        if (!Files.exists(path)) {
                            StatusReporter.ErrorMessage("Error: The specified directory does not exist.");
                            continue;
                        }

                        if(parser.commandArgumants.get(0).equals("-r")) {
                            removeFilesCommand.RemoveFileRecursively(new File(filePath));
                        }
                        else {
                            StatusReporter.ErrorMessage("Error: Invalid Command");
                        }
                    }
                    else {
                        StatusReporter.ErrorMessage("Error: Invalid Command");
                    }
                }
            }
            catch(Exception e) {
               StatusReporter.ErrorMessage(e.getMessage());
            }

        }
    }
}