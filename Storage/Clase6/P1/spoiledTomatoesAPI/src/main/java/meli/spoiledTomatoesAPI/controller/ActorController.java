package meli.spoiledTomatoesAPI.controller;

import meli.spoiledTomatoesAPI.dto.actor.ActorDTO;
import meli.spoiledTomatoesAPI.dto.actor.ActorRatingResponseDTO;
import meli.spoiledTomatoesAPI.dto.actor.ActorResponseDTO;
import meli.spoiledTomatoesAPI.dto.genre.GenreResponseDTO;
import meli.spoiledTomatoesAPI.service.IActorService;
import meli.spoiledTomatoesAPI.service.IGenreService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/actors")
public class ActorController {
    IActorService actorService;

    public ActorController(IActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("?favorite_movie")
    public List<ActorResponseDTO> getActorsWFavoriteMovie() {
        return actorService.getAllActorsWFavoriteMovie();
    }

    @GetMapping("/rating>{rating}")
    public List<ActorRatingResponseDTO> getActorsRatingHigherThan(@PathVariable("rating") Double rating) {
        return actorService.getActorsRatingHigherThan(rating);
    }
    @GetMapping("/movies")
    public List<ActorDTO> getActorsInMovie(@RequestParam("actor_id") Long id) {

        return actorService.getActorsInMovie(id);
    }

    /*
        @GetMapping("/getActorsWFavoriteMovie")
    public List<ActorResponseDTO> getActorsWFavoriteMovie(@PathParam(value = "favorite_movie") String name) {
        //return actorService.getActorsWFavoriteMovie(name);
        return new GenreResponseDTO();
    }
     */
}
