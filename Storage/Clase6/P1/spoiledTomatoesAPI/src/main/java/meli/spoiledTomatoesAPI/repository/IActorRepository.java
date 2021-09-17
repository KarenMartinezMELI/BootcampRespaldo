package meli.spoiledTomatoesAPI.repository;


import meli.spoiledTomatoesAPI.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IActorRepository extends JpaRepository<Actor, Long> {

    @Query("FROM Actor a WHERE a.favoriteMovie IS NOT NULL")
    List<Actor> getAllActorsWFavoriteMovie();

    @Query("FROM Actor a WHERE a.rating>:rating ORDER BY a.rating DESC")
    List<Actor> getActorsRatingHigherThan(@Param("rating") Double rating);

    @Query("FROM Actor a " +
            "JOIN ActorMovie am ON am.actor.id=a.id " +
            "WHERE am.movie.id=:id")
    List<Actor>getActorsInMovie(@Param("id") Long id);


}
