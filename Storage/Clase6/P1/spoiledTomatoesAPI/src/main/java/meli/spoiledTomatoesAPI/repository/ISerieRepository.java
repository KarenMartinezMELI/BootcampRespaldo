package meli.spoiledTomatoesAPI.repository;

import meli.spoiledTomatoesAPI.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISerieRepository extends JpaRepository<Serie, Long> {

    @Query( "FROM Serie serie "+
            "WHERE serie.id IN "+
            "(SELECT season.serie.id " +
            "FROM Season season " +
            "GROUP BY season.serie.id " +
            "HAVING COUNT (season.serie.id)>:seasons)")
    List<Serie> getSeriesWSeasonsHigherThan(@Param("seasons") Long seasons);
}
