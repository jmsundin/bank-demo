DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accounts;

CREATE TABLE users (
    username TEXT,
    password TEXT
);

INSERT INTO users VALUES ('admin', 'admin');