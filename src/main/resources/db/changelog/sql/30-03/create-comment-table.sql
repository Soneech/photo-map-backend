--liquibase formatted sql

CREATE TABLE Comment (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    user_id BIGINT NOT NULL,
    mark_id BIGINT NOT NULL,
    text TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    CONSTRAINT fk_comment_mark FOREIGN KEY (mark_id) REFERENCES Mark(id) ON DELETE CASCADE
);