public class BinaryFormatException extends Exception {

    public BinaryFormatException() {
        super("invalid format.");
    }

    public BinaryFormatException(String message) {
        super(message);
    }
}
