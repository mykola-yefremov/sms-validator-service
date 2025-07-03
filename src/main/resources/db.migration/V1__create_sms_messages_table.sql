CREATE TABLE sms_messages
(
    id                 BIGSERIAL PRIMARY KEY,
    phone_number       VARCHAR(20)              NOT NULL,
    text               TEXT                     NOT NULL,
    status             VARCHAR(20)              NOT NULL,
    validation_details TEXT,
    created_at         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    version            BIGINT                   NOT NULL DEFAULT 0,

    CONSTRAINT chk_sms_phone_number_format
        CHECK (phone_number ~ '^\+[1-9]\d{1,14}$'),
    CONSTRAINT chk_sms_text_length
        CHECK (char_length(text) <= 1600),
    CONSTRAINT chk_sms_status_valid
        CHECK (status IN ('VALID', 'INVALID', 'SPAM', 'PENDING', 'FAILED')),
    CONSTRAINT chk_sms_validation_details_length
        CHECK (validation_details IS NULL OR char_length(validation_details) <= 1000)
);

CREATE INDEX idx_sms_phone_number ON sms_messages (phone_number);
CREATE INDEX idx_sms_status ON sms_messages (status);
CREATE INDEX idx_sms_created_at ON sms_messages (created_at);
CREATE INDEX idx_sms_status_created_at ON sms_messages (status, created_at);

CREATE INDEX idx_sms_pending_messages ON sms_messages (created_at)
    WHERE status = 'PENDING';
CREATE INDEX idx_sms_failed_messages ON sms_messages (created_at)
    WHERE status = 'FAILED';

COMMENT ON TABLE sms_messages IS 'Stores SMS messages with validation status and audit information';
COMMENT ON COLUMN sms_messages.id IS 'Primary key - auto-generated sequence';
COMMENT ON COLUMN sms_messages.phone_number IS 'Phone number in international E.164 format';
COMMENT ON COLUMN sms_messages.text IS 'SMS message content (max 1600 characters)';
COMMENT ON COLUMN sms_messages.status IS 'Current validation status of the message';
COMMENT ON COLUMN sms_messages.validation_details IS 'Additional details about validation process';
COMMENT ON COLUMN sms_messages.created_at IS 'Timestamp when record was created';
COMMENT ON COLUMN sms_messages.updated_at IS 'Timestamp when record was last updated';
COMMENT ON COLUMN sms_messages.version IS 'Version number for optimistic locking';