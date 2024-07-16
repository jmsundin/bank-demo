DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accounts;

CREATE TABLE users (
    username TEXT PRIMARY KEY,
    password TEXT
);

CREATE TABLE accounts (
    account_number INTEGER PRIMARY KEY,
    balance REAL,
    owner TEXT,
    FOREIGN KEY (owner) REFERENCES users (username)

);

INSERT INTO users VALUES ('admin', 'admin');