package org.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class catCommand {
    public void cat(){
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        System.out.println(in);
    }
    public void cat(String fileName){
        Path filePath =  Paths.get(SystemProperties.currentDirectory).resolve(fileName);
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines){
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}
