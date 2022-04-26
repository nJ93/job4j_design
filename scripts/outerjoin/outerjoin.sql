CREATE TABLE departments (
	id SERIAL PRIMARY KEY,
	name text
);

CREATE TABLE employees (
	id SERIAL PRIMARY KEY,
	name text,
	department_id int references departments(id)
);

INSERT INTO departments (name) VALUES ('A');
INSERT INTO departments (name) VALUES ('B');
INSERT INTO departments (name) VALUES ('C');
INSERT INTO departments (name) VALUES ('D');
INSERT INTO departments (name) VALUES ('E');
INSERT INTO departments (name) VALUES ('F');

INSERT INTO employees (name, department_id) VALUES ('Alex', 1);
INSERT INTO employees (name, department_id) VALUES ('Ivan', 1);
INSERT INTO employees (name, department_id) VALUES ('Petr', 2);
INSERT INTO employees (name, department_id) VALUES ('Kolya', 3);
INSERT INTO employees (name, department_id) VALUES ('Olya', 4);
INSERT INTO employees (name, department_id) VALUES ('Anya', 5);
INSERT INTO employees (name, department_id) VALUES ('Sveta', 5);

/*2. Выполнить запросы с left, rigth, full, cross соединениями*/
SELECT
	  emp.name AS emp_name
	, dep.name AS dep_name
FROM employees emp
	LEFT JOIN departments dep
		ON emp.department_id = dep.id
		
SELECT
	  emp.name AS emp_name
	, dep.name AS dep_name
FROM employees emp
	RIGHT JOIN departments dep
		ON emp.department_id = dep.id
		
SELECT
	  emp.name AS emp_name
	, dep.name AS dep_name
FROM employees emp
	FULL JOIN departments dep
		ON emp.department_id = dep.id
		
SELECT
	  emp.name AS emp_name
	, dep.name AS dep_name
FROM employees emp
	CROSS JOIN departments dep
	
/*3. Используя left join найти департаменты, у которых нет работников*/
SELECT DISTINCT
	  dep.name AS dep_name
FROM departments dep
	LEFT JOIN employees emp
		ON emp.department_id = dep.id
WHERE emp.name IS NULL

/*4. Используя left и right join написать запросы, которые давали бы одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный). */
SELECT
	  emp.name AS emp_name
	, dep.name AS dep_name
FROM employees emp
	LEFT JOIN departments dep
		ON emp.department_id = dep.id
		
SELECT
	  emp.name AS emp_name
	, dep.name AS dep_name
FROM employees emp
	RIGHT JOIN departments dep
		ON emp.department_id = dep.id
WHERE emp.name IS NOT NULL

/*5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары*/
CREATE TABLE teens (
	id SERIAL PRIMARY KEY,
	name text,
	gender text
);

INSERT INTO teens (name, gender) VALUES ('Alex', 'M');
INSERT INTO teens (name, gender) VALUES ('Ivan', 'M');
INSERT INTO teens (name, gender) VALUES ('Petr', 'M');
INSERT INTO teens (name, gender) VALUES ('Kolya', 'M');
INSERT INTO teens (name, gender) VALUES ('Olya', 'F');
INSERT INTO teens (name, gender) VALUES ('Sveta', 'F');
INSERT INTO teens (name, gender) VALUES ('Anya', 'F');

SELECT 
	  t.name AS female
	, tt.name AS male
FROM teens t
CROSS JOIN teens tt
WHERE t.gender <> tt.gender
AND tt.gender <> 'F'












