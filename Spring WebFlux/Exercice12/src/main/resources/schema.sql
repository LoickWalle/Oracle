DROP TABLE IF EXISTS products CASCADE;

CREATE TABLE IF NOT EXISTS products (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE PRECISION NOT NULL
    );

INSERT INTO products (name, quantity, price)
VALUES
    ('Laptop', 5, 1200.50),
    ('Tablet', 10, 120.50),
    ('Phone', 15, 12.50)