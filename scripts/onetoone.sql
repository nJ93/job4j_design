CREATE TABLE registration_address (
	id serial primary key,
	address text
);

CREATE TABLE person (
	id serial primary key,
	name text,
	age int,
	registration_address_id int references registration_address(id) unique
);