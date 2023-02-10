package hu.preznyak.footballm.batch;

import hu.preznyak.footballm.data.Player;
import hu.preznyak.footballm.processor.PlayerItemProcessor;
import hu.preznyak.footballm.util.PlayerItemPreparedStatementSetter;
import hu.preznyak.footballm.util.PlayerRowMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class MarketPriceManipulator {

    private static final String SELECT_PLAYERS_SQL = "select PLAYER_ID, " +
            "NAME, POS, SHIRT_NUMBER, MARKET_PRICE, " +
            "AGE, NATIONALITY, GOOD_FORM, SALARY_PER_WEEK from PLAYER order by PLAYER_ID";
    private static final String UPDATE_PLAYERS_SQL = "UPDATE player SET market_price = ? WHERE player_id = ?";
    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    public JobLauncher jobLauncher;
    @Autowired
    public DataSource dataSource;

    @Bean
    public ItemReader<Player> itemReader() {

        return new JdbcCursorItemReaderBuilder<Player>()
                .dataSource(dataSource)
                .name("jdbcCursorItemReader")
                .sql(SELECT_PLAYERS_SQL)
                .rowMapper(new PlayerRowMapper())
                .build();
    }

    @Bean
    public PlayerItemProcessor processor() {
        return new PlayerItemProcessor();
    }

    @Bean
    public ItemWriter<Player> itemWriter() {

        return new JdbcBatchItemWriterBuilder<Player>()
                .dataSource(dataSource)
                .sql(UPDATE_PLAYERS_SQL)
                .itemPreparedStatementSetter(new PlayerItemPreparedStatementSetter())
                .build();
    }

    @Bean
    public Job manipulateMarketPriceJob() {
        return this.jobBuilderFactory.get("manipulateMarketPriceJob")
                .incrementer(new RunIdIncrementer())
                .start(changeMarketPriceStep()).build();
    }

    @Bean
    public Step changeMarketPriceStep() {
        return this.stepBuilderFactory.get("changeMarketPriceStep")
                .<Player, Player>chunk(5)
                .reader(itemReader())
                .processor(processor())
                .writer(itemWriter())
                .build();
    }

}
