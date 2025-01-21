DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    price DOUBLE PRECISION NOT NULL,
    stock INT NOT NULL
    );

INSERT INTO products (name, price, stock)
VALUES
    ('iPhone 13', 799.99, 150),
    ('Galaxy S21', 699.99, 200),
    ('PlayStation 5', 499.99, 50),
    ('Air Max 270', 149.99, 120),
    ('Dell Laptop', 999.99, 80);
