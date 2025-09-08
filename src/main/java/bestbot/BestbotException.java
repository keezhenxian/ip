package bestbot;

/**
 * Exception thrown when Duke encounters an error in user input.
 */
public class BestbotException extends Exception {
    public BestbotException(String message) {
        super(message);
    }
}
