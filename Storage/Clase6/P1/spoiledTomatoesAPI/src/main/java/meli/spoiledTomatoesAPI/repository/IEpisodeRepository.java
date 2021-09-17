package meli.spoiledTomatoesAPI.repository;

import meli.spoiledTomatoesAPI.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEpisodeRepository extends JpaRepository<Episode, Long> {

    @Query("FROM Episode e " +
            "JOIN ActorEpisode ae ON ae.episode.id=e.id " +
            "WHERE ae.actor.id  = :actorid ")
    List<Episode> getEpisodesOfActors(@Param("actorid") Long actorId);
}
