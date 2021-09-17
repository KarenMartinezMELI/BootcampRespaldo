package meli.spoiledTomatoesAPI.repository;


import meli.spoiledTomatoesAPI.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenreRepository  extends JpaRepository<Genre, Long> {
}
