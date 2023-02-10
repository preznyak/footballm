package hu.preznyak.footballm.util;

import hu.preznyak.footballm.data.Player;
import hu.preznyak.footballm.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class PlayerRowMapper implements RowMapper<Player> {

    @Autowired
    private TeamRepository teamRepository;
    @Override
    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
        Player player = new Player();
        player.setId(rs.getInt("player_id"));
        player.setName(rs.getString("name"));
        player.setPosition(rs.getString("pos"));
        player.setMarketPrice(rs.getDouble("market_price"));
        player.setNationality(rs.getString("nationality"));
        player.setSalaryPerWeek(rs.getDouble("salary_per_week"));
        player.setGoodForm(rs.getBoolean("good_form"));
        player.setShirtNumber(rs.getInt("shirt_number"));
        player.setAge(rs.getInt("age"));
        return player;

    }
}
