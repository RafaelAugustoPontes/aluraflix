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

ALTER TABLE video ALTER COLUMN category_id SET NOT NULL;

CREATE TABLE IF NOT EXISTS sys_user (
    id serial PRIMARY KEY,
    name VARCHAR (2000) NOT NULL,
    email VARCHAR(2000) NOT NULL,
    password VARCHAR(2000) NOT NULL
);

CREATE TABLE IF NOT EXISTS profile (
    id serial PRIMARY KEY,
    name VARCHAR(2000) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_profile(
    id_user INT NOT NULL,
    id_profile INT NOT NULL
);

ALTER TABLE user_profile
ADD CONSTRAINT fk_id_user
FOREIGN KEY (id_user)
REFERENCES sys_user (id);

ALTER TABLE user_profile
ADD CONSTRAINT fk_id_profile
FOREIGN KEY (id_profile)
REFERENCES profile (id);
REFERENCES profile (id);