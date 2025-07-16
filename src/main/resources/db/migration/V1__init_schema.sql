CREATE TABLE IF NOT EXISTS employee
(
    id         BIGINT  NOT NULL AUTO_INCREMENT,
    name       VARCHAR(255),
    age        INTEGER,
    gender     VARCHAR(255),
    salary     FLOAT(53),
    active     BOOLEAN NOT NULL DEFAULT TRUE,
    company_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE companies
(
    id   BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
)

