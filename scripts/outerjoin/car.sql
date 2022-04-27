CREATE TABLE body (
	id SERIAL PRIMARY KEY,
	name text
);

CREATE TABLE engine (
	id SERIAL PRIMARY KEY,
	name text
);

CREATE TABLE transmission (
	id SERIAL PRIMARY KEY,
	name text
);

CREATE TABLE car (
	id SERIAL PRIMARY KEY,
	name text,
	body_id int references body (id),
	engine_id int references engine (id),
	transmission_id int references transmission (id)
);

INSERT INTO body (name) VALUES ('body1');
INSERT INTO body (name) VALUES ('body2');
INSERT INTO body (name) VALUES ('body3');
INSERT INTO body (name) VALUES ('body4');
INSERT INTO body (name) VALUES ('body5');

INSERT INTO engine (name) VALUES ('engine1');
INSERT INTO engine (name) VALUES ('engine2');
INSERT INTO engine (name) VALUES ('engine3');
INSERT INTO engine (name) VALUES ('engine4');
INSERT INTO engine (name) VALUES ('engine5');

INSERT INTO transmission (name) VALUES ('transmission1');
INSERT INTO transmission (name) VALUES ('transmission2');
INSERT INTO transmission (name) VALUES ('transmission3');
INSERT INTO transmission (name) VALUES ('transmission4');
INSERT INTO transmission (name) VALUES ('transmission5');

INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('car1', 1, 1, 1);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('car2', 2, 2, NULL);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('car3', 3, NULL, 3);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('car4', 1, 2, 5);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('car5', 5, 3, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('car6', 5, 5, 2);
INSERT INTO car (name, body_id, engine_id, transmission_id) VALUES ('car7', NULL, NULL, NULL);

/*1) Вывести список всех машин и все привязанные к ним детали. Нужно учесть, что каких-то деталей машина может и не содержать.*/
SELECT 
	  c.name AS car_name
	, b.name AS body_name
	, e.name AS engine_name
	, t.name AS transmission_name
FROM car c
	LEFT JOIN body b
		ON b.id = c.body_id
	LEFT JOIN engine e
		ON e.id = c.engine_id
	LEFT JOIN transmission t
		ON t.id = c.transmission_id;
		
/*2) Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, кузова, двигатели, коробки передач.*/
SELECT 
	 b.name AS body_name
FROM car c
	RIGHT JOIN body b
		ON b.id = c.body_id
WHERE c.id is null;

SELECT 
	 e.name AS engine_name
FROM car c
	RIGHT JOIN engine e
		ON e.id = c.engine_id
WHERE c.id is null;

SELECT 
	 t.name AS transmission_name
FROM car c
	RIGHT JOIN transmission t
		ON t.id = c.transmission_id
WHERE c.id is null;