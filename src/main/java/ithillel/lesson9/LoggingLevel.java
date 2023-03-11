package ithillel.lesson9;

public enum LoggingLevel {
    INFO("Info", 0),
    DEBUG("Debug", 1);
    private final String value;
    private final int priority;

    LoggingLevel (String value, int priority){
        this.value = value;
        this.priority = priority;
    }
    public String getValue(){
        return value;
    }

    public int getPriority() {
        return priority;
    }
}
