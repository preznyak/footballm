package hu.preznyak.footballm.repository;

import hu.preznyak.footballm.data.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
