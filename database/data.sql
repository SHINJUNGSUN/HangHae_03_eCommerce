INSERT INTO `ecommerce`.`user` (`user_id`, `password`, `user_name`, `point`, `created_at`, `updated_at`)
VALUES
    ('user001', 'password123', 'John Doe', 10000, NOW(), NOW()),
    ('user002', 'password456', 'Jane Smith', 15000, NOW(), NOW()),
    ('user003', 'password789', 'Alice Johnson', 5000, NOW(), NOW()),
    ('user004', 'password101', 'Bob Brown', 20000, NOW(), NOW()),
    ('user005', 'password202', 'Charlie Davis', 2500, NOW(), NOW());

INSERT INTO `ecommerce`.`product` (`product_name`, `unit_price`, `stock`, `created_at`, `updated_at`)
VALUES
    ('Laptop', 1200000, 50, NOW(), NOW()),
    ('Smartphone', 800000, 100, NOW(), NOW()),
    ('Tablet', 600000, 70, NOW(), NOW()),
    ('Smartwatch', 300000, 200, NOW(), NOW()),
    ('Headphones', 150000, 150, NOW(), NOW()),
    ('Monitor', 400000, 80, NOW(), NOW()),
    ('Keyboard', 70000, 300, NOW(), NOW()),
    ('Mouse', 50000, 350, NOW(), NOW()),
    ('Printer', 200000, 40, NOW(), NOW()),
    ('Speaker', 250000, 120, NOW(), NOW());