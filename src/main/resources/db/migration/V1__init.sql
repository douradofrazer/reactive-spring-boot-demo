CREATE TABLE users (
    id serial NOT NULL,
    first_name VARCHAR (100) NOT NULL,
	last_name VARCHAR (100) NOT NULL,
	email VARCHAR (255) UNIQUE NOT NULL,
	created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY (id)
);

INSERT INTO users (first_name, last_name, email) VALUES ('Wesley', 'Sneijder', 'wesley.sneijder@test.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Robin Van', 'Persie', 'robinvan.persie@test.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Arjen', 'Robben', 'arjen.robben@test.com');
INSERT INTO users (first_name, last_name, email) VALUES ('Dennis', 'Bergkamp', 'dennis.bergkamp@test.com');