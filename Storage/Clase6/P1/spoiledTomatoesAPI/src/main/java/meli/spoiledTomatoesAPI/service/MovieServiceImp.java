package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.actor.ActorRatingResponseDTO;
import meli.spoiledTomatoesAPI.dto.movie.MovieGenreResponseDTO;
import meli.spoiledTomatoesAPI.dto.movie.MovieRatingResponseDTO;
import meli.spoiledTomatoesAPI.exception.ResourceNotFoundException;
import meli.spoiledTomatoesAPI.model.Movie;
import meli.spoiledTomatoesAPI.repository.IMovieRepository;
import meli.spoiledTomatoesAPI.utils.ListMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImp implements IMovieService{


    IMovieRepository movieRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;


    MovieServiceImp(IMovieRepository movieRepository,
                    ModelMapper modelMapper,
                    ListMapper listMapper) {
        this.movieRepository = movieRepository;
        this.listMapper = listMapper;

        Converter<Long, Movie> movieIdToMovieConverter = new AbstractConverter<Long, Movie>() {
            @Override
            protected Movie convert(Long movieId) throws ResourceNotFoundException {
                return movieRepository.findById(movieId)
                        .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", movieId));
            }
        };



        this.modelMapper = modelMapper;
    }



    @Override
    public List<MovieRatingResponseDTO> getMoviesWActorsRatingHigherThan(Double rating) {
        return listMapper.mapList(movieRepository.getMoviesWActorsRatingHigherThan(rating),MovieRatingResponseDTO.class);
    }

    @Override
    public List<MovieGenreResponseDTO> getMoviesWGenre(String genre) {
        return listMapper.mapList(movieRepository.getMoviesWGenre(genre),MovieGenreResponseDTO.class);
    }

}
