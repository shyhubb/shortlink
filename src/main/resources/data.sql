INSERT IGNORE INTO role (role_name , id) VALUES ('USER' , 1);
INSERT IGNORE INTO role (role_name , id) VALUES ('ADMIN', 2);

-- data.sql

-- Chèn dữ liệu vào bảng cpm_rate
-- Lưu ý: ID sẽ tự động tăng (AUTO_INCREMENT) nếu dùng GenerationType.IDENTITY
-- data.sql (dành cho PostgreSQL / H2)

INSERT IGNORE INTO cpm_rate (country, cpm) VALUES
('United States', 8.50),
('Canada', 7.80),
('United Kingdom', 6.90),
('Australia', 7.20),
('Germany', 6.00),
('France', 5.50),
('Japan', 5.00),
('South Korea', 4.80),
('Vietnam', 2.50),
('India', 1.80),
('Brazil', 3.20),
('Mexico', 2.90),
('Singapore', 4.50),
('Indonesia', 2.00),
('Thailand', 2.20),
('Philippines', 1.90),
('Malaysia', 2.10)
