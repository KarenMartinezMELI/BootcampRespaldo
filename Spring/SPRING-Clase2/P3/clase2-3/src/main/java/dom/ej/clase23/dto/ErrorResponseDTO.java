package dom.ej.clase23.dto;

import java.util.List;

public class ErrorResponseDTO extends RespuestaGenericaDTO{

    String error;

    public ErrorResponseDTO(String n){
        this.error=n;
    }

    public String getError() {
        return error;
    }

}
