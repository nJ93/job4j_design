CREATE TABLE city (
	id serial primary key,
	city_name text
);

CREATE TABLE person (
	id serial primary key,
	name text,
	age int,
	city_id int references city(id)
);