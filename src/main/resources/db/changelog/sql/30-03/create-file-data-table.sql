--liquibase formatted sql

CREATE TABLE File_Data (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    object_key VARCHAR(255) NOT NULL,
    bucket_name VARCHAR(255) NOT NULL,
    mark_id BIGINT NOT NULL,
    CONSTRAINT fk_file_data_mark FOREIGN KEY (mark_id) REFERENCES Mark(id) ON DELETE CASCADE
);