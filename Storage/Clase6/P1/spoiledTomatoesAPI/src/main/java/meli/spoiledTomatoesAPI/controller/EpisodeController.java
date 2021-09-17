package meli.spoiledTomatoesAPI.controller;


import meli.spoiledTomatoesAPI.dto.actor.ActorResponseDTO;
import meli.spoiledTomatoesAPI.dto.episode.EpisodeDTO;
import meli.spoiledTomatoesAPI.service.IActorService;
import meli.spoiledTomatoesAPI.service.IEpisodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {

    IEpisodeService episodeService;

    public EpisodeController(IEpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/actors/{id}")
    public List<EpisodeDTO> getEpisodesOfActors(@PathVariable Long id) {
        return episodeService.getEpisodesOfActors(id);
    }

}
