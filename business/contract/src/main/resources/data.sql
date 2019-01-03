 INSERT INTO deposit (
  version, customer_id, doc_number, date_creation, sum, term, rate, interest_payment,
  compliance_status, active)
  VALUES
  ( 1, 2, '134', '2018-05-27', 100000, 12, 9.234, 'MONTHLY', 'GRANTED', TRUE),
  ( 1, 3, '145', '2018-02-12', 120000, 24, 7.125, 'MONTHLY', 'GRANTED', TRUE);

    INSERT INTO loan (
  version, customer_id, doc_number, date_creation, payout_date, payoff_date, sum, term, rate,
  full_loan_coast, status, payment_interval, compliance_status, active)
  VALUES
  (1, 1, '223', '2018-04-23', '201-01-23', NULL , 100000, 12, 16.34, 17.456, 'NORMAL', 'MONTHLY',  'GRANTED', TRUE ),
  (1, 3, '223', '2018-04-23', '201-01-23', NULL , 135000, 18, 18.2, 21.455, 'NORMAL', 'MONTHLY',  'GRANTED', TRUE );
