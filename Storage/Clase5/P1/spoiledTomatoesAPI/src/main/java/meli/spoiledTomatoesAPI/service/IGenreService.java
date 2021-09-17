package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.genre.GenreDTO;
import meli.spoiledTomatoesAPI.dto.genre.GenreRequestDTO;
import meli.spoiledTomatoesAPI.dto.genre.GenreResponseDTO;
import meli.spoiledTomatoesAPI.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IGenreService {

    List<GenreResponseDTO> getAllGenres();

    GenreResponseDTO createGenre(GenreRequestDTO genreRequestDTO);

    GenreResponseDTO getGenreById(Long genreId);

    GenreResponseDTO updateGenre(Long genreId, GenreRequestDTO genreRequestDTO);

    void deleteGenre(Long genreId);
}
