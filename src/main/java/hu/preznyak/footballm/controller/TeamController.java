package hu.preznyak.footballm.controller;

import hu.preznyak.footballm.data.Team;
import hu.preznyak.footballm.repository.PlayerRepository;
import hu.preznyak.footballm.repository.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/team")
public class TeamController {

    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    public TeamController(TeamRepository teamRepository, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.playerRepository = playerRepository;
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Team>> findAll() {
        List<Team> teams = new ArrayList<>();
        this.teamRepository.findAll().forEach(teams::add);
        return new ResponseEntity<>(teams, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Team> create(@RequestBody Team team) {
        return new ResponseEntity<>(this.teamRepository.save(team), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Team> findById(@PathVariable Integer id) {
        Team team = this.teamRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No team found with the given id: " + id)
        );
        return new ResponseEntity<>(team, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Team> updateTeam(@RequestBody Team team) {
        Team dbTeam = this.teamRepository.findById(team.getId()).orElseThrow(
                () -> new NoSuchElementException("No team found with the given id: " + team.getId())
        );
        return new ResponseEntity<>(this.teamRepository.save(team), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable Integer id) {
        this.teamRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No team found with the given id: " + id)
        );
        this.teamRepository.deleteById(id);
        return new ResponseEntity<>("Team deleted successfully.", HttpStatus.OK);
    }

}
