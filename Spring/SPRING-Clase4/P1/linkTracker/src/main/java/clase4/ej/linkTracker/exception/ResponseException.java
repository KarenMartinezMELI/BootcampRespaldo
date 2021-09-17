package clase4.ej.linkTracker.exception;

import org.springframework.http.HttpStatus;

public abstract class ResponseException extends RuntimeException{

    private String name;
    private String description;

    public ResponseException(String msg){
        super(msg);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
