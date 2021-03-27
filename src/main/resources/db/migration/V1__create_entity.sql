CREATE TABLE client (id serial PRIMARY KEY, name varchar (100) NOT NULL, lastname varchar (100));

CREATE TABLE account (id serial PRIMARY KEY, number int UNIQUE, fund int, client_id int REFERENCES client(id));

INSERT INTO client
VALUES
(1, 'Maxim', 'Palarkin'),
(2, 'John', 'Dorian'),
(3, 'Fedor', 'Dostoevskiy'),
(4, 'Emelyan', 'Pugachev');

INSERT INTO account
VALUES
(1, 123113, 0, 1),
(2, 293113, 124, 1),
(3, 444123, 550, 1),
(4, 131233, 144, 2),
(5, 679534, 10000, 2),
(6, 978541, 4, 3);