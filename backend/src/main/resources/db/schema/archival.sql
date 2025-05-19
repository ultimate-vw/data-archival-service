-- db/schema/archival.sql
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE archival_config (
    id BIGSERIAL PRIMARY KEY,
    table_name VARCHAR(100) NOT NULL,
    criteria_column VARCHAR(100) NOT NULL,
    entity_class VARCHAR(255) NOT NULL,
    archive_after_months INT NOT NULL,
    delete_after_months INT NOT NULL,
    page_size INT NOT NULL
);


CREATE TABLE students (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    grade VARCHAR(50),
    created_at TIMESTAMP
);

CREATE TABLE archived_students (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100),
    grade VARCHAR(50),
    created_at TIMESTAMP,
    archived_at TIMESTAMP
);

CREATE TABLE teachers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    subject VARCHAR(100),
    email VARCHAR(100),
    created_at TIMESTAMP
);

CREATE TABLE archived_teachers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    subject VARCHAR(100),
    email VARCHAR(100),
    created_at TIMESTAMP,
    archived_at TIMESTAMP
);

CREATE TABLE app_user (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(50) NOT NULL -- e.g., STUDENT, TEACHER, ADMIN
);

