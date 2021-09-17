package meli.spoiledTomatoesAPI.repository;

import meli.spoiledTomatoesAPI.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMovieRepository extends JpaRepository<Movie, Long> {

   @Query("SELECT DISTINCT m " +
           "FROM Movie m " +
           "JOIN ActorMovie am ON am.movie.id=m.id " +
           "AND m.id NOT IN " +
           "(SELECT DISTINCT am2.movie.id " +
           "FROM ActorMovie am2 " +
           "WHERE am2.actor.rating<=:rating)")

  /*
   Lo mismo pero con JOINS que por el modelo no lo necesita
   @Query("FROM Movie m " +
           "JOIN ActorMovie am ON am.movie.id=m.id " +
           "WHERE m.id NOT IN " +
           "(SELECT DISTINCT am.movie.id " +
           "FROM ActorMovie am " +
           "INNER JOIN Actor a on a.id = am.actor.id " +
           "WHERE a.rating <=:rating)")
    */
    List<Movie> getMoviesWActorsRatingHigherThan(Double rating);

    @Query("FROM Movie m " +
            "WHERE m.genre.name LIKE :genre")
    List<Movie> getMoviesWGenre(@Param("genre") String genre);


}
