CREATE TABLE client (id serial PRIMARY KEY, name varchar (100) NOT NULL, lastname varchar (100), address varchar (100), photo varchar (100));

CREATE TABLE account (id serial PRIMARY KEY, number int UNIQUE, fund int, client_id int REFERENCES client(id));

INSERT INTO client
VALUES
(1, 'Maxim', 'Palarkin', 'SPb', '/images/photo_clients/default.png'),
(2, 'John', 'Dorian', 'USA', '/images/photo_clients/default.png'),
(3, 'Fedor', 'Dostoevskiy', 'Moscow', '/images/photo_clients/default.png'),
(4, 'Emelyan', 'Pugachev', 'Tomsk', '/images/photo_clients/default.png'),
(5, 'Henry', 'Cavill', 'USA, Hollywood', '/images/photo_clients/henry_cavill.jpg');

INSERT INTO account
VALUES
(1, 123113, 0, 1),
(2, 293113, 124, 1),
(3, 444123, 550, 1),
(4, 131233, 144, 2),
(5, 978541, 4, 3);