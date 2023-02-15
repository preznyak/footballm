package hu.preznyak.footballm.repository;

import hu.preznyak.footballm.data.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    Iterable<Player> findAllByPositionAndAgeLessThan(String position, Integer age);

    Iterable<Player> findAllByPosition(String position);
}
