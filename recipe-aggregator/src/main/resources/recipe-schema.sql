DROP TABLE IF EXISTS recipes;

CREATE TABLE recipes (
  id    INT          NOT NULL AUTO_INCREMENT,
  title VARCHAR(200) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (title)
);

