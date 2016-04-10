package Exceptions;

/**
 * Created by zhengf1 on 4/8/16.
 */
public class FibonacciInvalidNumberException extends Exception {
    public FibonacciInvalidNumberException() {

    }

    public FibonacciInvalidNumberException(String message) {
        super (message);
    }

    public FibonacciInvalidNumberException(Throwable cause) {
        super (cause);
    }

    public FibonacciInvalidNumberException(String message, Throwable cause) {
        super (message, cause);
    }
}
