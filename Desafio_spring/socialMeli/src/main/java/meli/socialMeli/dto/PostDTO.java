package meli.socialMeli.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meli.socialMeli.entity.Product;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {

    int id_post;
    int userId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",lenient = OptBoolean.FALSE, locale = "es_AR", timezone = "America/Argentina/Buenos_Aires")
    Date date;
    ProductDTO detail;
    int category;
    double price;
    boolean hasPromo;
    double discount;
}
