create database recipe_db;

CREATE TABLE recipes (
  recipe_title varchar(50),
  recipe_id bigint
);

INSERT INTO recipes (recipe_title, recipe_id) VALUES ('Fish and chips', 3);