CREATE TABLE driver_license (
	id serial primary key,
	serial_number text
);

CREATE TABLE person (
	id serial primary key,
	name text,
	age int,
	driver_license_id int references driver_license(id) unique
);