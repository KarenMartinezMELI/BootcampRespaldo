package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.genre.GenreRequestDTO;
import meli.spoiledTomatoesAPI.dto.genre.GenreResponseDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieRequestDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieResponseDTO;
import meli.spoiledTomatoesAPI.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IMiniSerieService {

    List<MiniSerieResponseDTO> getAllMiniSeries();

    MiniSerieResponseDTO createMiniSerie(MiniSerieRequestDTO miniSerieRequestDTO);

    MiniSerieResponseDTO getMiniSerieById(Long genreId);

    MiniSerieResponseDTO updateMiniSerie(Long genreId, MiniSerieRequestDTO miniSerieRequestDTO);

    void deleteMiniSerie(Long genreId);
}
