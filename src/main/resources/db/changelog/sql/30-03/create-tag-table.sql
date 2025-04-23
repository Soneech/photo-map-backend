--liquibase formatted sql

BEGIN;

CREATE TABLE Tag (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Mark_tag (
    mark_id BIGINT NOT NULL,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (mark_id, tag_id),
    CONSTRAINT fk_mark_tag_mark FOREIGN KEY (mark_id) REFERENCES Mark(id) ON DELETE CASCADE,
    CONSTRAINT fk_mark_tag_tag FOREIGN KEY (tag_id) REFERENCES Tag(id) ON DELETE CASCADE
);

COMMIT;