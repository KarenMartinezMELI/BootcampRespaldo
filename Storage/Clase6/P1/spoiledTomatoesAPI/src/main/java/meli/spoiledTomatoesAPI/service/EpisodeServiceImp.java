package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.episode.EpisodeDTO;
import meli.spoiledTomatoesAPI.dto.movie.MovieGenreResponseDTO;
import meli.spoiledTomatoesAPI.dto.movie.MovieRatingResponseDTO;
import meli.spoiledTomatoesAPI.exception.ResourceNotFoundException;
import meli.spoiledTomatoesAPI.model.Episode;
import meli.spoiledTomatoesAPI.model.Movie;
import meli.spoiledTomatoesAPI.repository.IEpisodeRepository;
import meli.spoiledTomatoesAPI.repository.IMovieRepository;
import meli.spoiledTomatoesAPI.utils.ListMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeServiceImp implements IEpisodeService{


    IEpisodeRepository episodeRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;


    EpisodeServiceImp(IEpisodeRepository episodeRepository,
                      ModelMapper modelMapper,
                      ListMapper listMapper) {
        this.episodeRepository = episodeRepository;
        this.listMapper = listMapper;

        Converter<Long, Episode> episodeIdToEpisodeConverter = new AbstractConverter<Long, Episode>() {
            @Override
            protected Episode convert(Long episodeId) throws ResourceNotFoundException {
                return episodeRepository.findById(episodeId)
                        .orElseThrow(() -> new ResourceNotFoundException("Episode", "id", episodeId));
            }
        };

        this.modelMapper = modelMapper;
    }



    @Override
    public List<EpisodeDTO> getEpisodesOfActors(Long actorId) {

        return listMapper.mapList(episodeRepository.getEpisodesOfActors( actorId),EpisodeDTO.class);
    }


}
