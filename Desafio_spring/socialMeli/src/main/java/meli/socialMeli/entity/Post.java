package meli.socialMeli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    int idPost;
    User user;
    Date date;
    Product detail;
    int category;
    double price;
    boolean hasPromo;
    double discount;
}
