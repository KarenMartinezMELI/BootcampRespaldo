package meli.spoiledTomatoesAPI.dto.episode;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EpisodeDTO {
    private Long id;
    @JsonProperty("title")
    private String title;
}
