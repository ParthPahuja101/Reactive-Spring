CREATE TABLE IF NOT EXISTS books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(255),
    author VARCHAR(255),
    pages INT,
    genre VARCHAR(50),
    published_on DATE
);

CREATE TABLE IF NOT EXISTS audit_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    action VARCHAR(255),
    timestamp DATE
);
