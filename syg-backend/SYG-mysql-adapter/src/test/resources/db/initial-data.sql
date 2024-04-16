CREATE TABLE categories(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255) NOT NULL
);

CREATE TABLE questions(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text varchar(255) NOT NULL,
    time_limit NUMERIC,
    category BIGINT,
    FOREIGN KEY (category) REFERENCES categories(id)
);

CREATE TABLE answers(
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text varchar(255) NOT NULL,
    is_correct boolean DEFAULT false,
    question BIGINT,
    FOREIGN KEY (question) REFERENCES questions(id)
);

CREATE TABLE users(
	id varchar(36) PRIMARY KEY,
    name varchar(255) NOT NULL,
    total_games NUMERIC NOT NULL default 0,
    correct_answers NUMERIC NOT NULL default 0,
    incorrect_answers NUMERIC NOT NULL default 0,
    total_questions_answered NUMERIC NOT NULL default 0,
    last_category_played varchar(255) NOT NULL,
    UNIQUE KEY unique_name_password (name)
);


INSERT INTO users (ID, NAME, TOTAL_GAMES, CORRECT_ANSWERS, INCORRECT_ANSWERS, TOTAL_QUESTIONS_ANSWERED, last_category_played) VALUES ('4366fdc8-b32d-46bc-9df8-2e8ce68f0743', 'Alvaro', 4, 33, 1, 100, 'Deportes');
INSERT INTO users (ID, NAME, TOTAL_GAMES, CORRECT_ANSWERS, INCORRECT_ANSWERS, TOTAL_QUESTIONS_ANSWERED, last_category_played) VALUES ('5366fdc8-b32d-46bc-9df8-2e8ce68f0743', 'Pablo', 8, 24, 12, 100, 'Deportes');

INSERT INTO categories (ID, NAME) VALUES (1, 'animales');
INSERT INTO categories (ID, NAME) VALUES (2, 'politica');
INSERT INTO categories (ID, NAME) VALUES (3, 'planta');
INSERT INTO categories (ID, NAME) VALUES (4, 'deportes');
INSERT INTO categories (ID, NAME) VALUES (5, 'paises');

INSERT INTO questions (ID, TEXT, TIME_LIMIT, CATEGORY) VALUES (1, '¿Cual es el animal que no puede saltar?', 60, 1);
INSERT INTO questions (ID, TEXT, TIME_LIMIT, CATEGORY) VALUES (2, '¿Cual es la planta mas alta del mundo?', 60, 3);
INSERT INTO questions (ID, TEXT, TIME_LIMIT, CATEGORY) VALUES (3, '¿Que animal es un priamte?', 60, 1);
INSERT INTO questions (ID, TEXT, TIME_LIMIT, CATEGORY) VALUES (4, '¿En que continente se encuenta España?', 60, 5);
INSERT INTO questions (ID, TEXT, TIME_LIMIT, CATEGORY) VALUES (5, '¿Que pais fue el ultimo campeon del mundo?', 60, 4);
INSERT INTO questions (ID, TEXT, TIME_LIMIT, CATEGORY) VALUES (6, '¿En que pais existe la monarquia?', 60, 5);
INSERT INTO questions (ID, TEXT, TIME_LIMIT, CATEGORY) VALUES (7, '¿Cual es el animal mas rápido?', 60, 1);
INSERT INTO questions (ID, TEXT, TIME_LIMIT, CATEGORY) VALUES (8, '¿Quien es el mayor ganador de Wimbeldon de la historia?', 60, 4);

INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (1, 'cocodrilo', 0, 1);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (2, 'perro', 0, 1);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (3, 'elefante', 1, 1);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (4, 'gato', 0, 1);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (5, 'cocodrilo', 0, 2);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (6, 'cocodrilo', 1, 2);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (7, 'cocodrilo', 0, 2);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (8, 'cocodrilo', 0, 2);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (9, 'cocodrilo', 1, 3);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (10, 'cocodrilo', 0, 3);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (11, 'cocodrilo', 0, 3);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (12, 'cocodrilo', 0, 3);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (13, 'cocodrilo', 0, 4);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (14, 'cocodrilo', 0, 4);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (15, 'cocodrilo', 0, 4);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (16, 'cocodrilo', 1, 4);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (17, 'cocodrilo', 0, 5);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (18, 'cocodrilo', 0, 5);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (19, 'cocodrilo', 1, 5);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (20, 'cocodrilo', 0, 5);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (21, 'cocodrilo', 1, 6);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (22, 'cocodrilo', 0, 6);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (23, 'cocodrilo', 0, 6);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (24, 'cocodrilo', 0, 6);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (25, 'cocodrilo', 0, 7);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (26, 'cocodrilo', 0, 7);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (27, 'cocodrilo', 1, 7);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (28, 'cocodrilo', 0, 7);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (29, 'cocodrilo', 0, 8);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (30, 'cocodrilo', 1, 8);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (31, 'cocodrilo', 0, 8);
INSERT INTO answers (ID, TEXT, IS_CORRECT, QUESTION) VALUES (32, 'cocodrilo', 0, 8);
