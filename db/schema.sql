create table posts (
  id serial primary key,
  name varchar(2000),
  description text,
  created timestamp without time zone not null default now()
);

CREATE TABLE users (
  id serial primary key,
  username VARCHAR(50) NOT NULL unique,
  password VARCHAR(100) NOT NULL,
  enabled boolean default true,
  authority_id int not null references authorities(id)
);

CREATE TABLE authorities (
  id serial primary key,
  authority VARCHAR(50) NOT NULL unique
);
select * from posts;

select * from authorities;

drop table users;
drop table authorities;

insert into users (username,  password, authority_id)
values ('root', '444',
(select id from authorities where authority = 'ROLE_ADMIN'));


insert into posts (name) values ('О чем этот форум?');
insert into posts (name) values ('Правила форума.');

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$vhvD/KJgkCYbNey6cyPv5uTesA7nZHG6QeCLz9uNyhVyJQ8zjNAcm',
(select id from authorities where authority = 'ROLE_ADMIN'));