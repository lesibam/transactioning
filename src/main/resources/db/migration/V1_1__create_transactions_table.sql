CREATE TABLE if NOT EXISTS transactions(
    id BIGSERIAL PRIMARY KEY,
    transaction_timestamp TIMESTAMP NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    description VARCHAR(255) NOT NULL,
    account VARCHAR(50) NOT NULL,
    transaction_type VARCHAR(10) NOT NULL,
    category VARCHAR(50) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    store_name VARCHAR(100) NOT NULL,
    location VARCHAR(255) NOT NULL,
    reference_number VARCHAR(100) NOT NULL,
    reference VARCHAR(100) NOT NULL,
    currency VARCHAR(3) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
) ;

CREATE INDEX idx_tx_user_id ON transactions(user_id);
CREATE INDEX idx_tx_store_name ON transactions(store_name);
CREATE INDEX idx_tx_timestamp ON transactions(transaction_timestamp);
CREATE INDEX idx_tx_category ON transactions(category);