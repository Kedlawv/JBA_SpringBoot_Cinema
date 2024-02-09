package cinema.exception;

import cinema.model.ErrorResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CinemaExceptionHandler {

    @ExceptionHandler(SeatNotAvailableException.class)
    public ResponseEntity<String> handleSeatNotAvailable(SeatNotAvailableException ex) {
        return ResponseEntity
                .badRequest()
                .body("{\"error\": \"The ticket has been already purchased!\"}");
    }

    @ExceptionHandler(SeatLocationOutOfBoundsException.class)
    public ResponseEntity<String> handleSeatLocationOutOfBounds(SeatLocationOutOfBoundsException ex) {
        return ResponseEntity
                .badRequest()
                .body("{\"error\": \"The number of a row or a column is out of bounds!\"}");
    }

    @ExceptionHandler(WrongTokenException.class)
    public ResponseEntity<String> handleWrongToken(WrongTokenException ex) {
        return ResponseEntity
                .badRequest()
                .body("{\"error\": \"Wrong token!\"}");
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponseObject> handleAuthException(AuthException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseObject(ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponseObject> handleMissingParameter(MissingServletRequestParameterException ex, HttpServletRequest request) throws MissingServletRequestParameterException {
        if ("/stats".equals(request.getRequestURI())) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponseObject("The password is wrong!"));
        } else {
            throw ex;
        }
    }

}
