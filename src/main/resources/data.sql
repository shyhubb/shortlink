INSERT IGNORE INTO role (role_name) VALUES ('USER');
INSERT IGNORE INTO role (role_name) VALUES ('ADMIN');

SET @admin_role_id = (SELECT id FROM role WHERE role_name = 'ADMIN');

INSERT INTO user (account, password, name, email, createAt, updateAt, bankName, bankAdress, role_id) VALUES
('admin', '$2a$10$4V4.KgBvXHcNd4j8a4Ml0eElFd5MNVqTePHietnkaWtWutGZ4EY86', 'Admin User', 'admin@example.com', NOW(), NOW(), 'Admin Bank', 'Admin Address', @admin_role_id);

SET @admin_user_id = (SELECT id FROM user WHERE account = 'admin');

INSERT INTO wallet (balance, user_id) VALUES (0.0, @admin_user_id);