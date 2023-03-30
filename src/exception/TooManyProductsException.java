package exception;

public class TooManyProductsException extends RuntimeException {

    public TooManyProductsException() {
        super();
    }

    public TooManyProductsException(String message) {
        super(message);
    }
}