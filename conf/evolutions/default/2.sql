# adding password column to user

# --- !Ups

ALTER TABLE user ADD password varchar(128) not null

# --- !Downs

ALTER TABLE user drop COLUMN password
