create table if not exists transaction(
    id serial primary key,
    uid char(32) unique not null,
    from_account int not null references account(id),
    to_account int not null references account(id),
    amount numeric not null check ( amount > 0 ),
    currency char(3) not null,
    created_at timestamp default now()
)