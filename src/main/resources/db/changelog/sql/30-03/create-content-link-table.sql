--liquibase formatted sql

CREATE TABLE Content_Link (
    id BIGINT NOT NULL PRIMARY KEY,
    object_key VARCHAR(255) NOT NULL,
    bucket_name VARCHAR(255) NOT NULL,
    url TEXT,
    mark_id BIGINT NOT NULL,
    CONSTRAINT fk_content_link_mark FOREIGN KEY (mark_id) REFERENCES Mark(id) ON DELETE CASCADE
);