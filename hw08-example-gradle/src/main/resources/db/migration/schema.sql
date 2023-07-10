DROP TABLE IF EXISTS Client, Manager;

CREATE TABLE IF NOT EXISTS Client (
    id serial,
    name varchar(50)
);

CREATE TABLE IF NOT EXISTS Manager (
    no serial,
    label varchar(50),
    param1 varchar(50)
);