DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS passport;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS deposit;
DROP TABLE IF EXISTS loan;
DROP TABLE IF EXISTS phone;
DROP TABLE IF EXISTS payment;
DROP SEQUENCE IF EXISTS global_seq;
DROP SEQUENCE IF EXISTS doc_seq;

CREATE SEQUENCE global_seq START 10000;
CREATE SEQUENCE doc_seq START 10000;


CREATE TABLE address
(
    id          INTEGER PRIMARY KEY,
    customer_id INTEGER                 NOT NULL,
    addr_type   INTEGER                 NOT NULL,
    version     INTEGER                 NOT NULL,
    zip_code    VARCHAR                 NOT NULL,
    region      VARCHAR                 NOT NULL,
    city        VARCHAR                 NOT NULL,
    street      VARCHAR                 NOT NULL,
    house       VARCHAR                 NOT NULL,
    flat        VARCHAR                 NOT NULL,
    active      BOOL DEFAULT TRUE,
    registered  TIMESTAMP DEFAULT now() NOT NULL
)   WITH (
    OIDS = FALSE
) TABLESPACE pg_default;
CREATE INDEX address_customer_id_idx ON address (customer_id);

CREATE TABLE passport
(
    id                  INTEGER PRIMARY KEY,
    version             INTEGER                 NOT NULL,
    customer_id         INTEGER                 NOT NULL,
    pass_serial         VARCHAR                 NOT NULL,
    pass_number         VARCHAR                 NOT NULL,
    pass_date           TIMESTAMP               NOT NULL,
    passport_office     VARCHAR                 NOT NULL,
    office_code         VARCHAR                 NOT NULL,
    expiration_date     TIMESTAMP               NOT NULL,
    compliance_status   INTEGER                 NOT NULL,
    active              BOOL DEFAULT TRUE,
    registered          TIMESTAMP DEFAULT now() NOT NULL
)    WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX passport_customer_id_idx ON passport (customer_id);

CREATE TABLE customer
(
    id                  INTEGER PRIMARY KEY ,
    version             INTEGER                 NOT NULL,
    customer_name       VARCHAR                 NOT NULL,
    middle_name         VARCHAR                 NOT NULL,
    surname             VARCHAR                 NOT NULL,
    maiden_name         VARCHAR,
    gender              INTEGER                 NOT NULL,
    birth_place         VARCHAR                 NOT NULL,
    birthday            TIMESTAMP               NOT NULL,
    email               VARCHAR                 NOT NULL,
    inn                 VARCHAR,
    snils               VARCHAR                 NOT NULL,
    compliance_status   INTEGER                 NOT NULL,
    active              boolean                 NOT NULL,
    registered          TIMESTAMP DEFAULT now() NOT NULL
)    WITH (
OIDS = FALSE
)TABLESPACE pg_default;

CREATE TABLE deposit
(
    id                  INTEGER PRIMARY KEY,
    version             INTEGER                 NOT NULL,
    customer_id         INTEGER                 NOT NULL,
    doc_number          VARCHAR                 NOT NULL,
    date_creation       TIMESTAMP               NOT NULL,
    sum                 NUMERIC(19,2)           NOT NULL,
    term                INTEGER                 NOT NULL,
    rate                NUMERIC(19,3)           NOT NULL,
    interest_payment    INTEGER                 NOT NULL,
    compliance_status   INTEGER                 NOT NULL,
    active              BOOL DEFAULT TRUE,
    registered          TIMESTAMP DEFAULT now() NOT NULL
)    WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX deposit_customer_id_idx ON deposit (customer_id);

CREATE TABLE loan
(
    id                  INTEGER PRIMARY KEY,
    version             INTEGER                 NOT NULL,
    customer_id         INTEGER                 NOT NULL,
    doc_number          VARCHAR                 NOT NULL,
    date_creation       TIMESTAMP               NOT NULL,
    payout_date         TIMESTAMP,
    payoff_date         TIMESTAMP,
    sum                 NUMERIC(19,2)           NOT NULL,
    term                INTEGER                 NOT NULL,
    rate                NUMERIC(19,3)           NOT NULL,
    full_loan_coast     NUMERIC(19,3),
    status              INTEGER                 NOT NULL,
    payment_interval    INTEGER                 NOT NULL,
    compliance_status   INTEGER                 NOT NULL,
    active              BOOL DEFAULT TRUE,
    registered          TIMESTAMP DEFAULT now() NOT NULL
)    WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX loan_customer_id_idx ON loan (customer_id);
CREATE INDEX loan_doc_customer_id_idx ON loan (doc_number);

CREATE TABLE phone
(
    id                  INTEGER PRIMARY KEY,
    version             INTEGER                 NOT NULL,
    customer_id         INTEGER                 NOT NULL,
    phone_type          INTEGER                 NOT NULL,
    phone_number        VARCHAR                 NOT NULL,
    active              BOOL DEFAULT TRUE,
    registered          TIMESTAMP DEFAULT now() NOT NULL
)    WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX phone_customer_id_idx ON phone (customer_id);
CREATE INDEX phone_number_idx ON phone (phone_number);


CREATE TABLE payment
(
    id                  INTEGER PRIMARY KEY,
    version             INTEGER                 NOT NULL,
    payment_date        TIMESTAMP               NOT NULL,
    sum                 NUMERIC(19,2)           NOT NULL,
    customer_id         INTEGER                 NOT NULL,
    contract_id         INTEGER                 NOT NULL,
    payment_document    VARCHAR                 NOT NULL,
    target              INTEGER                 NOT NULL,
    flow                INTEGER                 NOT NULL,
    active              BOOL DEFAULT TRUE,
    registered          TIMESTAMP DEFAULT now() NOT NULL
)    WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX payment_customer_id_idx ON payment (customer_id);
CREATE INDEX payment_contract_id_idx ON payment (contract_id);
