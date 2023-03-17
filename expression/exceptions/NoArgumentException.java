package expression.exceptions;

public class NoArgumentException extends IncorrectExpressionException {
    public NoArgumentException(String message) {
        super(message);
    }

    public NoArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
