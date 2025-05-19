-- db/schema/archived.sql
CREATE TABLE archived_users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    status VARCHAR(50) NOT NULL,
    archived_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    original_created_at TIMESTAMP
);

 ALTER TABLE archived_users
 ADD COLUMN created_at TIMESTAMP;

-- UPDATE archived_users
-- SET created_at = NOW()
-- WHERE created_at IS NULL;
