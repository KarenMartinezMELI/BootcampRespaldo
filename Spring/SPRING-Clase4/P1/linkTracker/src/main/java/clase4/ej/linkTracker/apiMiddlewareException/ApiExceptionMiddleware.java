package clase4.ej.linkTracker.apiMiddlewareException;

import clase4.ej.linkTracker.exception.ResponseException;
import clase4.ej.linkTracker.exception.ResponseExceptionNoExist;
import clase4.ej.linkTracker.exception.ResponseExceptionNoValidUrl;
import clase4.ej.linkTracker.exception.ResponseExceptionUrlAlreadyExist;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@ControllerAdvice
public class ApiExceptionMiddleware extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new HashMap<>() {{
            put("error_code", "system error");
            put("message", ex.getMessage());
        }}, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new HashMap<>() {{
            put("message", ex.getMessage());
        }}, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { ResponseException.class })
    public ResponseEntity<Object> handleResponseException(ResponseException ex, WebRequest request) {
        ResponseExceptionNoExist exp= new ResponseExceptionNoExist(ex.getMessage());
        exp.setName("ErrorDTO");
        exp.setDescription("["+ex.getMessage()+"]");
        return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = { ResponseExceptionNoExist.class })
    public ResponseEntity<Object> handleResponseNoExistException(ResponseExceptionNoExist ex, WebRequest request) {
        ResponseExceptionNoExist exp= new ResponseExceptionNoExist(ex.getMessage());
        exp.setName("Item no encontrado");
        exp.setDescription("Items ["+ex.getMessage()+"] no encontrados");
        return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = { ResponseExceptionNoValidUrl.class })
    public ResponseEntity<Object> handleResponseNoExistException(ResponseExceptionNoValidUrl ex, WebRequest request) {
        ResponseExceptionNoValidUrl exp= new ResponseExceptionNoValidUrl(ex.getMessage());
        exp.setName("Url no válida");
        exp.setDescription("Url ["+ex.getMessage()+"] no válida");
        return new ResponseEntity<>(exp,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ResponseExceptionUrlAlreadyExist.class })
    public ResponseEntity<Object> handleResponseNoExistException(ResponseExceptionUrlAlreadyExist ex, WebRequest request) {
        ResponseExceptionUrlAlreadyExist exp= new ResponseExceptionUrlAlreadyExist(ex.getMessage());
        exp.setName("La url ya existe");
        exp.setDescription("Url ["+ex.getMessage()+"] ya existente");
        return new ResponseEntity<>(exp,HttpStatus.IM_USED);
    }
}

