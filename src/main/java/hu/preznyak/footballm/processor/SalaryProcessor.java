package hu.preznyak.footballm.processor;

import hu.preznyak.footballm.data.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class SalaryProcessor implements ItemProcessor<Player, Player> {

    private static final Logger log = LoggerFactory.getLogger(SalaryProcessor.class);

    @Override
    public Player process(Player player) {
        double currentSalaryPerWeek = player.getSalaryPerWeek();
        //Changing maximum 3% of the price ><
        double change = Math.random() * 3;
        int formParameter = player.isGoodForm() ? 1 : -1;
        double newSalaryPerWeek = currentSalaryPerWeek + (currentSalaryPerWeek * (change/100) * formParameter);
        player.setSalaryPerWeek(newSalaryPerWeek);
        log.info(String.format("Salary of %s changed from %f to %f", player.getName(), currentSalaryPerWeek, newSalaryPerWeek));
        return player;
    }
}
