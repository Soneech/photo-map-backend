--liquibase formatted sql

BEGIN;

CREATE TABLE Category (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Mark_category (
    mark_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    PRIMARY KEY (mark_id, category_id),
    CONSTRAINT fk_mark_category_mark FOREIGN KEY (mark_id) REFERENCES Mark(id) ON DELETE CASCADE,
    CONSTRAINT fk_mark_category_category FOREIGN KEY (category_id) REFERENCES Category(id) ON DELETE CASCADE
);

COMMIT;