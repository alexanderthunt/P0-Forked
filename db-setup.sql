-- Use this script to setup your Planetarium database

create table users(
	id serial primary key,
	username varchar(20) unique,
	password varchar(20)
);

create table planets(
	id serial primary key,
	name varchar(20),
	ownerId int references users(id) on delete cascade -- or on delete set null
);

create table moons(
	id serial primary key,
	name varchar(20),
	myPlanetId int references planets(id) on delete cascade --or on delete set null
);