package com.upgrad.ublog.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {
    public static void writeLog(String logMessage, String path) throws IOException{
    String fileName = "logs"+".log";
     String filePath = path +"/"+ fileName;
        try {
            if (!Files.exists(Paths.get(path))) {
                Files.createDirectory(Paths.get(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) {
            bw.write(LocalDateTime.now().format(DateTimeFormatter.ofPattern(("dd/MM/yyyy HH:mm:ss")))+ "\t" +logMessage);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

}
}
