package hu.preznyak.footballm.controller;

import hu.preznyak.footballm.business.PlayerService;
import hu.preznyak.footballm.data.Player;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(this.playerService.createPlayer(player), HttpStatus.OK);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<List<Player>> findAll() {
        return new ResponseEntity<>(this.playerService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Player> findByPlayerId(@PathVariable Integer id) {
        Player player = this.playerService.findByPlayerId(id);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        return new ResponseEntity<>(this.playerService.updatePlayer(player), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deletePlayer(@PathVariable Integer id) {
        this.playerService.deletePlayerById(id);
        return new ResponseEntity<>("Player successfully deleted.", HttpStatus.OK);
    }

    @PostMapping(path = "/market/update")
    public ResponseEntity<String> updateMarketPrices() {
        this.playerService.updateMarketPrices();
        return new ResponseEntity<>("Update has started...", HttpStatus.OK);
    }
}
