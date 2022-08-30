package exceptions;

public class FileNotReachableException extends Exception{
    public FileNotReachableException() {
        super("File not exists or not readable.");
    }
}
