create table if not exists account(
    id serial primary key,
    uid varchar not null unique,
    iban varchar not null,
    balance numeric default 0 check (balance >= 0),
    person_id int references person(id)
)