CREATE TABLE IF NOT EXISTS students(
 id serial primary key,
 first_name VARCHAR(40) not null,
 last_name VARCHAR(40) not null,
 email VARCHAR(40) not null
);