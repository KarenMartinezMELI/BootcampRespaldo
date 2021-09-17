package meli.spoiledTomatoesAPI.dto.movie;

import lombok.*;
import meli.spoiledTomatoesAPI.dto.actor.ActorDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieRatingResponseDTO extends MovieDTO {

    private Double rating;
}
