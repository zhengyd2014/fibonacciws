package Exceptions;

/**
 * Created by zhengf1 on 4/8/16.
 */
public class FibonacciOutOfRangeException extends Exception {
    public FibonacciOutOfRangeException() {

    }

    public FibonacciOutOfRangeException(String message) {
        super (message);
    }

    public FibonacciOutOfRangeException(Throwable cause) {
        super (cause);
    }

    public FibonacciOutOfRangeException(String message, Throwable cause) {
        super (message, cause);
    }
}
