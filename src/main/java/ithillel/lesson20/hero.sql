create table if not exists hero_information(
	id serial primary key,
	name text not null,
	gender text,
	eye_color text,
	race text,
	hair_color text,
	height text,
	publisher text,
	skin_color text,
	alignment text,
	weight int
);
create table if not exists publisher(
	id serial primary key,
	name text unique,
	created_at timestamp default now()
);
create table if not exists hero(
	id serial primary key,
	name text not null,
	gender text,
	eye_color text,
	race text,
	hair_color text,
	height numeric(5,2),
	publisher_id int references publisher(id),
	skin_color text,
	alignment text,
	weight int
);
--import from csv


insert into publisher (name)(
	select distinct publisher
	from hero_information
);

insert into hero (name, gender, eye_color, race, hair_color, height, publisher_id, skin_color, alignment, weight) (
	select hero_information.name, gender, eye_color, race, hair_color, replace(height, ',', '.')::numeric, publisher.id, skin_color, alignment, weight
	from hero_information
		join publisher on hero_information.publisher = publisher.name
);


--avg height
select avg(height)
from hero
where height > 0;

--name of highest
select id, name
from hero
where height in (
	select max(height) from hero
);

--name of heaviest
select id, name
from hero
where weight in (
	select max(weight) from hero
);

--count of each gender
select gender, count(id)
from hero
group by gender;

--count of each alignment
select alignment, count(id)
from hero
group by alignment;

--5 most popular publishers
select publisher.id, publisher.name
from hero
	join publisher on hero.publisher_id = publisher.id
group by publisher.id
order by count(hero.id) desc
limit 5;

--3 most popular hair colors
select hair_color
from hero
group by hair_color
order by count(id) desc
limit 3;

--the most popular eye color
select eye_color
from hero
group by eye_color
order by count(id) desc
limit 1;