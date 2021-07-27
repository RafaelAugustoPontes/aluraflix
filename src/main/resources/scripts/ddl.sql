CREATE TABLE IF NOT EXISTS video (
   id serial PRIMARY KEY,
   description VARCHAR (2000) NOT NULL,
   url VARCHAR(2000) NOT NULL
);


CREATE TABLE IF NOT EXISTS category (
   id serial PRIMARY KEY,
   title VARCHAR (2000) NOT NULL,
   color VARCHAR(2000) NOT NULL
);

ALTER TABLE video
ADD COLUMN category_id INT;

ALTER TABLE video
ADD CONSTRAINT fk_category
FOREIGN KEY (category_id)
REFERENCES category (id);

INSERT INTO category (id, title, color) VALUES (1, 'LIVRE', 'black');

