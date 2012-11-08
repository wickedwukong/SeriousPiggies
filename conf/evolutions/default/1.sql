# User schema

# --- !Ups

CREATE SEQUENCE user_id_seq;
CREATE TABLE user (
    id integer NOT NULL DEFAULT nextval('user_id_seq'),
    email varchar(128),
    firstName varchar(256),
    lastName varchar(256)
);

# --- !Downs

DROP TABLE user;
DROP SEQUENCE user_id_seq;