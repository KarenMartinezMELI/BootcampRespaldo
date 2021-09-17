package meli.spoiledTomatoesAPI.controller;

import meli.spoiledTomatoesAPI.dto.movie.MovieDTO;
import meli.spoiledTomatoesAPI.dto.movie.MovieGenreResponseDTO;
import meli.spoiledTomatoesAPI.dto.movie.MovieRatingResponseDTO;
import meli.spoiledTomatoesAPI.service.IMovieService;
import meli.spoiledTomatoesAPI.service.ISerieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    ISerieService serieService;

    public SerieController(ISerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/seasons/count>{seasons}")
    public List<MovieDTO> getSeriesWSeasonsHigherThan(@PathVariable("seasons") Long seasons) {
        return serieService.getSeriesWSeasonsHigherThan(seasons);
    }

}
