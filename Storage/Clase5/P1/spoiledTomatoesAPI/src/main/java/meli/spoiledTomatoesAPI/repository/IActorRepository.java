package meli.spoiledTomatoesAPI.repository;


import meli.spoiledTomatoesAPI.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActorRepository extends JpaRepository<Actor, Long> {
}
