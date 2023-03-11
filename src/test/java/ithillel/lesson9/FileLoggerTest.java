package ithillel.lesson9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class FileLoggerTest {

    private static final String PATH_TO_FILES = "logs";
    private static final String PATH_TO_RESOURCES = "src/main/resources/conf.properties";

    @Test
    void infoTest() {
        FileLogger logger = new FileLogger(new FileLoggerConfiguration(PATH_TO_FILES, LoggingLevel.INFO, 1024));
        logger.info("info message");
        fileLoggerTester(LoggingLevel.INFO, "info message");
    }

    @Test
    void debugTest() {
        FileLogger logger = new FileLogger(PATH_TO_RESOURCES);
        logger.debug("debug message");
        fileLoggerTester(LoggingLevel.DEBUG, "debug message");
    }

    private static void fileLoggerTester(LoggingLevel logLvl, String message){
        try (FileReader reader = new FileReader(latestFileFounder(PATH_TO_FILES))) {
            Scanner scanner = new Scanner(reader);
            String lastLine = "";
            while (scanner.hasNext()){
                lastLine = scanner.nextLine();
            }
            assertTrue(lastLine.endsWith(String.format("[%s] Message: %s", logLvl, message)));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static String latestFileFounder(String path) {
        File[] fileArr = new File(path).listFiles();
        String currentFileName = "";
        assert fileArr != null;
        for (File f : fileArr) {
            if (f.isFile()) {
                if (currentFileName.compareTo(f.getName()) < 0) {
                    currentFileName = f.getName();
                }
            }
        }
        return path + "/" + currentFileName;
    }
}