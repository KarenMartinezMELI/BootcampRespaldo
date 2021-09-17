package meli.spoiledTomatoesAPI.dto.movie;

import lombok.*;
import meli.spoiledTomatoesAPI.dto.genre.GenreDTO;
import meli.spoiledTomatoesAPI.dto.genre.GenreResponseDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovieGenreResponseDTO extends MovieDTO {

    private GenreResponseDTO genre;
}
