package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.actor.ActorDTO;
import meli.spoiledTomatoesAPI.dto.movie.MovieDTO;
import meli.spoiledTomatoesAPI.exception.ResourceNotFoundException;
import meli.spoiledTomatoesAPI.model.Serie;
import meli.spoiledTomatoesAPI.repository.ISerieRepository;
import meli.spoiledTomatoesAPI.utils.ListMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServiceImp implements ISerieService{


    ISerieRepository serieRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;


    SerieServiceImp(ISerieRepository serieRepository,
                    ModelMapper modelMapper,
                    ListMapper listMapper) {
        this.serieRepository = serieRepository;
        this.listMapper = listMapper;

        Converter<Long, Serie> serieIdToSerieConverter = new AbstractConverter<Long, Serie>() {
            @Override
            protected Serie convert(Long serieId) throws ResourceNotFoundException {
                return serieRepository.findById(serieId)
                        .orElseThrow(() -> new ResourceNotFoundException("Serie", "id", serieId));
            }
        };

        this.modelMapper = modelMapper;
    }


    @Override
    public List<MovieDTO> getSeriesWSeasonsHigherThan(Long seasons) {
        return listMapper.mapList(serieRepository.getSeriesWSeasonsHigherThan(seasons), MovieDTO.class);
    }
}
