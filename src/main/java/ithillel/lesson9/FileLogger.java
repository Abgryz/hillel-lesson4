package ithillel.lesson9;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger {
    private final FileLoggerConfiguration conf;

    public FileLogger(FileLoggerConfiguration conf) {
        this.conf = conf;

        try {
            Files.createDirectories(Path.of(conf.pathToFiles()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public FileLogger (String pathToResource){
        this.conf = FileLoggerConfigurationLoader.load(pathToResource);

        try {
            Files.createDirectories(Path.of(conf.pathToFiles()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void info(String message){
        if (conf.logLvl().getPriority() > LoggingLevel.INFO.getPriority()){
            return;
        }
        writeInFile(message, LoggingLevel.INFO);
    }
    public void debug(String message){
        if (conf.logLvl().getPriority() > LoggingLevel.DEBUG.getPriority()){
            return;
        }
        writeInFile(message, LoggingLevel.DEBUG);
    }

    private void writeInFile(String message, LoggingLevel logLvl){
        String output = String.format("[%s][%s] Message: %s\n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),logLvl, message);

        File file = sizeChecker(conf.pathToFiles(), output, conf.maxsize());
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static File sizeChecker(String path, String data, long maxSize){
        byte[] byteArr = data.getBytes();
        if (byteArr.length > maxSize)
            throw new RuntimeException("Message size exceeds maximum size");

        File[] fileArr = new File(path).listFiles();
        if(fileArr.length != 0){
            String currentFileName = "";
            for (File f: fileArr){
                if (f.isFile()){
                    if (currentFileName.compareTo(f.getName()) < 0){
                        currentFileName = f.getName();
                    }
                }
            }

            File file = new File(path + "/" + currentFileName);
            if(file.length() + byteArr.length < maxSize)
                return file;
        }

        return new File(path + "/log" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")) + ".log");
    }
}
