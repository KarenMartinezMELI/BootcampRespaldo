package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.movie.MovieDTO;

import java.util.List;

public interface ISerieService {


    List<MovieDTO> getSeriesWSeasonsHigherThan(Long seasons);

}
