CREATE TABLE sms_message
(
    sms_id       BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    phone_number VARCHAR(20) NOT NULL,
    text         TEXT        NOT NULL,
    status       VARCHAR(10) NOT NULL CHECK (status IN ('VALID', 'INVALID', 'SPAM')),
    created_at   TIMESTAMP   NOT NULL DEFAULT now()
);

CREATE INDEX idx_phone_number ON sms_message(phone_number);