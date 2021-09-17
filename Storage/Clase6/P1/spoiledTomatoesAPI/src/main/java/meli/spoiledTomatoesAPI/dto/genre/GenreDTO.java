package meli.spoiledTomatoesAPI.dto.genre;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenreDTO {

    private String name;
    private Long ranking;
}
