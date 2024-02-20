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

CREATE USER 'sygAdmin'@'%' IDENTIFIED BY 'sygAdmin';
GRANT ALL PRIVILEGES ON `syg-db`.* TO 'sygAdmin'@'%';