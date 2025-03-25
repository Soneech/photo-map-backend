--liquibase formatted sql

INSERT INTO Roles(name)
    VALUES
    ('ROLE_ADMIN'),
    ('ROLE_USER');
