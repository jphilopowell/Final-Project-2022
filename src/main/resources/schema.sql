DROP TABLE IF EXISTS bats;
CREATE TABLE bats (
id BIGINT NOT NULL AUTO_INCREMENT,
make VARCHAR(20),
model VARCHAR(20),
weight FLOAT,
age INT,
PRIMARY KEY(id)
);