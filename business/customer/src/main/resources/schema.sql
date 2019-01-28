DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS passport;
DROP TABLE IF EXISTS customer CASCADE ;
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS bank_account;
DROP SEQUENCE IF EXISTS global_seq;


CREATE SEQUENCE global_seq START 10000;
CREATE TABLE address
(
    id                BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    version           INTEGER DEFAULT 0,
    active            BOOL DEFAULT TRUE,
    trash             BOOL DEFAULT FALSE,
    created_date      BIGINT,
    modified_date     BIGINT,

    customer_id       BIGINT                    NOT NULL,
    addr_type         VARCHAR                   NOT NULL,
    zip_code          VARCHAR                   NOT NULL,
    region            VARCHAR                   NOT NULL,
    city              VARCHAR                   NOT NULL,
    street            VARCHAR                   NOT NULL,
    house             VARCHAR                   NOT NULL,
    flat              VARCHAR                   NOT NULL

)   WITH (
    OIDS = FALSE
) TABLESPACE pg_default;
CREATE INDEX address_customer_id_idx ON address (customer_id);

CREATE TABLE passport
(
    id                  BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    version             INTEGER DEFAULT 0,
    active              BOOL DEFAULT TRUE,
    trash               BOOL DEFAULT FALSE,
    created_date        BIGINT,
    modified_date       BIGINT,

    customer_id         BIGINT                NOT NULL,
    pass_serial         VARCHAR               NOT NULL,
    pass_number         VARCHAR               NOT NULL,
    pass_date           TIMESTAMP             NOT NULL,
    passport_office     VARCHAR               NOT NULL,
    office_code         VARCHAR               NOT NULL,
    expiration_date     TIMESTAMP             NOT NULL

)    WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX passport_customer_id_idx ON passport (customer_id);

CREATE TABLE bank_account
(
    id                      BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    version                 INTEGER DEFAULT 0,
    active                  BOOL DEFAULT TRUE,
    trash                   BOOL DEFAULT FALSE,
    created_date            BIGINT,
    modified_date           BIGINT,

    customer_id             BIGINT                NOT NULL,
    beneficiary_name        VARCHAR               NOT NULL,
    account_number          VARCHAR               NOT NULL,
    bank_name               VARCHAR               NOT NULL,
    bank_inn                VARCHAR               NOT NULL,
    bank_bik                VARCHAR               NOT NULL,
    correspondent_account   VARCHAR               NOT NULL

)    WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX bank_account_customer_id_idx ON bank_account (customer_id);

CREATE TABLE customer
(
    id                  BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    version             INTEGER DEFAULT 0,
    active              BOOL DEFAULT TRUE,
    trash               BOOL DEFAULT FALSE,
    created_date        BIGINT,
    modified_date       BIGINT,

    customer_uuid       VARCHAR,
    customer_name       VARCHAR               NOT NULL,
    middle_name         VARCHAR               NOT NULL,
    surname             VARCHAR               NOT NULL,
    maiden_name         VARCHAR,
    gender              VARCHAR               NOT NULL,
    birth_place         VARCHAR               NOT NULL,
    birthday            TIMESTAMP             NOT NULL,
    email               VARCHAR               NOT NULL,
    inn                 VARCHAR,
    snils               VARCHAR               NOT NULL,
    compliance_status   VARCHAR               NOT NULL

)    WITH (
OIDS = FALSE
)TABLESPACE pg_default;

CREATE TABLE phone
(
    id                  BIGINT PRIMARY KEY DEFAULT nextval('global_seq'),
    version             INTEGER DEFAULT 0,
    active              BOOL DEFAULT TRUE,
    trash               BOOL DEFAULT FALSE,
    created_date        BIGINT,
    modified_date       BIGINT,

    customer_id         BIGINT                NOT NULL,
    phone_type          VARCHAR               NOT NULL,
    phone_number        VARCHAR               NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE

) WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX phone_customer_id_idx ON phone (customer_id);
CREATE INDEX phone_number_idx ON phone (phone_number);