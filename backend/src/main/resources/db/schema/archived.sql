-- db/schema/archived.sql
CREATE TABLE archived_users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    status VARCHAR(50) NOT NULL,
    archived_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    original_created_at TIMESTAMP
);