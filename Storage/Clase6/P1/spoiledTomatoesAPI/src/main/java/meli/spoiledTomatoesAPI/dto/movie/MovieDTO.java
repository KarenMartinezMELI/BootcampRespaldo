package meli.spoiledTomatoesAPI.dto.movie;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieDTO {
    private Long id;
    @JsonProperty("title")
    private String title;

}
