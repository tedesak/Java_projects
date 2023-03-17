package expression.exceptions;

public class ConstFormatException extends IncorrectExpressionException {
    public ConstFormatException(String message) {
        super(message);
    }

    public ConstFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
