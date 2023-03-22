DROP DATABASE IF EXISTS authdb;
CREATE DATABASE authdb;
USE authdb;

CREATE TABLE IF NOT EXISTS user (
      id INTEGER NOT NULL AUTO_INCREMENT,
      first_name VARCHAR(100) NOT NULL,
      last_name VARCHAR(100) NOT NULL,
      user_name VARCHAR(50) NOT NULL,
      password VARCHAR(255) NOT NULL,
      salt VARCHAR(50) NOT NULL,
      street_name VARCHAR(100) NOT NULL,
      street_number VARCHAR(20) NOT NULL,
      postal_code VARCHAR(10) NOT NULL,
      city VARCHAR(50) NOT NULL,
      province VARCHAR(100) NOT NULL,
      country VARCHAR(50) NOT NULL,

      PRIMARY KEY (id)
);

CREATE INDEX ix_user_name  ON user (
   user_name
);
