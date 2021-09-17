package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.movie.MovieGenreResponseDTO;
import meli.spoiledTomatoesAPI.dto.movie.MovieRatingResponseDTO;

import java.util.List;

public interface IMovieService {


    List<MovieRatingResponseDTO> getMoviesWActorsRatingHigherThan(Double rating);
    List<MovieGenreResponseDTO> getMoviesWGenre(String genre);

}
