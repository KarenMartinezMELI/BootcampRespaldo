package meli.spoiledTomatoesAPI.dto.miniserie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import meli.spoiledTomatoesAPI.dto.genre.GenreResponseDTO;

import java.util.Date;

@Data
// Used when extends from other DTO
// link: https://stackoverflow.com/questions/38572566/warning-equals-hashcode-on-data-annotation-lombok-with-inheritance
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class MiniSerieResponseDTO extends MiniSerieDTO{
    private Long id;
    private GenreResponseDTO genre;
    @JsonProperty("created_at")
    private Date createdAt;
    @JsonProperty("updated_at")
    private Date updatedAt;
}
