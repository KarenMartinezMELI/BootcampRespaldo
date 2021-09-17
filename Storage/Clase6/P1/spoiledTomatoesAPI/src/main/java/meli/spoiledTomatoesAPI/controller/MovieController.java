package meli.spoiledTomatoesAPI.controller;

import meli.spoiledTomatoesAPI.dto.movie.MovieGenreResponseDTO;
import meli.spoiledTomatoesAPI.dto.movie.MovieRatingResponseDTO;
import meli.spoiledTomatoesAPI.service.IMovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    IMovieService movieService;

    public MovieController(IMovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/rating>{rating}")
    public List<MovieRatingResponseDTO> getMoviesWActorsRatingHigherThan(@PathVariable("rating") Double rating) {
        return movieService.getMoviesWActorsRatingHigherThan(rating);
    }

    @GetMapping("/genre={genre}")
    public List<MovieGenreResponseDTO> getMoviesWGenre(@PathVariable("genre") String genre) {
        return movieService.getMoviesWGenre(genre);
    }

}
