create table if not exists person
(
    id serial primary key,
    name varchar not null,
    uid varchar not null unique,
    created_at timestamp default now(),
    updated_at timestamp default now()
)