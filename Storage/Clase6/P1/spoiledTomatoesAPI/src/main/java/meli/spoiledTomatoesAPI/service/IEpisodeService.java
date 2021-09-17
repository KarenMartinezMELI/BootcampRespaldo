package meli.spoiledTomatoesAPI.service;

import meli.spoiledTomatoesAPI.dto.episode.EpisodeDTO;

import java.util.List;

public interface IEpisodeService {

    List<EpisodeDTO> getEpisodesOfActors(Long actorId);
}
