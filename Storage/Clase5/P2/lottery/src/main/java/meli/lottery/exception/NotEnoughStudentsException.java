package meli.lottery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotEnoughStudentsException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public NotEnoughStudentsException(String resourceName) {
        super(String.format("No hay suficientes alumnos para el objeto de tipo: %s.",
                resourceName));
        this.resourceName = resourceName;
    }

}
