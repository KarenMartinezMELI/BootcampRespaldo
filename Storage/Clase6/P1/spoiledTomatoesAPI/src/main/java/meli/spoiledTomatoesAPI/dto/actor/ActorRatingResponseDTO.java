package meli.spoiledTomatoesAPI.dto.actor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActorRatingResponseDTO extends ActorDTO{

    private Double rating;
}
