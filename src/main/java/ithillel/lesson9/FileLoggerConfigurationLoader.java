package ithillel.lesson9;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class FileLoggerConfigurationLoader {
    public static FileLoggerConfiguration load(String pathToResource){
        try(FileReader config = new FileReader(pathToResource)){
            Properties properties = new Properties();
            properties.load(config);
            return new FileLoggerConfiguration(
                    properties.getProperty("path"),
                    stringToLoggingLevel(properties.getProperty("logLvl")),
                    Integer.parseInt(properties.getProperty("maxSize")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static LoggingLevel stringToLoggingLevel(String str){
        switch (str.toLowerCase()){
            case "info": return LoggingLevel.INFO;
            case "debug": return LoggingLevel.DEBUG;
            default: return null;
        }
    }
}
