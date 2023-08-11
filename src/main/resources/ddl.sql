USE animals;

DROP TABLE animal;

CREATE TABLE animal
(
    id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    age INT(11),
    name VARCHAR(45),
    tail BOOL
)