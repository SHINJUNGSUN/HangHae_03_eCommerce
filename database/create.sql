CREATE DATABASE `ecommerce`;
CREATE USER ecommerce@'%' IDENTIFIED BY '1q2w3e4r!!';
GRANT ALL PRIVILEGES ON ecommerce.* TO ecommerce@'%';
FLUSH PRIVILEGES;

DROP TABLE IF EXISTS `ecommerce`.`user`;
CREATE TABLE `ecommerce`.`user`
(
    `id`        BIGINT      NOT NULL    AUTO_INCREMENT  COMMENT '사용자 고유 식별자',
    `user_name` VARCHAR(20) NOT NULL    COMMENT '사용자 이름',
    `point`     BIGINT      NOT NULL    COMMENT '포인트 잔액',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `ecommerce`.`point_history`;
CREATE TABLE `ecommerce`.`point_history`
(
    `id`    BIGINT  NOT NULL    AUTO_INCREMENT  COMMENT '포인트 사용 내역 고유 식별자',
    `user_id`   BIGINT  NOT NULL    COMMENT '사용자 고유 식별자',
    `amount`    BIGINT  NOT NULL    COMMENT '사용/충전 포인트',
    `transaction_type`  VARCHAR(20) NOT NULL    COMMENT '포인트 변동 유형',
    `created_at`    DATETIME    NOT NULL    COMMENT '포인트 변동 일시',
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`)
);

DROP TABLE IF EXISTS `ecommerce`.`product`;
CREATE TABLE `ecommerce`.`product`
(
    `id`    BIGINT  NOT NULL    AUTO_INCREMENT  COMMENT '상품 고유 식별자',
    `product_name`   VARCHAR(50)  NOT NULL    COMMENT '상품명',
    `unit_price`    BIGINT  NOT NULL    COMMENT '단가',
    `stock`  BIGINT NOT NULL    COMMENT '재고',
    PRIMARY KEY (`id`)
);