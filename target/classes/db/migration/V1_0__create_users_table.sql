CREATE TABLE IF NOT EXISTS users (
    id bigserial PRIMARY KEY,
    username varchar(50) UNIQUE NOT NULL,
    password varchar(255) NOT NULL,
    roles varchar(10) NOT NULL,
    disabled bool default false,
    locked bool default false,
    created_timestamp timestamp default current_timestamp,
    password_expiry_timestamp timestamp not null
);

CREATE INDEX idx_users_username ON users(username);
CREATE INDEX idx_pwd_exp_tmstmp ON users(password_expiry_timestamp);