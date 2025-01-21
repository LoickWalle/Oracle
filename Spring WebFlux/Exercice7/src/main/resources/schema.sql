DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

INSERT INTO users (username, email, active)
VALUES
    ('john_doe', 'john.doe@example.com', TRUE),
    ('jane_smith', 'jane.smith@example.com', TRUE),
    ('mike_jones', 'mike.jones@example.com', FALSE);