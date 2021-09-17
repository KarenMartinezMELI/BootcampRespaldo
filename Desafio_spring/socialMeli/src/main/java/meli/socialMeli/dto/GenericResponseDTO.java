package meli.socialMeli.dto;

public class GenericResponseDTO {
    String message;

    public GenericResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
