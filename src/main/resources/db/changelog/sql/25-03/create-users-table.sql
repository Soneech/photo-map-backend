--liquibase formatted sql

CREATE TABLE Users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(320) NOT NULL UNIQUE,
    password VARCHAR(1000) NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES Roles(id)
);