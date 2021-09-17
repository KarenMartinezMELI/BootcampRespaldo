package com.example.sendmessages.apiMiddlewareException;

import com.example.sendmessages.dto.GenericExceptionDTO;
import com.example.sendmessages.exception.CostNotReachedMsg;
import com.example.sendmessages.exception.EnableStateIncompatibleMsg;
import com.example.sendmessages.exception.MessengerNotFound;
import com.example.sendmessages.exception.NotPosibleSendMsg;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;


@ControllerAdvice
public class ApiExceptionMiddleware{

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleAnyException(Exception ex) {
        return new ResponseEntity<>(new HashMap<>() {{
            put("error_code", "system error");
            put("message", ex.getMessage());
        }}, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeExceptions(RuntimeException ex) {
        return new ResponseEntity<>(new HashMap<>() {{
            put("message", ex.getMessage());
        }}, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { NotPosibleSendMsg.class })
    public ResponseEntity<Object> handleNotPosibleSendMsgException(NotPosibleSendMsg ex) {
        return new ResponseEntity<>(new GenericExceptionDTO("No se ha podido enviar el mensaje",""),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { CostNotReachedMsg.class })
    public ResponseEntity<Object> handleCostNotReachedException(CostNotReachedMsg ex) {
        return new ResponseEntity<>(new GenericExceptionDTO("Costo mayor al monto pagado","Costo:"+ex.getNedded()),HttpStatus.EXPECTATION_FAILED);

    }

    @ExceptionHandler(value = { EnableStateIncompatibleMsg.class })
    public ResponseEntity<Object> handleEnableStateIncpmpatibleException(EnableStateIncompatibleMsg ex) {
        return new ResponseEntity<>(new GenericExceptionDTO("Estado incopatible al otorgado","Estado ocupado:"+ex.isBusyState()),HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = { MessengerNotFound.class })
    public ResponseEntity<Object> handleMessengerNotFoundException(MessengerNotFound ex) {
        return new ResponseEntity<>(new GenericExceptionDTO("Mensajero no encontrado",""),HttpStatus.NOT_FOUND);

    }

}

