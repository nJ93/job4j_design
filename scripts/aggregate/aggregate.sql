create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO devices (name, price) VALUES ('Keyboard', 2000.00);
INSERT INTO devices (name, price) VALUES ('Mouse', 1000.00);
INSERT INTO devices (name, price) VALUES ('Monitor', 6000.00);
INSERT INTO devices (name, price) VALUES ('Printer', 8000.00);
INSERT INTO devices (name, price) VALUES ('Headphones', 3500.00);

INSERT INTO people (name) VALUES ('Ivan');
INSERT INTO people (name) VALUES ('Alex');
INSERT INTO people (name) VALUES ('Petr');

INSERT INTO devices_people (device_id, people_id) VALUES (1, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (2, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (3, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (4, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (5, 1);
INSERT INTO devices_people (device_id, people_id) VALUES (1, 2);
INSERT INTO devices_people (device_id, people_id) VALUES (2, 2);
INSERT INTO devices_people (device_id, people_id) VALUES (3, 2);
INSERT INTO devices_people (device_id, people_id) VALUES (3, 3);
INSERT INTO devices_people (device_id, people_id) VALUES (4, 3);
INSERT INTO devices_people (device_id, people_id) VALUES (5, 3);

/*1*/
SELECT 
	avg(dev.price) AS avg_price
FROM devices AS dev;

/*2*/
SELECT 
	  ppl.name AS people_name
	, avg(dev.price) AS avg_price
FROM people AS ppl
	INNER JOIN devices_people AS dev_ppl
		ON dev_ppl.people_id = ppl.id
	INNER JOIN devices AS dev
		ON dev.id = dev_ppl.device_id
GROUP BY ppl.name;

/*3*/
SELECT 
	  ppl.name AS people_name
	, avg(dev.price) AS avg_price
FROM people AS ppl
	INNER JOIN devices_people AS dev_ppl
		ON dev_ppl.people_id = ppl.id
	INNER JOIN devices AS dev
		ON dev.id = dev_ppl.device_id
GROUP BY ppl.name
HAVING avg(dev.price) > 5000;

DROP TABLE devices_people;
DROP TABLE people;
DROP TABLE devices;