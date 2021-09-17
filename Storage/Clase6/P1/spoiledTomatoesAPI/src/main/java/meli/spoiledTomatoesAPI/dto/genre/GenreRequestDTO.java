package meli.spoiledTomatoesAPI.dto.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
// Used when extends from other DTO
// link: https://stackoverflow.com/questions/38572566/warning-equals-hashcode-on-data-annotation-lombok-with-inheritance
@EqualsAndHashCode(callSuper=true)
public class GenreRequestDTO extends GenreDTO{

}
