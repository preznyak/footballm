CREATE TABLE player (
    id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    shirt_number INTEGER,
    pos VARCHAR(5) NOT NULL,
    market_price DOUBLE,
    age INTEGER NOT NULL,
    nationality VARCHAR(30) NOT NULL,
    salary_per_week DOUBLE,
    good_form BOOLEAN
);