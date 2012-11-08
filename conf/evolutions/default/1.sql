# User schema

# --- !Ups

CREATE SEQUENCE user_id_seq;
CREATE TABLE user (
    id integer NOT NULL DEFAULT nextval('user_id_seq') PRIMARY KEY,
    email varchar(128) not null,
    firstName varchar(256) not null,
    lastName varchar(256) not null
);

# --- !Downs

DROP TABLE user;
DROP SEQUENCE user_id_seq;