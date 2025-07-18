INSERT IGNORE INTO role (role_name , id) VALUES ('USER' , 1);
INSERT IGNORE INTO role (role_name , id) VALUES ('ADMIN', 2);

-- INSERT IGNORE INTO user (account, password, name, email, create_at, update_at, bank_name, bank_adress, role_id) VALUES
-- ('admin', '$2a$10$4V4.KgBvXHcNd4j8a4Ml0eElFd5MNVqTePHietnkaWtWutGZ4EY86', 'Admin User', 'admin@example.com', NOW(), NOW(), 'Admin Bank', 'Admin Address', 2);