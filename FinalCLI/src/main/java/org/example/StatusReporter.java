package org.example;

public class StatusReporter {
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String RESET = "\033[0m";

    public static void ErrorMessage(String message) {
        System.out.print(RED);
        System.out.println(message);
        System.out.print(RESET);
    }

    public static void SuccessMessage(String message) {
        System.out.print(GREEN);
        System.out.println(message);
        System.out.print(RESET);
    }
}

