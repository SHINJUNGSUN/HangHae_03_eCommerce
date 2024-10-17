/* user */
INSERT INTO `ecommerce`.`user` (`user_name`, `point`)
VALUES
    ('Alice', 100000),
    ('Bob', 50000),
    ('Charlie', 75000),
    ('David', 120000),
    ('Eve', 30000);

/* product */
INSERT INTO `ecommerce`.`product` (`product_name`, `unit_price`, `stock`)
VALUES
    ('Laptop', 1500000, 10),
    ('Smartphone', 800000, 20),
    ('Headphones', 150000, 50),
    ('Keyboard', 50000, 30),
    ('Mouse', 30000, 40),
    ('Monitor', 300000, 15),
    ('Tablet', 600000, 25),
    ('Smartwatch', 250000, 35),
    ('External Hard Drive', 120000, 20),
    ('USB Cable', 10000, 100);

/* orders */
INSERT INTO `ecommerce`.`orders` (`id`, `user_id`, `order_status`, `created_at`, `updated_at`)
VALUES
   (1, 3, 'COMPLETED', NOW(), NOW()),
   (2, 2, 'COMPLETED', NOW(), NOW()),
   (3, 4, 'COMPLETED', NOW(), NOW()),
   (4, 1, 'COMPLETED', NOW(), NOW()),
   (5, 5, 'COMPLETED', NOW(), NOW()),
   (6, 1, 'COMPLETED', NOW(), NOW()),
   (7, 1, 'COMPLETED', NOW(), NOW()),
   (8, 5, 'COMPLETED', NOW(), NOW()),
   (9, 2, 'COMPLETED', NOW(), NOW()),
   (10, 1, 'COMPLETED', NOW(), NOW());

/* order_line */
INSERT INTO `ecommerce`.`order_line` (`order_id`, `product_id`, `product_name`, `unit_price`, `quantity`, `created_at`)
VALUES
    (1, 2, 'Smartphone', 800000, 3, NOW()),
    (1, 7, 'Tablet', 600000, 5, NOW()),
    (1, 4, 'Keyboard', 50000, 4, NOW()),
    (2, 6, 'Monitor', 300000, 3, NOW()),
    (2, 3, 'Headphones', 150000, 1, NOW()),
    (2, 8, 'Smartwatch', 250000, 1, NOW()),
    (3, 7, 'Tablet', 600000, 3, NOW()),
    (3, 1, 'Laptop', 1500000, 4, NOW()),
    (3, 2, 'Smartphone', 800000, 5, NOW()),
    (4, 4, 'Keyboard', 50000, 1, NOW()),
    (4, 10, 'USB Cable', 10000, 5, NOW()),
    (4, 8, 'Smartwatch', 250000, 2, NOW()),
    (5, 1, 'Laptop', 1500000, 4, NOW()),
    (5, 5, 'Mouse', 30000, 1, NOW()),
    (5, 9, 'External Hard Drive', 120000, 3, NOW()),
    (6, 2, 'Smartphone', 800000, 1, NOW()),
    (6, 8, 'Smartwatch', 250000, 2, NOW()),
    (7, 8, 'Smartwatch', 250000, 1, NOW()),
    (7, 2, 'Smartphone', 800000, 4, NOW()),
    (7, 4, 'Keyboard', 50000, 4, NOW()),
    (8, 4, 'Keyboard', 50000, 4, NOW()),
    (8, 6, 'Monitor', 300000, 2, NOW()),
    (9, 4, 'Keyboard', 50000, 3, NOW()),
    (9, 10, 'USB Cable', 10000, 2, NOW()),
    (9, 1, 'Laptop', 1500000, 2, NOW()),
    (10, 7, 'Tablet', 600000, 2, NOW()),
    (10, 1, 'Laptop', 1500000, 1, NOW());