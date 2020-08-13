package exception;

public class OperationNotExistException extends Exception {
    public OperationNotExistException(int index) {
        super(String.format("Operation with index %d not found", index));
    }
}
