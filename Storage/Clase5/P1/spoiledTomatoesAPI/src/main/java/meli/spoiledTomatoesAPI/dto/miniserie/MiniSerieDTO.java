package meli.spoiledTomatoesAPI.dto.miniserie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiniSerieDTO {

    @NotEmpty
    private String name;
    private Double rating;
    private Integer awards;

    @JsonProperty("release_date")
    private Date releaseDate;
    @JsonProperty("end_date")
    private Date endDate;
}
