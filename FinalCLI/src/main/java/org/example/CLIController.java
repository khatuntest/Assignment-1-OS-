package org.example;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CLIController {
      private ArrayList<String> HandleListCommand(String[] args) throws Exception {
          ListCommand ls = new ListCommand();
          if(args.length == 1) {
              return ls.NormalListCommand();
          }
          else if(args[1].equals("-r")){
              return ls.ReverseListCommand();
          }
          else if(args[1].equals("-a")){
              return ls.hiddenList();
          }
          else {
              StatusReporter.ErrorMessage("Error: Invalid Command");
          }
          return null;
      }
      private void HandleRemoveCommand(String[] args) throws Exception {
          RemoveFilesCommand removeFilesCommand = new RemoveFilesCommand();
          if(args.length == 2) {
              Path path = Paths.get(args[1]);
              String filePath = "";
              if(path.isAbsolute())
                  filePath = args[1];
              else
                  filePath = SystemProperties.currentDirectory + File.separator + args[1];

              if (!Files.exists(path)) {
                  StatusReporter.ErrorMessage("Error: The specified directory does not exist.");
                  return;
              }
              if(Files.isDirectory(path)) {
                  StatusReporter.ErrorMessage("Error: Cannot Remove a Directory");
                  StatusReporter.ErrorMessage("HINT: Use rm -r");
                  return;
              }
              removeFilesCommand.RemoveFile(new File(filePath));
          }
          else if(args.length == 3) {
              String filePath = SystemProperties.currentDirectory + File.separator + args[2];
              Path path = Paths.get(filePath);
              if (!Files.exists(path)) {
                  StatusReporter.ErrorMessage("Error: The specified directory does not exist.");
                  return;
              }

              if(args[1].equals("-r")) {
                  removeFilesCommand.RemoveFileRecursively(new File(filePath));
                  StatusReporter.SuccessMessage("Removed Successfully");
              }
              else {
                  StatusReporter.ErrorMessage("Error: Invalid Command");
              }
          }
          else {
              StatusReporter.ErrorMessage("Error: Invalid Command");
          }
      }
      private void HandleMakeDirectoryCommand(String[] args) throws Exception {
          if(args.length == 1) {
              StatusReporter.ErrorMessage("Error: Directory Isn't Provided");
              return;
          }
          MakeDirectoryCommand makeDirectoryCommand = new MakeDirectoryCommand();
          for(int i = 1 ;i < args.length;i++) {
              if(makeDirectoryCommand.MakeDirectory(args[i])) {
                  StatusReporter.SuccessMessage(args[i] + "Created Successfully");
              }
              else {
                  break;
              }
          }
      }
      private void HandleRemoveDirectoryCommand(String[] args) throws Exception {

      }
      public void HelpCommand() {
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
      private ArrayList<String> HandleCatCommand(String[] args) throws Exception {
          catCommand cat = new catCommand();
          ArrayList<String> res = null;
          if(args.length < 2)
              StatusReporter.ErrorMessage("not enough parameters");
          for (int i = 1 ; i < args.length ;i ++){
             res = cat.cat(args[i]);
          }
          return res;
      }
      private void HandleChangeDirectoryCommand(String[] parts) {
          if (parts.length != 2) {
              StatusReporter.ErrorMessage("Usage: cd <directory>");
          } else {
              ChangeDirectoryCommand cd = new ChangeDirectoryCommand();
              cd.changeDir(parts[1]);
              System.out.println("Changed directory to: " + cd.getCurrentDirection());
          }
      }
      private void HandleMoveCommand(String[] parts) throws Exception {
          if (parts.length != 3) {
              StatusReporter.ErrorMessage("Usage: mv <source_path> <destination_path>");
          }
         else{
             MV mv = new MV();
             String sourcePath = parts[1];
             String destinationPath = parts[2];
             mv.mv(sourcePath, destinationPath);
         }
      }
      private void HandlePipeCommand(String input) throws Exception {
          // split the input based on the pipe operator
          String[] commands = input.split("\\|");
          ArrayList<String> prevOutput = null;
          for(int i = 0;i < commands.length;i++) {
              commands[i] = commands[i].trim();
              prevOutput = ExecuteCommandsWithPipe(commands[i], prevOutput);
          }
          if(prevOutput == null) {
              StatusReporter.ErrorMessage("Error: Check All Pipe commands are valid");
              return;
          }
          for(String out: prevOutput) {
              System.out.println(out);
          }
      }
      public ArrayList<String> Sort(ArrayList<String> list) {
          Collections.sort(list);
          return list;
      }
      public ArrayList<String> Unique(ArrayList<String> list) {
         return new ArrayList<>(new HashSet<>(list));
      }
      public ArrayList<String> Grep(ArrayList<String> list, String pattern) {
          ArrayList<String> result = new ArrayList<>();
          for (String line : list) {
             if (line.contains(pattern)) {
                result.add(line);
             }
         }
         return result;
      }
      private ArrayList<String> ExecuteCommandsWithPipe(String line, ArrayList<String> oldOutput) throws Exception {
          String[] args = line.split(" ");
          ArrayList<String> result = null;
          switch (args[0]) {
              case "ls":
                  result = HandleListCommand(args);
                  break;
              case "cat":
                  result = HandleCatCommand(args);
                  break;
              case "sort":
                  result = Sort(oldOutput);
                  break;
              case "unique":
                  result = Unique(oldOutput);
                  break;
              case "grep":
                  if(args.length != 2)
                      return null;
                  result = Grep(oldOutput, args[1]);
                  break;
              default:
                  StatusReporter.ErrorMessage("Error: Invalid Command");
                  break;
          }
          return result;
      }


      public void Run() throws Exception {
          Scanner scanner = new Scanner(System.in);
          boolean RUN = true;

          while(RUN) {
              System.out.print(SystemProperties.currentDirectory + ">");
              String line  = scanner.nextLine();
              String[] args = line.split(" ");
              if(line.contains("|")) {
                  HandlePipeCommand(line);
                  continue;
              }
              switch (args[0]) {
                  case "ls":
                      ArrayList<String> result = HandleListCommand(args);
                      for(String res: result) {
                          System.out.println(res);
                      }
                      break;
                  case "mkdir":
                      HandleMakeDirectoryCommand(args);
                      break;
                  case "rm":
                      HandleRemoveCommand(args);
                      break;
                  case "rmdir":
                      HandleRemoveDirectoryCommand(args);
                      break;
                  case "cat":
                      HandleCatCommand(args);
                      break;
                  case "help":
                      HelpCommand();
                      break;
                  case "pwd":
                      PwdCommand pwd = new PwdCommand();
                      pwd.Pwd();
                      break;
                  case "mv":
                      HandleMoveCommand(args);
                      break;
                  case "cd":
                      HandleChangeDirectoryCommand(args);
                      break;
                  case "exit":
                      RUN  = false;
                      break;
                  default:
                      StatusReporter.ErrorMessage("Error: Invalid Command");
                      break;
              }


          }
      }

}
