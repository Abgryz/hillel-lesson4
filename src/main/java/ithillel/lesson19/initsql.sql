create table homework(
    id serial primary key,
    name text not null,
    description text
);
create table lesson(
	id serial primary key,
	name text not null,
	updated_at timestamp default now(),
	homework_id int unique references homework(id)
);
create table schedule(
	id serial primary key,
	name text not null,
	updated_at timestamp default now()
);
create table schedule_lessons(
	shedule_id int references schedule(id),
	lesson_id int references lesson(id),
	primary key (shedule_id, lesson_id)
)