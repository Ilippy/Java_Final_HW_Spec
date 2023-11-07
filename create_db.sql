DROP DATABASE IF EXISTS human_friends;
CREATE DATABASE human_friends;
USE human_friends;


-- CREATE TABLES
DROP TABLE IF EXISTS animalType;
CREATE TABLE animalType(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_type_name VARCHAR(30) NOT NULL
);

DROP TABLE IF EXISTS animalKind;
CREATE TABLE animalKind(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_kind_name VARCHAR(30) NOT NULL,
    animal_type_id INT NOT NULL,
    FOREIGN KEY (animal_type_id) REFERENCES animalType(id)
);

DROP TABLE IF EXISTS command;
CREATE TABLE command(
	id INT PRIMARY KEY AUTO_INCREMENT,
    command_name VARCHAR(30) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS animal;
CREATE TABLE animal(
	id INT PRIMARY KEY AUTO_INCREMENT,
    animal_name VARCHAR(30) NOT NULL,
    birthday DATE,
    animal_kind_id INT,
    FOREIGN KEY (animal_kind_id) REFERENCES animalKind(id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS animal_command;
CREATE TABLE animal_command(
	id INT PRIMARY KEY AUTO_INCREMENT,
	animal_id INT NOT NULL,
    command_id INT NOT NULL,
    FOREIGN KEY (animal_id) REFERENCES animal(id) ON DELETE CASCADE,
    FOREIGN KEY (command_id) REFERENCES command(id) ON DELETE CASCADE
);

-- INSERT VALUES INTO TABLES
INSERT INTO animalType(animal_type_name)
VALUES
	('Pet'), ('Pack Animals');

-- SELECT * FROM  animalType;

INSERT INTO animalKind(animal_kind_name, animal_type_id)
VALUES
	('Dog', 1),
    ('Cat', 1),
    ('Hamster', 1),
    ('Horse', 2),
    ('Camel', 2),
    ('Donkey', 2);

-- SELECT * FROM animalKind;

INSERT INTO command(command_name)
VALUES
	('Sit'), ('Stay'), ('Fetch'), ('Pounce'), ('Roll'), ('Hide'),
    ('Paw'), ('Bark'), ('Scratch'), ('Spin'), ('Meow'), ('Jump'),
    ('Trot'), ('Canter'), ('Gallop'), ('Walk'), ('Carry Load'),
    ('Bray'), ('Kick'), ('Run');

-- SELECT * FROM command; --  ORDER BY command_name;
-- SET SQL_SAFE_UPDATES = 0;
-- DELETE FROM animal;
-- ALTER TABLE animal AUTO_INCREMENT = 1;
-- SET SQL_SAFE_UPDATES = 1;

INSERT INTO animal(animal_name, animal_kind_id, birthday)
VALUES
	('Fido', 1, '2020-01-01'),
    ('Whiskers', 2, '2019-05-15'),
    ('Hammy', 3, '2021-03-10'),
    ('Buddy', 1, '2018-12-10'),
    ('Smudge', 2, '2020-02-20'),
    ('Peanut', 3, '2021-08-01'),
    ('Bella', 1, '2019-11-11'),
    ('Oliver', 2, '2020-06-30'),
    ('Thunder', 4, '2015-07-21'),
    ('Sandy', 5, '2016-11-03'),
    ('Eeyore', 6, '2017-09-18'),
    ('Storm', 4, '2014-05-05'),
    ('Dune', 5, '2018-12-12'),
    ('Burro', 6, '2019-01-23'),
    ('Blaze', 4, '2016-02-29'),
    ('Sahara', 5, '2015-08-14');

-- SELECT * FROM animal;

INSERT INTO animal_command(animal_id, command_id)
VALUES
	(1, 1), (1, 2), (1, 3),
    (2, 1), (2, 4),
    (3, 5), (3, 6),
    (4, 1), (4, 7), (4, 8),
    (5, 1), (5, 4), (5, 9),
    (6, 5), (6, 10),
    (7, 1), (7, 2), (7, 5),
    (8, 11), (8, 9), (8, 12),
    (9, 13), (9, 14), (9, 15),
    (10, 16), (10, 17),
    (11, 16), (11, 17), (11, 18),
    (12, 13), (12, 14),
    (13, 16), (13, 1),
    (14, 16), (14, 18), (14, 19),
    (15, 13), (15, 12), (15, 15),
    (16, 16), (16, 20);

-- SELECT * FROM animal_command;


SELECT
	a.id,
    a.animal_name,
    a.birthday,
    ak.animal_kind_name,
    at.animal_type_name,
    group_concat(c.command_name) AS Commands
FROM animal a
JOIN animalKind ak ON a.animal_kind_id = ak.id
JOIN animalType at ON ak.animal_type_id = at.id
JOIN animal_command ac ON a.id = ac.animal_id
JOIN command c ON ac.command_id = c.id
WHERE ak.animal_kind_name = 'Horse'
GROUP BY a.id;