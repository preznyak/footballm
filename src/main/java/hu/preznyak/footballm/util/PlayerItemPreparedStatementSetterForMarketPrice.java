package hu.preznyak.footballm.util;

import hu.preznyak.footballm.data.Player;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerItemPreparedStatementSetterForMarketPrice implements ItemPreparedStatementSetter<Player> {
    @Override
    public void setValues(Player player, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDouble(1, player.getMarketPrice());
        preparedStatement.setInt(2, player.getId());

    }
}
