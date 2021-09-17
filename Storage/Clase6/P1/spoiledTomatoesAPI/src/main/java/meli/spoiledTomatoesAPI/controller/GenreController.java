package meli.spoiledTomatoesAPI.controller;

import meli.spoiledTomatoesAPI.dto.genre.GenreRequestDTO;
import meli.spoiledTomatoesAPI.dto.genre.GenreResponseDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieRequestDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieResponseDTO;
import meli.spoiledTomatoesAPI.service.IGenreService;
import meli.spoiledTomatoesAPI.service.IMiniSerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    IGenreService genreService;

    GenreController(IGenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("")
    public List<GenreResponseDTO> getAllGenres() {
        return genreService.getAllGenres();
    }

    @PostMapping()
    public GenreResponseDTO createGenre(@Valid @RequestBody GenreRequestDTO genreRequestDTO) {
        return genreService.createGenre(genreRequestDTO);
    }

    @GetMapping("/{id}")
    public GenreResponseDTO getGenreById(@PathVariable(value = "id") Long genreId) {
        return genreService.getGenreById(genreId);
    }

    @PutMapping("/{id}")
    public GenreResponseDTO updateGenre(@PathVariable(value = "id") Long genreId, @Valid @RequestBody GenreRequestDTO genreDTO) {
        return genreService.updateGenre(genreId, genreDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable(value = "id") Long genreId) {
        genreService.deleteGenre(genreId);
        return ResponseEntity.ok().build();
    }
}
