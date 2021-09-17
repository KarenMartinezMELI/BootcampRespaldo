package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.actor.ActorDTO;
import meli.spoiledTomatoesAPI.dto.actor.ActorRatingResponseDTO;
import meli.spoiledTomatoesAPI.dto.actor.ActorResponseDTO;
import meli.spoiledTomatoesAPI.dto.genre.GenreResponseDTO;
import meli.spoiledTomatoesAPI.exception.ResourceNotFoundException;
import meli.spoiledTomatoesAPI.model.Actor;
import meli.spoiledTomatoesAPI.model.Genre;
import meli.spoiledTomatoesAPI.repository.IActorRepository;
import meli.spoiledTomatoesAPI.utils.ListMapper;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImp implements IActorService{


    IActorRepository actorRepository;
    ModelMapper modelMapper;
    ListMapper listMapper;


    ActorServiceImp(IActorRepository actorRepository,
                    ModelMapper modelMapper,
                    ListMapper listMapper) {
        this.actorRepository = actorRepository;
        this.listMapper = listMapper;

        Converter<Long, Actor> actorIdToActorConverter = new AbstractConverter<Long, Actor>() {
            @Override
            protected Actor convert(Long actorId) throws ResourceNotFoundException {
                return actorRepository.findById(actorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Actor", "id", actorId));
            }
        };


        this.modelMapper = modelMapper;
    }

    @Override
    public List<ActorResponseDTO> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        return listMapper.mapList(actors,ActorResponseDTO.class);
    }

    @Override
    public List<ActorResponseDTO> getAllActorsWFavoriteMovie() {

        //List<Actor> actors = actorRepository.findAll().stream().filter(actor->actor.getFavoriteMovie()!=null).collect(Collectors.toList());
        return listMapper.mapList(actorRepository.getAllActorsWFavoriteMovie(),ActorResponseDTO.class);
    }

    @Override
    public List<ActorRatingResponseDTO> getActorsRatingHigherThan(Double rating) {
        return listMapper.mapList(actorRepository.getActorsRatingHigherThan(rating),ActorRatingResponseDTO.class);
    }

    @Override
    public List<ActorDTO> getActorsInMovie(Long id) {
        return listMapper.mapList(actorRepository.getActorsInMovie(id),ActorDTO.class);
    }
}
