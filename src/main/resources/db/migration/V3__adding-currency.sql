alter table account
add column currency char(3) not null default 'UAH';


alter table account
add constraint unique_iban unique (iban);