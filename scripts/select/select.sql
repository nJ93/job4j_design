/*1*/
SELECT 
	  f.name
	, f.avg_age
	, f.discovery_date
FROM fauna f 
WHERE f.name LIKE '%fish%';

/*2*/
SELECT 
	  f.name
	, f.avg_age
	, f.discovery_date
FROM fauna f 
WHERE f.avg_age BETWEEN 10000 AND 21000;

/*3*/
SELECT 
	  f.name
	, f.avg_age
	, f.discovery_date
FROM fauna f 
WHERE f.discovery_date IS NULL;

/*4*/
SELECT 
	  f.name
	, f.avg_age
	, f.discovery_date
FROM fauna f 
WHERE date_part('year', f.discovery_date) < 1950;