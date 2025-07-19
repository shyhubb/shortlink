INSERT IGNORE INTO role (role_name , id) VALUES ('USER' , 1);
INSERT IGNORE INTO role (role_name , id) VALUES ('ADMIN', 2);


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
('Malaysia', 2.10),
('Other', 2.00);


INSERT IGNORE INTO wallet (id, balance) VALUES (1, 0.0);


INSERT IGNORE INTO user (id, account, password, name, email, create_at, update_at, bank_name, bank_adress, role_id, wallet_id) VALUES
(1, 'admin', '$2a$10$4V4.KgBvXHcNd4j8a4Ml0eElFd5MNVqTePHietnkaWtWutGZ4EY86', 'Admin', 'admin@example.com', NOW(), NOW(), 'Vietcombank', 'Hanoi', 2, 1);