DROP TABLE IF EXISTS deposit;
DROP TABLE IF EXISTS loan;
DROP SEQUENCE IF EXISTS loan_seq;
DROP SEQUENCE IF EXISTS deposit_seq;

CREATE SEQUENCE loan_seq START 100000;
CREATE SEQUENCE deposit_seq START 100000;
CREATE TABLE deposit
(
    id                  BIGSERIAL,
    version             BIGINT                  NOT NULL,
    active              BOOL DEFAULT TRUE,
    trash               BOOL DEFAULT FALSE,
    created_date        BIGINT,
    modified_date       BIGINT,

    customer_id         BIGINT                  NOT NULL,
    doc_number          VARCHAR                 NOT NULL,
    date_creation       TIMESTAMP               NOT NULL,
    sum                 NUMERIC(19,2)           NOT NULL,
    term                INTEGER                 NOT NULL,
    rate                NUMERIC(19,3)           NOT NULL,
    interest_payment    VARCHAR                 NOT NULL,
    compliance_status   VARCHAR                 NOT NULL,
)    WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX deposit_customer_id_idx ON deposit (customer_id);

CREATE TABLE loan
(
    id                  BIGSERIAL,
    version             INTEGER                 NOT NULL,
    created_date        INTEGER,
    modified_date       INTEGER,

    customer_id         BIGINT                 NOT NULL,
    doc_number          VARCHAR                 NOT NULL,
    date_creation       TIMESTAMP               NOT NULL,
    payout_date         TIMESTAMP,
    payoff_date         TIMESTAMP,
    sum                 NUMERIC(19,2)           NOT NULL,
    term                INTEGER                 NOT NULL,
    rate                NUMERIC(19,3)           NOT NULL,
    full_loan_coast     NUMERIC(19,3),
    status              VARCHAR                 NOT NULL,
    payment_interval    VARCHAR                 NOT NULL,
    compliance_status   VARCHAR                 NOT NULL,
)    WITH (
         OIDS = FALSE
    )TABLESPACE pg_default;
CREATE INDEX loan_customer_id_idx ON loan (customer_id);
CREATE INDEX loan_doc_customer_id_idx ON loan (doc_number);
