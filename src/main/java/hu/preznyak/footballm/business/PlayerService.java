package hu.preznyak.footballm.business;

import hu.preznyak.footballm.batch.MarketPriceManipulator;
import hu.preznyak.footballm.data.Player;
import hu.preznyak.footballm.repository.PlayerRepository;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final MarketPriceManipulator marketPriceManipulator;
    private final JobLauncher jobLauncher;

    public PlayerService(PlayerRepository playerRepository, MarketPriceManipulator marketPriceManipulator, JobLauncher jobLauncher) {
        this.playerRepository = playerRepository;
        this.marketPriceManipulator = marketPriceManipulator;
        this.jobLauncher = jobLauncher;
    }

    public Player createPlayer(Player player) {
        return this.playerRepository.save(player);
    }

    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        Iterable<Player> playerIterable = this.playerRepository.findAll();
        playerIterable.forEach(players::add);
        return players;
    }

    public Player findByPlayerId(Integer id) {
        return this.playerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No player found")
        );
    }

    public Player updatePlayer(Player player) {
        if (Objects.isNull(player)) {
            throw new IllegalArgumentException("The input data is null.");
        }
        Player toUpdate = this.playerRepository.findById(player.getId()).orElseThrow(
                () -> new NoSuchElementException("No player found with the given id: "+ player.getId())
        );
        toUpdate.setShirtNumber(player.getShirtNumber());
        toUpdate.setGoodForm(player.isGoodForm());
        toUpdate.setSalaryPerWeek(player.getSalaryPerWeek());
        toUpdate.setPosition(player.getPosition());
        toUpdate.setMarketPrice(player.getMarketPrice());
        return this.playerRepository.save(toUpdate);
    }

    public void deletePlayerById(Integer id) {
        Player player = this.playerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("No player found with the given id: "+ id)
        );
        this.playerRepository.deleteById(id);
    }

    public List<Player> findAllByPositionAndAgeLessThan(String position, Integer age) {
        List<Player> players = new ArrayList<>();
        this.playerRepository.findAllByPositionAndAgeLessThan(position, age).forEach(players::add);
        return players;
    }

    public List<Player> findAllByPosition(String position) {
        List<Player> players = new ArrayList<>();
        this.playerRepository.findAllByPosition(position).forEach(players::add);
        return players;
    }

    public void updateMarketPrices() {
        JobParametersBuilder parametersBuilder = new JobParametersBuilder();
        parametersBuilder.addDate("date", new Date());
        try {
            this.jobLauncher.run(marketPriceManipulator.manipulateMarketPriceJob(), parametersBuilder.toJobParameters());
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobRestartException |
                 JobParametersInvalidException e) {
            //TODO
            throw new RuntimeException(e);
        }
    }
}
