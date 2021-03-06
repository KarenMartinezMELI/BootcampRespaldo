package meli.spoiledTomatoesAPI.repository;

import meli.spoiledTomatoesAPI.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISeasonRepository extends JpaRepository<Season, Long> {
}
