CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name) VALUES (1, 'company1');
INSERT INTO company (id, name) VALUES (2, 'company2');
INSERT INTO company (id, name) VALUES (3, 'company3');
INSERT INTO company (id, name) VALUES (4, 'company4');
INSERT INTO company (id, name) VALUES (5, 'company5');
INSERT INTO company (id, name) VALUES (6, 'company6');
INSERT INTO company (id, name) VALUES (7, 'company7');
INSERT INTO company (id, name) VALUES (8, 'company8');
INSERT INTO company (id, name) VALUES (9, 'company9');

INSERT INTO person (id, name, company_id) VALUES (1, 'person1', 1);
INSERT INTO person (id, name, company_id) VALUES (2, 'person2', 1);
INSERT INTO person (id, name, company_id) VALUES (3, 'person3', 2);
INSERT INTO person (id, name, company_id) VALUES (4, 'person4', 3);
INSERT INTO person (id, name, company_id) VALUES (5, 'person5', 4);
INSERT INTO person (id, name, company_id) VALUES (6, 'person6', 5);
INSERT INTO person (id, name, company_id) VALUES (7, 'person7', 5);
INSERT INTO person (id, name, company_id) VALUES (8, 'person8', 5);
INSERT INTO person (id, name, company_id) VALUES (9, 'person9', 6);
INSERT INTO person (id, name, company_id) VALUES (10, 'person10', 7);
INSERT INTO person (id, name, company_id) VALUES (11, 'person11', 8);
INSERT INTO person (id, name, company_id) VALUES (12, 'person12', 9);
INSERT INTO person (id, name, company_id) VALUES (13, 'person13', 9);
INSERT INTO person (id, name, company_id) VALUES (14, 'person14', 9);

SELECT 
	  p.name AS person_name
	, c.name AS company_name
FROM person p
INNER JOIN company c
	ON p.company_id = c.id
WHERE c.id <> 5;

SELECT 
	  c.name,
	  COUNT(p.company_id) AS cmp_cnt
FROM person p
INNER JOIN company c
	ON p.company_id = c.id
GROUP BY c.name
HAVING COUNT(p.company_id) = (
		SELECT MAX(inquery.cnt)
		FROM (
			SELECT COUNT(*) AS cnt
			FROM person p
			GROUP BY p.company_id
		) AS inquery
	);
