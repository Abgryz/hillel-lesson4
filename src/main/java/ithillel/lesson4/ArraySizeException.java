package ithillel.lesson4;

public class ArraySizeException extends RuntimeException{
    public ArraySizeException() {
    }

    public ArraySizeException(String message) {
        super(message);
    }

    public ArraySizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
