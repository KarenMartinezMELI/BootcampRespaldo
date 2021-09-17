package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.genre.GenreRequestDTO;
import meli.spoiledTomatoesAPI.dto.genre.GenreResponseDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieRequestDTO;
import meli.spoiledTomatoesAPI.dto.miniserie.MiniSerieResponseDTO;
import meli.spoiledTomatoesAPI.exception.ResourceNotFoundException;
import meli.spoiledTomatoesAPI.model.Actor;
import meli.spoiledTomatoesAPI.model.Genre;
import meli.spoiledTomatoesAPI.model.MiniSerie;
import meli.spoiledTomatoesAPI.repository.IActorRepository;
import meli.spoiledTomatoesAPI.repository.IGenreRepository;
import meli.spoiledTomatoesAPI.repository.IMiniSerieRepository;
import meli.spoiledTomatoesAPI.utils.ListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.*;


import java.util.List;

@Service
public class MiniSerieServiceImp implements IMiniSerieService{

    IMiniSerieRepository miniSerieRepository;
    IGenreRepository genreRepository;
    IActorRepository actorRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;


    MiniSerieServiceImp(IMiniSerieRepository miniSerieRepository,
                        IGenreRepository genreRepository,
                        IActorRepository actorRepository,
                        ModelMapper modelMapper,
                        ListMapper listMapper) {
        this.miniSerieRepository = miniSerieRepository;
        this.genreRepository = genreRepository;
        this.actorRepository=actorRepository;
        this.listMapper = listMapper;

        Converter<Long, Genre> genreIdToGenreConverter = new AbstractConverter<Long, Genre>() {
            @Override
            protected Genre convert(Long genreId) throws ResourceNotFoundException {
                return genreRepository.findById(genreId)
                        .orElseThrow(() -> new ResourceNotFoundException("Genre", "id", genreId));
            }
        };
        Converter<Long, Actor> actorIdToActorConverter = new AbstractConverter<Long, Actor>() {
            @Override
            protected Actor convert(Long actorId) throws ResourceNotFoundException {
                return actorRepository.findById(actorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Actor", "id", actorId));
            }
        };
        //Load converter to modelMapper used when we want convert from MiniSerie to MiniSerieDTO
        modelMapper.typeMap(MiniSerieRequestDTO.class, MiniSerie.class).addMappings( (mapper) ->
                mapper.using(genreIdToGenreConverter)
                        .map(MiniSerieRequestDTO::getGenreId, MiniSerie::setGenre)
        );

        this.modelMapper = modelMapper;
    }

    @Override
    public List<MiniSerieResponseDTO> getAllMiniSeries() {
        List<MiniSerie> genres = miniSerieRepository.findAll();
        return listMapper.mapList(genres, MiniSerieResponseDTO.class);
    }

    @Override
    public MiniSerieResponseDTO createMiniSerie(MiniSerieRequestDTO miniSerieRequestDTO) {
        // Create new note
        MiniSerie miniSerie = modelMapper.map(miniSerieRequestDTO, MiniSerie.class);


        MiniSerie miniSerieReq = miniSerieRepository.save(miniSerie);
        MiniSerieResponseDTO resp =  modelMapper.map(miniSerieReq, MiniSerieResponseDTO.class);
        return resp;
    }

    @Override
    public MiniSerieResponseDTO getMiniSerieById(Long miniSerieId) {
        MiniSerie miniSerie = miniSerieRepository.findById(miniSerieId)
                .orElseThrow(() -> new ResourceNotFoundException("MiniSerie", "id", miniSerieId));
        return modelMapper.map(miniSerie, MiniSerieResponseDTO.class);
    }

    @Override
    public MiniSerieResponseDTO updateMiniSerie(Long miniSerieId,
                                                MiniSerieRequestDTO miniSerieDTO) {

        MiniSerie miniSerie = miniSerieRepository.findById(miniSerieId)
                .orElseThrow(() -> new ResourceNotFoundException("MiniSerie", "id", miniSerieId));

        miniSerie.setAwards(miniSerieDTO.getAwards());
        miniSerie.setTitle(miniSerie.getTitle());
        miniSerie.setRating(miniSerie.getRating());


        modelMapper.map(miniSerieDTO, MiniSerieResponseDTO.class);
        GenreResponseDTO genre= modelMapper.map(miniSerieDTO, MiniSerieResponseDTO.class).getGenre();
        miniSerie.setGenre(modelMapper.map(genre, Genre.class));

        MiniSerie updatedMiniSerie = miniSerieRepository.save(miniSerie);
        return modelMapper.map(updatedMiniSerie, MiniSerieResponseDTO.class);
    }

    @Override
    public void deleteMiniSerie(Long miniSerieId) {
        MiniSerie miniSerie = miniSerieRepository.findById(miniSerieId)
                .orElseThrow(() -> new ResourceNotFoundException("MiniSerie", "id", miniSerieId));

        miniSerieRepository.delete(miniSerie);
    }
}
