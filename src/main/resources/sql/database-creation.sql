CREATE TABLE IF NOT EXISTS person
(
    person_id     INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT,
    lastname      VARCHAR(45)  NOT NULL,
    firstname     VARCHAR(45)  NOT NULL,
    nickname      VARCHAR(45)  NOT NULL,
    phone_number  VARCHAR(15)  NULL,
    address       VARCHAR(200) NULL,
    email_address VARCHAR(150) NULL,
    birth_date    DATE         NULL,
    category      VARCHAR(45)  NULL,
    name_file_icon VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS category
(
    category_id   INTEGER     NOT NULL PRIMARY KEY AUTOINCREMENT,
    category_name VARCHAR(45) NOT NULL
);