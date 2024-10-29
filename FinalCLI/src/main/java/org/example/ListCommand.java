package org.example;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ListCommand {
    public ArrayList<String> NormalListCommand() throws Exception {
        Path dir = Path.of(SystemProperties.currentDirectory);
        ArrayList<String> list = new ArrayList<>();
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        for (Path file : stream) {
            String fileName = file.getFileName().toString();
            if(fileName.charAt(0) != '.') {
                list.add(fileName);
            }
        }
        return list;
    }

    public void ReverseListCommand()  {
        File f;
        f = new File(SystemProperties.currentDirectory);
        File[] list = f.listFiles();
        if (list != null) {
            Arrays.sort(list, Collections.reverseOrder());

            for (File file : list) {

                if (file.isDirectory()) {
                    System.out.println("    " + file.getName());
                }
                else {
                    System.out.println(file.getName());
                }
            }
        }
    }
    public ArrayList<String> hiddenList() throws Exception {
        Path dir = Path.of(SystemProperties.currentDirectory);
        ArrayList<String> list = new ArrayList<>();
        DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
        for (Path file : stream) {
            String fileName = file.getFileName().toString();
            list.add(fileName);
        }
        return list;
    }


}
