BEGIN;

CREATE TABLE Likes (
    user_id BIGINT NOT NULL,
    mark_id BIGINT NOT NULL,
    PRIMARY KEY(user_id, mark_id),
    CONSTRAINT fk_likes_mark FOREIGN KEY (mark_id) REFERENCES Mark(id) ON DELETE CASCADE,
    CONSTRAINT fk_likes_user FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE
);

ALTER TABLE Mark DROP column likes;

COMMIT;