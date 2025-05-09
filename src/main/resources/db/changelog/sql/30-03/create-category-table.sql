--liquibase formatted sql

CREATE TABLE Category (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL
);

INSERT INTO Category(name) VALUES
    ('Архитектура'),
    ('Достопримечательности'),
    ('Кафе и рестораны'),
    ('Локации для съёмок'),
    ('Макросъёмка'),
    ('Ночная съёмка'),
    ('Пейзажи'),
    ('Портреты'),
    ('Природа и животные'),
    ('Рабочие проекты'),
    ('Транспортные точки'),
    ('Уличная фотография'),
    ('HDR и панорамы'),
    ('Другое');
