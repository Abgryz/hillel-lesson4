package ithillel.lesson9;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FileLoggerConfigurationLoaderTest {
    private static final String PATH_TO_RESOURCES = "src/main/resources/conf.properties";

    @Test
    void loadTest() {
        var conf = FileLoggerConfigurationLoader.load(PATH_TO_RESOURCES);
        try (FileReader reader = new FileReader(PATH_TO_RESOURCES)) {
            Scanner scanner = new Scanner(reader);
            String[] confArr = new String[3];
            for (int i = 0; scanner.hasNext(); i++){
                String[] strArr = scanner.nextLine().split("=");
                confArr[i] = strArr[strArr.length - 1].trim();
            }
            assertEquals(conf.pathToFiles(), confArr[0]);
            assertEquals(conf.logLvl().getValue().toLowerCase(), confArr[1].toLowerCase());
            assertEquals(conf.maxsize(), Integer.parseInt(confArr[2]));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
