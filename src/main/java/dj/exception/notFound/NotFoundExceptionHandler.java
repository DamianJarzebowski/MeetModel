package dj.exception.notFound;

import dj.exception.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.NOT_FOUND.getMessage());
    }

}
