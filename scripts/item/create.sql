CREATE TABLE role (
	id SERIAL PRIMARY KEY,
	role_name text
);

CREATE TABLE users (
	id SERIAL PRIMARY KEY,
	username text,
	role_id int references role(id)
);

CREATE TABLE rules (
	id SERIAL PRIMARY KEY,
	rule_description text
);

CREATE TABLE role_rules (
	id SERIAL PRIMARY KEY,
	role_id int references role(id),
	rules_id int references rules(id)
);

CREATE TABLE state (
	id SERIAL PRIMARY KEY,
	state_name text
);

CREATE TABLE category (
	id SERIAL PRIMARY KEY,
	category text
);

CREATE TABLE item (
	id SERIAL PRIMARY KEY,
	item_description text,
	user_id int references users(id),
	state_id int references state(id),
	category_id int references category(id)
);

CREATE TABLE comments (
	id SERIAL PRIMARY KEY,
	item_id int references item(id),
	comment text
);

CREATE TABLE attachs (
	id SERIAL PRIMARY KEY,
	item_id int references item(id),
	attach bytea
);