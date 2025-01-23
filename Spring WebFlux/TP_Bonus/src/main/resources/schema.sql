DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE IF NOT EXISTS users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    admin BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS products (
                                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    stock INT NOT NULL
    );

INSERT INTO users (username, password, admin)
VALUES
    ('user', 'user', FALSE),
    ('admin', 'admin', TRUE);

INSERT INTO products (name, price, stock)
VALUES
    ('Xbox Series X', 499.99, 100),
    ('PlayStation 5', 499.99, 80),
    ('Nintendo Switch', 299.99, 150),
    ('Razer Gaming Mouse', 69.99, 200),
    ('Gaming Chair', 159.99, 60),
    ('DualSense Wireless Controller', 69.99, 120),
    ('Xbox Game Pass Subscription', 14.99, 300),
    ('Red Dead Redemption 2', 59.99, 180),
    ('Cyberpunk 2077', 39.99, 120),
    ('Logitech G Pro Keyboard', 129.99, 90);