CREATE TABLE IF NOT EXISTS patient
(
    id        VARCHAR(36) NOT NULL,
    lastname  VARCHAR(255)  NOT NULL,
    firstname VARCHAR(255)  NOT NULL,
    birthdate VARCHAR(255)  NOT NULL,
    gender    VARCHAR(1)    NOT NULL,
    address   VARCHAR(255),
    phone     VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

