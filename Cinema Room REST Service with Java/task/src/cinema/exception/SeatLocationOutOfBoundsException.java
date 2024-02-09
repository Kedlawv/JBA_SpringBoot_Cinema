package cinema.exception;

public class SeatLocationOutOfBoundsException extends RuntimeException{
    public SeatLocationOutOfBoundsException(String message) {
        super(message);
    }
}
