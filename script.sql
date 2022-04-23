CREATE TABLE organisation_list (
	id SERIAL PRIMARY KEY,
	organisation_name TEXT,
	employee_count INT,
	open_date DATE
);

INSERT INTO organisation_list (organisation_name, employee_count, open_date) VALUES ('Yandex', 2000, current_date);

UPDATE ORGANISATION_LIST
SET ORGANISATION_NAME = 'Google'
WHERE ID = 1;

DELETE FROM ORGANISATION_LIST;