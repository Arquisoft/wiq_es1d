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
    password varchar(255) NOT NULL,
    total_games NUMERIC NOT NULL default 0,
    correct_answers NUMERIC NOT NULL default 0,
    incorrect_answers NUMERIC NOT NULL default 0,
    total_questions_answered NUMERIC NOT NULL default 0,
    last_category_played varchar(255) NOT NULL,
    UNIQUE KEY unique_name_password (name)
);

CREATE USER 'sygAdmin'@'%' IDENTIFIED BY 'sygAdmin';
GRANT ALL PRIVILEGES ON `syg-db`.* TO 'sygAdmin'@'%';