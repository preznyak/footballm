package hu.preznyak.footballm.processor;

import hu.preznyak.footballm.data.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PlayerItemProcessor implements ItemProcessor<Player, Player> {
    private static final Logger log = LoggerFactory.getLogger(PlayerItemProcessor.class);

    @Override
    public Player process(Player player) {
        double currentMarketPrice = player.getMarketPrice();
        //Changing maximum 3% of the price ><
        double change = Math.random() * 3;
        int formParameter = player.isGoodForm() ? 1 : -1;
        double newMarketPrice = currentMarketPrice + (currentMarketPrice * (change/100) * formParameter);
        player.setMarketPrice(newMarketPrice);
        log.info(String.format("Market price of %s changed from %f to %f", player.getName(), currentMarketPrice, newMarketPrice));
        return player;
    }
}
