package org.example;


public class Main {
    public static void main(String[] args) {
       CLIController controller = new CLIController();
       try {
           controller.Run();
       }
       catch (Exception e) {
           StatusReporter.ErrorMessage(e.getMessage());
       }
    }
}