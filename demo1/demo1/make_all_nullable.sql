-- ============================================================
-- Script: Cho phép NULL tất cả các cột trong WebBanHang
-- Chạy script này trong MySQL Workbench hoặc CLI:
--   mysql -u root -p WebBanHang < make_all_nullable.sql
-- ============================================================

USE WebBanHang;

-- ----------------------------------------
-- Bảng USERS
-- ----------------------------------------
ALTER TABLE users
    MODIFY COLUMN full_name    VARCHAR(255) NULL,
    MODIFY COLUMN phone        VARCHAR(255) NULL,
    MODIFY COLUMN gender       VARCHAR(50)  NULL,
    MODIFY COLUMN email        VARCHAR(255) NULL,
    MODIFY COLUMN password     VARCHAR(255) NULL,
    MODIFY COLUMN username     VARCHAR(255) NULL,
    MODIFY COLUMN role         VARCHAR(50)  NULL,
    MODIFY COLUMN address      VARCHAR(500) NULL,
    MODIFY COLUMN avatar       VARCHAR(500) NULL,
    MODIFY COLUMN created_at   DATETIME     NULL,
    MODIFY COLUMN updated_at   DATETIME     NULL;

-- ----------------------------------------
-- Bảng ORDERS
-- ----------------------------------------
ALTER TABLE orders
    MODIFY COLUMN customer_name    VARCHAR(255)   NULL,
    MODIFY COLUMN phone            VARCHAR(50)    NULL,
    MODIFY COLUMN gender           VARCHAR(50)    NULL,
    MODIFY COLUMN delivery_method  VARCHAR(50)    NULL,
    MODIFY COLUMN city             VARCHAR(100)   NULL,
    MODIFY COLUMN district         VARCHAR(100)   NULL,
    MODIFY COLUMN ward             VARCHAR(100)   NULL,
    MODIFY COLUMN address_detail   VARCHAR(500)   NULL,
    MODIFY COLUMN store_location   VARCHAR(500)   NULL,
    MODIFY COLUMN note             TEXT           NULL,
    MODIFY COLUMN total_price      DOUBLE         NULL,
    MODIFY COLUMN shipping_fee     DOUBLE         NULL,
    MODIFY COLUMN points           INT            NULL,
    MODIFY COLUMN user_id          BIGINT         NULL,
    MODIFY COLUMN status           VARCHAR(50)    NULL,
    MODIFY COLUMN created_at       DATETIME       NULL;

-- ----------------------------------------
-- Bảng ORDER_DETAILS
-- ----------------------------------------
ALTER TABLE order_details
    MODIFY COLUMN quantity     INT     NULL,
    MODIFY COLUMN price        DOUBLE  NULL,
    MODIFY COLUMN product_id   BIGINT  NULL,
    MODIFY COLUMN order_id     BIGINT  NULL;

-- ----------------------------------------
-- Bảng PRODUCTS
-- ----------------------------------------
ALTER TABLE products
    MODIFY COLUMN name             VARCHAR(500)   NULL,
    MODIFY COLUMN price            DOUBLE         NULL,
    MODIFY COLUMN original_price   DOUBLE         NULL,
    MODIFY COLUMN description      TEXT           NULL,
    MODIFY COLUMN image_url        VARCHAR(500)   NULL,
    MODIFY COLUMN discount_percent VARCHAR(50)    NULL,
    MODIFY COLUMN screen_size      VARCHAR(50)    NULL,
    MODIFY COLUMN ram              VARCHAR(50)    NULL,
    MODIFY COLUMN storage          VARCHAR(50)    NULL,
    MODIFY COLUMN badge_text       VARCHAR(100)   NULL,
    MODIFY COLUMN is_hot           TINYINT(1)     NULL,
    MODIFY COLUMN category_id      BIGINT         NULL,
    MODIFY COLUMN created_at       DATETIME       NULL;

-- ----------------------------------------
-- Bảng CATEGORIES
-- ----------------------------------------
ALTER TABLE categories
    MODIFY COLUMN name         VARCHAR(255) NULL,
    MODIFY COLUMN group_name   VARCHAR(255) NULL,
    MODIFY COLUMN image_url    VARCHAR(500) NULL;

SELECT 'Done! All columns are now nullable.' AS result;
