CREATE TABLE model (
	id SERIAL PRIMARY KEY,
	name text
);

CREATE TABLE product (
	id SERIAL PRIMARY KEY,
	name text,
	price decimal (10, 2),
	model_id int references model(id)
);

INSERT INTO model (name) VALUES ('Nvidia');
INSERT INTO model (name) VALUES ('MSI');
INSERT INTO model (name) VALUES ('ASUS');

INSERT INTO product (name, price, model_id) VALUES ('Videocard1', 600.00, 1);
INSERT INTO product (name, price, model_id) VALUES ('Videocard2', 65600.00, 1);
INSERT INTO product (name, price, model_id) VALUES ('Videocard3', 99900.00, 2);
INSERT INTO product (name, price, model_id) VALUES ('Videocard4', 61200.00, 2);
INSERT INTO product (name, price, model_id) VALUES ('Videocard5', 100.00, 3);

SELECT 
	  prod.name AS product_name
	, prod.price AS product_price
	, mod.name AS model_name
FROM product AS prod
	INNER JOIN model mod
		ON mod.id = prod.model_id
WHERE prod.price < 70000.00

SELECT 
	  prod.name AS product_name
	, prod.price AS product_price
	, mod.name AS model_name
FROM product AS prod
	INNER JOIN model mod
		ON mod.id = prod.model_id
WHERE mod.name <> 'Nvidia'

SELECT 
	  prod.name AS product_name
	, prod.price AS product_price
	, mod.name AS model_name
FROM product AS prod
	INNER JOIN model mod
		ON mod.id = prod.model_id
WHERE prod.price BETWEEN 50.00 AND 1000.00
