package meli.lottery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class SpoiledTomatoesExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> error(ResourceNotFoundException exception) {
        return new ResponseEntity<>(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> error(SQLException exception) {
        return new ResponseEntity<>(
                "No se pudieron hacer los datos de la operación. "+exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> error(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(
                "No se puede hacer la operación."+exception.getMessage(),
                HttpStatus.BAD_REQUEST);
    }

}