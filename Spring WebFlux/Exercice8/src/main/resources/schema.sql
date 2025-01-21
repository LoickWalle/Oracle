DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE IF NOT EXISTS orders (
                                      id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    customer_name VARCHAR(255) NOT NULL,
    total_amount DOUBLE PRECISION NOT NULL,
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDING', 'SHIPPED', 'DELIVERED')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

INSERT INTO orders (customer_name, total_amount, status, created_at)
VALUES
    ('Donald', 100.50, 'PENDING', CURRENT_TIMESTAMP),
    ('Picsou', 250.75, 'SHIPPED', CURRENT_TIMESTAMP),
    ('Picsou', 400.75, 'PENDING', CURRENT_TIMESTAMP),
    ('Riri', 75.00, 'DELIVERED', CURRENT_TIMESTAMP);
