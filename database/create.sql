CREATE DATABASE `ecommerce`;
CREATE USER ecommerce@'%' IDENTIFIED BY '1q2w3e4r!!';
GRANT ALL PRIVILEGES ON ecommerce.* TO ecommerce@'%';
FLUSH PRIVILEGES;

CREATE USER 'exporter'@'%' IDENTIFIED BY '1q2w3e4r!!' WITH MAX_USER_CONNECTIONS 3;
GRANT PROCESS, REPLICATION CLIENT, SELECT ON *.* TO 'exporter'@'%';
FLUSH PRIVILEGES;

DROP TABLE IF EXISTS `ecommerce`.`user`;
CREATE TABLE `ecommerce`.`user`
(
    `id`        BIGINT      NOT NULL    AUTO_INCREMENT  COMMENT '사용자 고유 식별자',
    `user_id` VARCHAR(50) NOT NULL    COMMENT '사용자 아이디',
    `password` VARCHAR(200) NOT NULL    COMMENT '사용자 비밀번호',
    `user_name` VARCHAR(20) NOT NULL    COMMENT '사용자 이름',
    `point`     BIGINT      NOT NULL    COMMENT '포인트 잔액',
    `created_at`    DATETIME    NOT NULL    COMMENT '사용자 생성 일시',
    `updated_at`    DATETIME    NOT NULL    COMMENT '사용자 갱신 일시',
    `version`   BIGINT      NOT NULL    COMMENT '버전',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `ecommerce`.`point_history`;
CREATE TABLE `ecommerce`.`point_history`
(
    `id`    BIGINT  NOT NULL    AUTO_INCREMENT  COMMENT '포인트 사용 내역 고유 식별자',
    `user_seq`   BIGINT  NOT NULL    COMMENT '사용자 고유 식별자',
    `amount`    BIGINT  NOT NULL    COMMENT '사용/충전 포인트',
    `transaction_type`  VARCHAR(20) NOT NULL    COMMENT '포인트 변동 유형',
    `created_at`    DATETIME    NOT NULL    COMMENT '포인트 사용 내역 생성 일시',
    `updated_at`    DATETIME    NOT NULL    COMMENT '포인트 사용 내역 갱신 일시',
    PRIMARY KEY (`id`),
    INDEX `idx_user_seq` (`user_seq`)
);

DROP TABLE IF EXISTS `ecommerce`.`product`;
CREATE TABLE `ecommerce`.`product`
(
    `id`    BIGINT  NOT NULL    AUTO_INCREMENT  COMMENT '상품 고유 식별자',
    `product_name`   VARCHAR(50)  NOT NULL    COMMENT '상품명',
    `unit_price`    BIGINT  NOT NULL    COMMENT '단가',
    `stock`  BIGINT NOT NULL    COMMENT '재고',
    `created_at`    DATETIME    NOT NULL    COMMENT '상품 생성 일시',
    `updated_at`    DATETIME    NOT NULL    COMMENT '상품 갱신 일시',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `ecommerce`.`cart`;
CREATE TABLE `ecommerce`.`cart`
(
    `id`    BIGINT  NOT NULL    AUTO_INCREMENT  COMMENT '장바구니 상품 고유 식별자',
    `user_seq`   BIGINT  NOT NULL    COMMENT '사용자 고유 식별자',
    `product_id`   BIGINT  NOT NULL    COMMENT '상품 고유 식별자',
    `quantity`  BIGINT NOT NULL    COMMENT '장바구니 상품 수량',
    `created_at`    DATETIME    NOT NULL    COMMENT '장바구니 상품 생성 일시',
    `updated_at`    DATETIME    NOT NULL    COMMENT '장바구니 상품 갱신 일시',
    PRIMARY KEY (`id`),
    INDEX `idx_user_seq` (`user_seq`),
    INDEX `idx_product_id` (`product_id`)
);

DROP TABLE IF EXISTS `ecommerce`.`orders`;
CREATE TABLE `ecommerce`.`orders`
(
    `id`    BIGINT  NOT NULL    AUTO_INCREMENT  COMMENT '주문 고유 식별자',
    `user_seq`   BIGINT  NOT NULL    COMMENT '사용자 고유 식별자',
    `order_status`  VARCHAR(20)    NOT NULL    COMMENT '주문 상태',
    `created_at`    DATETIME    NOT NULL    COMMENT '주문 생성 일시',
    `updated_at`    DATETIME    NOT NULL    COMMENT '주문 갱신 일시',
    PRIMARY KEY (`id`),
    INDEX `idx_user_seq` (`user_seq`)
);

DROP TABLE IF EXISTS `ecommerce`.`order_line`;
CREATE TABLE `ecommerce`.`order_line`
(
    `id`    BIGINT  NOT NULL    AUTO_INCREMENT  COMMENT '주문 상품 고유 식별자',
    `order_id`   BIGINT  NOT NULL   COMMENT '주문 고유 식별자',
    `product_id`   BIGINT  NOT NULL    COMMENT '상품 고유 식별자',
    `product_name`   VARCHAR(50)  NOT NULL    COMMENT '주문 상품명',
    `unit_price`    BIGINT  NOT NULL    COMMENT '주문 상품 단가',
    `quantity`  BIGINT NOT NULL    COMMENT '주문 상품 수량',
    `created_at`    DATETIME    NOT NULL    COMMENT '주문 상품 생성 일시',
    `updated_at`    DATETIME    NOT NULL    COMMENT '주문 상품 갱신 일시',
    PRIMARY KEY (`id`),
    INDEX `idx_order_id` (`order_id`),
    INDEX `idx_product_id` (`product_id`)
);

DROP TABLE IF EXISTS `ecommerce`.`payment`;
CREATE TABLE `ecommerce`.`payment`
(
    `id`    BIGINT  NOT NULL    AUTO_INCREMENT  COMMENT '결제 고유 식별자',
    `user_seq`   BIGINT  NOT NULL    COMMENT '사용자 고유 식별자',
    `order_id`   BIGINT  NOT NULL   COMMENT '주문 고유 식별자',
    `amount`  BIGINT NOT NULL    COMMENT '결제 금액',
    `payment_status`  VARCHAR(20)    NOT NULL    COMMENT '결제 상태',
    `created_at`    DATETIME    NOT NULL    COMMENT '결제 생성 일시',
    `updated_at`    DATETIME    NOT NULL    COMMENT '결제 갱신 일시',
    PRIMARY KEY (`id`),
    INDEX `idx_user_seq` (`user_seq`),
    INDEX `idx_order_id` (`order_id`)
);