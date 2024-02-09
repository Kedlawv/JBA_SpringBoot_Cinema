package cinema.exception;

public class WrongTokenException extends RuntimeException{
    public WrongTokenException() {
    }

    public WrongTokenException(String message) {
        super(message);
    }
}
