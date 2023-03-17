package expression.exceptions;

public class IncorrectExpressionException extends Exception {
    public IncorrectExpressionException(String message) {
        super(message);
    }

    public IncorrectExpressionException(String message, Throwable cause) {
        super(message, cause);
    }
}
