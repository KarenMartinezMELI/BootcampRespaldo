package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.actor.ActorDTO;
import meli.spoiledTomatoesAPI.dto.actor.ActorRatingResponseDTO;
import meli.spoiledTomatoesAPI.dto.actor.ActorResponseDTO;

import java.util.List;

public interface IActorService {

    List<ActorResponseDTO> getAllActors();
    List<ActorResponseDTO> getAllActorsWFavoriteMovie();
    List<ActorRatingResponseDTO> getActorsRatingHigherThan(Double rating);
    List<ActorDTO> getActorsInMovie(Long id);


}
