CREATE TABLE PLAYER (
    PLAYER_ID INTEGER NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    SHIRT_NUMBER INTEGER,
    POS VARCHAR(5) NOT NULL,
    MARKET_PRICE DOUBLE,
    AGE INTEGER NOT NULL,
    NATIONALITY VARCHAR(30) NOT NULL,
    SALARY_PER_WEEK DOUBLE,
    GOOD_FORM BOOLEAN,
--    TEAM_ID INTEGER,
    PRIMARY KEY (PLAYER_ID)
);

CREATE TABLE TEAM (
    TEAM_ID INTEGER NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(50) NOT NULL,
    CITY VARCHAR(30) NOT NULL,
    STADIUM VARCHAR(50) NOT NULL,
    COACH VARCHAR(40),
    BUDGET INTEGER NOT NULL,
    PRIMARY KEY (TEAM_ID)
);

-- ALTER TABLE PLAYER ADD FOREIGN KEY (TEAM_ID) REFERENCES TEAM(TEAM_ID);
