CREATE TABLE bank (
	id serial primary key,
	bank_name text,
	bank_account text
);

CREATE TABLE person (
	id serial primary key,
	name text,
	age int
);

CREATE TABLE person_bank (
	id serial primary key,
	person_id int references person(id),
	bank_id int references bank(id)
);
