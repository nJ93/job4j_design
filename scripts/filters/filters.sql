CREATE TABLE type (
	id SERIAL PRIMARY KEY,
	name text
);

CREATE TABLE product (
	id SERIAL PRIMARY KEY,
	name text,
	type_id int references type(id),
	expired_date date,
	price float
);

INSERT INTO type (name) VALUES ('СЫР');
INSERT INTO type (name) VALUES ('МОЛОКО');
INSERT INTO type (name) VALUES ('ХЛЕБ');
INSERT INTO type (name) VALUES ('КОНФЕТА');
INSERT INTO type (name) VALUES ('АЛКОГОЛЬ');

INSERT INTO product (name, type_id, expired_date, price) VALUES ('Сыр плавленный', 1, '2022-04-21', 120.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Сыр моцарелла', 1, '2022-04-22', 220.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Сыр российский', 1, '2022-04-27', 180.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Молоко 2,5%', 2, '2022-04-29', 50.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Молоко 3,2%', 2, '2022-04-22', 70.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('мороженое', 2, '2022-04-22', 70.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Молоко мороженое', 2, '2022-04-22', 70.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Батон', 3, '2022-05-01', 28.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Пряник', 3, '2022-05-11', 67.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Печенье', 3, '2022-05-21', 55.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка', 4, '2022-04-12', 100.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка вкусная', 4, '2022-04-13', 100.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка не вкусная', 4, '2022-04-14', 101.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка хорошая', 4, '2022-04-15', 102.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка плохая', 4, '2022-04-16', 103.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка великолепная', 4, '2022-04-17', 104.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка ужасная', 4, '2022-04-18', 105.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка лучшая', 4, '2022-04-19', 106.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка худшая', 4, '2022-04-22', 107.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка му', 4, '2022-04-28', 108.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка хрю', 4, '2022-04-29', 109.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка тоси', 4, '2022-04-29', 1000.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Шоколадка боси', 4, '2022-04-29', 10000.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Водка', 5, '2023-06-01', 320.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Пиво', 5, '2022-08-01', 70.00);
INSERT INTO product (name, type_id, expired_date, price) VALUES ('Виски', 5, '2022-03-01', 990.00);

/*1. Написать запрос получение всех продуктов с типом "СЫР"*/
SELECT 
	  prod.name AS product_name
	, prod.expired_date AS product_exp_date
	, prod.price AS product_price
	, type.name AS type_name
FROM product AS prod
	INNER JOIN type AS type
		ON type.id = prod.type_id
WHERE type.name = 'СЫР';

/*2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"*/
SELECT 
	  prod.name AS product_name
	, prod.expired_date AS product_exp_date
	, prod.price AS product_price
FROM product AS prod
WHERE prod.name LIKE '%мороженое%';

/*3. Написать запрос, который выводит все продукты, срок годности которых уже истек*/
SELECT 
	  prod.name AS product_name
	, prod.expired_date AS product_exp_date
	, prod.price AS product_price
FROM product AS prod
WHERE prod.expired_date < current_date;

/*4. Написать запрос, который выводит самый дорогой продукт.*/
SELECT 
	  prod.name AS product_name
	, prod.expired_date AS product_exp_date
	, prod.price AS product_price
FROM product AS prod
WHERE prod.price = (SELECT 
						MAX(inprod.price)
					FROM product AS inprod);
					
/*5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество*/
SELECT 
	  type.name AS type_name
	, COUNT(*) AS count_prod
FROM product AS prod
	INNER JOIN type AS type
		ON type.id = prod.type_id
GROUP BY type.name;

/*6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"*/
SELECT 
	  prod.name AS product_name
	, prod.expired_date AS product_exp_date
	, prod.price AS product_price
	, type.name AS type_name
FROM product AS prod
	INNER JOIN type AS type
		ON type.id = prod.type_id
WHERE type.name = 'СЫР' OR type.name = 'МОЛОКО';

/*7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. */
SELECT 
	type.name AS type_name
FROM product AS prod
	INNER JOIN type AS type
		ON type.id = prod.type_id
GROUP BY type.name
HAVING COUNT(prod.name) < 10;

/*8. Вывести все продукты и их тип.*/
SELECT 
	  prod.name AS product_name
	, prod.expired_date AS product_exp_date
	, prod.price AS product_price
	, type.name AS type_name
FROM product AS prod
	INNER JOIN type AS type
		ON type.id = prod.type_id;