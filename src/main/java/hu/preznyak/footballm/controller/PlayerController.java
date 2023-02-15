package hu.preznyak.footballm.controller;

import hu.preznyak.footballm.business.PlayerService;
import hu.preznyak.footballm.data.Player;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/player")
@Tag(name = "Player", description = "The Player API")
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
    @Operation(summary = "Find all players.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK")})
    public ResponseEntity<List<Player>> findAll() {
        return new ResponseEntity<>(this.playerService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Find a player with the given id.")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "404", description = "Not found")})
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

    @GetMapping(path = "/pa")
    public ResponseEntity<List<Player>> findAllByPositionAndAgeLessThan(@PathParam("position") String position,
                                                                        @PathParam("age") Integer age) {
        return new ResponseEntity<>(this.playerService.findAllByPositionAndAgeLessThan(position, age), HttpStatus.OK);
    }

    @GetMapping(path = "/position")
    public ResponseEntity<List<Player>> findAllByPosition(@PathParam("position") String position) {
        return new ResponseEntity<>(this.playerService.findAllByPosition(position), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException exception) {
        return exception.getMessage();
    }
}
