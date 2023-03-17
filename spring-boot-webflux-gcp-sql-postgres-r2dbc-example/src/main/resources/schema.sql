CREATE TABLE IF NOT EXISTS students(
 id serial primary key,
 name VARCHAR(40) not null,
 email VARCHAR(40) not null
);